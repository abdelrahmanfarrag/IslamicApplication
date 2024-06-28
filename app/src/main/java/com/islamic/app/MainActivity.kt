package com.islamic.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult.*
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.feature_quran.presentation.component.QuranScreen
import com.feature_quran.presentation.viewmodel.QuranContract
import com.feature_quran.presentation.viewmodel.QuranViewModel
import com.feature_surrah.presentation.components.Surrah
import com.feature_surrah.presentation.viewmodel.SurrahViewModel
import com.islamic.app.bottomnavigation.BottomNavigationItems
import com.islamic.app.navigation.Screens
import com.islamic.app.ui.theme.IslamicApplicationTheme
import com.islamic.app.viewmodel.MainEvents
import com.islamic.app.viewmodel.MainSingleUIEvent
import com.islamic.app.viewmodel.MainState
import com.islamic.app.viewmodel.MainViewModel
import com.islamic.presentation.HomeContract
import com.islamic.presentation.HomeViewModel
import com.islamic.presentation.content.HomeComposable
import com.islamic.services.alarmpraytime.IAlarmPrayTime
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var iAlarmPrayTime: IAlarmPrayTime

    @Composable
    private fun BottomNavigationSetup(
        navHostController: NavHostController,
        singleEvents: Flow<MainSingleUIEvent>,
        state: MainState = MainState(),
        bottomBarItems: ArrayList<BottomNavigationItems> = arrayListOf(),
        onEvent: (MainEvents) -> Unit = {}
    ) {

        LaunchedEffect(key1 = Unit) {
            singleEvents.collect { event ->
                when (event) {
                    is MainSingleUIEvent.OnRouteChange -> navHostController.navigate(
                        event.route
                    ) {
                        popUpTo(navHostController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        }
        AnimatedVisibility(visible = state.shouldShow) {
            BottomNavigation(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(PaddingValues(16.dp))
                    .clip(RoundedCornerShape(8.dp))
            ) {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val context = LocalContext.current
                bottomBarItems.forEach { item ->
                    val size = animateIntAsState(
                        targetValue = if (item.route == state.mainRoute) 32 else 24, label = "label"
                    ).value
                    BottomNavigationItem(selected = currentRoute == item.route.javaClass.simpleName,
                        onClick = {
                            onEvent.invoke(MainEvents.OnRouteUpdate(item.route))
                        },
                        icon = {
                            Icon(
                                painterResource(id = item.res),
                                modifier = Modifier.size(size.dp),
                                contentDescription = null,
                                tint = if (currentRoute == item.route.javaClass.canonicalName) Color.White else Color.Black
                            )
                        },
                        label = {
                            Text(
                                text = context.getString(item.label),
                                color = if (currentRoute == item.route.javaClass.canonicalName) Color.White else Color.Black
                            )
                        })
                }
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = hiltViewModel<MainViewModel>()
            val mainState = mainViewModel.state.collectAsStateWithLifecycle().value
            IslamicApplicationTheme {
                val scaffoldState = rememberScaffoldState()
                val navHostController = rememberNavController()

                val currentDestinationState by navHostController.currentBackStackEntryAsState()
                LaunchedEffect(key1 = currentDestinationState) {
                    mainViewModel.sendEvent(MainEvents.ShouldShowBottomNavigation(navHostController.currentDestination?.route))
                }
                Scaffold(scaffoldState = scaffoldState, bottomBar = {
                    BottomNavigationSetup(
                        navHostController = navHostController,
                        singleEvents = mainViewModel.singleEvent,
                        bottomBarItems = mainState.bottomNavItems,
                        state = mainState
                    ) { item ->
                        mainViewModel.sendEvent(item)
                    }
                }) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navHostController,
                        startDestination = Screens.BottomNavigation.HomeScreen
                    ) {
                        composable<Screens.BottomNavigation.HomeScreen> {
                            val homeViewModel = hiltViewModel<HomeViewModel>()
                            val context = LocalContext.current
                            val permissionLauncher =
                                rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions(),
                                    onResult = { permissions ->
                                        homeViewModel.sendEvent(
                                            HomeContract.HomeEvents.OnPermissionResultObtained(
                                                permissions
                                            )
                                        )
                                    })
                            LaunchedEffect(key1 = Unit) {
                                homeViewModel.singleEvent.collect { single ->
                                    when (single) {
                                        HomeContract.UIEvents.RequestLocationPermission -> homeViewModel.sendEvent(
                                            HomeContract.HomeEvents.CheckLocationPermission(
                                                context
                                            )
                                        )

                                        HomeContract.UIEvents.LaunchRequestPermission -> {
                                            permissionLauncher.launch(
                                                permissionsArray()
                                            )
                                        }

                                        HomeContract.UIEvents.ShowPermissionsSnackBar -> {
                                            val action =
                                                scaffoldState.snackbarHostState.showSnackbar(
                                                    context.getString(com.islamic.core_domain.R.string.permission_not_granted),
                                                    context.getString(com.islamic.core_domain.R.string.dismiss),
                                                    duration = SnackbarDuration.Long
                                                )
                                            when (action) {
                                                ActionPerformed -> launchSettingsForApplication()
                                                else -> launchSettingsForApplication()
                                            }
                                        }
                                    }
                                }
                            }
                            val state = homeViewModel.state.collectAsStateWithLifecycle().value

                            HomeComposable(state = state)
                        }
                        composable<Screens.BottomNavigation.QuranScreen> {
                            val quranViewModel = hiltViewModel<QuranViewModel>()
                            val state = quranViewModel.state.collectAsStateWithLifecycle().value
                            LaunchedEffect(key1 = Unit) {
                                quranViewModel.singleEvent.collect { uiEvents ->
                                    when (uiEvents) {
                                        is QuranContract.QuranUIEvents.NavigateToSurrahPage -> {
                                            navHostController.navigate(
                                                Screens.SurrahScreen(
                                                    uiEvents.number ?: 0,
                                                    uiEvents.audioId,
                                                    uiEvents.tafsirId
                                                )
                                            )
                                        }
                                    }

                                }
                            }
                            QuranScreen(state = state) { event ->
                                quranViewModel.sendEvent(event)
                            }
                        }
                        composable<Screens.BottomNavigation.RadioScreen> {
                            Text(text = "Radio")
                        }
                        composable<Screens.SurrahScreen> {
                            val surrahViewModel = hiltViewModel<SurrahViewModel>()
                            val state = surrahViewModel.state.collectAsStateWithLifecycle().value
                            val lifeCycle = LocalLifecycleOwner.current

                            DisposableEffect(lifeCycle) {
                                val lifecycleObserver = LifecycleEventObserver { _, event ->
                                    when (event) {
//                                        Lifecycle.Event.ON_START -> state.exoPlayer?.play()
//                                        Lifecycle.Event.ON_PAUSE -> state.exoPlayer?.pause()
//                                        Lifecycle.Event.ON_STOP -> state.exoPlayer?.pause()
//                                        Lifecycle.Event.ON_DESTROY -> state.exoPlayer?.pause()
                                        else -> {}
                                    }
                                }
                                lifeCycle.lifecycle.addObserver(lifecycleObserver)
                                onDispose {
                                }
                            }

                            Surrah(
                                surrahState = state,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                surrahViewModel.sendEvent(it)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun launchSettingsForApplication() {
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        ).also {
            startActivity(it)
        }
    }

    private fun permissionsArray(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) arrayOf(
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        else arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}

