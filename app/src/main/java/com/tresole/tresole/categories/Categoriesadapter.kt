package com.tresole.tresole.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tresole.tresole.R
import com.tresole.tresole.util.Categories


class Categoriesadapter(private val list: MutableList<String>) : RecyclerView.Adapter<Categoriesadapter.ViewHolder>() {
    var clickListener :ClickListener? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Categoriesadapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val categoryview = inflater.inflate(R.layout.categoryitem, parent, false)
        return ViewHolder(categoryview)
    }


    override fun onBindViewHolder(holder: Categoriesadapter.ViewHolder, position: Int) {
        holder.bind(list[position])

    }

    inner class ViewHolder(categoryview: View) : RecyclerView.ViewHolder(categoryview),
        View.OnClickListener {
        private val categoryicon :ImageView = categoryview.findViewById(R.id.categoryicon)
        private val categoryname :TextView= categoryview.findViewById(R.id.categorynametextview)

        init {
            // Define click listener for the ViewHolder's View.

            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }

        }

        override fun onClick(view: View?) {
            if (view != null) {

                clickListener?.onItemClick(view, list[adapterPosition])
            }
        }
     fun bind(categorynam: String)
    {
        val nametextview = categoryname
        nametextview.text = categorynam
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