package com.tresole.tresole.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tresole.tresole.R
import com.tresole.tresole.util.Item
import com.tresole.tresole.util.Itemutils
import com.tresole.tresole.databinding.FragmentCategoyshowingBinding


class CategoyshowingFragment : Fragment() {
    private lateinit var viewModel: CategoriesViewModel
    private var _binding:FragmentCategoyshowingBinding?  = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCategoyshowingBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        val categoryadapter=Categorysearchadapter(this,Itemutils.listofitems)
        val categoryshowRV=binding.categoryshowingRV
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        categoryshowRV.adapter=categoryadapter
        categoryshowRV.layoutManager=layoutManager
        categoryadapter.setOnItemClickListener(object :Categorysearchadapter.ClickListener
        {
            override fun onItemClick(v: View, Item: Item) {
                viewModel.onitemclicked(Item)
                findNavController().navigate(R.id.action_global_itemviewFragment)
            }


        })


    }


}