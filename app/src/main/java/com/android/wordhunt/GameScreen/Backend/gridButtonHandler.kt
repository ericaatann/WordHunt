package com.android.wordhunt.GameScreen.Backend

import android.view.View

class gridButtonHandler {
    private val letterArr = gridInitializer.get()
    private var currentString = ""

    fun getString(): String {
        return currentString
    }

    fun clearString() {
        currentString = ""
    }

    fun onClickListener(row: Int, col: Int): View.OnClickListener {
        // Return the onClick function
        return View.OnClickListener {
            if (letterArr != null) {
                currentString = currentString.plus(letterArr[row][col])
            }
            print(currentString)
        }
    }

}