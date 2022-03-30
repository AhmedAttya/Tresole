package com.tresole.tresole.categories

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
import com.tresole.tresole.util.Item
import com.tresole.tresole.repository.Repository

 class Categorysearchadapter(
     fragment: CategoyshowingFragment,
     private val listitems: MutableList<Item>
 ) : RecyclerView.Adapter<Categorysearchadapter.ViewHolder>() {
    var clickListener: ClickListener? = null
     val lifecycleOwner =fragment
    private var repo=Repository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Categorysearchadapter.ViewHolder {
        val context =parent.context
        val inflater= LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.linearitem,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: Categorysearchadapter.ViewHolder, position: Int) {
        holder.bind(listitems[position])


    }

    private fun getimage(get: Item): MutableLiveData<Bitmap> {
        return repo.getimage(get.itemimage)

    }

    override fun getItemCount(): Int {
        return listitems.size
    }
    inner class ViewHolder (itemview : View) : RecyclerView.ViewHolder(itemview), View.OnClickListener {
       private val linearitemimage: ImageView =itemview.findViewById(R.id.linearitemimageview)
        private val simplepriceview: TextView = itemview.findViewById(R.id.linearitemprice)
        private val simpleitemview: TextView =itemview.findViewById(R.id.linearitemname)
        private val ratingbar: RatingBar =itemview.findViewById(R.id.linearitemratingbar)
        init {
            // Define click listener for the ViewHolder's View.

            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }

        }
        fun bind(get: Item) {
            val textView = simplepriceview
            val nametextview = simpleitemview
            textView.text = get.itemprice.toString() + lifecycleOwner.context!!.getText(R.string.EGP)
            nametextview.text = get.itemname
            val imageview = linearitemimage
            getimage(get).observe(lifecycleOwner,
                {
                  imageview.setImageBitmap(it)
                })
            ratingbar.rating=rating(get)

        }

        override fun onClick(view: View?) {
            if (view != null) {

                clickListener?.onItemClick(view, listitems[adapterPosition])
            }
        }

    }
    fun rating(Item :Item): Float {
        val result :Float
        val itemratings = mutableListOf<Int>()
        itemratings.addAll(Item.itemratings)
        var allratings = 0
        val count=Item.itemratings.size
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
        fun onItemClick(v: View, Item: Item)
    }




}
