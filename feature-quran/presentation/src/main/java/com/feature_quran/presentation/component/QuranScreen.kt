package com.feature_quran.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.feature_quran.presentation.viewmodel.QuranContract

@Composable
fun QuranScreen(
    modifier: Modifier = Modifier,
    state: QuranContract.QuranState = QuranContract.QuranState(),
    onEvent: (QuranContract.QuranEvents) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        item {
            if (state.isLoading)
                Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
        }
    }
}
