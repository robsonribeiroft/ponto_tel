package br.com.rrdev.pontotel.util

import android.content.Context
import br.com.rrdev.pontotel.PontotelApplication.Companion.application

class PreferencesHelper {

    companion object {
        private const val PREF_ID = "prefID"
        private const val ID = "savedID"


        fun save(id: String){
            val myPref = application.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)
            val editor = myPref.edit()
            editor.putString(ID, id)
            editor.apply()
        }

        fun retrieve(): String? {
            val myPref = application.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)
            return myPref.getString(ID,null)
        }

        fun clear(){
            val myPref = application.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)
            val editor = myPref.edit()
            editor.clear()
            editor.apply()
        }

        fun userIsLogged(): Boolean {
            val user = application.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE).getString(ID, null)
            return !user.isNullOrEmpty()
        }
    }
}