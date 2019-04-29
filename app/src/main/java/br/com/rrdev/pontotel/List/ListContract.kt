package br.com.rrdev.pontotel.List

import br.com.rrdev.pontotel.model.User

class ListContract {

    interface Presenter<in T>{

        fun attach(view: T)

        fun retrieveUsers()

        fun signOut()

    }

    interface View{

        fun onSuccessRetrieve(users: List<User>)

        fun onFailureRetrieve(message: String)

        fun signOutResult()
    }
}