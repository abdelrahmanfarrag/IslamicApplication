
package com.feature_quran.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.domain.entities.SingleSurrah
import com.feature_quran.presentation.viewmodel.QuranContract
import com.islamic.domain.extension.replaceEnglishNumberWithArabic

@Composable
fun ItemSurrah(
    modifier: Modifier = Modifier,
    singleSurrah: SingleSurrah = SingleSurrah(),
    onSurrahChose: (QuranContract.QuranEvents) -> Unit = {}
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .border(
                border = BorderStroke(1.dp, color = Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .clipToBounds()
            .clickable {
                onSurrahChose.invoke(QuranContract.QuranEvents.OnSurrahChose(singleSurrah.number,singleSurrah.name))
            }, horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(id = com.islamic.core_domain.R.drawable.ic_verse),
                contentDescription = "",
                modifier = Modifier.wrapContentSize()
            )
            Text(
                text = singleSurrah.number.toString().replaceEnglishNumberWithArabic(),
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black,
                style = MaterialTheme.typography.h5
            )

        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp),
            text = singleSurrah.name ?: "", color = Color.Black,
            style = MaterialTheme.typography.h5
        )
    }
}