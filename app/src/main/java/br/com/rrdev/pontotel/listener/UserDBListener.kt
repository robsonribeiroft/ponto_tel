package br.com.rrdev.pontotel.listener

import br.com.rrdev.pontotel.model.User

interface UserDBListener {

    fun getAll(allUsers: List<User>)
}