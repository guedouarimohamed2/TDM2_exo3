package com.a0.projet1.master.projet.Decoration

import android.content.Context
import android.graphics.Rect
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class ItemOffsetDecoration(private val itemOffset:Int): androidx.recyclerview.widget.RecyclerView.ItemDecoration () {
    constructor(context: Context, @DimenRes itemDimensId:Int):this(context.resources.getDimensionPixelSize(itemDimensId))

    override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect!!.set(itemOffset,itemOffset,itemOffset,itemOffset)
    }
}