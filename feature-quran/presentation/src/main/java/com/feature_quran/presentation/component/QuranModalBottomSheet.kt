package com.feature_quran.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.domain.entities.QuranSheikhAudio
import com.example.domain.entities.QuranTafsir
import com.feature_quran.presentation.viewmodel.QuranContract
import com.islamic.presentation.base.DarkGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranModalBottomSheet(
//    modifier: Modifier = Modifier,
    shouldShowModalBottomSheet: Boolean = false,
    quranSheikhAudio: List<QuranSheikhAudio> = arrayListOf(),
    quranTafsir: List<QuranTafsir> = arrayListOf(),
    modalBottomSheetState: SheetState = rememberModalBottomSheetState(),
    surrahName: String? = null,
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
                    Text(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .wrapContentHeight()
                            .background(DarkGreen)
                            .padding(vertical = 8.dp), text = surrahName.toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )

                }
                item {
                    TitleSection(
                        modifier = Modifier.fillParentMaxWidth(),
                        title = stringResource(id = com.islamic.core_domain.R.string.sheikh)
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
                    TitleSection(
                        modifier = Modifier.fillParentMaxWidth(),
                        title = stringResource(id = com.islamic.core_domain.R.string.tafsir)
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
                        onEvent(QuranContract.QuranEvents.OnTafsirSelected(tafsir.id,tafsir.name))
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