package com.daangn.sdk.core

/**
 * SDK 전역 설정을 관리하는 설정 클래스
 * @property batchIntervalHours 배치 작업 실행 주기 (시간 단위)
 * @property samplingRate 이벤트 샘플링 비율 (0.0 ~ 1.0)
 * @property enablePerformanceMetrics 성능 메트릭 수집 활성화 여부
 */
data class TraceConfig(
    val batchIntervalHours: Int = 1,
    val samplingRate: Double = 1.0,
    val enablePerformanceMetrics: Boolean = true
)
