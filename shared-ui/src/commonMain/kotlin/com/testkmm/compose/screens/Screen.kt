package com.testkmm.compose.screens

sealed class Screen(val title: String) {
    object TimeZonesScreen : Screen("Timezones")
    object FindTimeScreen : Screen("Find Time")
}