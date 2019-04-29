package br.com.rrdev.pontotel.List

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import br.com.rrdev.pontotel.R
import br.com.rrdev.pontotel.adapter.UserAdapter
import br.com.rrdev.pontotel.dao.DaoHelper
import br.com.rrdev.pontotel.dialog.ConfirmDialog
import br.com.rrdev.pontotel.extension.setupVertical
import br.com.rrdev.pontotel.listener.DialogListener
import br.com.rrdev.pontotel.model.User
import br.com.rrdev.pontotel.signIn.SignInActivity

class ListActivity : AppCompatActivity(), DialogListener, ListContract.View {



    companion object {
        const val tag = "PONTO_TEL"
    }

    private val adapter = UserAdapter()

    private lateinit var dao : DaoHelper

    private val confirmDialog = ConfirmDialog()

    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var txtStatus: TextView
    private lateinit var btnRefresh: Button

    private lateinit var presenter: ListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        txtStatus = findViewById(R.id.txt_status)
        btnRefresh = findViewById(R.id.btn_refresh)
        refreshLayout = findViewById(R.id.refresh)
        recyclerView = findViewById(R.id.recycler)
        recyclerView.setupVertical(applicationContext)

        recyclerView.adapter = adapter

        confirmDialog.listener = this

        presenter = ListPresenter()
        presenter.attach(this)

        dao = DaoHelper(this)

        btnRefresh.setOnClickListener {
            presenter.retrieveUsers()
        }

        refreshLayout.setOnRefreshListener {
            presenter.retrieveUsers()
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.retrieveUsers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_list_exit -> {
                presenter.signOut()
                true
            }
            else-> super.onOptionsItemSelected(item)

        }
    }

    override fun signOutResult() {
        startActivity(Intent(applicationContext, SignInActivity::class.java))
        finish()
    }

    override fun onSuccessRetrieve(users: List<User>) {
        refreshLayout.isRefreshing = false
        adapter.setItems(users)
    }

    override fun onFailureRetrieve(message: String) {
        refreshLayout.isRefreshing = false
        txtStatus.visibility = View.VISIBLE
        txtStatus.text = message
    }


    override fun confirmResult(result: Boolean) {
        if (result){
            dao.obtain()
        }
    }


}
