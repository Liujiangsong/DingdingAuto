package com.pengxh.autodingding.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import com.pengxh.autodingding.databinding.BottomSelectTimeSheetBinding
import com.pengxh.kt.lite.R
import com.pengxh.kt.lite.extensions.appendZero
import com.pengxh.kt.lite.extensions.binding
import com.pengxh.kt.lite.extensions.resetParams

class BottomSelectTimeSheet constructor(
    context: Context, private val callback: OnTimeSelectedCallback
) : Dialog(context, R.style.UserDefinedActionStyle) {

    private val binding: BottomSelectTimeSheetBinding by binding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.resetParams(Gravity.BOTTOM, R.style.ActionSheetDialogAnimation, 1f)
        setCancelable(true)
        setCanceledOnTouchOutside(true)

        binding.sheetConfirmView.setOnClickListener {
            val startTime =
                "${binding.startTimeSelectView.selectedHour}:${binding.startTimeSelectView.selectedMinute.appendZero()}"
            val endTime =
                "${binding.endTimeSelectView.selectedHour}:${binding.endTimeSelectView.selectedMinute.appendZero()}"

            //保存数据
            callback.onTimePicked(startTime, endTime)
            dismiss()
        }
    }

    interface OnTimeSelectedCallback {
        fun onTimePicked(startTime: String, endTime: String)
    }
}