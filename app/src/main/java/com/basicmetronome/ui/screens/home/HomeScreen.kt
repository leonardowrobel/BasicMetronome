package com.basicmetronome.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basicmetronome.ui.theme.BasicMetronomeTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val homeUiState by viewModel.uiState.collectAsState()

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
                        text = homeUiState.currentBpm.toString(),
                        fontSize = 50.sp,
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
                    FilledButtonExample("-", onClick = { viewModel.modifyBpm((-1)) })
                    Spacer(modifier = Modifier.width(10.dp))
                    FilledButtonExample("+", onClick = { viewModel.modifyBpm(1) })
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledButtonExample("-5", onClick = { viewModel.modifyBpm((-5)) })
                    Spacer(modifier = Modifier.width(10.dp))
                    FilledButtonExample("+5", onClick = { viewModel.modifyBpm((5)) })
                }
                Column {
                    if (homeUiState.isPlaying) {
                        FilledButtonExample("Stop", onClick = { viewModel.stopBeep() })
                    } else {
                        FilledButtonExample("Play", onClick = { viewModel.playBeep() })
                    }
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
//    HomeScreen()
}