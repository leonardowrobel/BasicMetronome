package com.basicmetronome.ui.screens.home

//import android.util.Log
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.media.ToneGenerator
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.basicmetronome.R
import com.basicmetronome.service.SoundService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val soundService: SoundService) : ViewModel() {

    private val generator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
//    private val _beeps = beeps
//    @Inject
//    lateinit var soundService: SoundService

    val mainHandler = Handler(Looper.getMainLooper())

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    fun modifyBpm(difference: Int) {
        val newBpm = _uiState.value.currentBpm + difference
        if (newBpmIsValid(newBpm)) {
            _uiState.update { currentState -> currentState.copy(currentBpm = newBpm) }
            stopBeep()
            playBeep()
        }
    }

    private fun newBpmIsValid(newBpm: Int): Boolean {
        return !(newBpm > 200 || newBpm < 40)
    }

    fun playBeep() {
        _uiState.update { currentState -> currentState.copy(isPlaying = true) }
        val time: Long = (60000 / uiState.value.currentBpm).toLong()
        mainHandler.post(object : Runnable {
            override fun run() {
//                generator.startTone(ToneGenerator.TONE_PROP_BEEP, 200)
                soundService.playSound(0)
                mainHandler.postDelayed(this, time)
            }
        })
    }

    fun stopBeep() {
        _uiState.update { currentState -> currentState.copy(isPlaying = false) }
        mainHandler.removeCallbacksAndMessages(null)
    }

    companion object {
        const val TAG: String = "HOME_VM"
    }
}