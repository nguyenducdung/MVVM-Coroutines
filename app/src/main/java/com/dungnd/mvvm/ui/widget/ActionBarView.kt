package com.dungnd.mvvm.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.dungnd.mvvm.R
import com.dungnd.mvvm.util.setOnSingClickListener

class ActionBarView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private var ivLeft: ImageView
    private var tvTitle: TextView
    private var ivRight: ImageView

    init {
        //Gắn file layout vào class kotlin
        LayoutInflater.from(context).inflate(R.layout.layout_action_bar, this, true)
        //find viewbyid
        ivLeft = findViewById(R.id.ivLeft)
        tvTitle = findViewById(R.id.tvTitle)
        ivRight = findViewById(R.id.ivRight)

        attrs?.let { initAttr(it) }
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    fun setTitleColor(@ColorRes color: Int) {
        tvTitle.setTextColor(ContextCompat.getColor(context, color))
    }

    fun setOnClickImageLeft(callback: (() -> Unit)) {
        ivLeft.setOnSingClickListener {
            callback.invoke()
        }
    }

    //lấy ra các thuộc tính -> xử lý dữ liệu -> hiển thị vào các view ivLeft, tvTitle, ivRight
    private fun initAttr(attr: AttributeSet) {
        //dòng này lấy ra file attr cần thiết cho view
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.ActionBarView)

        val title = typedArray.getString(R.styleable.ActionBarView_action_bar_title) ?:""
        tvTitle.text = title

        val srcLeft = typedArray.getResourceId(R.styleable.ActionBarView_action_bar_src_left, -1)
        if (srcLeft != -1) {
            ivLeft.setImageDrawable(ContextCompat.getDrawable(context, srcLeft))
        }

        val enableLeft = typedArray.getBoolean(R.styleable.ActionBarView_action_bar_enable_left,false)
        ivLeft.visibility = if (enableLeft) View.VISIBLE else View.GONE

        //Dòng này giúp vẽ lại view theo các thuộc tính mà chúng ta config ở file attr
        typedArray.recycle()
    }
}