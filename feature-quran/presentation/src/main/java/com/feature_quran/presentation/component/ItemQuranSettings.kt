package com.feature_quran.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.islamic.presentation.base.DarkGreen
import com.islamic.presentation.base.composable.animateColorState
import com.islamic.presentation.base.composable.animateDpState

@Composable
fun ItemQuranSettings(
    modifier: Modifier = Modifier,
    name: String = "",
    isSelected: Boolean = false,
    @DrawableRes icon: Int = -1,
    onClick: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                border = BorderStroke(
                    isSelected.animateDpState(
                        source = 1,
                        target = 2
                    ), isSelected.animateColorState(
                        source = Color.Black,
                        target = DarkGreen
                    )
                ), shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                onClick.invoke()

            }, horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(
                id = icon
            ),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp), text = name,
            style = MaterialTheme.typography.h5
        )

    }
}