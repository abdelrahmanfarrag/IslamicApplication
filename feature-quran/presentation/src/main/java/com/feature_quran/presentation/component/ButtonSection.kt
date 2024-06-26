package com.feature_quran.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.feature_quran.presentation.viewmodel.QuranContract
import com.islamic.core_domain.R
import com.islamic.presentation.base.DarkGreen

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
    onEvent: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .padding(8.dp), onClick = {
            onEvent.invoke()
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = DarkGreen
        )
    ) {
        Text(
            text = stringResource(id = R.string.txt_continue),
            style = MaterialTheme.typography.button
        )
    }
}