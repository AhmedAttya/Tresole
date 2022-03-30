package com.tresole.tresole.Kart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tresole.tresole.R
import com.tresole.tresole.databinding.FragmentCheckOutBinding


class CheckOutFragment : Fragment() {
    private lateinit var viewModel: KartViewModel
    private var _binding: FragmentCheckOutBinding?  = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCheckOutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KartViewModel::class.java)
        val months= listOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12)
        val years = listOf<Int>(2022,2023,2024,2025,2026,2027,2028)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, months)
        (binding.ccmonthmenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        val adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, years)
        (binding.ccyearmenu.editText as? AutoCompleteTextView)?.setAdapter(adapter2)
        binding.checkoutCCradionbutton.setOnClickListener {
            binding.ccnameinput.visibility=View.VISIBLE
            binding.ccnumberinput.visibility=View.VISIBLE
            binding.cclayout.visibility=View.VISIBLE
        }
        binding.checkoutCODradionbutton.setOnClickListener {
            binding.ccnameinput.visibility=View.GONE
            binding.ccnumberinput.visibility=View.GONE
            binding.cclayout.visibility=View.GONE
        }
        Log.e("exception","SIUUU "+binding.nameinput.editText!!.text.toString())
        Log.e("exception","SIUUU "+binding.nameinput.editText.toString())
        binding.proceedbutton.setOnClickListener {
            if(binding.nameinput.editText!!.text.toString()=="")
            {
             binding.nameinput.error=getString(R.string.nonameentered)
            }
            else if(binding.addressinput.editText!!.text.toString()=="")
            {
                binding.addressinput.error=getString(R.string.noaddressentered)
            }
            else if (binding.cityinput.editText!!.text.toString()==""){
                binding.cityinput.error=getString(R.string.nocityentered)
            }
            else if (binding.stateinput.editText!!.text.toString()==""){
                binding.stateinput.error=getString(R.string.nostaateentered)
            }
            else if(binding.phoneinput.editText!!.text.toString()==""){
                binding.phoneinput.error=getString(R.string.nophonenumberentered)
            }
            else if(binding.checkoutCCradionbutton.isSelected&&binding.ccmonthmenu.editText!!.text.toString()=="")
            {
                binding.ccmonthmenu.error=getString(R.string.nomonthentered)
            }
            else if(binding.checkoutCCradionbutton.isSelected&&binding.ccyearmenu.editText!!.text.toString()==""){
                binding.ccyearmenu.error=getString(R.string.noyearentered)
            }
            else if(binding.checkoutCCradionbutton.isSelected&&binding.ccnameinput.editText!!.text.toString()=="")
            {
                binding.ccnameinput.error=getString(R.string.noccnameentered)
            }
            else if(binding.checkoutCCradionbutton.isSelected&&binding.ccnumberinput.editText!!.text.toString()==""){
                binding.ccnumberinput.error=getString(R.string.noccnumberentered)
            }
            else if(binding.checkoutCCradionbutton.isChecked)
            {
                viewModel.addCCshipment(binding.nameinput.editText!!.text.toString(),
                    binding.addressinput.editText!!.text.toString(),binding.cityinput.editText!!.text.toString(),
                    binding.stateinput.editText!!.text.toString(),
                    binding.zipcodeinput.editText!!.text.toString(),Integer.parseInt(binding.phoneinput.editText!!.text.toString()),
                    "CC",kartcurrentitems.gettotal(),kartcurrentitems.getitemids())
               findNavController().navigate(R.id.action_checkOutFragment_to_store)
            }
            else if(binding.checkoutCODradionbutton.isChecked)
            {
                viewModel.addCODshipment(binding.nameinput.editText!!.text.toString(),
                    binding.addressinput.editText!!.text.toString(),binding.cityinput.editText!!.text.toString(),
                    binding.stateinput.editText!!.text.toString(),
                    binding.zipcodeinput.editText!!.text.toString(),Integer.parseInt(binding.phoneinput.editText!!.text.toString()),
                    "COD",kartcurrentitems.gettotal(),kartcurrentitems.getitemids())
                findNavController().navigate(R.id.action_checkOutFragment_to_store)
            }
            else if(!binding.checkoutCODradionbutton.isChecked && !binding.checkoutCCradionbutton.isChecked)
                Toast.makeText(this.context,binding.checkoutCODradionbutton.isSelected.toString()+binding.checkoutCCradionbutton.isSelected.toString(),Toast.LENGTH_LONG).show()
                binding.checkoutCCradionbutton.error=getString(R.string.selectpaymentmethod)
        }

    }

    }
