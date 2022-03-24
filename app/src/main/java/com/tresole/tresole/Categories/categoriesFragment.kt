package com.tresole.tresole.Categories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tresole.tresole.R
import com.tresole.tresole.Util.Categories
import com.tresole.tresole.Util.item
import com.tresole.tresole.databinding.CategoriesFragmentBinding
import com.tresole.tresole.databinding.ItemviewFragmentBinding

class categoriesFragment : Fragment() {
    private var _binding: CategoriesFragmentBinding? = null
    private val binding get() = _binding!!



    private lateinit var viewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding= CategoriesFragmentBinding.inflate(inflater, container, false)
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
        val categoryRV=binding.categoriesrecyclerview
        val categoryadapter=categoriesadapter(Categories.Categories.getlist())
        val layoutManager=GridLayoutManager(this.context,4)
        categoryRV.layoutManager=layoutManager
        categoryRV.adapter=categoryadapter

        categoryadapter.setOnItemClickListener(object :categoriesadapter.ClickListener
        {
            override fun onItemClick(v: View, categoriesname: String) {
                viewModel.showcategory(categoriesname)
                findNavController().navigate(R.id.action_categoriesFragment_to_categoyshowingFragment)
            }

        })

    }

}