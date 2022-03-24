package com.tresole.tresole.Categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tresole.tresole.R
import com.tresole.tresole.Util.item
import com.tresole.tresole.Util.itemutils
import com.tresole.tresole.databinding.FragmentCategoyshowingBinding


class CategoyshowingFragment : Fragment() {
    private lateinit var viewModel: CategoriesViewModel
    private var _binding:FragmentCategoyshowingBinding?  = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCategoyshowingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        val categoryadapter=categorysearchadapter(this,itemutils.listofitems)
        val categoryshowRV=binding.categoryshowingRV
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        categoryshowRV.adapter=categoryadapter
        categoryshowRV.layoutManager=layoutManager
        categoryadapter.setOnItemClickListener(object :categorysearchadapter.ClickListener
        {
            override fun onItemClick(v: View, item: item) {
                viewModel.onitemclicked(item)
                findNavController().navigate(R.id.action_global_itemviewFragment)
            }


        })


    }


}