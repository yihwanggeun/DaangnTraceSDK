package com.daangn.sdk.core

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.daangn.sdk.data.model.Event
import com.daangn.sdk.data.network.RetrofitClient
import com.daangn.sdk.eventlog.EventProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 주기적으로 실행되는 배치 작업을 처리하는 Worker 클래스
 */
class BatchWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "=== 배치 작업 시작 ===")

            val events = EventProcessor.eventQueue.map { event ->
                Event(
                    type = event.type.toString(),
                    timestamp = event.timestamp,
                    metadata = event.metadata.mapValues { it.value.toString() }
                )
            }

            if (events.isEmpty()) {
                Log.d(TAG, "처리할 이벤트 없음")
                return@withContext Result.success()
            }

            val response = RetrofitClient.apiService.sendEvents(events)
            Log.d(TAG, "서버 응답: $response")

            // 성공적으로 전송된 이벤트 제거
            EventProcessor.eventQueue.clear()

            Log.d(TAG, "=== 배치 작업 완료 ===")
            Result.success()

        } catch (e: Exception) {
            Log.e(TAG, "배치 작업 실패: ${e.message}", e)
            Result.retry()
        }
    }
        companion object {
        private const val TAG = "BatchWorker"
    }
}

