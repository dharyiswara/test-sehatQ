package com.dharyiswara.sehatqtest.ui.splash

import android.os.Handler
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.base.BaseActivity
import com.dharyiswara.sehatqtest.database.ProductRealm
import com.dharyiswara.sehatqtest.helper.LogoutHelper
import com.dharyiswara.sehatqtest.preferences.UserSession
import com.dharyiswara.sehatqtest.ui.login.LoginActivity
import com.dharyiswara.sehatqtest.ui.main.MainActivity
import io.realm.Realm
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity() {

    private val userSession by inject<UserSession>()

    private val productRealm by lazy { ProductRealm(Realm.getDefaultInstance()) }

    private val logoutHelper by lazy { LogoutHelper(this) }

    companion object {

        const val DELAY_SECOND = 4000L

    }

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun initView() {
        super.initView()
        logoutHelper
        Handler().postDelayed({
            when {
                userSession.isRemember -> {
                    when {
                        userSession.isLoggedIn -> startActivity<MainActivity>()
                        else -> startActivity<LoginActivity>()
                    }
                }
                else -> {
                    productRealm.reset()
                    logoutHelper.logout()
                    startActivity<LoginActivity>()
                }
            }
            finish()
        }, DELAY_SECOND)
    }

}