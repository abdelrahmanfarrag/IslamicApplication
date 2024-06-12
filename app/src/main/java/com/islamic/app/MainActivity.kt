package com.islamic.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.islamic.app.navigation.Screens
import com.islamic.app.ui.theme.IslamicApplicationTheme
import com.islamic.presentation.HomeViewModel
import com.islamic.presentation.content.HomeComposable
import com.islamic.services.alarmpraytime.IAlarmPrayTime
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var iAlarmPrayTime: IAlarmPrayTime


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        AppCompatDelegate.setApplicationLocales((LocaleListCompat.forLanguageTags("ar")))
        setContent {
            IslamicApplicationTheme {
                val navHostController = rememberNavController()
                NavHost(navController = navHostController, startDestination = Screens.HomeScreen) {
                    composable<Screens.HomeScreen> {
                        val homeViewModel = hiltViewModel<HomeViewModel>()
                        val state = homeViewModel.state.collectAsStateWithLifecycle().value
                        HomeComposable(state = state)
                    }
                }
            }
        }
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

