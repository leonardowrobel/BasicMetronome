package com.basicmetronome.ui.screens.home

//import android.util.Log
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val generator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
    val mainHandler = Handler(Looper.getMainLooper())

    var bpm by mutableIntStateOf(80)

    fun incrementBpm() {
        bpm = bpm.inc()
    }

    fun decrementBpm() {
        bpm = bpm.dec()
    }

    fun playBeep() {
        val time : Long = (60000/bpm).toLong()
        mainHandler.post(object : Runnable {
            override fun run() {
                generator.startTone(ToneGenerator.TONE_PROP_BEEP, 200)
                mainHandler.postDelayed(this, time)
            }
        })
    }

    fun stopBeep() {
        mainHandler.removeCallbacksAndMessages(null)
    }

    companion object {
        const val TAG: String = "HOME_VM"
    }
}