// server/src/metrics/prometheus.js
const promBundle = require('express-prom-bundle');
const client = require('prom-client');

// 전체 버튼 클릭 카운터
const totalButtonClicks = new client.Counter({
    name: 'app_button_clicks_total',
    help: 'Total number of button clicks'
});

// 각 버튼별 클릭 카운터
const buttonClicksByIdCounter = new client.Counter({
    name: 'app_button_clicks_by_id',
    help: 'Number of clicks per button ID',
    labelNames: ['buttonId']
});

// 버튼 클릭 처리 시간 측정 
const buttonClickLatency = new client.Histogram({
    name: 'app_button_click_processing_duration_seconds',
    help: 'Button click processing duration in seconds',
    labelNames: ['type', 'buttonId'] 
});

const metricsMiddleware = promBundle({
    includeMethod: true,
    includePath: true,
    promClient: {
        collectDefaultMetrics: {
            timeout: 5000
        }
    }
});

module.exports = {
    metricsMiddleware,
    totalButtonClicks,
    buttonClicksByIdCounter,
    buttonClickLatency
};