package com.islamic.presentation.base.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Boolean.animateDpState(source: Int, target: Int): Dp {
    return animateDpAsState(targetValue = if (this) target.dp else source.dp, label = "").value
}

@Composable
fun Boolean.animateColorState(source: Color, target: Color): Color {
    return animateColorAsState(targetValue = if (this) target else source, label = "").value
}