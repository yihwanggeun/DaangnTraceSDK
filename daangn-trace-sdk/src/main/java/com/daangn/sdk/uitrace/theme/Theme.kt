package com.daangn.sdk.uitrace.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalView
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider


object DaangnTheme {
    val color: DaangnColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current

    val typography: DaangnTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypographyProvider.current
}

@Composable
fun DaangnTheme(
    content: @Composable () -> Unit
) {
    ProvideDaangnColors(
        colors = defaultDaangnColors,
        typography = defaultDaangnTypography
    ) {
        val view = LocalView.current

        MaterialTheme(
            content = content
        )
    }
}

@Composable
fun ProvideDaangnColors(
    colors: DaangnColors,
    typography: DaangnTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorProvider provides colors,
        LocalTypographyProvider provides typography,
        content = content
    )
}