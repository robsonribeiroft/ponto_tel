package br.com.rrdev.pontotel

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.rrdev.pontotel.adapter.UserAdapter
import br.com.rrdev.pontotel.dialog.ConfirmDialog
import br.com.rrdev.pontotel.extension.setupVertical
import br.com.rrdev.pontotel.viewmodels.ListActivityViewModel
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {


    private val userAdapter = UserAdapter()
    private val confirmDialog = ConfirmDialog()
    private lateinit var listActivityViewModel: ListActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listActivityViewModel = ViewModelProviders.of(this).get(ListActivityViewModel::class.java)

        recycler.apply {
            setupVertical()
            adapter = userAdapter
        }

        confirmDialog.confirmResult = {result: Boolean ->
            if (result) listActivityViewModel.getAllUsersLocal()
        }

        refresh.setOnRefreshListener {
            onLoad()
            listActivityViewModel.getAllUsers()
        }

        listActivityViewModel.allUsers.observe(this, Observer {list ->
            userAdapter.setItems(list)
            onLoadComplete()
        })

    }

    override fun onResume() {
        super.onResume()
        listActivityViewModel.getAllUsers()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_list_exit-> {
                listActivityViewModel.logOut()
                startActivity(Intent(applicationContext, SignInActivity::class.java))
                finish()
                true
            }
            else-> super.onOptionsItemSelected(item!!)
        }
    }

    private fun onLoad() {
        txt_status.apply {
            visibility = View.VISIBLE
            text = getString(R.string.busca_servidor)
        }
        refresh.isRefreshing = true
    }

    private fun onLoadComplete(){
        refresh.isRefreshing = false
        txt_status.visibility = View.GONE
    }
}
