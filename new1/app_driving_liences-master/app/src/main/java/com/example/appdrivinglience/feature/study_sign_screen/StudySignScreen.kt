package com.example.appdrivinglience.feature.study_sign_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import com.example.appdrivinglience.R

@Composable
fun StudySignScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        item {
            LazyRow(modifier = Modifier.fillMaxWidth()) {

            }
        }
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                )
            ) {

            }
        }
    }
}

@Composable
fun ItemSectionSign(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Biển Báo Cấm",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.bevietnam_thin)),
                fontWeight = FontWeight(500),
                color = colorResource(id = R.color.green_primary),

                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            color = colorResource(id = R.color.green_primary), modifier = Modifier
                .height(3.dp)
                .width(100.dp)
        )
    }
}

@Composable
fun ItemSign(modifier: Modifier = Modifier) {
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
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier.padding(horizontal = 10.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "P.101",

                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.vietnampro_black)),
                    fontWeight = FontWeight(400),
                    color = colorResource(id = R.color.dark_ne),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewItemSign() {
    val horizontalScrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyRow(
            modifier = Modifier
                .wrapContentSize()
                .horizontalScroll(horizontalScrollState),

            ) {
            items(6) {
                ItemSectionSign()
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
            }
            items(9) {
                ItemSign();
            }
        }

    }
}