package com.android.wordhunt

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentGame : Fragment() {

    private var score = 0
    private var timer: CountDownTimer? = null
    private var string: String = ""

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

//        val timerTextView: TextButton? = view?.findViewById(R.id.timerTextView) ?: return

        val grid = GridInitializer.get()
        val buttonMap = HashMap<Button?, ButtonWrapper>()

        for (i in 0 until grid!!.size) {
            for (j in 0 until grid[i].size) {
                val buttonId = resources.getIdentifier("button$i$j", "id", requireActivity().packageName)
                val button: Button? = view?.findViewById(buttonId)
                buttonMap[button] = ButtonWrapper(button)
                button?.text = grid[i][j].toString()
                button?.setOnClickListener {
                    if (buttonMap[button]?.wasPressed() == false) {
                        // need to add text to total string
                        buttonMap[button]?.setPressed(true)
                        string += button.text
                    }
                    else {
                        // create toast saying button was already used
                        val toast = Toast.makeText(requireContext(), "Same button cannot be reused in string", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
            }
        }

        val submitButton: Button? = view?.findViewById(R.id.submit_button)
        submitButton?.setOnClickListener {
            // reset all pressed buttons
            for (buttonWrapper in buttonMap.values) {
                buttonWrapper.setPressed(false)
            }
            // check to see if it is a valid value
            // reset string
            string = ""
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
//        timerTextView.text = seconds.toString()
    }

    private fun gameOver() {
        // Implement game over logic
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }
}
