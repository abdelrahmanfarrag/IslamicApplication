package com.islamic.services

import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver
import com.islamic.core_domain.R

class AlarmPrayReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)
        setWorkManager(context)
        val notification =
            NotificationCompat.Builder(
                context,
                context.getString(R.string.notification_channel_id)
            )
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(context.getString(R.string.pray_times))
                .setContentText(context.getString(R.string.pray_times_updates))
                .build()

        val notifcationManager = NotificationManagerCompat.from(context)
        notifcationManager.notify(0, notification)
    }


    private fun setWorkManager(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val compressionWork = OneTimeWorkRequestBuilder<SyncPrayTimeManager>()
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(context).enqueue(compressionWork)
    }
}