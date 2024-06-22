package com.islamic.domain.usecase.location

import android.location.Geocoder
import android.os.Build
import com.google.android.gms.location.FusedLocationProviderClient
import com.islamic.core_domain.R
import com.islamic.domain.PermissionResult
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.domain.model.Location
import com.islamic.domain.usecase.locationpermission.ICheckLocationPermissionUseCase
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject


class GetUserLocation @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    private val iCheckLocationPermissionUseCase: ICheckLocationPermissionUseCase
) : IGetUserLocation {
    override suspend fun getUserLocation(): ResultState<Location> {
        val location = CompletableDeferred<ResultState<Location>>()
        if (iCheckLocationPermissionUseCase.checkForLocationPermission() is PermissionResult.PermissionNotGranted)
            location.complete(ResultState.ResultError(TextWrapper.ResourceText(R.string.permission_not_granted)))
        else
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { obtainedLoc ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    obtainedLoc?.let {
                        geocoder.getFromLocation(it.latitude, obtainedLoc.longitude, 1) { locationsResult->
                            if (locationsResult.isEmpty())
                                location.complete(ResultState.ResultError(TextWrapper.ResourceText(R.string.no_location_found)))
                            else {
                                val firstLocation = locationsResult[0]
                                val cityName = firstLocation.subAdminArea
                                val countryName = firstLocation.countryName
                                location.complete(
                                    ResultState.ResultSuccess(
                                        Location(
                                            cityName,
                                            countryName
                                        )
                                    )
                                )
                            }
                        }
                    }?: location.complete(ResultState.ResultError(TextWrapper.StringText("Location is not obtained")))
                } else {
                    val addresses =
                        geocoder.getFromLocation(obtainedLoc.latitude, obtainedLoc.longitude, 1)
                    if (addresses.isNullOrEmpty())
                        location.complete(ResultState.ResultError(TextWrapper.ResourceText(R.string.no_location_found)))
                    else {
                        val firstLocation = addresses[0]
                        val cityName = firstLocation.subAdminArea
                        val countryName = firstLocation.countryName
                        location.complete(
                            ResultState.ResultSuccess(
                                Location(
                                    cityName,
                                    countryName
                                )
                            )
                        )
                    }
                }
            }.addOnFailureListener {
                location.complete(ResultState.ResultError(TextWrapper.ResourceText(R.string.something_went_wrong)))
            }
        return location.await()
    }
}