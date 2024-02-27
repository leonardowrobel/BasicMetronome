package com.basicmetronome.service

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.basicmetronome.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SoundService @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private var soundPool: SoundPool
    private val beeps: IntArray = IntArray(3)

    init{
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(
                AudioAttributes.USAGE_ASSISTANCE_SONIFICATION
            )
            .setContentType(
                AudioAttributes.CONTENT_TYPE_SONIFICATION
            )
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(3)
            .setAudioAttributes(
                audioAttributes
            )
            .build()

        beeps[0] = soundPool.load(context, R.raw.beep_a, 1)
        beeps[1] = soundPool.load(context, R.raw.beep_b, 1)
        beeps[2] = soundPool.load(context, R.raw.mouse_click, 1)
    }

    fun playSound(beep: Int) {
        when (beep) {
            0 -> {
                soundPool.play(
                    beeps[0], 1f, 1f, 0, 0, 1f
                )
                soundPool.autoPause()
            }

            1 -> {
                soundPool.play(
                    beeps[1], 1f, 1f, 0, 0, 1f
                )
                soundPool.autoPause()
            }

            2 -> {
                soundPool.play(
                    beeps[2], 1f, 1f, 0, 0, 1f
                )
                soundPool.autoPause()
            }
        }
    }

}