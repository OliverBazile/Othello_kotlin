package com.example.othello.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.othello.R
import com.example.othello.adapter.CheckerAdapter
import com.example.othello.model.PieceScore
import com.example.othello.model.PlayGamerToGamer
import kotlinx.android.synthetic.main.activity_gamer_vs_gamer.*


@SuppressLint("NonConstantResourceId")
class GamerVsGamerActivity : AppCompatActivity() {

    private var scoreWhite = 2
    private var scoreBlack = 2
    private var checkerAdapter: CheckerAdapter? = null
    private var checkerBoard = Array(64) { 0.0 }


    private fun initBoard() {
        this.checkerBoard[35] = 2.0
        this.checkerBoard[27] = 1.0
        this.checkerBoard[28] = 2.0
        this.checkerBoard[36] = 1.0

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamer_vs_gamer)
        initBoard()
        text_view_score_white_gamer_vs_gamer.text = String.format("Score White %d", scoreWhite)
        text_view_score_black_gamer_vs_gamer.text = String.format("Score Black %d", scoreBlack)
        val vertical = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.line_separator)?.let {
            vertical.setDrawable(it)
        }
        recycler_view_checker_board_gamer_vs_gamer.layoutManager = GridLayoutManager(this, 8)
        recycler_view_checker_board_gamer_vs_gamer.setHasFixedSize(true)
        recycler_view_checker_board_gamer_vs_gamer.addItemDecoration(vertical)
        playScore(false)
        recycler_view_checker_board_gamer_vs_gamer.adapter = checkerAdapter
    }

    @SuppressLint("SetTextI18n")
    fun playScore(isPlay: Boolean) {
        val playGameToGamer = PlayGamerToGamer()
        val pieceScore = playGameToGamer.isOverGamer(this.checkerBoard)
        var isGameOver = false
        text_view_score_white_gamer_vs_gamer.text = String.format("Score White %d", pieceScore.scoreWhite)
        text_view_score_black_gamer_vs_gamer.text = String.format("Score Black %d", pieceScore.scoreBlack)
        var turn = !isPlay
        if (!pieceScore.possibilityWhite) {
            turn = false
        }
        if (!pieceScore.possibilityBlack) {
            turn = true
        }

        if(turn) text_view_attack_gamer_vs_gamer.text = "White just beat it !!!!!"
        else text_view_attack_gamer_vs_gamer.text = "Black you will revenge !!!!!"
        if (!pieceScore.possibilityWhite && !pieceScore.possibilityBlack) {
            isGameOver = true
            text_view_attack_gamer_vs_gamer.text = "Game is Over"
            winner(pieceScore)
        }

        checkerAdapter = CheckerAdapter(
            this, this.checkerBoard, this, turn, isGameOver
        )
        recycler_view_checker_board_gamer_vs_gamer.adapter = checkerAdapter
    }

    private fun winner(pieceScore: PieceScore){
        when {
            pieceScore.scoreBlack == pieceScore.scoreWhite -> {
                text_view_score_white_gamer_vs_gamer.text= "Is the Draw \n ${ pieceScore.scoreBlack}"
                text_view_score_black_gamer_vs_gamer.text= "Is the Draw \n ${pieceScore.scoreWhite}"
            }
            pieceScore.scoreBlack > pieceScore.scoreWhite -> {
                text_view_score_white_gamer_vs_gamer.text= "White Lose !!! \n ${pieceScore.scoreWhite}"
                text_view_score_black_gamer_vs_gamer.text= "Black Win !!! \n ${ pieceScore.scoreBlack}"
            }
            else -> {
                text_view_score_white_gamer_vs_gamer.text= "White Win !!! \n ${pieceScore.scoreWhite}"
                text_view_score_black_gamer_vs_gamer.text= "Black Lose !!! \n ${ pieceScore.scoreBlack}"
            }
        }
    }
}
