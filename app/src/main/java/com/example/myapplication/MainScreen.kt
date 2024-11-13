package com.example.myapplication.ui

import androidx.compose.runtime.*
import com.example.myapplication.ui.screens.Profil
import com.example.myapplication.ui.screens.SecondScreen

@Composable
fun MainScreen() {
    var showSecondScreen by remember { mutableStateOf(false) }

    if (showSecondScreen) {
        SecondScreen(onBack = { showSecondScreen = false })
    } else {
        Profil(
            name = "NABIL EL BISOUS",
            onStartClicked = { showSecondScreen = true }
        )
    }
}
