package com.example.a7workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.a7workoutapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private var binding: ActivitySecondBinding? = null
    private var restProgress = 0
    private var timer: CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.toolbarExercise?.setNavigationOnClickListener { onBackPressed() }

        setProgressBar()
    }

    private fun setProgressBar(){

        if (timer != null) {
            timer!!.cancel()
            restProgress = 0
        }

        binding?.progressBar?.progress = restProgress

        timer = object : CountDownTimer(10000,1000 ){

            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@SecondActivity,
                    "Here now we will start the exercise.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }.start()

    }

    public override fun onDestroy() {
        if (timer != null) {
            timer?.cancel()
            restProgress = 0
        }
        super.onDestroy()
        binding = null
    }
}