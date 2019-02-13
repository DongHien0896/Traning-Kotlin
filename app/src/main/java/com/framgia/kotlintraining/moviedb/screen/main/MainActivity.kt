package com.framgia.kotlintraining.moviedb.screen.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseActivity
import com.framgia.kotlintraining.moviedb.screen.favorite.FavoriteFragment
import com.framgia.kotlintraining.moviedb.screen.home.HomeFragment
import com.framgia.kotlintraining.moviedb.utils.checkNetworkConnection
import com.framgia.kotlintraining.moviedb.utils.constant.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayout(): Int = R.layout.activity_main

    override fun initComponent(saveInstantState: Bundle?) {
        if (checkNetworkConnection(Context.CONNECTIVITY_SERVICE).not()) {
            showInformationDialog()
        }
        ViewModelProviders.of(this).get(MainViewModel::class.java).apply {}
        addFragment(
            HomeFragment.newInstance(),
            R.id.frame_container,
            Constant.TAG_HOME_FRAGMENT,
            false
        )
        setEvenBottomNavigation()
    }


    private fun setEvenBottomNavigation() {
        navigation_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> replaceFragment(
                    HomeFragment.newInstance(),
                    R.id.frame_container,
                    Constant.TAG_HOME_FRAGMENT,
                    false
                )
                R.id.navigation_favorite -> replaceFragment(
                    FavoriteFragment.newInstance(),
                    R.id.frame_container,
                    Constant.TAG_FAVORITE_FRAGMENT,
                    false
                )
            }
            false
        }
    }

    private fun showInformationDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_oops))
            .setMessage(getString(R.string.msg_no_network))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.title_try_again)) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
