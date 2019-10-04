package com.dharyiswara.sehatqtest.helper

import android.content.Context
import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient

class LogoutHelper(context: Context) : GoogleApiClient.ConnectionCallbacks {

    private var googleApiClient: GoogleApiClient = GoogleApiClient.Builder(context)
        .addApi(Auth.GOOGLE_SIGN_IN_API)
        .addConnectionCallbacks(this)
        .build()

    private var isGoogleLoggedIn = false

    init {
        googleApiClient.connect()
    }

    fun logout() {
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

        if (isGoogleLoggedIn) {
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback {
                disconnectGoogle()
            }
        } else {
            disconnectGoogle()
        }
    }

    private fun disconnectGoogle() {
        googleApiClient.disconnect()
    }

    override fun onConnected(bundle: Bundle?) {
        val opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient)
        if (opr.isDone) {
            val result = opr.get()
            isGoogleLoggedIn = result.isSuccess
        } else {
            opr.setResultCallback {
                isGoogleLoggedIn = it.isSuccess
            }
        }
    }

    override fun onConnectionSuspended(i: Int) {}
}