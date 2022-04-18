package com.tresole.tresole.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.util.Shipment
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class Shipementsadapter(fragment: AccountFragment, private val listitems: MutableList<Shipment>?) : RecyclerView.Adapter<Shipementsadapter.ViewHolder>() {

      val lifecycleowner=fragment
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Shipementsadapter.ViewHolder {
        val context =parent.context
        val inflater= LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.linearshipmentinfo,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: Shipementsadapter.ViewHolder, position: Int) {
        holder.bind(listitems!![position])


    }



    override fun getItemCount(): Int {
        return listitems!!.size
    }
    inner class ViewHolder (itemview : View) : RecyclerView.ViewHolder(itemview) {
        private val idview :TextView =itemview.findViewById(R.id.orderidshipmentinfo)
        private val orderdateview :TextView=itemview.findViewById(R.id.orderdateshipmentinfo)
        private val arrivaldateview :TextView= itemview.findViewById(R.id.arrivaldateshipmentinfo)!!
        private val totalpriceview :TextView = itemview.findViewById(R.id.totalpriceshipmentinfo)

        fun bind(Shipment: Shipment) {
            val formatter=SimpleDateFormat("dd-MM")
            idview.text = Shipment.id.toString()
            orderdateview.text = formatter.format(Shipment.date)
            val localDate = Shipment.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(7)
            arrivaldateview.text =Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()).toString()
            totalpriceview.text = Shipment.total.toString()+ lifecycleowner.requireContext().getText(R.string.EGP)

        }



    }


}

