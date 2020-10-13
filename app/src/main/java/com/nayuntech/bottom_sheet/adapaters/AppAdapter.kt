package com.nayuntech.bottom_sheet.adapaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.nayuntech.bottom_sheet.model.AppInfo
import com.nayuntech.bottom_sheet.R

class AppAdapter(
    context: Context,
    private val apps: List<AppInfo>,
    isGrid: Boolean
) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private val textColor: Int = ContextCompat.getColor(context, R.color.black_85)

    @LayoutRes
    private val layoutResource: Int =
        if (isGrid) R.layout.bottom_sheet_grid_item else R.layout.bottom_sheet_list_item

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val appInfo = getItem(position)

        return when (convertView) {
            null -> {
                ViewHolder(inflater.inflate(layoutResource, parent, false)).apply {
                    title.setTextColor(textColor)
                    view.tag = this
                }
            }

            else -> {
                convertView.tag as ViewHolder
            }
        }.apply {
            icon.setImageDrawable(appInfo.drawable)
            title.text = appInfo.title
        }.view
    }

    override fun getItem(position: Int): AppInfo = apps[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int {
        return apps.size
    }

    override fun hasStableIds(): Boolean = false
}