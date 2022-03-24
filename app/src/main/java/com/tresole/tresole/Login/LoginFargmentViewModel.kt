package com.tresole.tresole.Login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFargmentViewModel : ViewModel() {
    val auth :FirebaseAuth= FirebaseAuth.getInstance()

   fun checkifuserislogged(){
       if (auth.currentUser !=null){
           // TODO:go to next fragment
       }
   }

    // TODO: Implement the ViewModel
}