@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package com.example.othello.model

import android.media.AudioTrack
import java.io.PipedOutputStream
import java.util.*
import kotlin.collections.HashMap

class PlayIa(array: Array<Int>, black: Int, white: Int) {
    fun score(arrays: Array<Int>, color: Int, posList: List<Int>): Int {
        var cpt = 0
        for (i in arrays.indices) {
            if (color === arrays[i])
                cpt++
            posList.toMutableList().add(i);
        }
        return cpt
    }

    fun isContinueToPlay(
        arrays: Array<Int>,
        boardToPlay: List<Int>,
        posList: List<Int>?,
        hitList: HashMap<String, Int>,
        colorHit: Int,
        caseToHit: Int,
        reverseColor: HashMap<String, Int>
    ): Boolean {
        var hitPossible = false;
        for (i in posList?.indices!!) {
            //todo horizontal +
            if (horizontalPlusHit(
                    posList[i],
                    arrays,
                    colorHit,
                    reverseColor,
                    caseToHit,
                    hitList
                )
            ) hitPossible = true
            //todo horizontal -
            if (horizontalSubTractHit(
                    posList[i],
                    arrays,
                    colorHit,
                    reverseColor,
                    caseToHit,
                    hitList
                )
            ) hitPossible = true
            //todo vertical +
            if (verticalPlusHit(
                    posList[i],
                    arrays,
                    colorHit,
                    reverseColor,
                    caseToHit,
                    hitList
                )
            ) hitPossible = true
            //todo vertical -
            if (verticalSubtractHit(
                    posList[i],
                    arrays,
                    colorHit,
                    reverseColor,
                    caseToHit,
                    hitList
                )
            ) hitPossible = true
            //todo digonale -7
            //todo digonale +7
            //todo digonale -9
            //todo digonale +9
        }
        if (hitPossible) {
            reverseColor["horizontalPlusHit score"]?.let { boardToPlay.toMutableList().add(it) }
            reverseColor["horizontalSubTractHit score"]?.let { boardToPlay.toMutableList().add(it) }
            reverseColor["verticalPlusHit score"]?.let { boardToPlay.toMutableList().add(it) }
            reverseColor["verticalPlusHit score"]?.let { boardToPlay.toMutableList().add(it) }
        }
        return hitPossible
    }

    private fun horizontalPlusHit(
        position: Int, arrays: Array<Int>, hitColor: Int,
        eraser: HashMap<String, Int>, caseToHit: Int,
        hitListHorizontal: HashMap<String, Int>,
    ): Boolean {
        for (i in 1 until arrays.size) {
            var cpt = 0;
            if ((position + i) < arrays.size && ((position + i) % 8) < 8 && arrays[position + i] === hitColor) {
                cpt += 1
                eraser["horizontalPlusHit $i"] = position + i
                val nextPosition: Int = position + 2 * i;
                if (nextPosition < arrays.size && (nextPosition % 8) < 8 && arrays[position + 2 * i] === 0) {
                    hitListHorizontal["horizontalPlusHit position"] = nextPosition
                    arrays[position + 2 * i] = caseToHit
                    eraser["horizontalPlusHit score"] = cpt;
                    return true;
                }
            }
        }
        eraser.toMutableMap().clear()
        return false;
    }

    private fun horizontalSubTractHit(
        position: Int, arrays: Array<Int>, hitColor: Int,
        eraser: HashMap<String, Int>, caseToHit: Int,
        hitListHorizontal: HashMap<String, Int>,
    ): Boolean {
        for (i in 1 until arrays.size) {
            var cpt = 0;
            if ((position - i) > 0 && ((position - i) % 8) < 8 && arrays[position - i] === hitColor) {
                cpt += 1
                eraser["horizontalSubTractHit $i"] = position - i
                val nextPosition: Int = position - 2 * i;
                if (nextPosition > 0 && (nextPosition % 8) < 8 && arrays[position - 2 * i] === 0) {
                    hitListHorizontal["horizontalSubTractHit position"] = nextPosition
                    arrays[position - 2 * i] = caseToHit
                    return true;
                }
            }
        }
        eraser.toMutableMap().clear()
        return false;
    }

    private fun verticalSubtractHit(
        position: Int, arrays: Array<Int>, hitColor: Int,
        eraser: HashMap<String, Int>, caseToHit: Int,
        hitListHorizontal: HashMap<String, Int>,
    ): Boolean {
        for (i in 1 until arrays.size step 8) {
            var cpt = 0;
            if ((position - i) > 0 && ((position - i) % 8) < 8 && arrays[position - i] === hitColor) {
                cpt += 1
                eraser["verticalSubtractHit $i"] = position - i
                val nextPosition: Int = position - 2 * i;
                if (nextPosition > 0 && (nextPosition % 8) < 8 && arrays[position - 2 * i] === 0) {
                    hitListHorizontal["verticalSubtractHit position"] = nextPosition
                    arrays[position - 2 * i] = caseToHit
                    eraser["verticalSubtractHit score"] = cpt;
                    return true;
                }
            }
        }
        eraser.toMutableMap().clear()
        return false;
    }

    private fun verticalPlusHit(
        position: Int, arrays: Array<Int>, hitColor: Int,
        eraser: HashMap<String, Int>, caseToHit: Int,
        hitListHorizontal: HashMap<String, Int>
    ): Boolean {
        for (i in 1 until arrays.size step 8) {
            var cpt = 0;
            if ((position + i) < arrays.size && ((position + i) % 8) < 8 && arrays[position - i] === hitColor) {
                cpt += 1
                eraser["verticalPlusHit $i"] = position + i
                var nextPosition: Int = position + 2 * i;
                if ((nextPosition) < arrays.size && (nextPosition % 8) < 8 && arrays[position + 2 * i] === 0) {
                    hitListHorizontal["verticalPlusHit position"] = nextPosition
                    arrays[position + 2 * i] = caseToHit
                    eraser["verticalPlusHit score"] = cpt;
                    return true;
                }
            }
        }
        eraser.toMutableMap().clear()
        return false;
    }

}


