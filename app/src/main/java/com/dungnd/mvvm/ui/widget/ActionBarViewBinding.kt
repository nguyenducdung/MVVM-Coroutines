package com.dungnd.mvvm.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.LayoutActionBarBindingBinding

class ActionBarViewBinding(context: Context?, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {
    private var binding: LayoutActionBarBindingBinding

    init {
        binding = LayoutActionBarBindingBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)

        //dòng này lấy ra file attr cần thiết cho view
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.ActionBarViewBinding)

        val title = typedArray?.getString(R.styleable.ActionBarViewBinding_action_bar2_title) ?: ""
        binding.tvTitle.text = title

        val srcLeft =
            typedArray?.getResourceId(R.styleable.ActionBarViewBinding_action_bar2_src_left, -1)
                ?: -1
        if (srcLeft != -1) {
            binding.ivLeft.setImageDrawable(ContextCompat.getDrawable(context!!, srcLeft))
        }

        val enableLeft =
            typedArray?.getBoolean(R.styleable.ActionBarViewBinding_action_bar2_enable_left, false)
                ?: false
        binding.ivLeft.visibility = if (enableLeft) View.VISIBLE else View.GONE

        //Dòng này giúp vẽ lại view theo các thuộc tính mà chúng ta config ở file attr
        typedArray?.recycle()
    }
}