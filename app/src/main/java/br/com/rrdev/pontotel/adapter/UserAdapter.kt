package br.com.rrdev.pontotel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.rrdev.pontotel.R
import br.com.rrdev.pontotel.model.User

class UserAdapter(private val listItems: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_user, parent, false))

    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            txtId.text = "ID : ${listItems[position].id}"
            txtName.text = "Name : ${listItems[position].name}"
            txtPwd.text = "Pwd : ${listItems[position].pwd}"
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtId = view.findViewById<TextView>(R.id.txt_id)
        val txtName = view.findViewById<TextView>(R.id.txt_name)
        val txtPwd = view.findViewById<TextView>(R.id.txt_pwd)
    }
}