package com.tresole.tresole.kart

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

class Kartadapter(kartfragment: KartFragment, private val listitems: MutableList<Item>) : RecyclerView.Adapter<Kartadapter.ViewHolder>() {
    var clickListener: ClickListener? = null
    private var repo=Repository()
    val lifecycleowner=kartfragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Kartadapter.ViewHolder {
        val context =parent.context
        val inflater= LayoutInflater.from(context)
        val itemview =inflater.inflate(R.layout.kartlinearitem,parent,false)
        return ViewHolder(itemview)
    }


    override fun onBindViewHolder(holder: Kartadapter.ViewHolder, position: Int) {
        holder.bind(listitems[position])


    }

    private fun getimage(get: Item): MutableLiveData<Bitmap> {
        return repo.getimage(get.itemimage)

    }

    override fun getItemCount(): Int {
        return listitems.size
    }
    inner class ViewHolder (itemview : View) : RecyclerView.ViewHolder(itemview), View.OnClickListener {
        private val linearitemimage: ImageView =itemview.findViewById(R.id.kartlinearitemimageview)
        private val simplepriceview: TextView = itemview.findViewById(R.id.kartlinearitemprice)
        private val simpleitemview: TextView =itemview.findViewById(R.id.kartlinearitemname)
        private val delete: ImageView =itemview.findViewById(R.id.layout_list_delete)
        init {
            // Define click listener for the ViewHolder's View.

            if (clickListener != null) {
                delete.setOnClickListener(this)
            }

        }
        fun bind(get: Item) {
            val textView = simplepriceview
            val nametextview = simpleitemview
            textView.text = (get.itemprice-(get.itemprice/100*get.itemdiscount)).toString()+ lifecycleowner.context!!.getText(R.string.EGP)
            nametextview.text = get.itemname
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
