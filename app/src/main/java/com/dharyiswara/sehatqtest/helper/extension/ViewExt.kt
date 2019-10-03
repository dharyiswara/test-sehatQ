package com.dharyiswara.sehatqtest.helper.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showKeyboard() {
    val imm: InputMethodManager by lazy { this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    val imm: InputMethodManager by lazy { this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    imm.hideSoftInputFromWindow(windowToken, 0)
}