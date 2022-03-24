package com.tresole.tresole.Account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.Util.Categories
import com.tresole.tresole.Util.item
import com.tresole.tresole.Util.shipment
import java.text.SimpleDateFormat
import java.util.*

class shipementsadapter(private val listitems:MutableList<shipment>) : RecyclerView.Adapter<shipementsadapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shipementsadapter.ViewHolder {
        val context =parent.context
        val inflater= LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.linearshipmentinfo,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: shipementsadapter.ViewHolder, position: Int) {
        holder.bind(listitems.get(position))


    }



    override fun getItemCount(): Int {
        return listitems.size
    }
    inner class ViewHolder (itemview : View) : RecyclerView.ViewHolder(itemview) {
        val idview=itemview.findViewById<TextView>(R.id.orderidshipmentinfo)
        val orderdateview=itemview.findViewById<TextView>(R.id.orderdateshipmentinfo)
        val arrivaldateview =itemview.findViewById<TextView>(R.id.arrivaldateshipmentinfo)
        val totalpriceview = itemview.findViewById<TextView>(R.id.totalpriceshipmentinfo)

        fun bind(shipment: shipment) {
            val formatter=SimpleDateFormat("dd-MM")
            idview.setText(shipment.id.toString())
            orderdateview.setText(formatter.format(shipment.date))
            arrivaldateview.setText(formatter.format(shipment.date.day+7))
            totalpriceview.setText(shipment.total.toString()+" EGP")

        }



    }


}

