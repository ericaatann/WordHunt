package com.android.wordhunt

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FragmentMenu : Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playButton: Button = view.findViewById(R.id.playButton)

        playButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMenu_to_fragmentGame)
        }
    }
}