package com.basicmetronome.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.basicmetronome.Greeting
import com.basicmetronome.ui.theme.BasicMetronomeTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {
    BasicMetronomeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        viewModel.bpm.toString(), fontSize = 50.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "bpm",
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledButtonExample("-", onClick = { viewModel.decrementBpm() })
                    Spacer(modifier = Modifier.width(10.dp))
                    FilledButtonExample("+", onClick = { viewModel.incrementBpm() })
                }
                Column {
                    FilledButtonExample("Play", onClick = { viewModel.playBeep() })
                    FilledButtonExample("Stop", onClick = { viewModel.stopBeep() })
                }
            }
        }
    }
}

@Composable
fun FilledButtonExample(text: String, onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text(text, fontSize = 30.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}