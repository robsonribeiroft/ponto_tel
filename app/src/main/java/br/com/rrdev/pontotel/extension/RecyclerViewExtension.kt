package br.com.rrdev.pontotel.extension

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

fun RecyclerView.setupVertical(context: Context){
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}