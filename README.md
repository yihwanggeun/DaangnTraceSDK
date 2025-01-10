# Daangn Event Tracking SDK

## 프로젝트 설명

Daangn Event Tracking SDK는 기본적인 SDK 생성과 로깅 시스템을 이해하기 위한 실습입니다.

안드로이드 애플리케이션에서 사용자 상호작용을 추적하고 분석하기 위한 종합적인 솔루션입니다. 실시간 데이터 수집부터 시각화까지 완벽한 분석 파이프라인을 제공하며, 사용자 행동 패턴을 효과적으로 모니터링할 수 있습니다.

## 주요 기능

### 커스텀 UI 컴포넌트(TraceButton만 작동)

* **TraceButton** : 클릭 이벤트 로깅이 포함된 원형 아이콘 버튼
* **TraceButtonTextColumn** : 텍스트와 함께 사용할 수 있는 버튼 컴포넌트
* **TraceList** : 커스텀 리스트 아이템 컴포넌트

### 이벤트 추적 시스템

* 실시간 사용자 상호작용 추적
* UI 컴포넌트 클릭 이벤트 자동 로깅
* 배치 처리 방식의 이벤트 전송 (기본값: 1시간 주기)

### 데이터 수집 및 분석

* 효율적인 네트워크 사용을 위한 배치 처리 시스템
* 이벤트 타입별 메트릭 수집
* 실시간 데이터 시각화 대시보드

### 모니터링 기능

* Prometheus를 통한 메트릭 수집
* Grafana 대시보드를 통한 실시간 모니터링
* 상세한 버튼 클릭 분석 및 성능 지표 확인

## 기술 스택

* Android SDK: Kotlin, Jetpack Compose, WorkManager
* 서버: Node.js, Express, MongoDB
* 모니터링: Prometheus, Grafana

## 프로젝트 구조

```plaintext
daangn-trace-sdk/
├── android/
│   ├── src/
│   │   ├── androidTest/            # Android 테스트 코드
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── daangn/
│   │       │           └── sdk/
│   │       │               ├── core/                    # SDK 핵심 기능
│   │       │               │   ├── BatchWorker.kt      # 배치 작업 처리
│   │       │               │   ├── TraceConfig.kt      # SDK 설정
│   │       │               │   └── TraceManager.kt     # SDK 관리자
│   │       │               ├── data/                   # 데이터 계층
│   │       │               │   ├── model/
│   │       │               │   │   └── Event.kt        # 이벤트 모델
│   │       │               │   └── network/
│   │       │               │       ├── ApiService.kt   # API 서비스
│   │       │               │       └── RetrofitClient.kt
│   │       │               ├── eventlog/               # 이벤트 로깅
│   │       │               │   └── ComponentMetrics.kt # 컴포넌트 메트릭
│   │       │               └── uitrace/               # UI 컴포넌트
│   │       │                   ├── components/
│   │       │                   │   └── button/
│   │       │                   │       ├── TraceButton.kt
│   │       │                   │       ├── TraceButtonTextColumn.kt
│   │       │                   │       └── TraceList.kt
│   │       │                   └── theme/             # 테마 관련
│   │       │                       ├── Color.kt
│   │       │                       ├── Theme.kt
│   │       │                       └── Typography.kt
│   │       └── res/                # 리소스 파일
│   └── build.gradle               # Android 빌드 설정
│
└── server/
    ├── src/
    │   ├── metrics/              # 메트릭 관련
    │   │   └── prometheus.js     # Prometheus 설정
    │   ├── model/               # 데이터 모델
    │   │   └── Event.js
    │   ├── routes/              # API 라우트
    │   │   ├── health.js
    │   │   ├── index.js
    │   │   └── metrics.js
    │   └── app.js              # 서버 메인
    ├── monitoring/             # 모니터링 설정
    │   ├── grafana/
    │   │   ├── dashboards/
    │   │   │   └── dashboard.yml
    │   │   └── provisioning/
    │   │       └── button-clicks.json
    │   └── prometheus/
    │       ├── prometheus/
    │       │   ├── docker-compose.yml
    │       │   └── prometheus.yml
    ├── node_modules/          # Node.js 의존성
    ├── Dockerfile            # Docker 설정
    ├── .env                 # 환경 변수
    └── package.json         # Node.js 프로젝트 설정
```

