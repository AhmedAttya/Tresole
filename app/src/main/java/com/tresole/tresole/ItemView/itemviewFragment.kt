package com.tresole.tresole.ItemView

import android.graphics.Paint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tresole.tresole.R
import com.tresole.tresole.databinding.ItemviewFragmentBinding

class itemviewFragment : Fragment() {
    private var _binding: ItemviewFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ItemviewViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding=ItemviewFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemviewViewModel::class.java)
        Toast.makeText(this.context,"num ratings = "+viewModel.item.itemid+" "+viewModel.item.itemratings.size,Toast.LENGTH_LONG).show()

      binding.discounttextview.text=viewModel.item.itemdiscount.toString()+"% "+getString(R.string.discount)
      binding.itemtextview.text=viewModel.item.itemname
      binding.itemdescriptiontext.text=viewModel.item.itemdescreption
      binding.imageView2.setImageBitmap(viewModel.getimage().value)
        viewModel.getimage().observe(this,{
            binding.imageView2.setImageBitmap(it)
        })
      binding.brandnametextview.text=getText(R.string.brandname).toString()+" " + viewModel.item.itembrand
      binding.pricetextview.text=viewModel.item.itemprice.toString()
        binding.pricetextview.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
        binding.newpricetextview.text=viewModel.countprice().toString()+" "+getText(R.string.EGP)
        binding.availabilitytextview.text=if (viewModel.item.itemquantity>0) getText(R.string.available) else getText(R.string.unavialable)
      binding.shippingstatus.text=if(viewModel.countprice()>300) getText(R.string.freeshipping) else getText(R.string.notfreeshipping)
        binding.ratingshowbar.rating=viewModel.rating()
        binding.ratingshowbar.isEnabled=false
        binding.ratebutton.setOnClickListener {
            viewModel.rateadded(binding.ratingBar.rating.toInt())
            binding.ratebutton.isEnabled=false
            binding.ratingBar.isEnabled=false
        }
        binding.buynow.setOnClickListener {
          viewModel.additemtokart(viewModel.item)
         findNavController().navigate(R.id.action_itemviewFragment_to_kartFragment)
        }
        binding.addtokart.setOnClickListener {
            viewModel.additemtokart(viewModel.item)
            findNavController().navigateUp()
        }

    }

}