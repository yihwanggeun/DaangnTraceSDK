package com.daangn.sdk.eventlog

import android.util.Log
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * UI 컴포넌트 관련 메트릭을 수집하는 클래스
 */
class ComponentMetrics {
    /**
     * TraceButton 클릭 이벤트 로깅
     * @param buttonId 버튼 식별자
     */
    suspend fun logButtonClick(buttonId: Int) {
        val event = TraceEvent(
            type = EventType.BUTTON_CLICK,
            timestamp = System.currentTimeMillis(),
            metadata = mapOf(
                "buttonId" to buttonId
            )
        )
        EventProcessor.processEvent(event)
    }
}

enum class EventType {
    BUTTON_CLICK
}

data class TraceEvent(
    val type: EventType,
    val timestamp: Long,
    val metadata: Map<String, Any>
)

object EventProcessor {
    val eventQueue = mutableListOf<TraceEvent>()
    private val lock = Mutex()  // 동시성 제어를 위한 뮤텍스

    suspend fun processEvent(event: TraceEvent) {
        lock.withLock {
            eventQueue.add(event)
            Log.d("EventProcessor", "이벤트 큐에 추가됨: $event")
        }
    }
}