## 프로젝트 설정

### SDK 빌드

#### 1. 프로젝트 클론

```bash
git clone https://github.com/yihwanggeun/DaangnTraceSDK.git
git checkout develop
cd daangn-trace-sdk
```

#### 2. AAR 파일 생성

AAR 생성 후 프로젝트의 libs 폴더에서 AAR 파일을 확인할 수 있습니다.

`daangn-trace-sdk.aar` 파일을 자신의 프로젝트의 libs 폴더로 이동해주세요

```bash
./gradlew assembleRelease

```

### 개인 프로젝트에서 사용하는 방법

#### 1. 프로젝트의 app 수준 `build.gradle`에 의존성을 추가합니다

```bash
dependencies {
    implementation(files("./../libs/daangn-trace-sdk.aar"))

    implementation("androidx.work:work-runtime-ktx:2.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("io.coil-kt:coil-compose:2.7.0")
}
```

<pre><div class="relative flex flex-col rounded-lg"><div class="text-text-300 absolute pl-3 pt-2.5 text-xs"></div></div></pre>

#### 2. SDK 초기화

```kotlin
class YourApplication : Application() {
    override fun onCreate() {
        super.onCreate()
  
        TraceManager.initialize(
            context = this,
            config = TraceConfig(
                batchIntervalHours = 1,  // 배치 처리 주기
                samplingRate = 1.0,      // 샘플링 비율
                enablePerformanceMetrics = true  // 아직 사용 불가
            )
        )
    }
}
```

#### Compose UI에 Custom Component 추가

**원형 아이콘 버튼**

```kotlin
@Composable
fun YourScreen() {
    TraceButton(
        id = YOUR_BUTTON_ID,  // 버튼 식별자
        modifier = Modifier,
        iconSize = 60,        // 아이콘 크기
        icon = R.drawable.your_icon,  // 아이콘 리소스
        backgroundColor = Color.Gray,  // 배경색
        onButtonClick = {
            // 클릭 이벤트 처리
        }
    )
}
```

**ListItem**

```kotlin
DaangnTheme {
    TraceList(
        imageUrl = "https://placehold.co/400",
        text1 = "수원 빵다방",
        text2 = "수원 빵다방 딸기케익",
        text3 = "유화제(방부제)가 들어가지 않은 천연 생크림으로 만든 케이크입니다.",
        text4 = 5   
    )
}

```

### 필수 요구사항

* Docker 및 Docker Compose 설치
* Node.js 16 이상 (로컬 개발용)

### 로컬 개발 환경 설정

1. 저장소 클론

```bash
git clone https://github.com/yihwanggeun/DaangnTraceSDK.git
git checkout develop
cd daangn-trace-sdk
```

2. 의존성 설치

```bash
npm install
```

3. `.env` 파일 생성

```
MONGODB_URI=mongodb://localhost:27017/metrics-db
PORT=3000
```

4. `Prometheus` `Grafana` `Express Server` 기동

```
# 저장소 클론 및 의존성 설치
git clone https://github.com/yihwanggeun/DaangnTraceSDK.git
git checkout develop
npm install

# Prometheus & Grafana 실행
cd monitoring/prometheus
docker compose up --build -d

```

5. 기본 서버 구동
   Node.js 애플리케이션 (포트 3000)
   MongoDB (포트 27017)
   Prometheus (포트 9090)
   Grafana (포트 3001)

### 모니터링 대시보드

#### Grafana 접속

1. 브라우저에서 `http://localhost:3001` 접속
2. 로그인 정보:
   * 사용자명: `admin`
   * 비밀번호: `admin`
3. DataSource로 `http://prometheus:9090` 등록

#### 제공 메트릭

* 전체 버튼 클릭 수
* 버튼 ID별 클릭 수
* 분당 클릭률
* 처리 지연 시간

## 시연 예시
![image](https://github.com/user-attachments/assets/797f7129-baa9-4ea5-8b46-598ebafebe1b)
![image](https://github.com/user-attachments/assets/3c1064d1-7e73-4571-8b0a-59f3abf23b69)

