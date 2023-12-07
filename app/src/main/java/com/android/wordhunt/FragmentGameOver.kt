package com.android.wordhunt

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FragmentGameOver : Fragment(R.layout.fragment_gameover) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // retry button
        val retryButton : Button? = view.findViewById(R.id.retry_button)
//        val menuButton : Button? = view.findViewById(R.id.)

        retryButton?.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMenu_to_gameFragment)
        }

//        menuButton?.setOnClickListener {
//            findNavController().navigate(R.id.action_fragmentGameOver_to_fragmentMenu)
//        }
    }
}