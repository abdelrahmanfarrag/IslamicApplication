package com.islamic.domain.usecase.location

import com.islamic.domain.ResultState
import com.islamic.domain.model.Location

interface IGetUserLocation {
    suspend fun getUserLocation():ResultState<Location>
}