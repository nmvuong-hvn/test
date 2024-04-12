package com.example.appdrivinglience.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.appdrivinglience.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignDialog(modifier: Modifier = Modifier) {

    ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = rememberAsyncImagePainter("https://media.istockphoto.com/id/486744506/vi/vec-to/d%E1%BB%ABng-k%C3%BD-vector.jpg?s=1024x1024&w=is&k=20&c=AXQAr_VZ5ZBhgFmQxeO4jDOapGcnQ9ChbzLtRW3YWuA="),
                contentDescription = null
            )
            Text(text = "P.101", style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.bevietnampro_bold)),
                color = MaterialTheme.colorScheme.secondary,
            ))
            Text(text = "Biển báo đường cấm tất cả các" +
                    " loại phương tiện tham gia giao thông" +
                    " đi lại cả hai hướng, trừ xe ưu tiên " +
                    "theo luật định.", style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.bevietnampro_bold)),
                color = MaterialTheme.colorScheme.secondary,
            ))
        }
    }

}

@Preview
@Composable
fun PreviewSign(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SignDialog()
    }
}