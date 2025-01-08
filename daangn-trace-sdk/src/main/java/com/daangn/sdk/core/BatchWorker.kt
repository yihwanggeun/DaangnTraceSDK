package com.daangn.sdk.core

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.daangn.sdk.eventlog.EventProcessor

/**
 * 주기적으로 실행되는 배치 작업을 처리하는 Worker 클래스
 */
class BatchWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        try {
            Log.d("BatchWorker", "=== 배치 작업 시작 ===")
            Log.d("BatchWorker", "Worker ID: ${this.id}")

            val eventCount = EventProcessor.eventQueue.size
            Log.d("BatchWorker", "처리할 이벤트 수: $eventCount")

            // 이벤트 큐에서 이벤트들을 가져와서 로그로 출력
            EventProcessor.eventQueue.forEach { event ->
                Log.d("BatchWorker", """
               처리된 이벤트:
               타입: ${event.type}
               시간: ${event.timestamp}
               메타데이터: ${event.metadata}
               Thread: ${Thread.currentThread().name}
           """.trimIndent())
            }

            // 처리 완료된 이벤트들 삭제
            val queueSizeBefore = EventProcessor.eventQueue.size
            EventProcessor.eventQueue.clear()
            val queueSizeAfter = EventProcessor.eventQueue.size

            Log.d("BatchWorker", "큐 크기 변화: $queueSizeBefore -> $queueSizeAfter")
            Log.d("BatchWorker", "=== 배치 작업 완료 ===")
            return Result.success()
        } catch (e: Exception) {
            Log.e("BatchWorker", "=== 배치 작업 실패 ===")
            Log.e("BatchWorker", "에러 메시지: ${e.message}")
            return Result.failure()
        }
    }
}