package com.feature_surrah.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.islamic.domain.extension.replaceEnglishNumberWithArabic

@Composable
fun SurrahInformation(
    modifier: Modifier = Modifier,
    title: String? = "",
    ayahCount: Int? = -1,
    surrahNumber: Int? = -1
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextWithIcon(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            text = surrahNumber.toString().replaceEnglishNumberWithArabic(),
            icon = com.islamic.core_domain.R.drawable.ic_verse,
            iconTint = Color.White,
            textStyle = MaterialTheme.typography.subtitle2,
            textColor = Color.White,
            iconSize = 48
        )
        Text(
            text = title.toString(), color = Color.White,
            style = MaterialTheme.typography.h6,
            fontSize = 16.sp
        )

        TextWithIcon(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            text = ayahCount.toString().replaceEnglishNumberWithArabic(),
            icon = com.islamic.core_domain.R.drawable.ic_verse,
            iconTint = Color.White,
            textStyle = MaterialTheme.typography.subtitle2,
            textColor = Color.White,
            iconSize = 48
        )
    }

}