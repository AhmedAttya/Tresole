package com.tresole.tresole

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.tresole.tresole.Util.datacreation
import com.tresole.tresole.repository.repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
         val context=this.applicationContext
        super.onCreate(savedInstanceState)
        val repo=repository()
        repo.initiate()

        setContentView(R.layout.activity_main)

        val toolbar =findViewById<View>(R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
            getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.outline_list_24)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.menu,menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       super.onOptionsItemSelected(item)
       when(item.itemId)
       {
          R.id.search_menu_item -> findNavController(R.id.nav_host_fragment_container).navigate(R.id.action_global_search2)
          R.id.account ->findNavController(R.id.nav_host_fragment_container).navigate(R.id.action_global_account2)
          android.R.id.home -> findNavController(R.id.nav_host_fragment_container).navigate(R.id.action_global_categoriesFragment)
          R.id.shoppingkart ->findNavController(R.id.nav_host_fragment_container).navigate(R.id.action_global_kartFragment)
       }

       return true
    }

}