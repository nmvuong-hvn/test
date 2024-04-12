package com.example.appdrivinglience.feature.examination_test_screen

import android.os.CountDownTimer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdrivinglience.core.FileSharePreference
import com.example.appdrivinglience.core.ReadFileUtils
import com.example.appdrivinglience.database.model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class TestExaminationViewModel @Inject constructor(
    private val checkExaminationManager: CheckExaminationManager,
    private val readFileUtils: ReadFileUtils,
    private val fileSharePreference: FileSharePreference
) : ViewModel() {

    private val _timerState = mutableStateOf("")
    val timeState : State<String> = _timerState
    private var timerCounter : CountDownTimer ?= null

    private val _isFinishedExamination = mutableStateOf(false)
    private val isFinishedExamination: State<Boolean> = _isFinishedExamination

    private val _scoreState = mutableIntStateOf(0)
    val scoreState : State<Int> = _scoreState

    private val _idQuestionState = mutableIntStateOf(0)
    val idQuestionState : State<Int> = _idQuestionState

    val listQuestions = mutableListOf<Question>()

    private val _dataExamination = mutableStateOf<ExaminationState>(ExaminationState.Loading)
    val dataExamination : State<ExaminationState> = _dataExamination

    fun getNumberExamination(): Int{
        return readFileUtils.getNumberTest(fileSharePreference.getCategoryLicense())
    }

    fun getDataExamination(folder: String , file: String ){
        viewModelScope.launch {
            listQuestions.clear()
            listQuestions.addAll(readFileUtils.getInformationTest(folder,file))
            _dataExamination.value = ExaminationState.Success(listQuestions)
        }
    }

    fun startExamination(min: Long = TimeUnit.MINUTES.toMillis(25L)){
        timerCounter?.cancel()
        timerCounter = object : CountDownTimer(min,TimeUnit.SECONDS.toMillis(1)){
            override fun onTick(millisUntilFinished: Long) {
                val data = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                _timerState.value = data
            }
            override fun onFinish() {
                _isFinishedExamination.value = true
            }
        }
        timerCounter?.start()
    }

    fun setAnswerByIdQuestion(id: Long, index : Int) {
        checkExaminationManager.setAnswerByIdQuestion(id,index)
    }
    fun getAnswerByIdQuestion(id: Long): Int{
        return checkExaminationManager.getAnswerByIdQuestion(id)
    }
    fun submit(){
        _scoreState.intValue = checkExaminationManager.checkAnswerForTest(listQuestions)
        _isFinishedExamination.value = true
        timerCounter?.start()
    }
    fun stop() {
        timerCounter?.cancel()
    }

    override fun onCleared() {
        timerCounter?.cancel()
        super.onCleared()
    }
}
sealed class ExaminationState{

    data object Loading : ExaminationState()
    data class Success(val listQuestions: List<Question>) : ExaminationState()
    data class Error(val error: String?) : ExaminationState()
}