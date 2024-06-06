package com.islamic.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.islamic.app.ui.theme.IslamicApplicationTheme
import com.islamic.domain.usecase.home.ILoadPrayForHomeUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicApplicationTheme {
                val home = hiltViewModel<MainViewModel>()
                LaunchedEffect(key1 = Unit) {
//                    iLoadPrayForHomeUseCase.getPrayDTO().collect {
//                        Log.d("printIt", "$it")
//                    }
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