package com.tresole.tresole.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.util.Item
import com.tresole.tresole.databinding.StoreFragmentBinding

class StoreFragment : Fragment() {
    private var _binding: StoreFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: StoreViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        _binding = StoreFragmentBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[StoreViewModel::class.java]
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        val dealofthedayRV =binding.dealofthedayrecyclerview
        val newandhotRV = binding.hotandnewrecyclerview
        val recommendedRV =binding.Recommendeditemsrecyclerview
        val frequentlyboughtRV = binding.frequentlyboughtitemsrecyclerview
        //create list of the items

        val dealofthedaylayoutmanager=LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val newandhotlayoutManager =LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val recommendeditemslayoutManager =LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val frequentlyboughtitemslayoutManager =LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        dealofthedayRV.layoutManager =dealofthedaylayoutmanager
        newandhotRV.layoutManager =newandhotlayoutManager
        recommendedRV.layoutManager =recommendeditemslayoutManager
        frequentlyboughtRV.layoutManager =frequentlyboughtitemslayoutManager


      val newandhotadapter=ItemAdapter(this,viewModel.getnewandhot())
        val dealofthedayadapter =ItemAdapter(this,viewModel.getdealoftheday())
        val recommendedadapter=ItemAdapter(this,viewModel.getrecommended())
        val frequentlyboughtadapter=ItemAdapter(this,viewModel.getfrequentlybought())
        frequentlyboughtRV.adapter=frequentlyboughtadapter
        recommendedRV.adapter=recommendedadapter
        dealofthedayRV.adapter=dealofthedayadapter
        newandhotRV.adapter=newandhotadapter

        newandhotadapter.setOnItemClickListener(object :ItemAdapter.ClickListener{
            override fun onItemClick(v: View, Item: Item) {
               viewModel.onitemclicked(Item)
               findNavController().navigate(R.id.action_global_itemviewFragment)

            }

        })

        dealofthedayadapter.setOnItemClickListener(object :ItemAdapter.ClickListener{
            override fun onItemClick(v: View, Item: Item) {
                viewModel.onitemclicked(Item)
                findNavController().navigate(R.id.action_global_itemviewFragment)
            }

        })

        recommendedadapter.setOnItemClickListener(object :ItemAdapter.ClickListener{
            override fun onItemClick(v: View, Item: Item) {
                viewModel.onitemclicked(Item)
                findNavController().navigate(R.id.action_global_itemviewFragment)

            }

        })

        frequentlyboughtadapter.setOnItemClickListener(object :ItemAdapter.ClickListener{
            override fun onItemClick(v: View, Item: Item) {
                viewModel.onitemclicked(Item)
                findNavController().navigate(R.id.action_global_itemviewFragment)

            }

        })

       dealofthedayadapter.registerAdapterDataObserver(object :RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted( positionStart :Int,  itemCount:Int) {
               super.onItemRangeInserted(positionStart, itemCount)
               dealofthedayadapter.notifyDataSetChanged()
           }
       })


         recommendedadapter.registerAdapterDataObserver(object :RecyclerView.AdapterDataObserver() {
              override fun onItemRangeInserted(positionStart :Int, itemCount:Int) {
                 super.onItemRangeInserted(positionStart, itemCount)
                 recommendedadapter.notifyDataSetChanged()
             }
         })

      frequentlyboughtadapter.registerAdapterDataObserver(object :RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted( positionStart :Int,  itemCount:Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                frequentlyboughtadapter.notifyDataSetChanged()

            }
        })


        newandhotadapter.registerAdapterDataObserver(object :RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted( positionStart :Int,  itemCount:Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                newandhotadapter.notifyDataSetChanged()

            }
        })
    }


}