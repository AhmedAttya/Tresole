package com.tresole.tresole.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.util.Categories
import com.tresole.tresole.util.Item

class Searchresultadapter(fragment: SearchResultFragment, private val listitems: MutableList<Item>) : RecyclerView.Adapter<Searchresultadapter.ViewHolder>() {
    var clickListener: ClickListener? = null
    val lifecycleOwner=fragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Searchresultadapter.ViewHolder {
        val context =parent.context
        val inflater= LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.linearitem,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: Searchresultadapter.ViewHolder, position: Int) {
        holder.bind(listitems[position])


    }

    private fun getimage(get: Item): Int {
        when(get.itemcategory)
        { Categories.Categories.lifestyle -> return R.drawable.lifestyle
            Categories.Categories.cellphones -> return R.drawable.cell_phones
            Categories.Categories.computer_parts -> return R.drawable.computer_parts
            Categories.Categories.electronics -> return R.drawable.electronics
            Categories.Categories.fahsion -> return R.drawable.test
            Categories.Categories.game_consoles -> return R.drawable.game_consoles
            Categories.Categories.groceries -> return R.drawable.groceries
            Categories.Categories.home_appliances  -> return R.drawable.home_appliances
            Categories.Categories.home_entertainment -> return R.drawable.home_entertinment
            Categories.Categories.makeup  -> return R.drawable.make_up
            Categories.Categories.toys  -> return R.drawable.toys
            Categories.Categories.video_games  -> return R.drawable.video_gaames
            Categories.Categories.kitchen_and_cooking  -> return R.drawable.cooking
            else -> return R.drawable.test
        }

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
            textView.text = get.itemprice.toString() +lifecycleOwner.getText(R.string.EGP)
            nametextview.text = get.itemname
            val imageview = linearitemimage
            imageview.setImageResource(getimage(get))
            ratingbar.rating=rating(get)

        }

        override fun onClick(view: View?) {
            if (view != null) {

                clickListener?.onItemClick(view, listitems[adapterPosition])
            }
        }

    }
    fun rating(Item : Item): Float {
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
