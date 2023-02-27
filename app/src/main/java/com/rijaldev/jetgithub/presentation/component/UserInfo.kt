package com.rijaldev.jetgithub.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rijaldev.jetgithub.presentation.ui.theme.JetGithubTheme

@Composable
fun UserInfo(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = title)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

@Preview
@Composable
fun UserInfoPreview() {
    JetGithubTheme {
        UserInfo(
            title = "Repositories",
            content = "200K"
        )
    }
}