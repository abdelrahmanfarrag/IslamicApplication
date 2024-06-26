package com.feature_quran.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.entities.QuranSheikhAudio
import com.feature_quran.presentation.viewmodel.QuranContract

@Composable
fun SheikhSelection(
    modifier: Modifier = Modifier,
    quranSheikhAudio: List<QuranSheikhAudio> = arrayListOf(),
    onEvent: (QuranContract.QuranEvents) -> Unit = {}
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        modifier = modifier, horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(quranSheikhAudio) { audio ->
            ItemQuranSettings(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 4.dp)
                    .aspectRatio(8f),
                name = audio.name ?: "-",
                icon = com.islamic.core_domain.R.drawable.ic_sheikh,
                isSelected = audio.isSelected
            ) {
                onEvent.invoke(QuranContract.QuranEvents.OnAudioSelected(audio.id))
            }
        }

    }
}