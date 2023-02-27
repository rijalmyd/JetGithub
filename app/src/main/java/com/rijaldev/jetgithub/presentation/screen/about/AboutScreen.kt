package com.rijaldev.jetgithub.presentation.screen.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AsyncImage(
            model = "https://avatars.githubusercontent.com/u/71188953?v=4",
            contentDescription = "about_image",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        IconButton(
            onClick = navigateBack,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                tint = Color.White,
                contentDescription = "Back",
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Rijal Muhyidin",
                color = Color.White
            )
            Text(
                text = "rijalmuhyi12@gmail.com",
                color = Color.White
            )
        }
    }
}