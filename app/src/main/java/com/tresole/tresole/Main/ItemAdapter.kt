package com.tresole.tresole.Main

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


class ItemAdapter(storefragment: StoreFragment, private val listitems: MutableList<item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    var clickListener: ClickListener? = null
    val lifecycleowner=storefragment
    var repo=repository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
       val context =parent.context
        val inflater=LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.simpleitem,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
      holder.bind(listitems.get(position))


    }

    private fun getimage(get: item): MutableLiveData<Bitmap> {
        return repo.getimage(get.itemimage)

    }

    override fun getItemCount(): Int {
       return listitems.size
    }
    inner class ViewHolder (itemview : View) :RecyclerView.ViewHolder(itemview),View.OnClickListener {
        val simpleimage=itemview.findViewById<ImageView>(R.id.imageView)
        val simplepriceview = itemview.findViewById<TextView>(R.id.simpleitemprice)
        val simpleitemview=itemview.findViewById<TextView>(R.id.simpleitemname)
        init {
            // Define click listener for the ViewHolder's View.

            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }

        }
        fun bind(item: item) {
            val textView = simplepriceview
            val nametextview = simpleitemview
            textView.setText((item.itemprice-(item.itemprice/100*item.itemdiscount)).toString()+ "  EGP")
            nametextview.setText(item.itemname)
            val imageview = simpleimage
            imageview.setImageBitmap(getimage(item).value)

            getimage(item).observe(lifecycleowner,{
                imageview.setImageBitmap(it)
            })

        }

        override fun onClick(view: View?) {
            if (view != null) {

                clickListener?.onItemClick(view, listitems.get(adapterPosition))
            }
        }

    }

     fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener=clickListener
    }


    interface ClickListener {
        fun onItemClick(v: View, item: item)
    }

}

