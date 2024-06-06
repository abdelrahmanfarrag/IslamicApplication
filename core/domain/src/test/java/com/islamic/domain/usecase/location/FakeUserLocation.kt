package com.islamic.domain.usecase.location

import com.islamic.core_domain.R
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.domain.model.Location

class FakeUserLocation : IGetUserLocation {

    var isLocationPermissionGranted: Boolean = false
    var isLocationThrowError: Boolean = false
    var locationsResult = mutableListOf<Location>()
    override suspend fun getUserLocation(): ResultState<Location> {
        return if (isLocationPermissionGranted) {
            if (isLocationThrowError)
                ResultState.ResultError(TextWrapper.ResourceText(R.string.something_went_wrong))
            else {
                if (locationsResult.isEmpty())
                    ResultState.ResultError(TextWrapper.ResourceText(R.string.no_location_found))
                else
                    ResultState.ResultSuccess(
                        Location(
                            locationsResult[0].city,
                            locationsResult[0].country
                        )
                    )
            }
        } else {
            ResultState.ResultError(TextWrapper.ResourceText(R.string.permission_not_granted))
        }
    }
}