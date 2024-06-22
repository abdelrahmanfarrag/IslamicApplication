package com.islamic.domain.usecase.locationpermission

import com.islamic.domain.PermissionResult

interface ICheckLocationPermissionUseCase {

    fun checkForLocationPermission() : PermissionResult
}