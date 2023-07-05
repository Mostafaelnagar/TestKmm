import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.testkmm.compose.ui.MainView

data class WindowInfo(val windowName: String, val windowState: WindowState)

@OptIn(ExperimentalComposeUiApi::class)

// 1
fun main() {
    // 2
    application {
        // 3
        var initialized by remember { mutableStateOf(false) }
        var windowCount by remember { mutableStateOf(1) }
        val windowList = remember { SnapshotStateList<WindowInfo>() }
        // Add initial window
        if (!initialized) {
            windowList.add(WindowInfo("Timezone-${windowCount}", rememberWindowState()))
            initialized = true
        }
        // 4
        windowList.forEachIndexed { i, window ->
            Window(
                onCloseRequest = { windowList.removeAt(i) },
                state = windowList[i].windowState,
                title = windowList[i].windowName
            ) {
                // 1
                MenuBar {
                    // 2
                    Menu("File", mnemonic = 'F') {
                        val nextWindowState = rememberWindowState()
                        // 3
                        Item(
                            "New", onClick = {
                                // 4
                                windowCount++
                                windowList.add(
                                    WindowInfo(
                                        "Timezone-${windowCount}",
                                        nextWindowState
                                    )
                                )
                            }, shortcut = KeyShortcut(
                                Key.N, ctrl = true
                            )
                        )
                        Item("Open", onClick = { }, shortcut = KeyShortcut(Key.O, ctrl = true))
                        // 5
                        Item("Close", onClick = {
                            windowList.removeAt(i)

                        }, shortcut = KeyShortcut(Key.W, ctrl = true))
                        Item("Save", onClick = { }, shortcut = KeyShortcut(Key.S, ctrl = true))
                        // 6
                        Separator()
                        // 7
                        Item(
                            "Exit",
                            onClick = { windowList.clear() },
                        )
                    }
                    Menu("Edit", mnemonic = 'E') {
                        Item(
                            "Cut", onClick = { }, shortcut = KeyShortcut(
                                Key.X, ctrl = true
                            )
                        )
                        Item(
                            "Copy", onClick = { }, shortcut = KeyShortcut(
                                Key.C, ctrl = true
                            )
                        )
                        Item("Paste", onClick = { }, shortcut = KeyShortcut(Key.V, ctrl = true))
                    }
                }

                // 5
                Surface(modifier = Modifier.fillMaxSize()) {
                    // TODO: Add Theme
                    MainView()
                }
            }
        }
    }
}
