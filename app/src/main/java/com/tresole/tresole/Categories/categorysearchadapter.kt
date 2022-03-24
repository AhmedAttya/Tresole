package com.tresole.tresole.Categories

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.Util.item
import com.tresole.tresole.repository.repository

 class categorysearchadapter(
     listitems1: CategoyshowingFragment,
     private val listitems: MutableList<item>
 ) : RecyclerView.Adapter<categorysearchadapter.ViewHolder>() {
    var clickListener: ClickListener? = null
     val lifecycleOwner =listitems1
    var repo=repository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categorysearchadapter.ViewHolder {
        val context =parent.context
        val inflater= LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.linearitem,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: categorysearchadapter.ViewHolder, position: Int) {
        holder.bind(listitems.get(position))


    }

    private fun getimage(get: item): MutableLiveData<Bitmap> {
        return repo.getimage(get.itemimage)

    }

    override fun getItemCount(): Int {
        return listitems.size
    }
    inner class ViewHolder (itemview : View) : RecyclerView.ViewHolder(itemview), View.OnClickListener {
       val linearitemimage=itemview.findViewById<ImageView>(R.id.linearitemimageview)
        val simplepriceview = itemview.findViewById<TextView>(R.id.linearitemprice)
        val simpleitemview=itemview.findViewById<TextView>(R.id.linearitemname)
        val ratingbar=itemview.findViewById<RatingBar>(R.id.linearitemratingbar)
        init {
            // Define click listener for the ViewHolder's View.

            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }

        }
        fun bind(get: item) {
            val textView = simplepriceview
            val nametextview = simpleitemview
            textView.setText(get.itemprice.toString() + "  EGP")
            nametextview.setText(get.itemname)
            val imageview = linearitemimage
            getimage(get).observe(lifecycleOwner,
                {
                  imageview.setImageBitmap(it)
                })
            ratingbar.rating=rating(get)

        }

        override fun onClick(view: View?) {
            if (view != null) {

                clickListener?.onItemClick(view, listitems.get(adapterPosition))
            }
        }

    }
    fun rating(item :item): Float {
        var result :Float
        val itemratings = mutableListOf<Int>()
        itemratings.addAll(item.itemratings)
        var allratings = 0
        var count=item.itemratings.size
        while (itemratings.isNotEmpty())
        {
            allratings+=itemratings.removeAt(0)
        }
        result=allratings.toFloat() / count.toFloat()
        return result

    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener=clickListener
    }


    interface ClickListener {
        fun onItemClick(v: View, item: item)
    }




}
