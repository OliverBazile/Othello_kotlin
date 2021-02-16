package com.example.othello.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.othello.R
import com.example.othello.activity.MainActivity
import com.example.othello.model.PieceScore
import com.example.othello.model.PlayGamerToGamer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_view_checkerboard_activity_main.view.*

class CheckerAdapter(
    private val context: Context,
    var checkerCase: Array<Int>,
    var activity: MainActivity,
    var isMyTurn: Boolean,
    var endGame: Boolean
) : RecyclerView.Adapter<CheckerAdapter.CheckerHolder>() {

    private var checkPlayer: Boolean = true
    private var ruler: PlayGamerToGamer? = null


    @SuppressLint("NonConstantResourceId")
    class CheckerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(value: Int) {
            when (value) {
                2 -> {
                    itemView.image_view_case_checker.setImageResource(R.drawable.ic_black_circle)
                    itemView.image_view_case_checker.isEnabled = false
                }
                1 -> {
                    itemView.image_view_case_checker.setImageResource(R.drawable.ic_white_circle)
                    itemView.image_view_case_checker.isEnabled = false
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckerHolder {
        return CheckerHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_checkerboard_activity_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CheckerHolder, position: Int) {
        val piece = checkerCase[position]
        ruler = PlayGamerToGamer()

        holder.itemView.setOnClickListener {
            if (isMyTurn && !endGame && this.checkerCase[position] == 0
                && ruler!!.isPlayCase(checkerCase, position, 1, 2)) {
                this.checkerCase[position] = 1
                holder.bindView(1)
                Log.w("onBindViewHolder piece", " " + position)
                activity.playScore(isMyTurn)
                this.notifyDataSetChanged()
            } else if (!isMyTurn && !endGame && this.checkerCase[position] == 0
                && ruler!!.isPlayCase(checkerCase, position, 2, 1)) {
                this.checkerCase[position] = 2
                holder.bindView(2)
                activity.playScore(isMyTurn)
                this.notifyDataSetChanged()
            }
        }


        holder.bindView(piece)
    }

    override fun getItemCount(): Int = this.checkerCase.size

}