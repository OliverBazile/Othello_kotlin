package com.example.othello.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.othello.R
import com.example.othello.activity.GamerVsGamerActivity
import com.example.othello.model.PlayGamerToGamer
import kotlinx.android.synthetic.main.recycler_view_checkerboard_activity_main.view.*

class CheckerAdapter(
    val context: Context,
    var checkerCase: Array<Double>,
    var activity: GamerVsGamerActivity,
    var isMyTurn: Boolean,
    var endGame: Boolean
) : RecyclerView.Adapter<CheckerAdapter.CheckerHolder>() {

    private var checkPlayer: Boolean = true
    private var ruler: PlayGamerToGamer? = null


    @SuppressLint("NonConstantResourceId")
    class CheckerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(value: Double,arrays: Array<Double>, position: Int) {
            when (value) {
                 0.0 -> {
                    itemView.image_view_case_checker.setBackgroundResource(R.color.new_red)
                }
                2.0 -> {
                    itemView.image_view_case_checker.setImageResource(R.drawable.ic_black_circle)
                    itemView.image_view_case_checker.setBackgroundResource(R.color.new_red)
                    itemView.image_view_case_checker.isEnabled = false
                }
                1.0 -> {
                    itemView.image_view_case_checker.setImageResource(R.drawable.ic_white_circle)
                    itemView.image_view_case_checker.setBackgroundResource(R.color.new_red)
                    itemView.image_view_case_checker.isEnabled = false
                }
                2.1 -> {
                    itemView.image_view_case_checker.animation = AnimationUtils.loadAnimation(itemView.context,
                        R.anim.rotate)
                    arrays[position] = 2.0
                    itemView.image_view_case_checker.setImageResource(R.drawable.ic_black_circle)
                    itemView.image_view_case_checker.isEnabled = false
                }
                1.1 -> {
                    itemView.image_view_case_checker.animation = AnimationUtils.loadAnimation(itemView.context,
                        R.anim.rotate)
                    arrays[position] = 1.0
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CheckerHolder, position: Int) {
        val piece = checkerCase[position]
        ruler = PlayGamerToGamer()

        holder.itemView.setOnClickListener {
            if (isMyTurn && !endGame && this.checkerCase[position].toInt() == 0
                && ruler!!.isPlayCase(this.checkerCase, position, 1, 2,1.1)) {
                holder.bindView(1.0,this.checkerCase,position)
                this.checkerCase[position] = 1.0
                this.notifyDataSetChanged()
                activity.playScore(isMyTurn)

            } else if (!isMyTurn && !endGame && this.checkerCase[position].toInt() == 0
                && ruler!!.isPlayCase(this.checkerCase, position, 2, 1,2.1)) {
                holder.bindView(2.0, this.checkerCase, position)
                this.checkerCase[position] = 2.0
                this.notifyDataSetChanged()
                activity.playScore(isMyTurn)
            }
        }

        holder.bindView(piece,this.checkerCase,position)
    }

    override fun getItemCount(): Int = this.checkerCase.size

}