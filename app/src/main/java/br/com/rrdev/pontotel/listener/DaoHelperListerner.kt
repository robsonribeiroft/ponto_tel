package br.com.rrdev.pontotel.listener

import br.com.rrdev.pontotel.model.User

interface DaoHelperListerner {

    fun getAll(all: List<User>)
}