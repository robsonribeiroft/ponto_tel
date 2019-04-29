package br.com.rrdev.pontotel.List

import android.content.Context
import br.com.rrdev.pontotel.R
import br.com.rrdev.pontotel.api.configuration.RetrofitSetup
import br.com.rrdev.pontotel.dao.DaoHelper
import br.com.rrdev.pontotel.listener.UserDBListener
import br.com.rrdev.pontotel.model.Data
import br.com.rrdev.pontotel.model.User
import br.com.rrdev.pontotel.util.Network
import br.com.rrdev.pontotel.util.PreferencesHelper
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPresenter: ListContract.Presenter<ListContract.View>, UserDBListener {

    private lateinit var view: ListContract.View
    private lateinit var context: Context
    private lateinit var userDB: DaoHelper
    private var retrofit = RetrofitSetup.getInstance()

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun attach(view: ListContract.View) {
        this.view = view
        context = view as Context
        userDB = DaoHelper(context)
    }

    override fun getAll(allUsers: List<User>) {
        view.onSuccessRetrieve(allUsers)
    }

    override fun signOut() {
        PreferencesHelper.clear(context)
        mAuth.signOut()
        view.signOutResult()
    }

    override fun retrieveUsers() {
        retrofit.getUsers().enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>, t: Throwable) {
                view.onFailureRetrieve(if (Network.hasConnection(context)) context.getString(R.string.servidor_fora)
                                        else context.getString(R.string.sem_internet))
                userDB.obtain()
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {

                if (response.isSuccessful){
                    val body = response.body()!!.data
                    view.onSuccessRetrieve(body)
                    userDB.save(body)
                }
                else{
                    userDB.obtain()
                    view.onFailureRetrieve(context.getString(R.string.sem_internet))
                }

            }

        })
    }
}