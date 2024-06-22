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
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult.*
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.islamic.app.navigation.Screens
import com.islamic.app.ui.theme.IslamicApplicationTheme
import com.islamic.presentation.HomeContract
import com.islamic.presentation.HomeViewModel
import com.islamic.presentation.content.HomeComposable
import com.islamic.services.alarmpraytime.IAlarmPrayTime
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var iAlarmPrayTime: IAlarmPrayTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicApplicationTheme {
                val scaffoldState = rememberScaffoldState()
                val navHostController = rememberNavController()
                Scaffold(scaffoldState = scaffoldState) { paddingValues ->
                    NavHost(
                        navController = navHostController,
                        startDestination = Screens.HomeScreen
                    ) {
                        composable<Screens.HomeScreen> {
                            val homeViewModel = hiltViewModel<HomeViewModel>()
                            val context = LocalContext.current

                            val permissionLauncher =
                                rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.RequestMultiplePermissions(),
                                    onResult = { permissions ->
                                        homeViewModel.sendEvent(
                                            HomeContract.HomeEvents.OnPermissionResultObtained(
                                                permissions
                                            )
                                        )
                                    })

                            val coroutineScope = rememberCoroutineScope()
                            val lifeCycle = LocalLifecycleOwner.current
                            DisposableEffect(Unit) {
                                val lifeCycleObserver = LifecycleEventObserver { _, event ->
                                    if (event == Lifecycle.Event.ON_START) {
                                        coroutineScope.launch {
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
                                    }

                                }
                                lifeCycle.lifecycle.addObserver(lifeCycleObserver)
                                onDispose {
                                    lifeCycle.lifecycle.removeObserver(lifeCycleObserver)

                                }
                            }
                            val state = homeViewModel.state.collectAsStateWithLifecycle().value
                            HomeComposable(state = state)
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
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            arrayOf(
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        else
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IslamicApplicationTheme {
        Greeting("Android")
    }
}

