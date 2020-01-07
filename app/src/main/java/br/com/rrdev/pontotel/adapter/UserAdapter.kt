package br.com.rrdev.pontotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rrdev.pontotel.R
import br.com.rrdev.pontotel.model.User
import kotlinx.android.synthetic.main.adapter_user.view.*

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var listItems: List<User> = ArrayList()

    fun setItems(listItems: List<User>){
        this.listItems = listItems
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_user, parent, false))
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.apply {
            txtId.text = "ID : ${item.id}"
            txtName.text = "Name : ${item.name}"
            txtPwd.text = "Pwd : ${item.pwd}"
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtId = view.txt_id
        val txtName = view.txt_name
        val txtPwd = view.txt_pwd
    }
}