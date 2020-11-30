package com.example.mydaggerapplication.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.mydaggerapplication.R
import com.example.mydaggerapplication.databinding.ActivityMainBinding
import com.example.mydaggerapplication.di.annotation.MainScope
import com.example.mydaggerapplication.ui.BaseActivity
import com.example.mydaggerapplication.ui.main.profile.ProfileFragment

@MainScope
class MainActivity: BaseActivity(){

    private val vBinding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vBinding.root)

        setProfileFragment()
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
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setProfileFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.container_fragment, ProfileFragment())
            .commit()
    }
}