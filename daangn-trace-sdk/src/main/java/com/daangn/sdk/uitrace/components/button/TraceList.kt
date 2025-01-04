package com.daangn.sdk.uitrace.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.daangn.sdk.uitrace.theme.DaangnTheme


/**
 * Left Image + Right Text List 컴포저블
 * @param modifier 컴포저블 수정자
 * @param imageUrl Left Image Url
 * @param text1 Right Text 1
 * @param text2 Right Text 2
 * @param text3 Right Text 3
 * @param text4 Right Text 4
 */
@Composable
fun TraceList(
    modifier: Modifier = Modifier,
    imageUrl: String,
    text1: String,
    text2: String,
    text3: String,
    text4: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        // 이미지 (왼쪽)
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // 텍스트 컨텐츠 (오른쪽)
        Column(
            modifier = Modifier.weight(1f).heightIn(80.dp)
        ) {
            // 가게 이름
            Text(
                text = text1,
                style = DaangnTheme.typography.body5.copy(
                    fontSize = 12.sp
                ),
                color = DaangnTheme.color.gray7
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 글 제목
            Text(
                text = text2,
                style = DaangnTheme.typography.heading2,
                color = DaangnTheme.color.gray9,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 설명
            Text(
                text = text3,
                style = DaangnTheme.typography.body5.copy(
                    fontSize = 12.sp
                ),
                color = DaangnTheme.color.gray7,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 시간
            Text(
                text = "${text4}일 전",
                style = DaangnTheme.typography.caption2,
                color = DaangnTheme.color.gray5
            )
        }
    }
}

@Preview
@Composable
fun TraceListPreview() {
    DaangnTheme {
        TraceList(
            imageUrl = "https://placehold.co/400",
            text1 = "수원 빵다방",
            text2 = "수원 빵다방 딸기케익",
            text3 = "유화제(방부제)가 들어가지 않은 천연 생크림으로 만든 케이크입니다.",
            text4 = 5
        )
    }
}