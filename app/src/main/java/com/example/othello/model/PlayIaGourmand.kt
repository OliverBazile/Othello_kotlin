package com.example.othello.model


class PlayIaGourmand(var arrays: Array<Int>, var scoreWhite:Int, var scoreBlack:Int, var possibilityWhite:Boolean, var possibilityBlack: Boolean, var play:Boolean) {

    var strategy = Array(8) { Array(8) { 0 } }
    init {
        scoreBlack = 0
        scoreWhite = 0
        possibilityBlack = false
        possibilityWhite = false
        play = false
    }
    constructor(arrays: Array<Int>) :this(arrays,2,2,false,false,false)
    fun checkPossibilityToPlay(arrays: Array<Int>): PlayIaGourmand {
        var chooseEat = -1
        var playIa = PlayIaGourmand(arrays)
        playIa.possibilityWhite = false
        playIa.possibilityBlack = false
        for (i in arrays.indices) {
            if (arrays[i] == 1) {
                playIa.scoreWhite++
            }
            if (arrays[i] == 2) {
                playIa.scoreBlack++
            }
            chooseEat = strategyGourmand(arrays,i,2,1)

        }
        if(chooseEat != -1){
            checkCase(chooseEat,arrays,2)
            playIa.play = true
        }
        return playIa
    }

    private fun checkHorizontalPlus(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Int {
        if (arrays[position] == 0 && position != 7 && position < 62 && arrays[position + 1] == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i < 64 && (position + i) % 8 != 0) {
                if (arrays[position + i] == hitColor) {
                    ptColor += 1
                    strategy[0][i] = position + i
                } else if (arrays[position + i] == color) {

                    return ptColor
                }
                i++
            }
        }
        return 0
    }

    private fun checkHorizontalSub(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Int {
        if (arrays[position] == 0 && position - 1 >= 0 && arrays[position - 1] == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i >= 0 && (position - i) % 8 != 7) {
                if (arrays[position - i] == hitColor) {
                    ptColor += 1
                    strategy[1][i] = position - i
                } else if (arrays[position - i] == color) return ptColor
                i++
            }
        }
        return 0
    }


    private fun checkVerticalSub(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Int {
        if (arrays[position] == 0 && position - 8 >= 0 && arrays[position - 8] == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i * 8 >= 0) {
                if (arrays[position - i * 8] == hitColor) {
                    ptColor += 1
                    strategy[2][i] = position - i * 8
                } else if (arrays[position - i * 8] == color) {
                    return ptColor
                }
                i++
            }
        }
        return 0
    }

    private fun checkVerticalPlus(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Int {
        if (arrays[position] == 0 && position + 8 < 64 && arrays[position + 8] == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i * 8 < 64) {
                if (arrays[position + i * 8] == hitColor) {
                    ptColor += 1
                    strategy[3][i] = position + i * 8
                } else if (arrays[position + i * 8] == color) return ptColor
                i++
            }
        }
        return 0
    }

    private fun checkDiagonalLeftPlus(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Int {
        if (arrays[position] == 0 && position % 8 != 0 && position + 7 < 64 && arrays[position + 7] == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i * 7 < 64 && (position + i * 7) % 8 != 7) {
                if (arrays[position + i * 7] == hitColor) {
                    ptColor += 1
                    strategy[4][i] = position + i * 7
                } else if (arrays[position + i * 7] == color) return ptColor
                i++
            }
        }
        return 0
    }

    private fun checkDiagonalLeftSub(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Int {
        if (arrays[position] == 0 && position % 8 != 0 && position - 9 > 0 && arrays[position - 9] == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i * 9 >= 0 && (position - i * 9) % 8 != 7) {
                if (arrays[position - i * 9] == hitColor) {
                    ptColor += 1
                    strategy[5][i] = position - i * 9
                } else if (arrays[position - i * 9] == color) return ptColor
                i++
            }
        }
        return 0
    }

    private fun checkDiagonalRightSub(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Int {
        if (arrays[position] == 0 && position % 8 != 7 && position - 7 > 0 && arrays[position - 7] == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i * 7 >= 0 && (position - i * 7) % 8 != 0) {
                if (arrays[position - i * 7] == hitColor) {
                    ptColor += 1
                    strategy[6][i] = position - i * 7
                } else if (arrays[position - i * 7] == color) return ptColor
                i++
            }
        }
        return 0
    }

    private fun checkDiagonalRightPlus(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Int {
        if (arrays[position] == 0 && position % 8 != 7 && position + 9 < 64 && arrays[position + 9] == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i * 9 < 64 && (position + i * 9) % 8 != 0) {
                if (arrays[position + i * 9] == hitColor) {
                    ptColor += 1
                    strategy[7][i] = position + i * 9
                } else if (arrays[position + i * 9] == color) return ptColor
                i++
            }
        }
        return 0
    }

