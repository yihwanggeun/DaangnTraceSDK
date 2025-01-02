package com.daangn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.daangn.sdk.uitrace.components.button.TraceButton
import com.daangn.sdk.uitrace.theme.DaangnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaangnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DaangnTheme.color.primaryLight
                ) {
                    TraceButton()
                }
            }
        }
    }
}