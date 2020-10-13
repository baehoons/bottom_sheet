package com.nayuntech.bottom_sheet.adapaters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.nayuntech.bottom_sheet.R

internal class ViewHolder(val view: View) {
    val title: TextView = view.findViewById(R.id.title)

    val icon: ImageView = view.findViewById(R.id.icon)
}