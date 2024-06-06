package com.islamic.domain.usecase.location

import com.islamic.core_domain.R
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.domain.model.Location
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetUserLocationShould {

    private lateinit var iGetUserLocation: IGetUserLocation
    @Test
    fun `return ResultState error with error message Permission not granted when permission is not granted`()= runTest {
        iGetUserLocation = FakeUserLocation()
        val result =iGetUserLocation.getUserLocation()
        assertEquals(TextWrapper.ResourceText(R.string.permission_not_granted),(result as ResultState.ResultError).textWrapper)
    }
    @Test
    fun `return ResultState error when permission is granted while no location obtained`()= runTest {
        val location = FakeUserLocation()
        location.isLocationPermissionGranted=true
        iGetUserLocation = location
        val result =iGetUserLocation.getUserLocation()
        assertEquals(TextWrapper.ResourceText(R.string.no_location_found),(result as ResultState.ResultError).textWrapper)
    }

    @Test
    fun `return ResultState error when some error throw`()= runTest {
        val location =  FakeUserLocation()
        location.isLocationThrowError=true
        location.isLocationPermissionGranted=true
        iGetUserLocation = location
        val result =iGetUserLocation.getUserLocation()
        assertEquals(TextWrapper.ResourceText(R.string.something_went_wrong),(result as ResultState.ResultError).textWrapper)
    }
    @Test
    fun `return ResultState success when some location returned`()= runTest {
        val location = FakeUserLocation()
        location.isLocationPermissionGranted=true
        location.locationsResult.add(Location("zagazig","egypt"))
        iGetUserLocation = location
        val result =iGetUserLocation.getUserLocation()
        assertEquals(Location("zagazig","egypt"),(result as ResultState.ResultSuccess).result)
    }
}