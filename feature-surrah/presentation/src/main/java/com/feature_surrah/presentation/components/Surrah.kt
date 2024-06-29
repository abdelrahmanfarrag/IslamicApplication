package com.feature_surrah.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.feature_surrah.presentation.viewmodel.SurrahContract
import com.islamic.presentation.base.DarkGreen

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun Surrah(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    surrahState: SurrahContract.UIState = SurrahContract.UIState(),
    onEvent: (SurrahContract.SurrahEvents) -> Unit = {}
) {
    LazyColumn(modifier = modifier, state = lazyListState) {
        item {
            AnimatedVisibility(visible = surrahState.isLoading) {
                LoadingComponent(modifier = Modifier.fillParentMaxSize())
            }
        }

        item {
            SurrahInformation(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .wrapContentHeight()
                    .background(color = DarkGreen, shape = RoundedCornerShape(8.dp)),
                title = surrahState.surrah?.surrahName,
                ayahCount = surrahState.surrah?.ayahCount,
                surrahNumber = surrahState.surrah?.surrahNumber
            )
        }
        surrahState.surrah?.ayahs?.let {
            items(it) { singleAyah ->
                ItemAyah(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(8.dp)),
                    ayah = singleAyah,
                    powersRatio = surrahState.powerRatio
                ) { event ->
                    onEvent.invoke(event)
                }
            }
        }
    }

}