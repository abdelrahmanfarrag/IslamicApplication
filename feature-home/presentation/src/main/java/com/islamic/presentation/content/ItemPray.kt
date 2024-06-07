package com.islamic.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.islamic.domain.mapper.SinglePray
import com.islamic.core_domain.R

@Composable
fun ItemPray(modifier: Modifier = Modifier, singlePray: SinglePray = SinglePray.Default) {
    val dpSize = if (singlePray.isNextPray) 8.dp else 0.dp
    val color =
        if (singlePray.isNextPray) colorResource(id = R.color.blueOpacity65) else Color.Transparent
    Column(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(8.dp)
            .background(
                shape = RoundedCornerShape(dpSize),
                color = color
            )
            .padding(PaddingValues(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Text(
            text = singlePray.prayName.getStringFromTextWrapper(context),
            style = MaterialTheme.typography.caption,
            color = Color.White
        )
        Image(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .size(30.dp),
            painter = painterResource(id = singlePray.icon),
            contentDescription = "singleItem"
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = singlePray.timeAfString ?: "-",
            style = MaterialTheme.typography.caption,
            color = Color.White
        )

    }

}