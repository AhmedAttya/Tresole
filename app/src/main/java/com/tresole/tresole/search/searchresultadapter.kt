package com.tresole.tresole.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.Util.Categories
import com.tresole.tresole.Util.item

class searchresultadapter(private val listitems:MutableList<item>) : RecyclerView.Adapter<searchresultadapter.ViewHolder>() {
    var clickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchresultadapter.ViewHolder {
        val context =parent.context
        val inflater= LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.linearitem,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: searchresultadapter.ViewHolder, position: Int) {
        holder.bind(listitems.get(position))


    }

    private fun getimage(get: item): Int {
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
            imageview.setImageResource(getimage(get))
            ratingbar.rating=rating(get)

        }

        override fun onClick(view: View?) {
            if (view != null) {

                clickListener?.onItemClick(view, listitems.get(adapterPosition))
            }
        }

    }
    fun rating(item : item): Float {
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
