package br.com.rrdev.pontotel

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import br.com.rrdev.pontotel.adapter.UserAdapter
import br.com.rrdev.pontotel.api.configuration.RetrofitSetup
import br.com.rrdev.pontotel.dao.DaoHelper
import br.com.rrdev.pontotel.dialog.ConfirmDialog
import br.com.rrdev.pontotel.extension.setupVertical
import br.com.rrdev.pontotel.listener.DaoHelperListerner
import br.com.rrdev.pontotel.listener.DialogListener
import br.com.rrdev.pontotel.model.Data
import br.com.rrdev.pontotel.model.User
import br.com.rrdev.pontotel.service.AlarmHelper
import br.com.rrdev.pontotel.util.Network
import br.com.rrdev.pontotel.util.PreferencesHelper
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity(), DialogListener, DaoHelperListerner {



    companion object {
        const val tag = "PONTO_TEL"
    }

    private val adapter = UserAdapter()

    private val dao = DaoHelper(this)

    private val confirmDialog = ConfirmDialog()

    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var txtStatus: TextView
    private lateinit var btnRefresh: Button

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

        btnRefresh.setOnClickListener { fromNet() }

        refreshLayout.setOnRefreshListener {
            fromNet()
        }

    }

    override fun onResume() {
        super.onResume()
        fromNet()
    }

    override fun onPause() {
        startService(Intent(applicationContext, AlarmHelper::class.java))
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_list_exit-> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(applicationContext, SignInActivity::class.java))
                PreferencesHelper.clear(applicationContext)
                finish()
                true
            }
            else-> super.onOptionsItemSelected(item)

        }
    }

    private fun fromNet(){
        txtStatus.visibility = View.VISIBLE
        txtStatus.text = getString(R.string.busca_servidor)
        RetrofitSetup.retrofitInstance.getUsers().enqueue(object : Callback<Data>{
            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d(tag, "Error: ${t.message}")
                t.printStackTrace()
                confirmDialog.show(supportFragmentManager, null)
                refreshLayout.isRefreshing = false
                runOnUiThread {
                    txtStatus.visibility = View.VISIBLE

                    txtStatus.text = if (Network.hasConnection(applicationContext)) getString(R.string.servidor_fora)
                    else getString(R.string.sem_internet)
                }

            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                refreshLayout.isRefreshing = false
                Log.d(tag, "code: ${response.code()}")
                Log.d(tag, "error: ${response.errorBody()}")
                if (response.isSuccessful){
                    val body = response.body()!!.data
                    adapter.setItems(body)
                    dao.save(applicationContext, body)
                    txtStatus.visibility = View.GONE
                }
                else{
                    txtStatus.visibility = View.VISIBLE
                    btnRefresh.visibility = View.VISIBLE
                }

            }

        })
    }

    override fun confirmResult(result: Boolean) {
        if (result){
            dao.obtain(applicationContext)
        }
    }

    override fun getAll(all: List<User>) {
        if (all.isEmpty()){
            runOnUiThread {
                txtStatus.text = getString(R.string.tentar_novamente)
                btnRefresh.visibility = View.VISIBLE
                txtStatus.visibility = View.VISIBLE
            }

        }else{
            runOnUiThread {
                adapter.setItems(all)
                btnRefresh.visibility = View.GONE
                txtStatus.visibility = View.GONE
            }

        }

    }

}
