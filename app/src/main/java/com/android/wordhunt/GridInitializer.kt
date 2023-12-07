package com.android.wordhunt

class GridInitializer {
    companion object {
        private var letterArr: Array<Array<Char>>? = null

        fun get(): Array<Array<Char>>? {
            if (letterArr == null) {
                initializeGrid()
            }
            return letterArr
        }

        fun set(letterArr: Array<Array<Char>>) {
            Companion.letterArr = letterArr
        }

        private fun initializeGrid() {
            val letterArr: Array<Array<Char>> = Array(4) { Array(4) { ' ' } }

            for (i in 0..3) {
                for (j in 0..3) {
                    letterArr[i][j] = ('A'..'Z').random()
                }
            }

            set(letterArr)
        }
    }


}
