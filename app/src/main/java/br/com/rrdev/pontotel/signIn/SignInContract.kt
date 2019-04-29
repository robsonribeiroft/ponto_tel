package br.com.rrdev.pontotel.signIn

class SignInContract {

    interface Presenter<in T>{

        fun attach(view: T)

        fun signIn(email: String, password: String)
    }

    interface View{

        fun onSuccess()

        fun onFailure(message: String)

        fun showLoader()

        fun hideLoader()
    }
}