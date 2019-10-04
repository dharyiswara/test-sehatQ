package com.dharyiswara.sehatqtest.helper

import com.facebook.AccessToken
import com.facebook.login.LoginManager

class LogoutHelper {

    companion object {

        fun facebookLogout() {
            val accessToken: String = try {
                AccessToken.getCurrentAccessToken().token
            } catch (e: NullPointerException) {
                ""
            } catch (e: IllegalStateException) {
                ""
            }

            if (accessToken.isNotEmpty()) {
                LoginManager.getInstance().logOut()
            }
        }
    }
}