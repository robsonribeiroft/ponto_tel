package br.com.rrdev.pontotel.extension

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText

fun EditText.string(): String {
    return text.toString()
}

fun EditText.plainText(){
    this.transformationMethod = HideReturnsTransformationMethod.getInstance()
    this.setSelection(this.string().length)
}

fun EditText.passwordText(){
    this.transformationMethod = PasswordTransformationMethod.getInstance()
    this.setSelection(this.string().length)
}

