package com.tresole.tresole.Util

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.tresole.tresole.MainActivity
import com.tresole.tresole.R
import java.lang.StringBuilder
import kotlin.random.Random

class Categories() {
    object Categories{

         val lifestyle = "Lifestyle"
         val home_appliances ="Home Appliances"
         val computer_parts ="Computer Parts"
         val game_consoles = "Game Consoles"
         val kitchen_and_cooking = "Kitchen and Cooking"
         val fahsion = "Fashion"
         val makeup = "Makeup"
         val groceries = "Groceries"
         val toys = "Toys"
         val home_entertainment = "Home Entertainment"
         val video_games ="Video Games"
         val cellphones = "Cellphones"
         val electronics = "Electronics"
        public fun returnrandomcategory():String
        {
            var ran= Random.nextInt(1,13)
            when (ran)
            {
                1-> return Categories.lifestyle
                2-> return Categories.cellphones
                3-> return Categories.computer_parts
                4-> return Categories.electronics
                5-> return Categories.fahsion
                6-> return Categories.game_consoles
                7-> return Categories.groceries
                8-> return Categories.home_appliances
                9-> return Categories.home_entertainment
                10-> return Categories.makeup
                11-> return Categories.toys
                12-> return Categories.video_games
                13-> return Categories.kitchen_and_cooking
                else -> return Categories.fahsion
            }

        }
        fun getlist() :MutableList<String>{
            var list = mutableListOf<String>()
            list.add(Categories.cellphones)
            list.add(Categories.lifestyle)
            list.add(Categories.computer_parts)
            list.add(Categories.electronics)
            list.add(Categories.fahsion)
            list.add(Categories.game_consoles)
            list.add(Categories.groceries)
            list.add(Categories.home_appliances)
            list.add(Categories.home_entertainment)
            list.add(Categories.makeup)
            list.add(Categories.toys)
            list.add(Categories.video_games)
            list.add(Categories.kitchen_and_cooking)
            return list
        }
    }

}