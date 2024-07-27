package com.example.whitenoise

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SoundActivity_1 : AppCompatActivity() {
    private lateinit var buttons: List<Button>
    private lateinit var mediaPlayers: Map<Int, MediaPlayer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_1)

        buttons = listOf(
            findViewById(R.id.btnWaves),
            findViewById(R.id.btnRain),
            findViewById(R.id.btnForest),
            findViewById(R.id.btnStream),
            findViewById(R.id.btnThunder),
            findViewById(R.id.btnWind)
        )

        mediaPlayers = mapOf(
            R.id.btnWaves to MediaPlayer.create(this, R.raw.waves).apply { isLooping = true },
            R.id.btnRain to MediaPlayer.create(this, R.raw.rain).apply { isLooping = true },
            R.id.btnForest to MediaPlayer.create(this, R.raw.forest).apply { isLooping = true },
            R.id.btnStream to MediaPlayer.create(this, R.raw.stream).apply { isLooping = true },
            R.id.btnThunder to MediaPlayer.create(this, R.raw.thunder).apply { isLooping = true },
            R.id.btnWind to MediaPlayer.create(this, R.raw.wind).apply { isLooping = true }
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                toggleButtonSelection(button)
                toggleSound(button.id)
            }
        }

        findViewById<Button>(R.id.btnStop).setOnClickListener {
            stopAllSounds()
            deselectAllButtons()
        }
    }

    private fun toggleButtonSelection(clickedButton: Button) {
        clickedButton.isSelected = !clickedButton.isSelected
    }

    private fun deselectAllButtons() {
        buttons.forEach { button ->
            button.isSelected = false
        }
    }

    private fun toggleSound(buttonId: Int) {
        mediaPlayers[buttonId]?.let { mediaPlayer ->
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            } else {
                mediaPlayer.start()
            }
        }
    }

    private fun stopAllSounds() {
        mediaPlayers.values.forEach { mediaPlayer ->
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                mediaPlayer.seekTo(0)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayers.values.forEach { it.release() }
    }
}
