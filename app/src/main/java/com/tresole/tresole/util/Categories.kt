package com.tresole.tresole.util


import kotlin.random.Random

class Categories() {
    object Categories{

         const val lifestyle = "Lifestyle"
         const val home_appliances ="Home Appliances"
         const val computer_parts ="Computer Parts"
         const val game_consoles = "Game Consoles"
         const val kitchen_and_cooking = "Kitchen and Cooking"
         const val fahsion = "Fashion"
         const val makeup = "Makeup"
         const val groceries = "Groceries"
         const val toys = "Toys"
         const val home_entertainment = "Home Entertainment"
         const val video_games ="Video Games"
         const val cellphones = "Cellphones"
         const val electronics = "Electronics"
         fun returnrandomcategory():String
        {
            when (Random.nextInt(1,13))
            {
                1-> return lifestyle
                2-> return cellphones
                3-> return computer_parts
                4-> return electronics
                5-> return fahsion
                6-> return game_consoles
                7-> return groceries
                8-> return home_appliances
                9-> return home_entertainment
                10-> return makeup
                11-> return toys
                12-> return video_games
                13-> return kitchen_and_cooking
                else -> return fahsion
            }

        }
        fun getlist() :MutableList<String>{
            var list = mutableListOf<String>()
            list.add(cellphones)
            list.add(lifestyle)
            list.add(computer_parts)
            list.add(electronics)
            list.add(fahsion)
            list.add(game_consoles)
            list.add(groceries)
            list.add(home_appliances)
            list.add(home_entertainment)
            list.add(makeup)
            list.add(toys)
            list.add(video_games)
            list.add(kitchen_and_cooking)
            return list
        }
    }

}