package com.islamic.domain.usecase.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.islamic.core_domain.R
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.domain.model.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject


class GetUserLocation @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    private val context: Context
) : IGetUserLocation {
    override suspend fun getUserLocation(): ResultState<Location> {
        val location = CompletableDeferred<ResultState<Location>>()
        if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
            && context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
        )
            location.complete(ResultState.ResultError(TextWrapper.ResourceText(R.string.permission_not_granted)))
        else
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { obtainedLoc ->
                geocoder.getFromLocation(obtainedLoc.latitude, obtainedLoc.longitude, 1) {
                    if (it.isEmpty())
                        location.complete(ResultState.ResultError(TextWrapper.ResourceText(R.string.no_location_found)))
                    else {
                        val firstLocation = it[0]
                        val cityName = "zagazig"
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