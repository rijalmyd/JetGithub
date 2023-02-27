package com.rijaldev.jetgithub.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rijaldev.jetgithub.presentation.ui.theme.JetGithubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemUser(
    id: Int,
    username: String,
    avatarUrl: String,
    type: String,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { onItemClicked(id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3F6F7)
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = avatarUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "Photo $username",
                modifier = Modifier
                    .size(116.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Box(modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxHeight()) {
                Text(
                    text = username,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )

                Text(
                    text = type,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemUserPreview() {
    JetGithubTheme {
        ItemUser(
            id = 0,
            username = "rijalmyd",
            avatarUrl = "",
            type = "User",
            onItemClicked = {}
        )
    }
}