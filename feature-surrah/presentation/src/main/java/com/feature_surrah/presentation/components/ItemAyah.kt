package com.feature_surrah.presentation.components

import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import com.feature_surrah.domain.models.Ayah
import com.feature_surrah.presentation.viewmodel.SurrahContract
import com.islamic.domain.extension.replaceEnglishNumberWithArabic
import com.islamic.presentation.base.DarkGreen

@OptIn(UnstableApi::class)
@Composable
fun ItemAyah(
    modifier: Modifier = Modifier,
    ayah: Ayah? = Ayah(),
    powersRatio: List<Float> = arrayListOf(),
    onEvent: (SurrahContract.SurrahEvents) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = DarkGreen)
                .wrapContentHeight()
                .padding(PaddingValues(8.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextWithIcon(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                text = ayah?.ayahNumber.toString().replaceEnglishNumberWithArabic(),
                textColor = Color.White,
                textStyle = MaterialTheme.typography.h6,
                iconTint = Color.White,
                icon = com.islamic.core_domain.R.drawable.ic_verse,
                iconSize = 48
            )

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = ayah?.ayah.toString(),
                style = MaterialTheme.typography.h6,
                color = Color.White
            )

        }

        Text(
            modifier = Modifier.padding(8.dp),
            text = ayah?.ayahTafsirText.toString(),
            color = Color.Black,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
        AnimatedContent(targetState = ayah?.ayahPlayingAudioState, label = "") {
            when (it) {
                Ayah.AyahPlayingState.PLAYING -> AudioRunningDisplay(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkGreen)
                        .height(70.dp),
                    powersRatio
                )

                Ayah.AyahPlayingState.BUFFERING -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp))
                    }
                }

                else -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .background(color = DarkGreen),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onEvent.invoke(
                                        SurrahContract.SurrahEvents.OnPlayClick(
                                            ayah?.ayahAudioURL,
                                            ayah?.ayahNumber
                                        )
                                    )
                                },
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "",
                            tint = Color.White
                        )
                        Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Default.Share,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}