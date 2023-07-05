package com.testkmm.compose.screens

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)