package com.example.othello.model

import android.annotation.SuppressLint


class PlayGamerToGamer() {
    private var eatCasePiece = 0

    fun isOverGamer(arrays: Array<Double>): PieceScore {
        val pieceScore = PieceScore()
        for (i in arrays.indices) {
            if (arrays[i].toInt() == 1) {
                pieceScore.scoreWhite++
            }
            if (arrays[i].toInt() == 2) {
                pieceScore.scoreBlack++
            }
            if (isEndGame(arrays, i, 1, 2)) {
                pieceScore.possibilityWhite = true
            }
            if (isEndGame(arrays, i, 2, 1)) {
                pieceScore.possibilityBlack = true
            }
        }
        return pieceScore
    }

    private fun checkHorizontalPlus(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Boolean {
        if (arrays[position].toInt() == 0 && position != 7
            && position < 62
            && arrays[position + 1].toInt() == hitColor
        ) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i < 64 && (position + i) % 8 != 0) {
                when {
                    arrays[position + i].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position + i].toInt() == color && ptColor != 0 -> {
                        return true
                    }
                    else -> {
                        return false
                    }
                }
                i++
            }
        }
        return false
    }

    private fun checkHorizontalSub(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Boolean {
        if (arrays[position].toInt() == 0
            && position - 1 >= 0
            && arrays[position - 1].toInt() == hitColor
        ) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i > 0 && (position - i) % 8 != 7) {
                when {
                    arrays[position - i].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position - i].toInt() == color && ptColor != 0 -> {
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }


    private fun checkVerticalSub(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Boolean {
        if (arrays[position].toInt() == 0
            && position - 8 >= 0
            && arrays[position - 8].toInt() == hitColor
        ) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i * 8 >= 0) {
                when {
                    arrays[position - i * 8].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position - i * 8].toInt() == color && ptColor != 0 -> {
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun checkVerticalPlus(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Boolean {
        if (arrays[position].toInt() == 0
            && position + 8 < 64
            && arrays[position + 8].toInt() == hitColor
        ) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i * 8 < 64) {
                when {
                    arrays[position + i * 8].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position + i * 8].toInt() == color && ptColor != 0 -> {
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun checkDiagonalLeftPlus(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Boolean {
        if (arrays[position].toInt() == 0
            && position % 8 != 0
            && position + 7 < 64
            && arrays[position + 7].toInt() == hitColor
        ) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i * 7 < 64 && (position + i * 7) % 8 != 7) {
                when {
                    arrays[position + i * 7].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position + i * 7].toInt() == color && ptColor != 0 -> {
                        return true
                    }
                    else -> {
                        return false
                    }

                }
                i++
            }
        }
        return false
    }

    private fun checkDiagonalLeftSub(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Boolean {
        if (arrays[position].toInt() == 0
            && position % 8 != 0
            && position - 9 > 0
            && arrays[position - 9].toInt() == hitColor
        ) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i * 9 >= 0 && (position - i * 9) % 8 != 7) {
                when {
                    arrays[position - i * 9].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position - i * 9].toInt() == color && ptColor != 0 -> {
                        return true
                    }
                    else -> {
                        return false
                    }
                }
                i++
            }
        }
        return false
    }

    @SuppressLint("LongLogTag")
    private fun checkDiagonalRightSub(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Boolean {
        if (arrays[position].toInt() == 0
            && position % 8 != 7
            && position - 7 > 0
            && arrays[position - 7].toInt() == hitColor
        ) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i * 7 >= 0 && (position - i * 7) % 8 != 0) {
                when {
                    arrays[position - i * 7].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position - i * 7].toInt() == color && ptColor != 0 -> {
                        return true
                    }
                    else -> {
                        return false
                    }
                }

                i++
            }
        }
        return false
    }

    private fun checkDiagonalRightPlus(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Boolean {
        if (arrays[position].toInt() == 0
            && position % 8 != 7
            && position + 9 < 64
            && arrays[position + 9].toInt() == hitColor
        ) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i * 9 < 64 && (position + i * 9) % 8 != 0) {
                when {
                    arrays[position + i * 9].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position + i * 9].toInt() == color && ptColor != 0 -> {
                        return true
                    }
                    else -> {
                        return false
                    }
                }
                i++
            }
        }

        return false
    }

    private fun isEndGame(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int
    ): Boolean {
        var eatCase = false
        if (checkHorizontalSub(arrays, position, color, hitColor)) eatCase = true
        if (checkHorizontalPlus(arrays, position, color, hitColor)) eatCase = true

        if (checkVerticalPlus(arrays, position, color, hitColor)) eatCase = true
        if (checkVerticalSub(arrays, position, color, hitColor)) eatCase = true

        if (checkDiagonalLeftPlus(arrays, position, color, hitColor)) eatCase = true
        if (checkDiagonalLeftSub(arrays, position, color, hitColor)) eatCase = true
        if (checkDiagonalRightPlus(arrays, position, color, hitColor)) eatCase = true
        if (checkDiagonalRightSub(arrays, position, color, hitColor)) eatCase = true

        return eatCase
    }

    private fun attackHorizontalPlus(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int,
        animation: Double
    ): Boolean {
        if (position != 7 && position < 62 && arrays[(position + 1)].toInt() == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i < 64 && (position + i) % 8 != 0) {
                when {
                    arrays[(position + i)].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[(position + i)].toInt() == color -> {
                        eatCasePiece = ptColor
                        eatCaseListHorizontalPlus(arrays, position, animation)
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun attackHorizontalSub(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int,
        animation: Double
    ): Boolean {
        if (position > 1 && arrays[(position - 1)].toInt() == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i >= 0 && (position - i) % 8 != 7) {
                when {
                    arrays[(position - i)].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[(position - i)].toInt() == color -> {
                        eatCasePiece = ptColor
                        eatCaseListHorizontalSub(arrays, position, animation)
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun attackVerticalSub(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int,
        animation: Double
    ): Boolean {
        if (position - 8 >= 0 && arrays[(position - 8)].toInt() == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i * 8 >= 0) {
                when {
                    arrays[(position - i * 8)].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[(position - i * 8)].toInt() == color -> {
                        eatCasePiece = ptColor
                        eatCaseListVerticalSub(arrays, position, animation)
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun attackVerticalPlus(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int,
        animation: Double
    ): Boolean {
        if (position + 8 < 64 && arrays[(position + 8)].toInt() == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i * 8 < 64) {
                when {
                    arrays[(position + i * 8)].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[(position + i * 8)].toInt() == color -> {
                        eatCasePiece = ptColor
                        eatCaseListVerticalPlus(arrays, position, animation)
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun attackDiagonalLeftPlus(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int,
        animation: Double
    ): Boolean {
        if (position % 8 != 0 && position + 7 < 64 && arrays[(position + 7)].toInt() == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i * 7 < 64 && (position + i * 7) % 8 != 7) {
                when {
                    arrays[position + i * 7].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position + i * 7].toInt() == color -> {
                        eatCasePiece = ptColor
                        eatCaseListDiagonalLeftPlus(arrays, position, animation)
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun attackDiagonalLeftSub(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int,
        animation: Double
    ): Boolean {
        if (position % 8 != 0 && position - 9 > 0 && arrays[position - 9].toInt() == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i * 9 >= 0 && (position - i * 9) % 8 != 7) {
                when {
                    arrays[position - i * 9].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position - i * 9].toInt() == color -> {
                        eatCasePiece = ptColor
                        eatCaseListDiagonalLeftSub(arrays, position, animation)
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun attackDiagonalRightSub(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int,
        animation: Double
    ): Boolean {
        if (position % 8 != 7 && position - 7 > 0 && arrays[(position - 7)].toInt() == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position - i * 7 >= 0 && (position - i * 7) % 8 != 0) {
                when {
                    arrays[(position - i * 7)].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[(position - i * 7)].toInt() == color -> {
                        eatCasePiece = ptColor
                        eatCaseListDiagonalRightSub(arrays, position, animation)
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun attackDiagonalRightPlus(
        arrays: Array<Double>,
        position: Int,
        color: Int,
        hitColor: Int,
        animation: Double
    ): Boolean {
        if (position % 8 != 7 && position + 9 < 64 && arrays[(position + 9)].toInt() == hitColor) {
            var ptColor = 0
            var i = 1
            while (i < 8 && position + i * 9 < 64 && (position + i * 9) % 8 != 0) {
                when {
                    arrays[position + i * 9].toInt() == hitColor -> {
                        ptColor += 1
                    }
                    arrays[position + i * 9].toInt() == color -> {
                        eatCasePiece = ptColor
                        eatCaseListDiagonalRightPlus(arrays, position, animation)
                        return true
                    }
                    else -> return false
                }
                i++
            }
        }
        return false
    }

    private fun eatCaseListDiagonalLeftPlus(
        arrays: Array<Double>,
        position: Int,
        animation: Double
    ) {
        if (eatCasePiece != 0) {
            for (i in 0..eatCasePiece) {
                arrays[(position + i * 7)] = animation
            }
        }
    }

    private fun eatCaseListDiagonalLeftSub(
        arrays: Array<Double>,
        position: Int,
        animation: Double
    ) {
        if (eatCasePiece != 0) {
            for (i in 0..eatCasePiece) {
                arrays[(position - i * 9)] = animation
            }
        }
    }

    private fun eatCaseListDiagonalRightSub(
        arrays: Array<Double>,
        position: Int,
        animation: Double
    ) {
        if (eatCasePiece != 0) {
            for (i in 0..eatCasePiece) {
                arrays[(position - i * 7)] = animation
            }
        }
    }

    private fun eatCaseListDiagonalRightPlus(
        arrays: Array<Double>,
        position: Int,
        animation: Double
    ) {
        if (eatCasePiece != 0) {
            for (i in 0..eatCasePiece) {
                arrays[(position + i * 9)] = animation
            }
        }
    }

    private fun eatCaseListVerticalPlus(arrays: Array<Double>, position: Int, animation: Double) {
        if (eatCasePiece > 0) {
            for (i in 0..eatCasePiece) {
                arrays[position + i * 8] = animation
            }
        }
    }

    private fun eatCaseListVerticalSub(arrays: Array<Double>, position: Int, animation: Double) {
        if (eatCasePiece > 0) {
            for (i in 0..eatCasePiece) {
                arrays[position - i * 8] = animation
            }
        }
    }

    private fun eatCaseListHorizontalSub(arrays: Array<Double>, position: Int, animation: Double) {
        if (eatCasePiece > 0) {
            for (i in 0..eatCasePiece) {
                arrays[position - i] = animation
            }
        }
    }

    private fun eatCaseListHorizontalPlus(arrays: Array<Double>, position: Int, animation: Double) {
        if (eatCasePiece > 0) {
            for (i in 0..eatCasePiece) {
                arrays[position + i] = animation
            }
        }
    }

    fun isPlayCase(
        arrays: Array<Double>, position: Int, color: Int, hitColor: Int,
        animation: Double
    ): Boolean {
        var eatCase = false
        if (attackHorizontalPlus(arrays, position, color, hitColor, animation)) eatCase = true
        if (attackHorizontalSub(arrays, position, color, hitColor, animation)) eatCase = true
        if (attackVerticalPlus(arrays, position, color, hitColor, animation)) eatCase = true
        if (attackVerticalSub(arrays, position, color, hitColor, animation)) eatCase = true
        if (attackDiagonalLeftPlus(arrays, position, color, hitColor, animation)) {
            eatCase = true
        }
        if (attackDiagonalLeftSub(arrays, position, color, hitColor, animation)) {
            eatCase = true
        }
        if (attackDiagonalRightPlus(arrays, position, color, hitColor, animation)) {
            eatCase = true
        }
        if (attackDiagonalRightSub(arrays, position, color, hitColor, animation)) {
            eatCase = true
        }
        return eatCase
    }

}


