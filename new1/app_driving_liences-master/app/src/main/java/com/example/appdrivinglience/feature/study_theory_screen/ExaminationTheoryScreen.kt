package com.example.appdrivinglience.feature.study_theory_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdrivinglience.R
import com.example.appdrivinglience.components.CommonErrorScreen
import com.example.appdrivinglience.components.CommonLoadingScreen
import com.example.appdrivinglience.database.model.Question
import com.example.appdrivinglience.database.model.QuestionWrongModel
import com.example.appdrivinglience.feature.learn_wrong.LearnWrongState
import com.example.appdrivinglience.feature.learn_wrong.LearnWrongViewModel
import com.example.appdrivinglience.feature.model.QuestionModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> ExaminationScreen(
    modifier: Modifier = Modifier,
    listQuestionModel: List<QuestionModel>,
    idCategory: Long?= null,
    viewModel: T
) {
    LaunchedEffect(key1 = true) {
        when (viewModel) {
            is LearnWrongViewModel -> {
                viewModel.getAllListQuestionWrong()
            }
            is StudyTheoryScreenViewModel-> {
                viewModel.getQuestionByIdCategory(idCategory!!)
            }
        }
    }
    when (viewModel) {
        is LearnWrongViewModel -> {
            val state = viewModel.questionListState.value
            when (state) {
                is LearnWrongState.Error -> {
                    CommonErrorScreen(error = state.error)
                }

                LearnWrongState.Loading -> {
                    CommonLoadingScreen()
                }

                is LearnWrongState.Success -> {

                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .padding(top = 90.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        if (state.dataQuestionList.isEmpty()) {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Không có dữ liệu",
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontSize = 20.sp,
                                        lineHeight = 28.sp,
                                        fontFamily = FontFamily(
                                            Font(R.font.bevietnampro_bold)
                                        ))
                                }
                            }
                        } else {
                            val answerQuestionMap = state.dataQuestionList.mapIndexed { index, question ->  index to Pair( question.correctAns, question.analysis) }.toMap()
                            item {
                                val statePager = rememberPagerState {
                                    state.dataQuestionList.size
                                }
                                var isShowAnalysis by remember {
                                    mutableStateOf(false)
                                }
                                LaunchedEffect(Unit) {
                                    statePager.interactionSource.interactions.collect{
                                        if (it is DragInteraction.Start){
                                            isShowAnalysis = false
                                        }
                                    }
                                }
                                HorizontalPager(state = statePager, modifier = Modifier.wrapContentSize()) {currentPage ->
                                    Column (
                                        modifier = Modifier.padding(bottom = 150.dp),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        ItemQuestion<LearnWrongViewModel>(questionModel = state.dataQuestionList[currentPage], modifier = Modifier.fillMaxSize(), currentPage, answerQuestionMap,viewModel){
                                            isShowAnalysis = true
                                        }

                                        if (isShowAnalysis){
                                            ShowAnalysisQuestion(data =answerQuestionMap[currentPage]?.second ?: "Chúng tôi đang cập nhật", modifier)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        is StudyTheoryScreenViewModel -> {
            val state = viewModel.questionState.value
            when (state) {
                is QuestionState.Error -> {
                    CommonErrorScreen(error = state.error)
                }

                QuestionState.Loading -> {
                    CommonLoadingScreen()
                }

                is QuestionState.Success -> {
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .padding(top = 90.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        if (state.listQuestion.isNullOrEmpty()) {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Không có dữ liệu",
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontSize = 20.sp,
                                        lineHeight = 28.sp,
                                        fontFamily = FontFamily(
                                            Font(R.font.bevietnampro_bold)
                                        ))
                                }
                            }
                        } else {

                            val answerQuestionMap = state.listQuestion.mapIndexed { index, question ->  index to Pair( question.correctAns, question.analysis) }.toMap()
                            item {
                                val statePager = rememberPagerState {
                                    state.listQuestion.size
                                }
                                var isShowAnalysis by remember {
                                    mutableStateOf(false)
                                 }
                                LaunchedEffect(Unit) {
                                    statePager.interactionSource.interactions.collect{
                                        if (it is DragInteraction.Start){
                                            isShowAnalysis = false
                                        }
                                    }
                                }
                                HorizontalPager(state = statePager, modifier = Modifier.wrapContentSize()) {currentPage ->
                                    Column (
                                        modifier = Modifier.padding(bottom = 150.dp),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        ItemQuestion<StudyTheoryScreenViewModel>(questionModel = state.listQuestion[currentPage], modifier = Modifier.fillMaxSize(),
                                            currentPage, answerQuestionMap,viewModel){
                                            isShowAnalysis = true
                                        }

                                        if (isShowAnalysis){
                                            ShowAnalysisQuestion(data =answerQuestionMap[currentPage]?.second ?: "Chúng tôi đang cập nhật", modifier)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun <T> ItemQuestion(questionModel: Question, modifier: Modifier = Modifier,
                 index: Int , dataMap: Map<Int, Pair<Int , String?>>,
                 viewModel: T , onChange : ()-> Unit) {

    var userChoiceAState by remember{
        mutableIntStateOf(-1)
    }
    var userChoiceBState by remember{
        mutableIntStateOf(-1)
    }
    var userChoiceCState by remember{
        mutableIntStateOf(-1)
    }
    var userChoiceDState by remember{
        mutableIntStateOf(-1)
    }
    var isAnswer by remember {
        mutableStateOf(false)
    }

    val answerCorrect = dataMap[index]
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = questionModel.question,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.vietnampro_black)),
                    fontWeight = FontWeight(500),
                    color = colorResource(id = R.color.dark_ne),
                )
            )
            if (questionModel.ansA?.isNotEmpty() == true) {
                OneQuestion(question = questionModel.ansA , modifier.clickable {
                    println("a")
                    if (isAnswer) return@clickable
                    isAnswer = true

                    onChange()
                    userChoiceAState = 0
                    if (userChoiceAState != answerCorrect?.first){
                        val dataQuestionModel = QuestionWrongModel(idQuestion = questionModel.id)
                        saveDataQuestionWrong(viewModel,dataQuestionModel)
                        when(answerCorrect?.first) {
                            1-> userChoiceBState = 1
                            2-> userChoiceCState = 2
                            else -> userChoiceDState = 3
                        }
                    }else {
                        when(viewModel) {
                            is LearnWrongViewModel->  viewModel.deleteDataQuestion(questionModel.id)
                        }
                    }
                }, idColor = changeBgColorQuestion(userChoiceAState, answerCorrect?.first))
            }
            if (questionModel.ansB?.isNotEmpty() == true) {
                OneQuestion(question = questionModel.ansB , modifier.clickable {
                    println("b")
                    if (isAnswer) return@clickable
                    isAnswer = true

                    onChange()

                    userChoiceBState = 1
                    if (userChoiceBState != answerCorrect?.first){
                        val dataQuestionModel = QuestionWrongModel(idQuestion = questionModel.id)
                        saveDataQuestionWrong(viewModel,dataQuestionModel)
                        when(answerCorrect?.first) {
                            0-> userChoiceAState = 0
                            2-> userChoiceCState = 2
                            else -> userChoiceDState = 3
                        }
                    }else {
                        when(viewModel) {
                            is LearnWrongViewModel->  viewModel.deleteDataQuestion(questionModel.id)
                        }
                    }

                }, idColor = changeBgColorQuestion(userChoiceBState, answerCorrect?.first))
            }
            if (questionModel.ansC?.isNotEmpty() == true) {
                OneQuestion(question = questionModel.ansC, modifier.clickable {
                    println("c")
                    if (isAnswer) return@clickable
                    isAnswer = true

                    onChange()

                    userChoiceCState = 2
                    if (userChoiceCState != answerCorrect?.first){
                        val dataQuestionModel = QuestionWrongModel(idQuestion = questionModel.id)
                        saveDataQuestionWrong(viewModel,dataQuestionModel)
                        when(answerCorrect?.first) {
                            0-> userChoiceAState = 0
                            1-> userChoiceBState = 1
                            else -> userChoiceDState = 3
                        }
                    }else {
                        when(viewModel) {
                            is LearnWrongViewModel->  viewModel.deleteDataQuestion(questionModel.id)
                        }
                    }


                }, idColor = changeBgColorQuestion(userChoiceCState, answerCorrect?.first))
            }
            if (questionModel.ansD?.isNotEmpty()== true) {
                OneQuestion(question = questionModel.ansD, modifier.clickable {
                    println("d")
                    if (isAnswer) return@clickable
                    isAnswer = true
                    onChange()

                    userChoiceDState = 3
                    if (userChoiceDState != answerCorrect?.first){
                        val dataQuestionModel = QuestionWrongModel(idQuestion = questionModel.id)
                        saveDataQuestionWrong(viewModel,dataQuestionModel)
                        when(answerCorrect?.first) {
                            0-> userChoiceAState = 0
                            1-> userChoiceBState = 1
                            else -> userChoiceCState = 2
                        }
                    }else {
                        when(viewModel) {
                            is LearnWrongViewModel->  viewModel.deleteDataQuestion(questionModel.id)
                        }
                    }
                }, idColor = changeBgColorQuestion(userChoiceDState, answerCorrect?.first))
            }
        }
    }
}

fun <T>saveDataQuestionWrong(viewModel: T, data: QuestionWrongModel){
    when(viewModel) {
        is LearnWrongViewModel -> {
            viewModel.saveDataQuestion(data)
        }
        is StudyTheoryScreenViewModel ->{
            viewModel.saveDataQuestion(data)
        }
    }
}

fun changeBgColorQuestion(data: Int, ansCorrect: Int?): Int{
    return if (data != -1){
        if (data == ansCorrect){
            R.color.green_primary
        }else {
            R.color.red
        }
    }else {
        R.color.bg_question
    }
}

@Composable
fun ShowAnalysisQuestion(data: String, modifier: Modifier = Modifier) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(text = "Giải thích đáp án", style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.bevietnampro_bold)),
                color = MaterialTheme.colorScheme.secondary,
            ))
            Text(text = data, style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.bevietnampro_regular)),
                color = MaterialTheme.colorScheme.secondary,
            ))
        }
    }
}

@Composable
fun OneQuestion(question: String?, modifier: Modifier = Modifier, idColor : Int) {
    question?.let {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = idColor),
            ),
            modifier = modifier.padding(top = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                text = question,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.bevietnampro_bold)),
                    fontWeight = FontWeight(400),
                    color = colorResource(id = R.color.dark_ne),
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewExaminationScreen() {
//    ItemQuestion(
//        QuestionModel(
//            question = "Cuộc đua xe chỉ được thực hiện khi nào? (Câu điểm liệt)",
//            ansA = "Diễn ra trên đường phố không có người qua lại.",
//            ansB = "Được người dân ủng hộ",
//            ansC = "Được cơ quan có thẩm quyền cấp phép."
//        )
//    )
}