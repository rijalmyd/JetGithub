package com.rijaldev.jetgithub.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rijaldev.jetgithub.domain.model.user.User
import com.rijaldev.jetgithub.presentation.common.UiState
import com.rijaldev.jetgithub.presentation.component.ItemUser
import com.rijaldev.jetgithub.presentation.component.JetTopAppBar
import com.rijaldev.jetgithub.presentation.ui.theme.JetGithubTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAbout: () -> Unit,
    onItemClicked: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> viewModel.searchUsers(query)
            is UiState.Success -> {
                HomeContent(
                    users = uiState.data,
                    query = query,
                    onAboutPageClicked = navigateToAbout,
                    onItemClicked = onItemClicked,
                    onQueryChanged = viewModel::searchUsers
                )
            }
            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    users: List<User>,
    query: String,
    onAboutPageClicked: () -> Unit,
    onItemClicked: (Int) -> Unit,
    onQueryChanged: (String) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        item {
            JetTopAppBar(
                query = query,
                onQueryChanged = onQueryChanged,
                onAboutPageClicked = onAboutPageClicked,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (users.isNotEmpty()) {
            items(users, key = { it.id }) { item ->
                ItemUser(
                    id = item.id,
                    username = item.username,
                    avatarUrl = item.avatarUrl,
                    type = item.type,
                    onItemClicked = onItemClicked,
                )
            }
        } else {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Data Kosong",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    JetGithubTheme {
        HomeContent(
            users = emptyList(),
            onAboutPageClicked = {},
            onItemClicked = {},
            query = "",
            onQueryChanged = {},
        )
    }
}