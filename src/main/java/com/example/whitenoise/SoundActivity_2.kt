package com.example.whitenoise

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SoundActivity_2 : AppCompatActivity() {
    private lateinit var buttons: List<Button>
    private lateinit var mediaPlayers: Map<Int, MediaPlayer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_2)

        buttons = listOf(
            findViewById(R.id.btnBird),
            findViewById(R.id.btnCat),
            findViewById(R.id.btnFrog),
            findViewById(R.id.btnInsect),
            findViewById(R.id.btnOwl),
            findViewById(R.id.btnWolf)
        )

        mediaPlayers = mapOf(
            R.id.btnBird to MediaPlayer.create(this, R.raw.birds).apply { isLooping = true },
            R.id.btnCat to MediaPlayer.create(this, R.raw.cat).apply { isLooping = true },
            R.id.btnFrog to MediaPlayer.create(this, R.raw.frog).apply { isLooping = true },
            R.id.btnInsect to MediaPlayer.create(this, R.raw.insects).apply { isLooping = true },
            R.id.btnOwl to MediaPlayer.create(this, R.raw.owl).apply { isLooping = true },
            R.id.btnWolf to MediaPlayer.create(this, R.raw.wolf).apply { isLooping = true }
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
