package com.dharyiswara.sehatqtest.preferences

import android.content.Context
import com.dharyiswara.sehatqtest.helper.TextUtils

class UserSession(context: Context) {

    companion object {
        const val PREFERENCE_NAME = "user"
        const val NAME = "name"
        const val AVATAR = "avatar"
        const val REMEMBER = "remember"
    }

    private val sharedPreference =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    var name: String
        get() = sharedPreference.getString(NAME, TextUtils.BLANK) ?: TextUtils.BLANK
        set(name) = sharedPreference.edit().putString(NAME, name).apply()

    var avatar: String
        get() = sharedPreference.getString(AVATAR, TextUtils.BLANK) ?: TextUtils.BLANK
        set(url) = sharedPreference.edit().putString(AVATAR, url).apply()

    var isRemember: Boolean
        get() = sharedPreference.getBoolean(REMEMBER, false)
        set(isRemember) = sharedPreference.edit().putBoolean(REMEMBER, isRemember).apply()

    var isLoggedIn: Boolean = false
        get() = sharedPreference.getString(NAME, TextUtils.BLANK)?.isNotEmpty() ?: false

    fun logout() {
        sharedPreference.edit().clear().apply()
    }
}