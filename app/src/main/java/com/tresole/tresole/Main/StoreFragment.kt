package com.tresole.tresole.Main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.Util.item
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
        _binding= StoreFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StoreViewModel::class.java)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        val dealofthedayRV =binding.dealofthedayrecyclerview
        val newandhotRV = binding.hotandnewrecyclerview
        val RecommendedRV =binding.Recommendeditemsrecyclerview
        val frequentlyboughtRV = binding.frequentlyboughtitemsrecyclerview
        //create list of the items

        val dealofthedaylayoutmanager=LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val newandhotlayoutManager =LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val recommendeditemslayoutManager =LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val frequentlyboughtitemslayoutManager =LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        dealofthedayRV.layoutManager =dealofthedaylayoutmanager
        newandhotRV.layoutManager =newandhotlayoutManager
        RecommendedRV.layoutManager =recommendeditemslayoutManager
        frequentlyboughtRV.layoutManager =frequentlyboughtitemslayoutManager


      val newandhotadapter=ItemAdapter(this,viewModel.getnewandhot())
        val dealofthedayadapter =ItemAdapter(this,viewModel.getdealoftheday())
        val Recommendedadapter=ItemAdapter(this,viewModel.getrecommended())
        val frequentlyboughtadapter=ItemAdapter(this,viewModel.getfrequentlybought())
        frequentlyboughtRV.adapter=frequentlyboughtadapter
        RecommendedRV.adapter=Recommendedadapter
        dealofthedayRV.adapter=dealofthedayadapter
        newandhotRV.adapter=newandhotadapter

        newandhotadapter.setOnItemClickListener(object :ItemAdapter.ClickListener{
            override fun onItemClick(v: View, item: item) {
               viewModel.onitemclicked(item)
               findNavController().navigate(R.id.action_global_itemviewFragment)

            }

        })

        dealofthedayadapter.setOnItemClickListener(object :ItemAdapter.ClickListener{
            override fun onItemClick(v: View, item: item) {
                viewModel.onitemclicked(item)
                findNavController().navigate(R.id.action_global_itemviewFragment)
            }

        })

        Recommendedadapter.setOnItemClickListener(object :ItemAdapter.ClickListener{
            override fun onItemClick(v: View, item: item) {
                viewModel.onitemclicked(item)
                findNavController().navigate(R.id.action_global_itemviewFragment)

            }

        })

        frequentlyboughtadapter.setOnItemClickListener(object :ItemAdapter.ClickListener{
            override fun onItemClick(v: View, item: item) {
                viewModel.onitemclicked(item)
                findNavController().navigate(R.id.action_global_itemviewFragment)

            }

        })

       dealofthedayadapter.registerAdapterDataObserver(object :RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted( positionStart :Int,  itemCount:Int) {
               super.onItemRangeInserted(positionStart, itemCount)
               dealofthedayadapter.notifyDataSetChanged()
           }
       })


         Recommendedadapter.registerAdapterDataObserver(object :RecyclerView.AdapterDataObserver() {
              override fun onItemRangeInserted(positionStart :Int, itemCount:Int) {
                 super.onItemRangeInserted(positionStart, itemCount)
                 Recommendedadapter.notifyDataSetChanged()
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