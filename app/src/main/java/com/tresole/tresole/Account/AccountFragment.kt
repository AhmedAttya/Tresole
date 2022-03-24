package com.tresole.tresole.Account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.databinding.AccountFragmentBinding
import com.tresole.tresole.databinding.StoreFragmentBinding

class AccountFragment : Fragment() {

    private var _binding: AccountFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding= AccountFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        binding.userwelcomemessage.setText(getString(R.string.welcome)+" "+viewModel.getuserwelcomemessage())
        val adapter=shipementsadapter(viewModel.getshipments())
        val RecyclerView=binding.currentshipment
        val layoutManager=LinearLayoutManager(this.context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        RecyclerView.adapter=adapter
        RecyclerView.layoutManager=layoutManager
        if(adapter.itemCount==0){
           RecyclerView.visibility=View.GONE
           binding.emptyRVmessage.visibility= View.VISIBLE
        }
    }

}