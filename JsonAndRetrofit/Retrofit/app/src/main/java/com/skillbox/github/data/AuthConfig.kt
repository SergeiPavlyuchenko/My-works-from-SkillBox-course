package com.skillbox.github.data

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "3a13806342defbfd235b"
    const val CLIENT_SECRET = "25d1b290f1f29f35d11d642aba5bf821f4cc383c"
    const val CALLBACK_URL = "skillbox://skillbox.ru/callback"
}