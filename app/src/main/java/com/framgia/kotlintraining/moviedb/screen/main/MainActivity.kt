package com.framgia.kotlintraining.moviedb.screen.main

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayout(): Int = R.layout.activity_main

    override fun initComponent(saveInstantState: Bundle?) {
        ViewModelProviders.of(this).get(MainViewModel::class.java).apply {
            checkNetworkConnection(Context.CONNECTIVITY_SERVICE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {

            }
        }
    }
}
