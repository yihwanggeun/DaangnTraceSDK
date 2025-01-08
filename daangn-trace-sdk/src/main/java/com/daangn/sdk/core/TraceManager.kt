package com.daangn.sdk.core

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 *  SDK 초기화 및전역 상태를 관리하는 싱글톤 매니저
 */
object TraceManager{
    private var config: TraceConfig = TraceConfig()
    // SDK 초기화 여부
    private var initialized = false

    /**
     * SDK 초기화 함수
     * @param context Application Context
     * @param config SDK설정
     */
    fun initialize(context: Context, config: TraceConfig = TraceConfig()) {
        if(initialized){
            return
        }
        this.config = config
        setupWorkManager(context)
        initialized = true
    }

    private fun setupWorkManager(context : Context){
        WorkManager.getInstance(context).cancelAllWork()

        val workRequest = PeriodicWorkRequestBuilder<BatchWorker>(
            config.batchIntervalHours.toLong(), TimeUnit.HOURS
        )
            .setInitialDelay(15, TimeUnit.SECONDS)  // 30초에서 5초로 변경
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "trace_batch_worker",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )

    }
}