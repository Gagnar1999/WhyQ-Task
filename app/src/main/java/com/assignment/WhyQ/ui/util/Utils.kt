package com.assignment.WhyQ.ui.util

import android.app.Activity
import com.assignment.WhyQ.R
import com.iamageo.library.*

object Utils {
    fun showErrorDialog(activity: Activity, description: String) {
        BeautifulDialog.build(activity).title("Error", titleColor = R.color.black)
            .description(description, color = R.color.black).type(type = BeautifulDialog.TYPE.ERROR)
            .position(BeautifulDialog.POSITIONS.CENTER)
            .onPositive(text = "Retry", shouldIDismissOnClick = true) {}
            .onNegative(text = "Cancel") {

            }
    }

}