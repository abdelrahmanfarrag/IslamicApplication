package com.islamic.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : State, EVENT : Event, SINGLE_UI_EVENT : SingleUIEvent>() :
    ViewModel() {

    abstract fun createInitialState(): STATE
    abstract fun onEvent(event: Event)

    private val initialState by lazy { createInitialState() }

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state :StateFlow<STATE> = _state

    private val _event: MutableSharedFlow<EVENT> = MutableSharedFlow()
    val event = _event

    private val _singleUIEvent: Channel<SINGLE_UI_EVENT> = Channel()
    val singleEvent = _singleUIEvent.receiveAsFlow()

    val currentState: STATE
        get() = state.value

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            event.collect {
                onEvent(it)
            }
        }
    }

    fun sendSingleUIEvent(uiEvent: () -> SINGLE_UI_EVENT) {
        val singleUiEvent = uiEvent()
        viewModelScope.launch {
             _singleUIEvent.send(singleUiEvent)

        }
    }

    fun sendEvent(event :EVENT){
        val newEvent = event
        viewModelScope.launch {
            _event.emit(newEvent)
        }
    }

    protected fun setState(reduce: STATE.() -> STATE) {
        val newState = currentState.reduce()
        _state.value = newState
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}