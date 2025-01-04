package com.daangn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.daangn.sdk.R
import com.daangn.sdk.uitrace.components.button.TraceButton
import com.daangn.sdk.uitrace.components.button.TraceButtonTextColumn
import com.daangn.sdk.uitrace.components.button.TraceList
import com.daangn.sdk.uitrace.theme.DaangnTheme

class MainActivity : ComponentActivity() {


    private val buttonItems = listOf(
        ButtonItem("붕어빵", R.drawable.ic_category_camera_24),
        ButtonItem("음식점", R.drawable.ic_category_cart_24),
        ButtonItem("카페/간식", R.drawable.ic_category_chat_24),
        ButtonItem("운동", R.drawable.ic_category_message_24),
        ButtonItem("취미/클래스", R.drawable.ic_category_picture_24),
        ButtonItem("미용실", R.drawable.ic_category_profile_24),
        ButtonItem("뷰티", R.drawable.ic_category_security_24),
        ButtonItem("학원/과외", R.drawable.ic_category_suitcase_24),
        ButtonItem("이사/용달", R.drawable.ic_category_trash_24),
        ButtonItem("지갑", R.drawable.ic_category_wallet_24)
    )

    private val listItems = listOf(
        // 붕어빵 카테고리 (10개)
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/200",
            text1 = "수원 붕어빵",
            text2 = "팥 붕어빵 10개 세트",
            text3 = "매일 아침 직접 만드는 팥앙금으로 만든 붕어빵입니다.",
            text4 = 1
        ),
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/201",
            text1 = "강남 붕어빵",
            text2 = "슈크림 붕어빵 특대 사이즈",
            text3 = "프랑스 생크림으로 만든 슈크림 붕어빵입니다.",
            text4 = 2
        ),
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/202",
            text1 = "홍대 붕어빵",
            text2 = "누텔라 붕어빵 세트",
            text3 = "초코덕후를 위한 누텔라 크림 붕어빵입니다.",
            text4 = 1
        ),
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/203",
            text1 = "신촌 붕어빵",
            text2 = "말차 크림 붕어빵",
            text3 = "제주 말차 파우더로 만든 크림 붕어빵입니다.",
            text4 = 3
        ),
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/204",
            text1 = "이대 붕어빵",
            text2 = "큐브 붕어빵 박스",
            text3 = "한입 크기로 만든 미니 붕어빵 세트입니다.",
            text4 = 2
        ),
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/205",
            text1 = "건대 붕어빵",
            text2 = "치즈 붕어빵 스페셜",
            text3 = "모짜렐라 치즈가 가득한 붕어빵입니다.",
            text4 = 4
        ),
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/206",
            text1 = "종로 붕어빵",
            text2 = "단팥 붕어빵 골드",
            text3 = "100% 국내산 팥으로 만든 전통 붕어빵입니다.",
            text4 = 1
        ),
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/207",
            text1 = "명동 붕어빵",
            text2 = "크로플 붕어빵 세트",
            text3 = "바삭한 크로와상 식감의 붕어빵입니다.",
            text4 = 2
        ),
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/208",
            text1 = "을지로 붕어빵",
            text2 = "흑임자 붕어빵 박스",
            text3 = "국내산 흑임자로 만든 고소한 붕어빵입니다.",
            text4 = 3
        ),
        ListItem(
            category = "붕어빵",
            imageUrl = "https://picsum.photos/209",
            text1 = "광화문 붕어빵",
            text2 = "피넛버터 붕어빵 세트",
            text3 = "진한 피넛버터 크림이 들어간 붕어빵입니다.",
            text4 = 2
        ),

        // 음식점 카테고리 (10개)
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/210",
            text1 = "맛있는 부대찌개",
            text2 = "부대찌개 2인 세트",
            text3 = "진한 사골육수로 만든 깊은 맛의 부대찌개",
            text4 = 1
        ),
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/211",
            text1 = "황금 돈까스",
            text2 = "등심 돈까스 스페셜",
            text3 = "국내산 돼지고기로 만든 바삭한 돈까스",
            text4 = 2
        ),
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/212",
            text1 = "일미 닭갈비",
            text2 = "춘천식 닭갈비 2인분",
            text3 = "직접 만든 특제 소스의 춘천 닭갈비",
            text4 = 3
        ),
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/213",
            text1 = "명품 삼겹살",
            text2 = "생삼겹살 500g",
            text3 = "청정 제주산 돼지고기 삼겹살",
            text4 = 1
        ),
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/214",
            text1 = "왕가 된장찌개",
            text2 = "된장찌개 2인 세트",
            text3 = "전통 방식으로 숙성한 된장으로 만든 찌개",
            text4 = 2
        ),
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/215",
            text1 = "해물 칼국수",
            text2 = "해물 칼국수 1인분",
            text3 = "신선한 해물이 가득한 칼국수",
            text4 = 4
        ),
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/216",
            text1 = "천하 짜장면",
            text2 = "짜장면 2그릇",
            text3 = "수제 면과 특제 소스의 짜장면",
            text4 = 1
        ),
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/217",
            text1 = "황금 김밥",
            text2 = "김밥 2줄 세트",
            text3 = "신선한 재료로 만든 맛있는 김밥",
            text4 = 2
        ),
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/218",
            text1 = "명가 떡볶이",
            text2 = "매콤한 떡볶이 2인분",
            text3 = "특제 고추장으로 만든 매콤한 떡볶이",
            text4 = 1
        ),
        ListItem(
            category = "음식점",
            imageUrl = "https://picsum.photos/219",
            text1 = "골목 순대국",
            text2 = "순대국밥 1인분",
            text3 = "진한 사골육수의 담백한 순대국밥",
            text4 = 3
        ),

        // 카페/간식 카테고리 (10개)
        ListItem(
            category = "카페/간식",
            imageUrl = "https://picsum.photos/220",
            text1 = "달달 카페",
            text2 = "아메리카노 2잔",
            text3 = "직접 로스팅한 원두로 내린 커피",
            text4 = 1
        ),
        ListItem(
            category = "카페/간식",
            imageUrl = "https://picsum.photos/221",
            text1 = "달콤 베이커리",
            text2 = "크로와상 3개 세트",
            text3 = "프랑스 버터로 만든 바삭한 크로와상",
            text4 = 2
        ),
        ListItem(
            category = "카페/간식",
            imageUrl = "https://picsum.photos/222",
            text1 = "행복 카페",
            text2 = "카페라떼 2잔",
            text3 = "고소한 우유와 에스프레소의 조화",
            text4 = 1
        ),
        ListItem(
            category = "카페/간식",
            imageUrl = "https://picsum.photos/223",
            text1 = "케이크 하우스",
            text2 = "티라미수 1조각",
            text3 = "이탈리아 마스카포네 치즈로 만든 티라미수",
            text4 = 3
        ),
        ListItem(
            category = "카페/간식",
            imageUrl = "https://picsum.photos/224",
            text1 = "커피 천국",
            text2 = "바닐라 라떼",
            text3 = "천연 바닐라빈으로 만든 달콤한 라떼",
            text4 = 2
        ),
        ListItem(
            category = "카페/간식",
            imageUrl = "https://picsum.photos/225",
            text1 = "디저트 카페",
            text2 = "마카롱 6개 세트",
            text3 = "프랑스 레시피로 만든 정통 마카롱",
            text4 = 1
        ),
        ListItem(
            category = "카페/간식",
            imageUrl = "https://picsum.photos/226",
            text1 = "쿠키 하우스",
            text2 = "초코칩쿠키 세트",
            text3 = "벨기에 초콜릿으로 만든 쿠키",
            text4 = 2
        ),
        ListItem(
            category = "카페/간식",
            imageUrl = "https://picsum.photos/227",
            text1 = "스무디 카페",
            text2 = "베리베리 스무디",
            text3 = "신선한 베리류로 만든 스무디",
            text4 = 1
        ),
        ListItem(
            category = "카페/간식",
            imageUrl = "https://picsum.photos/228",
            text1 = "와플 하우스",
            text2 = "벨기에 와플 2개",
            text3 = "신선한 베리류로 만든 스무디",
            text4 = 1
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaangnTheme {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                ) {
                    var selectedCategory by remember { mutableStateOf("") }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_location_line_black_16px),
                            contentDescription = "위치",
                            tint = DaangnTheme.color.primary,
                            modifier = Modifier.size(16.dp)
                        )

                        Text(
                            text = "서울특별시 강남구 역삼동",
                            style = DaangnTheme.typography.caption2,
                            color = DaangnTheme.color.primary
                        )
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(5),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        items(count = buttonItems.size, itemContent = { index ->
                            val item = buttonItems[index]
                            TraceButtonTextColumn(
                                modifier = Modifier.padding(4.dp),
                                title = item.title,
                                icon = item.icon,
                                size = 40,
                                onButtonClick = { selectedCategory = item.title }
                            )
                        })

                    }

                    Text(
                        text = "서울대입구역 근처 소식",
                        style = DaangnTheme.typography.heading2,
                        color = DaangnTheme.color.primary
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    HorizontalDivider(
                        color = DaangnTheme.color.gray3,
                        thickness = 1.dp
                    )

                    LazyColumn{
                        val filteredList = listItems.filter{it.category == selectedCategory}
                        items(count = filteredList.size, itemContent = { index ->
                            val item = filteredList[index]
                            TraceList(
                                imageUrl = item.imageUrl,
                                text1 = item.text1,
                                text2 = item.text2,
                                text3 = item.text3,
                                text4 = item.text4
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                        })
                    }

                }
            }
        }
    }
}

// Data class for button items
data class ButtonItem(
    val title: String,
    @DrawableRes val icon: Int
)

data class ListItem(
    val category: String,
    val imageUrl: String,
    val text1: String,
    val text2: String,
    val text3: String,
    val text4: Int,
)