package com.dharyiswara.sehatqtest.ui.main

import androidx.fragment.app.Fragment
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.base.BaseActivity
import com.dharyiswara.sehatqtest.database.ProductRealm
import com.dharyiswara.sehatqtest.helper.BlankFragment
import com.dharyiswara.sehatqtest.helper.extension.inTransaction
import com.dharyiswara.sehatqtest.preferences.UserSession
import com.dharyiswara.sehatqtest.ui.main.home.HomeFragment
import com.dharyiswara.sehatqtest.ui.main.profile.ProfileFragment
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private val userSession by inject<UserSession>()

    private val homeFragment by lazy { HomeFragment.newInstance() }

    private val feedFragment by lazy { BlankFragment.newInstance(getString(R.string.string_feed)) }

    private val cartFragment by lazy { BlankFragment.newInstance(getString(R.string.string_cart)) }

    private val profileFragment by lazy { ProfileFragment.newInstance() }

    private val productRealm by lazy { ProductRealm(Realm.getDefaultInstance()) }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()
        replaceFragment(homeFragment)
    }

    override fun initEvent() {
        super.initEvent()
        navigationView.setOnNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == navigationView.selectedItemId) {
                return@setOnNavigationItemSelectedListener false
            }
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.feed -> {
                    replaceFragment(feedFragment)
                    true
                }
                R.id.cart -> {
                    replaceFragment(cartFragment)
                    true
                }
                R.id.profile -> {
                    replaceFragment(profileFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun <F> replaceFragment(fragment: F) where F : Fragment {
        supportFragmentManager.inTransaction {
            replace(R.id.fragmentLayout, fragment)
        }
    }

    override fun onBackPressed() {
        if (!userSession.isRemember) {
            productRealm.reset()
            userSession.logout()
        }
        super.onBackPressed()
    }

}
