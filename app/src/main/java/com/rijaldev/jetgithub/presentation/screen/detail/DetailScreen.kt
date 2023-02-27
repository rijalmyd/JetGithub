package com.rijaldev.jetgithub.presentation.screen.detail

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rijaldev.jetgithub.presentation.common.UiState
import com.rijaldev.jetgithub.presentation.component.UserInfo
import com.rijaldev.jetgithub.presentation.ui.theme.JetGithubTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    id: Int,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> viewModel.getDetailUserById(id)
            is UiState.Success -> {
                val user = uiState.data
                DetailContent(
                    avatarUrl = user.avatarUrl,
                    username = user.username,
                    fullName = user.fullName,
                    bio = user.bio,
                    followers = user.followers,
                    following = user.following,
                    repositories = user.repositories,
                    type = user.type,
                    navigateBack = navigateBack,
                )
            }
            is UiState.Error -> {

            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailContent(
    avatarUrl: String,
    username: String,
    fullName: String,
    bio: String,
    followers: Int,
    following: Int,
    repositories: Int,
    type: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = "Photo $username",
                modifier = Modifier
                    .size(136.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.TopStart)
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Back",

                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable { navigateBack() }
                    .padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        FlowRow(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = username,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = type,
                color = Color.White,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(50)
                    )
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = fullName,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = bio,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
            context.startActivity(
                Intent(Intent.ACTION_SEND).apply {
                    setType("text/plain")
                    putExtra(Intent.EXTRA_TEXT, "Let's build a great portfolio through Github. My name is $fullName, see me in github.com/$username")
                })
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Row(modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Share",
                    fontSize = 16.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            UserInfo(title = "Repository", content = repositories.toString())
            UserInfo(title = "Followers", content = followers.toString())
            UserInfo(title = "Following", content = following.toString())
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun DetailContentPreview() {
    JetGithubTheme {
        DetailContent(
            username = "rijalmyd",
            avatarUrl = "",
            fullName = "Rijal Muhyidin",
            bio = "I am a software developer. I like studying about Android Development, Android Architecture and all things near to mobile technology",
            followers = 603,
            following = 302,
            repositories = 20,
            type = "Organization",
            navigateBack = {},
        )
    }
}