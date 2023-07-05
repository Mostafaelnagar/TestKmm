package com.testkmm.compose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.testkmm.compose.EmptyComposable
import com.testkmm.compose.screens.bottomNavigationItems
import com.testkmm.compose.topBarFun

@Composable
fun MainView(actionBarFun: topBarFun = { EmptyComposable() }) {
    // 3
    val showAddDialog = remember { mutableStateOf(false) }
    // 4
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }
    // 5
    val selectedIndex = remember { mutableStateOf(0) }
    // 6
    MaterialTheme {
        Scaffold(
            topBar = {
                actionBarFun(selectedIndex.value)
            },
            floatingActionButton = {
                if (selectedIndex.value == 0) {
                    // 1
                    FloatingActionButton(
                        // 2
                        modifier = Modifier
                            .padding(16.dp),
                        // 3
                        onClick = {
                            showAddDialog.value = true
                        }
                    ) {
                        // 4
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            },
            bottomBar = {
                // 1
                BottomNavigation(
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    // 2
                    bottomNavigationItems.forEachIndexed { i, bottomNavigationItem ->
                        // 3
                        BottomNavigationItem(
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.Black,
                            alwaysShowLabel = false,
                            label = {
                                Text(
                                    bottomNavigationItem.route,
                                    style = MaterialTheme.typography.h4
                                )
                            },
                            // 4
                            icon = {
                                Icon(
                                    bottomNavigationItem.icon,
                                    contentDescription = bottomNavigationItem.iconContentDescription
                                )
                            },
                            // 5
                            selected = selectedIndex.value == i,
                            // 6
                            onClick = {
                                selectedIndex.value = i
                            }
                        )
                    }
                }
            }
        ) {

            if (showAddDialog.value) {
                AddTimeZoneDialog(
                    onAdd = { newTimezones ->
                        showAddDialog.value = false
                        for (zone in newTimezones) {
                            if (!currentTimezoneStrings.contains(zone)) {
                                currentTimezoneStrings.add(zone)
                            }
                        }
                    },
                    onDismiss = {
                        showAddDialog.value = false
                    },
                )
            }

            when (selectedIndex.value) {
                0 -> TimeZoneScreen(currentTimezoneStrings)
                 1 -> FindMeetingScreen(currentTimezoneStrings)
            }

        }
    }
}