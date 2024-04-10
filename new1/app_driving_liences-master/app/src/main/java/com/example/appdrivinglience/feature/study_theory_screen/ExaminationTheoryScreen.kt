package com.example.appdrivinglience.feature.study_theory_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appdrivinglience.R
import com.example.appdrivinglience.components.CommonErrorScreen
import com.example.appdrivinglience.components.CommonLoadingScreen
import com.example.appdrivinglience.database.model.Question
import com.example.appdrivinglience.feature.learn_wrong.LearnWrongState
import com.example.appdrivinglience.feature.learn_wrong.LearnWrongViewModel
import com.example.appdrivinglience.feature.model.QuestionModel

@Composable
fun <T> ExaminationScreen(
    modifier: Modifier = Modifier,
    listQuestionModel: List<QuestionModel>,
    viewModel: T
) {
    LaunchedEffect(key1 = true) {
        when (viewModel) {
            is LearnWrongViewModel -> {
                viewModel.getAllListQuestionWrong()
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

                is LearnWrongState.Success<*> -> {
                    val listQuestion = (state.dataQuestionList as? List<*>)
                        ?.mapNotNull { it as Question }?.toList()
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .padding(top = 90.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        if (listQuestion.isNullOrEmpty()) {
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
                            items(1) {
                                ItemQuestion(
                                    QuestionModel(
                                        question = "Cuộc đua xe chỉ được thực hiện khi nào? (Câu điểm liệt)",
                                        ansA = "Diễn ra trên đường phố không có người qua lại.",
                                        ansB = "Được người dân ủng hộ",
                                        ansC = "Được cơ quan có thẩm quyền cấp phép."
                                    )
                                )
                            }
                        }

                    }

                }
            }

        }
    }
}

@Composable
fun ItemQuestion(questionModel: QuestionModel, modifier: Modifier = Modifier) {
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
            if (questionModel.ansA.isNotEmpty()) {
                OneQuestion(question = questionModel.ansA, modifier)
            }
            if (questionModel.ansB.isNotEmpty()) {
                OneQuestion(question = questionModel.ansB, modifier)
            }
            if (questionModel.ansC.isNotEmpty()) {
                OneQuestion(question = questionModel.ansC, modifier)
            }
            if (questionModel.ansD.isNotEmpty()) {
                OneQuestion(question = questionModel.ansD, modifier)
            }
        }
    }
}

@Composable
fun OneQuestion(question: String, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.bg_question),
        ),
        modifier = modifier.padding(top = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            text = question,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.vietnampro_black)),
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.dark_ne),
            )
        )
    }
}

@Preview
@Composable
fun PreviewExaminationScreen() {
    ItemQuestion(
        QuestionModel(
            question = "Cuộc đua xe chỉ được thực hiện khi nào? (Câu điểm liệt)",
            ansA = "Diễn ra trên đường phố không có người qua lại.",
            ansB = "Được người dân ủng hộ",
            ansC = "Được cơ quan có thẩm quyền cấp phép."
        )
    )
}