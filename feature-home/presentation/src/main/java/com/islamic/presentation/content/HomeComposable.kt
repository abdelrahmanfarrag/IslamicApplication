package com.islamic.presentation.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.islamic.domain.mapper.SkyState
import com.islamic.core_domain.R
import com.islamic.domain.TextWrapper
import com.islamic.domain.mapper.SinglePray
import com.islamic.presentation.HomeContract
import com.islamic.presentation.base.composable.ErrorComposable


@Composable
fun HomeComposable(
    modifier: Modifier = Modifier,
    state: HomeContract.UIState = HomeContract.UIState()
) {
    val skyState = state.prayDTO?.skyState
    val lazyListState = rememberLazyListState()
    LaunchedEffect(state.prayDTO?.nextPray) {
        val nextPray = state.prayDTO?.nextPray
        if (nextPray is SinglePray.Isha) {
            lazyListState.animateScrollToItem(5)
        }
    }
    val color = when (skyState) {
        SkyState.AFTER_ISHA -> colorResource(id = R.color.black)
        SkyState.BETWEEN_SHROUK_DHUHR, SkyState.BETWEEN_DHUHR_MAGHREB -> colorResource(id = R.color.light_yellow)
        SkyState.BETWEEN_MAGHREB_ISHA -> colorResource(id = R.color.darkBlue)
        null -> colorResource(id = R.color.black)
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            if (state.isLoading)
                Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
        }
        Log.d("printX","${state.textWrapper} ${state.isLoading}")
        item {
            if (state.textWrapper != null && !state.isLoading)
                ErrorComposable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    errorIcon = R.drawable.ic_location,
                    errorMessage = state.textWrapper
                )
            else
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = color)
                        .shadow(1.dp)

                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(top = 8.dp, start = 16.dp),
                                text = state.prayDTO?.hijriDate ?: "NO-DATE ASSISNED",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Image(
                                modifier = Modifier
                                    .size(56.dp)
                                    .padding(top = 8.dp, end = 16.dp),
                                painter = painterResource(id = R.drawable.ic_moon),
                                contentDescription = ""
                            )
                        }
                        LazyRow(
                            state = lazyListState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = 8.dp)
                        ) {
                            items(state.prayDTO?.prays ?: arrayListOf()) { singlePray ->
                                ItemPray(singlePray = singlePray)
                            }
                        }

                    }


                }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun Preview_HomeComposable() {
    HomeComposable()
}