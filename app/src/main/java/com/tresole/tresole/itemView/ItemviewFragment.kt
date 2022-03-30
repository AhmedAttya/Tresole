package com.tresole.tresole.itemView

import android.graphics.Paint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tresole.tresole.R
import com.tresole.tresole.databinding.ItemviewFragmentBinding

class ItemviewFragment : Fragment() {
    private var _binding: ItemviewFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ItemviewViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ItemviewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ItemviewViewModel::class.java]

      binding.discounttextview.text=viewModel.Item.itemdiscount.toString()+"% "+getString(R.string.discount)
      binding.itemtextview.text=viewModel.Item.itemname
      binding.itemdescriptiontext.text=viewModel.Item.itemdescreption
      binding.imageView2.setImageBitmap(viewModel.getimage().value)
        viewModel.getimage().observe(this,{
            binding.imageView2.setImageBitmap(it)
        })
      binding.brandnametextview.text=getText(R.string.brandname).toString()+" " + viewModel.Item.itembrand
      binding.pricetextview.text=viewModel.Item.itemprice.toString()
        binding.pricetextview.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
        binding.newpricetextview.text=viewModel.countprice().toString()+" "+getText(R.string.EGP)
        binding.availabilitytextview.text=if (viewModel.Item.itemquantity>0) getText(R.string.available) else getText(R.string.unavialable)
      binding.shippingstatus.text=if(viewModel.countprice()>300) getText(R.string.freeshipping) else getText(R.string.notfreeshipping)
        binding.ratingshowbar.rating=viewModel.rating()
        binding.ratingshowbar.isEnabled=false
        binding.ratebutton.setOnClickListener {
            viewModel.rateadded(binding.ratingBar.rating.toInt())
            binding.ratebutton.isEnabled=false
            binding.ratingBar.isEnabled=false
        }
        binding.buynow.setOnClickListener {
          viewModel.additemtokart(viewModel.Item)
         findNavController().navigate(R.id.action_itemviewFragment_to_kartFragment)
        }
        binding.addtokart.setOnClickListener {
            viewModel.additemtokart(viewModel.Item)
            findNavController().navigateUp()
        }

    }

}