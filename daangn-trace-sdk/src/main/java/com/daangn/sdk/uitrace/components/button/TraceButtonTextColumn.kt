package com.daangn.sdk.uitrace.components.button

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daangn.sdk.R
import com.daangn.sdk.uitrace.theme.DaangnTheme

/**
 * 원형 아이콘 + Text 버튼 컴포저블
 * @param modifier 컴포저블 수정자
 * @param title 타이틀 Text
 * @param icon 아이콘 이미지
 * @param size 아이콘 사이즈
 * @param onButtonClick 버튼 클릭 이벤트 핸들러
 */
@Composable
fun TraceButtonTextColumn(
    modifier : Modifier = Modifier,
    title : String,
    @DrawableRes icon: Int,
    size : Int,
    onButtonClick : () -> Unit
){
    Column(
        modifier = modifier.height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        TraceButton(
            modifier = modifier,
            iconSize = size,
            icon = icon,
            backgroundColor = DaangnTheme.color.gray3,
            onButtonClick = onButtonClick
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            style = DaangnTheme.typography.caption2,
            text = title
        )
    }
}

@Preview
@Composable
fun TraceButtonTextColumnPreview(){
    DaangnTheme {
        Row() {
        }
    }
}