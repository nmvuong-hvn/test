package com.example.appdrivinglience.feature.learn_wrong

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdrivinglience.feature.trick_screen.TrickModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnWrongViewModel @Inject constructor() : ViewModel() {
    private val _questionListState = mutableStateOf<LearnWrongState>(LearnWrongState.Loading)
    val questionListState: State<LearnWrongState> = _questionListState

    fun getAllListQuestionWrong(){
        _questionListState.value = LearnWrongState.Loading
        viewModelScope.launch {
            delay(1000)
            _questionListState.value = LearnWrongState.Success(listOf<TrickModel>())
        }
    }
}

sealed class LearnWrongState {
    data object Loading : LearnWrongState()
    data class Success<out T>(val dataQuestionList : T ) : LearnWrongState()
    data class Error(val error : String?) : LearnWrongState()
}