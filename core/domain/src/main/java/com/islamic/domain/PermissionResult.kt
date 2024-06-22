package com.islamic.domain

sealed class PermissionResult {
    data object PermissionGranted : PermissionResult()
    data object PermissionNotGranted : PermissionResult()
}