package com.islamic.domain

import android.content.Context
import androidx.annotation.StringRes

sealed class TextWrapper {
    data class StringText(val text: String) : TextWrapper()
    data class ResourceText(@StringRes val id: Int) : TextWrapper()

    fun getStringFromTextWrapper(context: Context): String {
        return when (this) {
            is StringText -> this.text
            is ResourceText -> context.getString(this.id)
        }
    }
}