package br.com.rrdev.pontotel.createAccount

class CreateAccountContract {

    interface Presenter<in T>{

        fun attach(view: T)

        fun createNewAccount(email: String, password: String)

    }

    interface View{

        fun onSuccess()

        fun onFailure(message: String)
    }
}