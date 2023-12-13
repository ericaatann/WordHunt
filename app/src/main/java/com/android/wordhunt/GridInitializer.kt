package com.android.wordhunt

import kotlin.random.Random

class GridInitializer {
    companion object {
        fun initializeGrid() : Array<Array<Char>> {
            val letterArr: Array<Array<Char>> = Array(4) { Array(4) { ' ' } }
            var vowelCount = 0
            val vowels = listOf('A', 'E', 'I', 'O', 'U')
            val consonants = listOf('B', 'C', 'D', 'F', 'G', 'H',
                'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z')

            for (i in 0..3) {
                for (j in 0..3) {
                    val randomVal = Random.nextInt(2);
                    if (randomVal == 0 && vowelCount < 8) {
                        letterArr[i][j] = vowels[Random.nextInt(vowels.size)]
                        vowelCount++
                    }
                    else {
                        letterArr[i][j] = consonants[Random.nextInt(consonants.size)]
                    }

                }
            }

            return letterArr
        }
    }


}
