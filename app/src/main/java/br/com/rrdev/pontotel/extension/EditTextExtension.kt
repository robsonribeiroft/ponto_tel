package br.com.rrdev.pontotel.extension

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText

fun EditText.string(): String = text.toString()

fun EditText.plainText() = this.apply {
    transformationMethod = HideReturnsTransformationMethod.getInstance()
    setSelection(string().length)
}

fun EditText.passwordText() = this.apply {
    transformationMethod = PasswordTransformationMethod.getInstance()
    setSelection(string().length)
}


