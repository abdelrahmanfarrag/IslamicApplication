package com.islamic.presentation.base.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.islamic.domain.TextWrapper

@Composable
fun ErrorComposable(
    modifier: Modifier = Modifier,
    @DrawableRes errorIcon: Int = com.islamic.core_domain.R.drawable.ic_moon,
    errorMessage: TextWrapper = TextWrapper.StringText("")
) {
    val context = LocalContext.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painter = painterResource(id = errorIcon), contentDescription = errorIcon.toString(),
            tint = MaterialTheme.colors.onBackground)
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = errorMessage.getStringFromTextWrapper(context),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onBackground
        )
    }

}