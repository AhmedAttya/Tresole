package com.tresole.tresole.main

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.util.Item
import com.tresole.tresole.repository.Repository


class ItemAdapter(storefragment: StoreFragment, private val listitems: MutableList<Item>?) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    var clickListener: ClickListener? = null
    val lifecycleowner=storefragment
    private var repo=Repository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
       val context =parent.context
        val inflater=LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.simpleitem,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
      holder.bind(listitems!![position])


    }

    private fun getimage(get: Item): MutableLiveData<Bitmap> {
        return repo.getimage(get.itemimage)

    }

    override fun getItemCount(): Int {
       return listitems!!.size
    }
    inner class ViewHolder (itemview : View) :RecyclerView.ViewHolder(itemview),View.OnClickListener {
        private val simpleimage: ImageView =itemview.findViewById(R.id.imageView)
        private val simplepriceview: TextView = itemview.findViewById(R.id.simpleitemprice)
        private val simpleitemview: TextView =itemview.findViewById(R.id.simpleitemname)
        init {
            // Define click listener for the ViewHolder's View.

            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }

        }
        fun bind(Item: Item) {
            val textView = simplepriceview
            val nametextview = simpleitemview
            textView.text = (Item.itemprice-(Item.itemprice/100*Item.itemdiscount)).toString()+ lifecycleowner.requireContext().getText(R.string.EGP)
            nametextview.text = Item.itemname
            val imageview = simpleimage
            imageview.setImageBitmap(getimage(Item).value)

            getimage(Item).observe(lifecycleowner,{
                imageview.setImageBitmap(it)
            })

        }

        override fun onClick(view: View?) {
            if (view != null) {

                clickListener?.onItemClick(view, listitems!![adapterPosition])
            }
        }

    }

     fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener=clickListener
    }


    interface ClickListener {
        fun onItemClick(v: View, Item: Item)
    }

}

