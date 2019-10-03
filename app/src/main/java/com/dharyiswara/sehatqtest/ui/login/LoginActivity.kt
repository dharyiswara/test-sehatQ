package com.dharyiswara.sehatqtest.ui.login

import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.base.BaseActivity
import com.dharyiswara.sehatqtest.helper.TextUtils
import com.dharyiswara.sehatqtest.preferences.UserSession
import com.dharyiswara.sehatqtest.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity() {

    private val userSession by inject<UserSession>()

    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun initEvent() {
        super.initEvent()
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isNotEmpty() || password.isNotEmpty()) {
                login(username)
            } else {
                toast(getString(R.string.string_warning_login))
            }
        }
    }

    private fun login(username: String, imageUrl: String = TextUtils.BLANK) {
        userSession.isRemember = cbRemember.isChecked
        userSession.name = username
        userSession.avatar = imageUrl
        startActivity<MainActivity>()
        finish()
    }

}