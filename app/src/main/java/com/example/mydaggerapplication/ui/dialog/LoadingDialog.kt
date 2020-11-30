package com.example.mydaggerapplication.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.example.mydaggerapplication.R

class LoadingDialog(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)

        window?.apply{
            attributes.apply {
                flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                dimAmount = 0.5f
                width = WindowManager.LayoutParams.MATCH_PARENT
                //height = WindowManager.LayoutParams.MATCH_PARENT
                gravity = Gravity.CENTER
            }
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}