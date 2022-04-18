package com.tresole.tresole.account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.tresole.tresole.R
import com.tresole.tresole.databinding.AccountFragmentBinding

class AccountFragment : Fragment() {

    private var _binding: AccountFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = AccountFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        binding.userwelcomemessage.text = getString(R.string.welcome)+" "+viewModel.getuserwelcomemessage()
        val adapter=Shipementsadapter(this,viewModel.getshipments().value)
        val recyclerView=binding.currentshipment
        val layoutManager=LinearLayoutManager(this.context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        recyclerView.adapter=adapter
        recyclerView.layoutManager=layoutManager
        if(adapter.itemCount==0){
           recyclerView.visibility=View.GONE
           binding.emptyRVmessage.visibility= View.VISIBLE
        }
        binding.signout.setOnClickListener {
            AuthUI.getInstance()
                .signOut(this.requireContext())
                .addOnCompleteListener {
                    // ...
                }
            findNavController().navigate(R.id.action_account2_to_login_Fargment)
        }
    }

}