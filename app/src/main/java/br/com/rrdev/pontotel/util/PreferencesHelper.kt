package br.com.rrdev.pontotel.util

import android.content.Context

class PreferencesHelper {

    companion object {
        private const val PREF_ID = "prefID"
        private const val ID = "savedID"


        fun save(context: Context, id: String){
            val myPref = context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)
            val editor = myPref.edit()
            editor.putString(ID, id)
            editor.apply()
        }

        fun retrieve(context: Context): String? {
            val myPref = context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)
            return myPref.getString(ID,null)
        }

        fun clear(context: Context){
            val myPref = context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)
            val editor = myPref.edit()
            editor.clear()
            editor.apply()
        }
    }
}