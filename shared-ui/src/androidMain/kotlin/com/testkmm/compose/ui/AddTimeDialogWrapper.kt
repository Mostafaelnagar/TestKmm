package com.testkmm.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.testkmm.compose.onDismissType

@Composable
actual fun AddTimeDialogWrapper(onDismiss: onDismissType, content: @Composable () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss) {
        content()
    }
}