package com.islamic.services

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.islamic.core_domain.R
import com.islamic.domain.ResultState
import com.islamic.domain.repository.IHomeRepository
import com.islamic.domain.usecase.date.IGetCurrentDateUseCase
import com.islamic.domain.usecase.location.IGetUserLocation
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CompletableDeferred

@HiltWorker
class SyncPrayTimeManager @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val iHomeRepository: IHomeRepository,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        iHomeRepository.clearRecords()
        return Result.success()
    }
}