package com.dharyiswara.sehatqtest.ui.main.profile

import androidx.core.content.ContextCompat
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.base.BaseFragment
import com.dharyiswara.sehatqtest.database.ProductRealm
import com.dharyiswara.sehatqtest.helper.LogoutHelper
import com.dharyiswara.sehatqtest.helper.extension.loadFromUrl
import com.dharyiswara.sehatqtest.preferences.UserSession
import com.dharyiswara.sehatqtest.ui.history.PurchaseHistoryActivity
import com.dharyiswara.sehatqtest.ui.login.LoginActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment() {

    private val userSession by inject<UserSession>()

    private val productRealm by lazy { ProductRealm(Realm.getDefaultInstance()) }

    private val logoutHelper by lazy { LogoutHelper(requireContext()) }

    companion object {

        fun newInstance(): ProfileFragment = ProfileFragment()

    }

    override fun getLayoutResId(): Int = R.layout.fragment_profile

    override fun initView() {
        super.initView()

        fetchProfile()
        logoutHelper
    }

    override fun initEvent() {
        super.initEvent()
        tvPurchasedHistory.setOnClickListener {
            startActivity<PurchaseHistoryActivity>()
        }
        tvLogout.setOnClickListener {
            logoutHelper.logout()
            userSession.logout()
            productRealm.reset()
            startActivity<LoginActivity>()
            requireActivity().finish()
        }
    }

    private fun fetchProfile() {
        ivProfile.loadFromUrl(
            userSession.avatar,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_profile)
        )
        tvUsername.text = userSession.name
    }

}