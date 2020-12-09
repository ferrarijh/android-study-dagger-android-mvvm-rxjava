package com.example.mydaggerapplication.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.mydaggerapplication.R
import com.example.mydaggerapplication.databinding.ActivityMainBinding
import com.example.mydaggerapplication.di.annotation.MainScope
import com.example.mydaggerapplication.ui.BaseActivity
import com.example.mydaggerapplication.ui.main.profile.ProfileFragment
import com.google.android.material.navigation.NavigationView

@MainScope
class MainActivity: BaseActivity(), NavigationView.OnNavigationItemSelectedListener{

    private val vBinding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private val navController by lazy{Navigation.findNavController(this, R.id.fragment_nav_host)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vBinding.root)

        setNavComponent()

//        setProfileFragment()
        setAuthObserver()
//        setTextObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.log_out -> sessionManager.logOut()
            android.R.id.home -> {
                return if (vBinding.drawerLayout.isOpen) {
                    vBinding.drawerLayout.close()
                    true
                } else
                    false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setNavComponent(){
        NavigationUI.setupActionBarWithNavController(this, navController, vBinding.drawerLayout)    //integrate home button with Nav Comps
//        NavigationUI.setupWithNavController(vBinding.navView, navController)    //TODO("??")
        vBinding.navView.setNavigationItemSelectedListener(this)    //route to onNavigationItemSelected()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){  //defined in menu_drawer.xml
            R.id.nav_profile->{
                if(isValidDestination(R.id.profileFragment)){
                    NavOptions.Builder()
                        .setPopUpTo(R.id.profileFragment, true)
                        .build()
                        .let{navController.navigate(R.id.profileFragment, null, it)}   //defined in nav_graph.xml
                }
            }
            R.id.nav_posts->{
                if (isValidDestination(R.id.postsFragment)) { //prevent stacking up same fragments - NOT R.id.nav_posts
                    navController.navigate(R.id.postsFragment)
                }
            }
        }

        vBinding.drawerLayout.close()
        return true
    }

    private fun isValidDestination(id: Int): Boolean{
        return id != navController.currentDestination!!.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, vBinding.drawerLayout)
    }
}