    private fun eatCaseListDiagonalLeftPlus(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        eatCasePiece: Int
    ) {
        if (eatCasePiece != 0) {
            for (i in 0..eatCasePiece) {
                arrays[(position + i * 7)] = color
            }
        }
    }

    private fun eatCaseListDiagonalLeftSub(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        eatCasePiece: Int
    ) {
        if (eatCasePiece != 0) {
            for (i in 0..eatCasePiece) {
                arrays[(position - i * 9)] = color
            }
        }
    }

    private fun eatCaseListDiagonalRightSub(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        eatCasePiece: Int
    ) {
        if (eatCasePiece != 0) {
            for (i in 0..eatCasePiece) {
                arrays[(position - i * 7)] = color
            }
        }
    }

    private fun eatCaseListDiagonalRightPlus(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        eatCasePiece: Int
    ) {
        if (eatCasePiece != 0) {
            for (i in 0..eatCasePiece) {
                arrays[(position + i * 9)] = color
            }
        }
    }

    private fun eatCaseListVerticalPlus(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        eatCasePiece: Int
    ) {
        if (eatCasePiece > 0) {
            for (i in 0..eatCasePiece) {
                arrays[position + i * 8] = color
            }
        }
    }

    private fun eatCaseListVerticalSub(
        arrays: Array<Int>, position: Int,
        color: Int, eatCasePiece: Int
    ) {
        if (eatCasePiece > 0) {
            for (i in 0..eatCasePiece) {
                arrays[position - i * 8] = color
            }
        }
    }

    private fun eatCaseListHorizontalSub(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        eatCasePiece: Int
    ) {
        if (eatCasePiece > 0) {
            for (i in 0..eatCasePiece) {
                arrays[position - i] = color
            }
        }
    }

    private fun eatCaseListHorizontalPlus(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        eatCasePiece: Int
    ) {
        if (eatCasePiece > 0) {
            for (i in 0..eatCasePiece) {
                arrays[position + i] = color
            }
        }
    }

    private fun checkCase(gourmand: Int, arrays: Array<Int>, color: Int) {
        when (gourmand) {
            0 -> eatCaseListHorizontalPlus(arrays, strategy[0][0], color, strategy[0].size)
            1 -> eatCaseListHorizontalSub(arrays, strategy[1][0], color, strategy[1].size)
            2 -> eatCaseListVerticalPlus(arrays, strategy[2][0], color, strategy[2].size)
            3 -> eatCaseListVerticalSub(arrays, strategy[3][0], color, strategy[3].size)
            4 -> eatCaseListDiagonalLeftPlus(arrays, strategy[4][0], color, strategy[4].size)
            5 -> eatCaseListDiagonalLeftSub(arrays, strategy[5][0], color, strategy[5].size)
            6 -> eatCaseListDiagonalRightPlus(arrays, strategy[6][0], color, strategy[6].size)
            7 -> eatCaseListDiagonalRightSub(arrays, strategy[6][0], color, strategy[6].size)
        }
    }


    fun strategyGourmand(
        arrays: Array<Int>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Int {
        var eatCase = -1
        var strategyGourmand: Int? = -1
        var scoreStrategy = -1
        strategyGourmand = checkHorizontalSub(arrays, position, color, hitColor)
        if (strategyGourmand >= eatCase) {
            eatCase = strategyGourmand
            scoreStrategy = 0
        }
        strategyGourmand = checkHorizontalPlus(arrays, position, color, hitColor)
        if (strategyGourmand >= eatCase) {
            eatCase = strategyGourmand
            scoreStrategy = 1
        }
        strategyGourmand = checkVerticalPlus(arrays, position, color, hitColor)
        if (strategyGourmand >= eatCase) {
            eatCase = strategyGourmand
            scoreStrategy = 2
        }
        strategyGourmand = checkVerticalSub(arrays, position, color, hitColor)
        if (strategyGourmand >= eatCase) {
            eatCase = strategyGourmand
            scoreStrategy = 3
        }
        //todo Diagonal
        strategyGourmand = checkDiagonalLeftPlus(arrays, position, color, hitColor)
        if (strategyGourmand >= eatCase) {
            eatCase = strategyGourmand
            scoreStrategy = 4
        }
        strategyGourmand = checkDiagonalLeftSub(arrays, position, color, hitColor)
        if (strategyGourmand >= eatCase) {
            eatCase = strategyGourmand
            scoreStrategy = 5
        }
        strategyGourmand = checkDiagonalRightPlus(arrays, position, color, hitColor)
        if (strategyGourmand >= eatCase) {
            eatCase = strategyGourmand
            scoreStrategy = 6
        }
        strategyGourmand = checkDiagonalRightSub(arrays, position, color, hitColor)
        if (strategyGourmand > eatCase) {
            eatCase = strategyGourmand
            scoreStrategy = 7
        }
        return scoreStrategy
    }

}


