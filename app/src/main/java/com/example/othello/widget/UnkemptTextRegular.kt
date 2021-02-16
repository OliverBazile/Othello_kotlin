package com.example.othello.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.example.othello.R

@SuppressLint("AppCompatCustomView")
class UnkemptTextRegular @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr){
        init {
            val typeface: Typeface? = ResourcesCompat.getFont(context, R.font.unkempt_regular);
            this.typeface = typeface;
        }
}