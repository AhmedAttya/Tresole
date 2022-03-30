package com.tresole.tresole.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFargmentViewModel : ViewModel() {
    private val auth :FirebaseAuth= FirebaseAuth.getInstance()

   fun checkifuserislogged(){
       if (auth.currentUser !=null){
           // TODO:go to next fragment
       }
   }

}