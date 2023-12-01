package com.android.wordhunt

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    private var score = 0
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeGame()
        startGame()
    }

    private fun initializeGame() {
        // Set up UI elements
        val submitButton: Button = view?.findViewById(R.id.submitButton) ?: return
        val timerTextView: TextView = view?.findViewById(R.id.timerTextView) ?: return

        submitButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun startGame() {
        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateTimerText(millisUntilFinished)
            }

            override fun onFinish() {
                gameOver()
            }
        }.start()
    }

    private fun checkAnswer() {
        // Implement your answer checking logic
    }

    private fun updateTimerText(millisUntilFinished: Long) {
        val seconds = millisUntilFinished / 1000
        timerTextView.text = seconds.toString()
    }

    private fun gameOver() {
        // Implement game over logic
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }
}
