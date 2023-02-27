package com.rijaldev.jetgithub.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijaldev.jetgithub.domain.model.user.User
import com.rijaldev.jetgithub.domain.usecase.user.ListUserUseCase
import com.rijaldev.jetgithub.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val listUserUseCase: ListUserUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<User>>> = MutableStateFlow(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchUsers(query: String) = viewModelScope.launch {
        _query.value = query
        listUserUseCase.searchUsers(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect { users ->
                _uiState.value = UiState.Success(users)
            }
    }
}