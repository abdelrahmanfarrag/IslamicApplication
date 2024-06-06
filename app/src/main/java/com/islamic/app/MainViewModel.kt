package com.islamic.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islamic.domain.usecase.home.ILoadPrayForHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val iLoadPrayForHomeUseCase: ILoadPrayForHomeUseCase
): ViewModel() {

    init {
        loadHome()
    }

    private fun loadHome(){
        viewModelScope.launch {
            iLoadPrayForHomeUseCase.getPrayDTO()
                .collect{
Log.d("printResult","Error Happened ! $it")
                }
        }
    }
}