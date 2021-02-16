package com.example.othello.model

class PieceScore (var scoreWhite:Int,var scoreBlack:Int,var possibilityWhite:Boolean,var possibilityBlack: Boolean){
    init {
        scoreBlack = 2
        scoreWhite = 2
        possibilityBlack = false
        possibilityWhite = false
    }
    constructor() : this(2, 2,  false, false)

}