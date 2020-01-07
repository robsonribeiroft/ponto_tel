package br.com.rrdev.pontotel.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rrdev.pontotel.PontotelApplication.Companion.application

fun RecyclerView.setupVertical(){
    layoutManager = LinearLayoutManager(
        application,
        LinearLayoutManager.VERTICAL,
        false
    )
}