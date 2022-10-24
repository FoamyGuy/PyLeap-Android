package com.adafruit.pyleap.ui.status

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adafruit.pyleap.R
import com.adafruit.pyleap.ui.theme.ProjectCardBackground
import com.adafruit.pyleap.ui.theme.PyLeapTheme
import io.openroad.filetransfer.ble.state.BleState

/**
 * Created by Antonio García (antonio@openroad.es)
 */

@Composable
fun BluetoothStatusScreen(
    bleState: BleState
) {
    // Texts
    val title = when (bleState) {
        BleState.Unknown -> "Bluetooth is in an unknown state"
        BleState.BluetoothNotAvailable -> "This device doesn't support Bluetooth"
        BleState.BleNotAvailable -> "This device doesn't support Bluetooth LE"
        BleState.Disabled -> "Bluetooth is currently powered off"
        BleState.TurningOn -> "Bluetooth is currently turning on"
        BleState.TurningOff -> "Bluetooth is currently turning off"
        else -> ""
    }

    val message = when (bleState) {
        BleState.Unknown -> "Bluetooth is in an unknown state"
        BleState.BluetoothNotAvailable -> "Bluetooth support and specifically Bluetooth Low Energy support is needed to communicate with a Bluefruit Device"
        BleState.BleNotAvailable -> "Bluetooth support and specifically Bluetooth Low Energy support is needed to communicate with a Bluefruit Device"
        BleState.Disabled -> "Bluetooth should be enabled on your device for the app to connect to a Bluetooth device"
        BleState.TurningOn -> ""
        BleState.TurningOff -> ""
        else -> ""
    }

    // UI
    Surface(color = ProjectCardBackground) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = spacedBy(20.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painterResource(R.drawable.bluetooth_status_logo),
                contentDescription = "Bluetooth logo",
            )

            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                color = Color.White
            )
        }
    }
}

// region Previews
@Preview(showSystemUi = true)
@Composable
private fun BluetoothStatusScreenPreview() {
    PyLeapTheme() {
        BluetoothStatusScreen(bleState = BleState.Disabled)
    }
}
//endregion