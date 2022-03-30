package com.tresole.tresole.util

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.lang.StringBuilder
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.random.Random

class Datacreation {
   companion object{
       private val storage = Firebase.storage
       private val rtdb =Firebase.database.reference
       private var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
       var storageRef: StorageReference = storage.reference

        fun createdata() {
           var i = 0
           while(i<130)
           {
               val dateday0 = Random.nextInt(1,28)
               val dateday :String = if(dateday0<10)
                   "0$dateday0"
               else
                   "$dateday0"

               val datemonth0=Random.nextInt(1,12)
               val datemonth :String = if(datemonth0<10)
                   "0$datemonth0"
               else
                   "$datemonth0"

               val dateyear=Random.nextInt(2018,2021)
               val date=LocalDate.parse("$dateday-$datemonth-$dateyear", formatter)
               //getting random category
               val cat=Categories.Categories.returnrandomcategory()
               val item =Item(randomString(50),Random.nextInt(1,10000),
                   Random.nextInt(1,100),randomRatings(),Random.nextInt(1,99),
                   Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()),getimagelocation(cat),
                   randomString(10),randomString(10),randomString(200),cat

               )
               rtdb.child("items").child(item.itemid.toString()).setValue(item)
              i++
           }

       }

       private fun getimagelocation(cat: String): String {
           when(cat){

               Categories.Categories.lifestyle -> return "lifestyle.png"
               Categories.Categories.cellphones -> return "cell phones.png"
               Categories.Categories.computer_parts -> return "computer parts.png"
               Categories.Categories.electronics -> return "electronics.png"
               Categories.Categories.fahsion -> return "IMG_5346.png"
               Categories.Categories.game_consoles -> return "game consoles.png"
               Categories.Categories.groceries -> return "groceries.png"
               Categories.Categories.home_appliances  -> return "home appliances.png"
               Categories.Categories.home_entertainment -> return "home entertinment.png"
               Categories.Categories.makeup  -> return "make up.png"
               Categories.Categories.toys  -> return "toys.png"
               Categories.Categories.video_games  -> return "video_gaames.png"
               Categories.Categories.kitchen_and_cooking  -> return "cooking.png"
               else -> return "cooking.png"
           }

       }

       private fun randomString(count :Int) :String {

           val stringBuilder = StringBuilder()
           var ing = 0
           while (ing < count) {
               if(ing %10==0) {
                   stringBuilder.append(" ")
               }
               when(Random.nextInt(1,26)){
                   1 -> stringBuilder.append("a")
                   2 -> stringBuilder.append("b")
                   3 -> stringBuilder.append("c")
                   4 -> stringBuilder.append("d")
                   5 -> stringBuilder.append("e")
                   6 -> stringBuilder.append("f")
                   7 -> stringBuilder.append("g")
                   8 -> stringBuilder.append("h")
                   9 -> stringBuilder.append("i")
                   10 -> stringBuilder.append("j")
                   11 -> stringBuilder.append("k")
                   12 -> stringBuilder.append("l")
                   13 -> stringBuilder.append("m")
                   14 -> stringBuilder.append("n")
                   15 -> stringBuilder.append("o")
                   16 -> stringBuilder.append("p")
                   17 -> stringBuilder.append("q")
                   18 -> stringBuilder.append("r")
                   19 -> stringBuilder.append("s")
                   20 -> stringBuilder.append("t")
                   21 -> stringBuilder.append("u")
                   22 -> stringBuilder.append("v")
                   23 -> stringBuilder.append("w")
                   24 -> stringBuilder.append("x")
                   25 -> stringBuilder.append("y")
                   26 -> stringBuilder.append("z")
               }
               ing++
           }

           return stringBuilder.toString()
       }
        private fun randomRatings() :MutableList<Int>{
           val listofratings =mutableListOf<Int>()
           var ing=0
           while(ing<10)
           {
               listofratings.add(Random.nextInt(1,5))
               ing++
           }
           return listofratings
       }

}
}