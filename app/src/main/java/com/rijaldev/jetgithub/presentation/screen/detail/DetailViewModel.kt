package com.rijaldev.jetgithub.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijaldev.jetgithub.domain.model.user.User
import com.rijaldev.jetgithub.domain.usecase.user.DetailUserUseCase
import com.rijaldev.jetgithub.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUserUserUseCase: DetailUserUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getDetailUserById(id: Int) = viewModelScope.launch {
        detailUserUserUseCase.getDetailUserById(id)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect { user ->
                _uiState.value = UiState.Success(user)
            }
    }
}