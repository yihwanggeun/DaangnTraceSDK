package com.daangn.sdk.uitrace.components.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daangn.sdk.R
import com.daangn.sdk.eventlog.ComponentMetrics
import com.daangn.sdk.uitrace.theme.DaangnTheme

/**
 * 원형 아이콘 버튼 컴포저블
 * @param id 버튼 ID
 * @param modifier 버튼 컴포저블 수정자
 * @param iconSize 아이콘 크기
 * @param icon 아이콘 이미지
 * @param backgroundColor 버튼 배경 색상
 * @param onButtonClick 버튼 클릭 이벤트 핸들러
 */
@Composable
fun TraceButton(
    id : Int,
    modifier : Modifier = Modifier,
    iconSize : Int,
    @DrawableRes icon: Int,
    backgroundColor : Color = DaangnTheme.color.primaryLight,
    onButtonClick : () -> Unit = {},
){
    val buttonSize = iconSize + 16
    val metrics = remember { ComponentMetrics() }
    var shouldLogClick by remember { mutableStateOf(false) }

    LaunchedEffect(shouldLogClick) {
        if (shouldLogClick) {
            metrics.logButtonClick(id)
            shouldLogClick = false
        }
    }
    Button(
        modifier = modifier.size(buttonSize.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        onClick = {
            onButtonClick()
            shouldLogClick = true  // 클릭 시 로깅 트리거
        },
        contentPadding = PaddingValues(0.dp)
    ){
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(iconSize.dp)
        )
    }


}

@Preview
@Composable
fun TraceButtonPreview(){
    DaangnTheme {
        TraceButton(
            id = 0,
            modifier = Modifier,
            iconSize = 60,
            icon = R.drawable.ic_category_camera_24,
            backgroundColor = DaangnTheme.color.gray6,
            onButtonClick = {}
        )
    }
}