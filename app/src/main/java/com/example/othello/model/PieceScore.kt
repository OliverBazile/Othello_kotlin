package com.example.othello.model

class PieceScore (var scoreWhite:Int,var scoreBlack:Int,var possibilityWhite:Boolean,var possibilityBlack: Boolean){
    init {
        scoreBlack = 0
        scoreWhite = 0
        possibilityBlack = false
        possibilityWhite = false
    }
    constructor() : this(0, 0,  false, false)

}