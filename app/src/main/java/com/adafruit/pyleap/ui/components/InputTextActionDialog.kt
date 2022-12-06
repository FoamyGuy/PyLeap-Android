package com.adafruit.pyleap.ui.components

/**
 * Created by Antonio García (antonio@openroad.es)
 */

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adafruit.pyleap.ui.theme.PyLeapTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextActionDialog(
    alertText: String,
    alertMessage: String,
    placeholderText: String,
    currentText: String= "",
    actionText: String,
    onAction: (String?) -> Unit,            // returns the text or null if cancel was pressed
) {
    var inputText by remember { mutableStateOf(currentText) }

    AlertDialog(
        onDismissRequest = { onAction(null) },
        title = { Text(alertText) },
        containerColor = Color.White,
        titleContentColor = Color.Black,
        textContentColor = Color.Black,
        text = {
            Column(verticalArrangement = Arrangement.Absolute.spacedBy(16.dp)) {
                Text(alertMessage)
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        placeholderColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                    ),
                    placeholder = { Text(placeholderText) },
                    singleLine = true,
                    value = inputText,
                    onValueChange = { inputText = it },
                )
            }
        },
        confirmButton = {
            OutlinedButton(enabled = inputText.isNotBlank(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    disabledContentColor = Color.Gray,
                ),
                border = BorderStroke(1.dp, Color.Black),
                onClick = {
                    onAction(inputText)
                }) {
                Text(actionText)
            }
        },
        dismissButton = {
            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.Black),
                onClick = {
                    onAction(null)
                }) {
                Text("Cancel")
            }
        })
}

@Preview(showSystemUi = true)
@Composable
private fun InputTextActionDialogPreview() {
    PyLeapTheme {
        InputTextActionDialog(
            alertText = "New Directory",
            alertMessage = "Enter name for the new directory",
            placeholderText = "Directory name",
            actionText = "Create",
        ) {}
    }
}

//endregion