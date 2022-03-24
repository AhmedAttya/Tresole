package com.tresole.tresole.Categories

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.Main.ItemAdapter
import com.tresole.tresole.R
import com.tresole.tresole.Util.Categories
import java.lang.StringBuilder


class categoriesadapter(private val list: MutableList<String>) : RecyclerView.Adapter<categoriesadapter.ViewHolder>() {
    var clickListener: categoriesadapter.ClickListener? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): categoriesadapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val categoryview = inflater.inflate(R.layout.categoryitem, parent, false)
        return ViewHolder(categoryview)
    }


    override fun onBindViewHolder(holder: categoriesadapter.ViewHolder, position: Int) {
        holder.bind(list.get(position))

    }

    inner class ViewHolder(categoryview: View) : RecyclerView.ViewHolder(categoryview),
        View.OnClickListener {
        val categoryicon = categoryview.findViewById<ImageView>(R.id.categoryicon)
        val categoryname = categoryview.findViewById<TextView>(R.id.categorynametextview)

        init {
            // Define click listener for the ViewHolder's View.

            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }

        }

        override fun onClick(view: View?) {
            if (view != null) {

                clickListener?.onItemClick(view, list.get(adapterPosition))
            }
        }
     fun bind(categorynam: String)
    {
        val nametextview = categoryname
        nametextview.setText(categorynam)
        val imageview = categoryicon
        imageview.setImageResource(getimage(categorynam))
    }
}



    interface ClickListener {
        fun onItemClick(v: View, categoriesname: String)
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener=clickListener
    }


    override fun getItemCount(): Int {
        return list.size
    }
    private fun getimage(categoryname: String): Int {
      when(categoryname){
              Categories.Categories.lifestyle -> return R.drawable.lifestyleicon
          Categories.Categories.home_appliances ->return R.drawable.homeappliancesicon
          Categories.Categories.computer_parts  ->return R.drawable.computericon
          Categories.Categories.game_consoles ->return R.drawable.gameconsolesicon
          Categories.Categories.kitchen_and_cooking ->return R.drawable.cookingicon
          Categories.Categories.fahsion ->return R.drawable.fashionicon
          Categories.Categories.makeup ->return R.drawable.mekeupicon
          Categories.Categories.groceries ->return R.drawable.groceriesicon
          Categories.Categories.toys ->return R.drawable.toysicon
          Categories.Categories.home_entertainment ->return R.drawable.tvicon
          Categories.Categories.video_games ->return R.drawable.videogamesicon
          Categories.Categories.cellphones ->return R.drawable.cellphoneicon
          Categories.Categories.electronics ->return R.drawable.electronicsicon
              else -> return R.drawable.cellphoneicon
      }
    }
}