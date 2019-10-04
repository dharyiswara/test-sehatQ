package com.dharyiswara.sehatqtest.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.base.BaseActivity
import com.dharyiswara.sehatqtest.helper.TextUtils
import com.dharyiswara.sehatqtest.model.FacebookPicture
import com.dharyiswara.sehatqtest.preferences.UserSession
import com.dharyiswara.sehatqtest.ui.main.MainActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONException
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity() {

    private val userSession by inject<UserSession>()

    private var callbackManager: CallbackManager? = null

    companion object {
        const val PUBLIC_PROFILE = "public_profile"
        const val FACEBOOK_NAME = "name"
        const val FACEBOOK_PICTURE = "picture"
        const val PROFILE = "profile"
        const val PARAMETERS = "id, name, email, picture.width(800).height(800)"
        const val FIELDS = "fields"
    }

    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun initView() {
        super.initView()
        setupFacebook()
    }

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

        clFacebook.setOnClickListener {
            val params = listOf(PUBLIC_PROFILE)
            LoginManager.getInstance().logInWithReadPermissions(this, params)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    private fun login(username: String, imageUrl: String = TextUtils.BLANK) {
        userSession.isRemember = cbRemember.isChecked
        userSession.name = username
        userSession.avatar = imageUrl
        startActivity<MainActivity>()
        finish()
    }

    private fun setupFacebook() {

        callbackManager = CallbackManager.Factory.create()

        val facebookJsonCallback = GraphRequest.GraphJSONObjectCallback { jsonObject, response ->
            try {
                Log.d("Facebook", "FacebookJSONObject ${jsonObject?.toString()}")
                val facebookName = jsonObject?.getString(FACEBOOK_NAME) ?: TextUtils.BLANK
                val jsonImage = jsonObject?.getString(FACEBOOK_PICTURE) ?: TextUtils.BLANK
                val facebookImage = convertFacebookImage(jsonImage).data.url
                if (!facebookName.isEmpty()) {
                    login(facebookName, facebookImage)
                } else {
                    Log.e("Facebook", response?.error?.errorMessage)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        val facebookCallback = object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val accessToken = result?.accessToken
                val request = GraphRequest.newMeRequest(accessToken, facebookJsonCallback)
                val param = Bundle()
                param.putString(FIELDS, PARAMETERS)
                request.parameters = param
                request.executeAsync()
            }

            override fun onCancel() {
                Log.e("Facebook", "Facebook login : Cancelled")
            }

            override fun onError(error: FacebookException?) {
                Log.e("Facebook", "Facebook error login : ${error?.localizedMessage}")
            }

        }

        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback)
    }

    private fun convertFacebookImage(json: String): FacebookPicture {
        val gson = Gson()
        val type = object : TypeToken<FacebookPicture>() {}.type
        return gson.fromJson(json, type)
    }

}