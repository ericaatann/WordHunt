package com.android.wordhunt

import android.content.Context
import android.widget.Button

class ButtonWrapper(button: Button?) {
    private val button: Button? = button
    private var pressed = false

    fun getButton() : Button? {
        return button
    }

    fun setPressed(boolean: Boolean) {
        pressed = boolean
    }

    fun wasPressed() : Boolean {
        return pressed
    }
}