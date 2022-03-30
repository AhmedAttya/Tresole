package com.tresole.tresole.categories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tresole.tresole.R
import com.tresole.tresole.util.Categories
import com.tresole.tresole.databinding.CategoriesFragmentBinding

class CategoriesFragment : Fragment() {
    private var _binding: CategoriesFragmentBinding? = null
    private val binding get() = _binding!!



    private lateinit var viewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = CategoriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        val categoryRV=binding.categoriesrecyclerview
        val categoryadapter=Categoriesadapter(Categories.Categories.getlist())
        val layoutManager=GridLayoutManager(this.context,4)
        categoryRV.layoutManager=layoutManager
        categoryRV.adapter=categoryadapter

        categoryadapter.setOnItemClickListener(object :Categoriesadapter.ClickListener
        {
            override fun onItemClick(v: View, categoriesname: String) {
                viewModel.showcategory(categoriesname)
                findNavController().navigate(R.id.action_categoriesFragment_to_categoyshowingFragment)
            }

        })

    }

}