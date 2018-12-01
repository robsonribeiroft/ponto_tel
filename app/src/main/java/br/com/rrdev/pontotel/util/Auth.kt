package br.com.rrdev.pontotel.util

class Auth{


    companion object {
        const val EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)\$"

        fun validateEmail(email: String): Boolean {
            return email.matches(Regex(EMAIL_REGEX))
        }
    }


}