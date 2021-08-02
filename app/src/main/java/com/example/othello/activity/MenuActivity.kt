package com.example.othello.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.othello.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        text_view_gamer_vs_gamer_menu.setOnClickListener {
            text_view_title_menu.setTextColor(it.context.getColor(R.color.new_white))
            text_view_title_menu.setBackgroundResource(R.color.new_black)
            text_view_gamer_vs_gamer_menu.setTextColor(it.context.getColor(R.color.new_black))
            text_view_gamer_vs_gamer_menu.setBackgroundResource(R.color.new_white)
            relative_layout_background_menu.setBackgroundResource(R.color.new_black)
            intent = Intent(this, GamerVsGamerActivity::class.java)
            startActivity(intent)
        }

    }
}