package com.tresole.tresole.Kart

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.Util.item
import com.tresole.tresole.repository.repository

class kartadapter(kartfragment: KartFragment, private val listitems: MutableList<item>) : RecyclerView.Adapter<kartadapter.ViewHolder>() {
    var clickListener: ClickListener? = null
    var repo=repository()
    val lifecycleowner=kartfragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kartadapter.ViewHolder {
        val context =parent.context
        val inflater= LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.kartlinearitem,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: kartadapter.ViewHolder, position: Int) {
        holder.bind(listitems.get(position))


    }

    private fun getimage(get: item): MutableLiveData<Bitmap> {
        return repo.getimage(get.itemimage)

    }

    override fun getItemCount(): Int {
        return listitems.size
    }
    inner class ViewHolder (itemview : View) : RecyclerView.ViewHolder(itemview), View.OnClickListener {
        val linearitemimage=itemview.findViewById<ImageView>(R.id.kartlinearitemimageview)
        val simplepriceview = itemview.findViewById<TextView>(R.id.kartlinearitemprice)
        val simpleitemview=itemview.findViewById<TextView>(R.id.kartlinearitemname)
        val delete=itemview.findViewById<ImageView>(R.id.layout_list_delete)
        init {
            // Define click listener for the ViewHolder's View.

            if (clickListener != null) {
                delete.setOnClickListener(this)
            }

        }
        fun bind(get: item) {
            val textView = simplepriceview
            val nametextview = simpleitemview
            textView.setText((get.itemprice-(get.itemprice/100*get.itemdiscount)).toString()+" EGP")
            nametextview.setText(get.itemname)
            val imageview = linearitemimage
            imageview.setImageBitmap(getimage(get).value)

            getimage(get).observe(lifecycleowner,{
                imageview.setImageBitmap(it)
            })

        }


        override fun onClick(view: View?) {
            if (view != null) {

                clickListener?.onItemClick(view, adapterPosition)
            }
        }

    }


    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener=clickListener
    }


    interface ClickListener {
        fun onItemClick(v: View, position: Int)
    }

}
