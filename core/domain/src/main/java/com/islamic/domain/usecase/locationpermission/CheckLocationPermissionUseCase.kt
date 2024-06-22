package com.islamic.domain.usecase.locationpermission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.fragment.app.FragmentActivity
import com.islamic.domain.PermissionResult
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CheckLocationPermissionUseCase @Inject constructor(
    private val context: Context
) : ICheckLocationPermissionUseCase {
    override fun checkForLocationPermission(): PermissionResult {
        return if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
            && context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
        )
            return PermissionResult.PermissionNotGranted
        else
            PermissionResult.PermissionGranted
    }
}