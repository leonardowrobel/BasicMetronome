package com.basicmetronome.ui.screens.home

data class HomeState(
    val currentBpm: Int = 80,
    val isPlaying: Boolean = false
)