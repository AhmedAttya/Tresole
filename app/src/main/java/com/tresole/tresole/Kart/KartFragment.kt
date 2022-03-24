package com.tresole.tresole.Kart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.Categories.SwipeToDeleteCallback
import com.tresole.tresole.R
import com.tresole.tresole.databinding.KartFragmentBinding

class KartFragment : Fragment() {

    private var _binding: KartFragmentBinding?  = null
    private val binding get() = _binding!!
    private lateinit var viewModel: KartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding= KartFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KartViewModel::class.java)
        binding.totalpriceshow.text=viewModel.calcprice()+" EGP"
        binding.proceedtocheckout.setOnClickListener {
            if(kartcurrentitems.listofitems.isEmpty()){
                findNavController().navigate(R.id.action_kartFragment_to_store)
            }
            else
            findNavController().navigate(R.id.action_kartFragment_to_checkOutFragment)
        }
        binding.continueshopping.setOnClickListener {
            findNavController().navigate(R.id.action_kartFragment_to_store)
        }
        val kartRV=binding.recyclerView
        val kartadapter=kartadapter(this,kartcurrentitems.listofitems)
        val layoutManager=LinearLayoutManager(this.context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        kartRV.layoutManager=layoutManager
        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                kartcurrentitems.listofitems.removeAt(pos)
                binding.totalpriceshow.text=viewModel.calcprice()+" EGP"
               kartadapter.notifyDataSetChanged()
            }
        }
        kartRV.adapter=kartadapter
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(kartRV)
        kartadapter.setOnItemClickListener(object :kartadapter.ClickListener{
            override fun onItemClick(v: View, position: Int) {
                kartcurrentitems.listofitems.removeAt(position)
                binding.totalpriceshow.text=viewModel.calcprice()+" EGP"
                kartadapter.notifyDataSetChanged()
            }
        })

    }

}