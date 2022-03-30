package com.tresole.tresole.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tresole.tresole.R
import com.tresole.tresole.databinding.SerchFragmentBinding

class SearchFragment : Fragment() {
    private var _binding: SerchFragmentBinding? = null
    private val binding get() = _binding!!



    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = SerchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.searchbutton.setOnClickListener {
            if(binding.searchinput.text.isEmpty())
            {
                binding.warningmessage.visibility=View.VISIBLE
            }
            else
            {
                viewModel.search(binding.searchinput.text.toString())
                findNavController().navigate(R.id.action_search2_to_searchResultFragment)

        }
        }

    }

}