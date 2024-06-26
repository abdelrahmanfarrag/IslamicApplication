package com.feature_quran.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.domain.entities.QuranSheikhAudio
import com.example.domain.entities.QuranTafsir
import com.feature_quran.presentation.viewmodel.QuranContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranModalBottomSheet(
//    modifier: Modifier = Modifier,
    shouldShowModalBottomSheet: Boolean = false,
    quranSheikhAudio: List<QuranSheikhAudio> = arrayListOf(),
    quranTafsir: List<QuranTafsir> = arrayListOf(),
    modalBottomSheetState: SheetState = rememberModalBottomSheetState(),
    onEvent: (QuranContract.QuranEvents) -> Unit = {}
) {
    if (shouldShowModalBottomSheet)
        ModalBottomSheet(
            sheetState = modalBottomSheetState,
            containerColor = Color.White,
            onDismissRequest = {
                onEvent.invoke(
                    QuranContract.QuranEvents.ChangeBottomSheetState(
                        false
                    )
                )
            }) {
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 56.dp)
            ) {
                item {
                    TitleSection(
                        modifier = Modifier.fillParentMaxWidth(),
                        title = "Audio"
                    )
                }
                item {
                    SheikhSelection(
                        modifier = Modifier
                            .height(120.dp)
                            .fillParentMaxWidth(),
                        quranSheikhAudio = quranSheikhAudio
                    ) {
                        onEvent(it)
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 32.dp)
                            .height(4.dp)
                            .background(Color.Black, shape = RoundedCornerShape(2.dp))
                    )
                }
                item {
                    TitleSection(
                        modifier = Modifier.fillParentMaxWidth(),
                        title = "Tafsir"
                    )
                }
                items(quranTafsir) { tafsir ->
                    ItemQuranSettings(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillParentMaxWidth()
                            .padding(8.dp),
                        isSelected = tafsir.isSelected,
                        name = tafsir.name ?: "-",
                        icon = com.islamic.core_domain.R.drawable.ic_quran
                    ) {
                        onEvent(QuranContract.QuranEvents.OnTafsirSelected(tafsir.id))
                    }
                }
                item {
                    ButtonSection(
                        Modifier
                            .fillParentMaxWidth()
                            .wrapContentHeight()
                            .padding(8.dp)
                    ) {
                        onEvent.invoke(QuranContract.QuranEvents.OnContinueClick)
                    }
                }
            }
        }
}