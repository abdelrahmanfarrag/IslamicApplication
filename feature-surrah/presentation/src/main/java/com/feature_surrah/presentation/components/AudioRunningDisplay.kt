package com.feature_surrah.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.islamic.presentation.base.DarkGreen
import kotlin.random.Random

@Composable
fun AudioRunningDisplay(
    modifier: Modifier = Modifier, powerRatio: List<Float> = arrayListOf()
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .padding(
                horizontal = 32.dp,
                vertical = 8.dp
            )
            .drawBehind {
                val powerRatioWidth = 3.dp.toPx()
                val powerRatioCount = (size.width / (2 * powerRatioWidth)).toInt()
                clipRect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height
                ) {
                    powerRatio
                        .takeLast(powerRatioCount)
                        .reversed()
                        .forEachIndexed { i, ratio ->
                            val yTopStart = center.y - (size.height / 2f) * ratio
                            drawRoundRect(
                                color = Color.White,
                                topLeft = Offset(
                                    x = size.width - i * 2 * powerRatioWidth,
                                    y = yTopStart
                                ),
                                size = Size(
                                    width = powerRatioWidth,
                                    height = (center.y - yTopStart) * 2f
                                ),
                                cornerRadius = CornerRadius(100f)
                            )
                        }
                }
            }
    )

}


@Preview(showBackground = true)
@Composable
private fun Preview_AudioRunningDisplay() {
    AudioRunningDisplay(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkGreen)
            .height(100.dp),
        powerRatio = (0..50).map {
            Random.nextFloat()
        })
}