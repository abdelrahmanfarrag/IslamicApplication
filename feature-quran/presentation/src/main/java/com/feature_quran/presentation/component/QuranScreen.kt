package com.feature_quran.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.feature_quran.presentation.viewmodel.QuranContract

@Composable
fun QuranScreen(
    modifier: Modifier = Modifier,
    state: QuranContract.QuranState = QuranContract.QuranState(),
    onEvent: (QuranContract.QuranEvents) -> Unit = {}
) {
    Log.d("pritnState", "${state.quranDto} - ${state.errorText} - ${state.isLoading}")
    Text(
        modifier = Modifier.fillMaxSize(),
        text =
        state.quranDto.toString()
    )

}