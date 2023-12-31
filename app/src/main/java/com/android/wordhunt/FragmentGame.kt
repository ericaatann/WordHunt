package com.android.wordhunt

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentGame : Fragment() {
    private var score = 0
    private var timer: CountDownTimer? = null
    private var string: String = ""
    private var longestString: String = ""
    private lateinit var scoreTextView: TextView
    private val usedWords = mutableSetOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeGame()

        // Find and initialize the score TextView
        scoreTextView = view.findViewById(R.id.scoreTextView)
        updateScoreText()

        startGame()
    }

    private fun updateScoreText() {
        scoreTextView.text = getString(R.string.score_format, score)
    }

    private fun checkAnswer(string : String) {
        // Check if the word is already used
        if (usedWords.contains(string.lowercase())) {
            // toast that the word is a duplicate
            val toast = Toast.makeText(requireContext(), "Word has already been used", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        // If the answer is correct, increment and update the score
        val wordLength = string.length
        val wordScore = when (wordLength) {
            3 -> 100
            4 -> 400
            5 -> 800
            6 -> 1400
            7 -> 1800
            8 -> 2200
            else -> 0 // if the word is incorrect
        }

        // Add score to the total score
        score += wordScore

        // Update score
        updateScoreText()

        // Add the word to the used words set
        usedWords.add(string.lowercase())
    }

    private fun initializeGame() {
        // Set up UI elements

//        val timerTextView: TextButton? = view?.findViewById(R.id.timerTextView) ?: return

        val grid = GridInitializer.initializeGrid()
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
                        val word : TextView? = view?.findViewById(R.id.word_view)
                        word?.text = string
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

            var stringcopy = string
            val wordApi = RetrofitHelper.getInstance().create(WordApi::class.java)
            // launching a new coroutine
            GlobalScope.launch(Dispatchers.IO) {
                val result = wordApi.getWord(stringcopy)
                withContext(Dispatchers.Main) {
                    if (result.body().toString() == "null") {
                        Log.d("user: ", "Not a valid word")
                        // Update your UI, perhaps show an error message
                    } else {
                        var string = result.body().toString()
                        string = parseString(string)
                        Log.d("user: ", string)
                        checkAnswer(string)
                        if (string.length > longestString.length) {
                            longestString = string
                        }
                        // Update your UI with the valid word, e.g., checkAnswer(string)
                    }
                }
            }
            // reset string
            string = ""
            val word : TextView? = view?.findViewById(R.id.word_view)
            word?.text = string
//            lifecycleScope.launch { ApiFunctions.checkWord(string) }
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

    private fun updateTimerText(millisUntilFinished: Long) {
        val seconds = millisUntilFinished / 1000
        val timerTextView: TextView? = view?.findViewById(R.id.timerTextView)
        timerTextView?.text = seconds.toString()
    }

    private fun gameOver() {
        // Implement game over logic
        saveGameStats()
        findNavController().navigate(R.id.action_fragmentGame_to_fragmentGameOver)
    }

    private fun saveGameStats() {
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save highest score
        val highestScore = sharedPreferences.getInt("highest_score", 0)
        if (score > highestScore) {
            editor.putInt("highest_score", score)
        }

        // Save longest word
        val longestWord = sharedPreferences.getString("longest_word", "") ?: ""
        if (longestString.length > longestWord.length || longestString.length == longestWord.length && longestString > longestWord) {
            editor.putString("longest_word", longestString)
        }

        // Apply changes
        editor.apply()
    }


    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }

    private fun parseString(string: String) : String {
        val charArray = string.toCharArray()
        val newArray : Array<Char?> = arrayOfNulls(charArray.size-14)
        for (i in 14 until charArray.size-1) {
            print(charArray[i])
            newArray[i-14] = charArray[i]
        }
        return String(newArray.filterNotNull().toCharArray())
    }
}
