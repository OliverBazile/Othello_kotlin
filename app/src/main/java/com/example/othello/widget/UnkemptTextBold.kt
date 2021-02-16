package com.example.othello.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.example.othello.R

class UnkemptTextBold @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {
    init {
       this.typeface =  ResourcesCompat.getFont(context, R.font.unkempt_bold);
    }
}