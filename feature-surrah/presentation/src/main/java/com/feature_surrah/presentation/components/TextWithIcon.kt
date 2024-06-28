package com.feature_surrah.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    text: String? = "",
    @DrawableRes icon: Int = -1,
    iconTint: Color = Color.Unspecified,
    textStyle: TextStyle = MaterialTheme.typography.h1,
    textColor: Color = Color.Unspecified,
    iconSize : Int = 32
) {
    Box(
        modifier = modifier
//        Modifier
//            .wrapContentSize()
//            .padding(start = 8.dp)
//            .align(Alignment.CenterVertically)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(iconSize.dp),
            tint = iconTint
        )
        Text(

            text = text.toString(),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(PaddingValues(8.dp)),
            color = textColor,
            style = textStyle,

            )

    }
}