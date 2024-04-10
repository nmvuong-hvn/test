package com.example.appdrivinglience.feature.trick_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun TrickScreen(modifier: Modifier = Modifier){
    Spacer(modifier = Modifier.height(60.dp))
    ItemTrick(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp));

}


@Composable
fun ItemTrick(modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
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
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Cấp phép",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.vietnampro_black)),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    letterSpacing = 0.02.sp,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "- Đường cấm dừng, cấm đỗ, cấm đi do UBND cấp tỉnh cấp\n-Xe quá khổ, quá tải do: cơ quan quản lý đường bộ có thẩm quyền cấp phép",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.bevietnampro_regular)),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    letterSpacing = 0.03.sp,
                )
            )
        }
    }
}

