package com.android.wordhunt

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView

class FragmentStats : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Display highest score
        val highestScoreTextView: TextView = view.findViewById(R.id.highestScoreTextView)
        val highestScore = getSharedPreferences().getInt("highest_score", 0)
        highestScoreTextView.text = getString(R.string.score_format, highestScore)

        // Display longest word
        val longestWordTextView: TextView = view.findViewById(R.id.longestWordTextView)
        val longestWord = getSharedPreferences().getString("longest_word", "") ?: ""
        longestWordTextView.text = longestWord
    }

    private fun getSharedPreferences(): SharedPreferences {
        return requireActivity().getPreferences(Context.MODE_PRIVATE)
    }
}
