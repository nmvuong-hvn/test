package com.example.appdrivinglience.feature.examination_test_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appdrivinglience.R

@Composable
fun TestScreen(
    testExaminationViewModel: TestExaminationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier) {
    println("Size = ${testExaminationViewModel.getNumberExamination()}")
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(top = 90.dp, bottom = 100.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        items(testExaminationViewModel.getNumberExamination()){
            ItemTest(index = it, testExaminationViewModel = testExaminationViewModel)
        }
    }
}

@Composable
fun ItemTest(index: Int ,modifier: Modifier = Modifier, testExaminationViewModel: TestExaminationViewModel) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.red),
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier
                    .wrapContentSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top

            ) {
                Text(
                    text = "Đề số ${index+1}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.bevietnampro_bold)),
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.dark_ne),
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = "${testExaminationViewModel.listQuestions.size} câu / 19 phút",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.bevietnampro_regular)),
                        fontWeight = FontWeight(400),
                        color = colorResource(id = R.color.neutra_2),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Image(painter = painterResource(id = R.drawable.activity), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun PreviewItemTest() {
//    ItemTest()
}