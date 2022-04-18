package com.tresole.tresole.util

import com.google.firebase.auth.FirebaseUser

class Currentuser {
    companion object{
         lateinit var currentuser :FirebaseUser
        fun getcurrentusername(): String? {
            return currentuser.displayName
        }

        fun setcurrentuser(currentuser: FirebaseUser?){
            this.currentuser= currentuser!!
        }

    }
}