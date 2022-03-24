package com.tresole.tresole.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tresole.tresole.Categories.CategoriesViewModel
import com.tresole.tresole.Categories.categoriesadapter
import com.tresole.tresole.R
import com.tresole.tresole.Util.Categories
import com.tresole.tresole.Util.item
import com.tresole.tresole.databinding.CategoriesFragmentBinding


class SearchResultFragment : Fragment() {
    private var _binding: CategoriesFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding= CategoriesFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val searchresultRV=binding.categoriesrecyclerview
        val searchresultadapter= searchresultadapter(viewModel.getsearcheditem())
        val layoutManager= LinearLayoutManager(this.context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        searchresultRV.layoutManager=layoutManager
        searchresultRV.adapter=searchresultadapter

        searchresultadapter.setOnItemClickListener(object : searchresultadapter.ClickListener
        {
            override fun onItemClick(v: View, item: item) {
                viewModel.onitemclicked(item)
                findNavController().navigate(R.id.action_searchResultFragment_to_itemviewFragment)
            }

        })

    }


    }
