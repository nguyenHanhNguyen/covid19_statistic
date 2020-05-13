package com.n2n.covid19.ui.main.widget

import android.text.Editable
import android.text.TextWatcher


abstract class CustomTextWatcher: TextWatcher {
    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable?) {}
}