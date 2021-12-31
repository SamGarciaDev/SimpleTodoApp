package edu.samgarcia.todomvvm.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Grey100 = Color(0xFFF5F5F5)
val Grey900 = Color(0xFF212121)

val Colors.cardBackgroundColor
    @Composable
    get() = if (isLight) Grey100 else Grey900