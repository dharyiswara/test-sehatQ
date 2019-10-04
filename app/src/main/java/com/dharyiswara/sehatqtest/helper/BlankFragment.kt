package com.dharyiswara.sehatqtest.helper

import android.os.Bundle
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : BaseFragment() {

    companion object {

        const val TITLE = "title"

        fun newInstance(title: String): BlankFragment {
            val bundle = Bundle()
            bundle.putString(TITLE, title)
            val fragment = BlankFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun getLayoutResId(): Int = R.layout.fragment_blank

    override fun initView() {
        super.initView()
        tvFragment.text = arguments?.getString(TITLE) ?: TextUtils.BLANK
    }

}