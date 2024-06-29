@file:OptIn(ExperimentalMaterial3Api::class)

package com.feature_quran.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.feature_quran.presentation.viewmodel.QuranContract

@Composable
fun QuranScreen(
    modifier: Modifier = Modifier,
    state: QuranContract.QuranState = QuranContract.QuranState(),
    onEvent: (QuranContract.QuranEvents) -> Unit = {},
) {
    val modalBottomSheetState =
        androidx.compose.material3.rememberModalBottomSheetState()
    QuranModalBottomSheet(
        shouldShowModalBottomSheet = state.shouldShowModalBottomSheet,
        quranSheikhAudio = state.quranDto?.quranSheikhAudios ?: arrayListOf(),
        quranTafsir = state.quranDto?.quranTafsir ?: arrayListOf(),
        surrahName = state.name,
        modalBottomSheetState = modalBottomSheetState
    ) {
        onEvent.invoke(it)
    }
    LazyColumn(modifier = modifier) {
        item {
            if (state.isLoading)
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
        }
        item {
            Text(
                text = stringResource(id = com.islamic.core_domain.R.string.quran),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                style = MaterialTheme.typography.h1
            )
        }
        items(state.quranDto?.quranMeta?.surrahs ?: arrayListOf()) {
            ItemSurrah(singleSurrah = it) { event ->
                onEvent(event)
            }
        }
    }
}
