package com.framgia.kotlintraining.moviedb.screen.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseActivity
import com.framgia.kotlintraining.moviedb.screen.favorite.FavoriteFragment
import com.framgia.kotlintraining.moviedb.screen.home.HomeFragment
import com.framgia.kotlintraining.moviedb.utils.checkNetworkConnection
import com.framgia.kotlintraining.moviedb.utils.constant.Constant
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayout(): Int = R.layout.activity_main

    override val viewModel by viewModel<MainViewModel>()

    private val fragmentHome = supportFragmentManager.findFragmentByTag(Constant.TAG_HOME_FRAGMENT)
        ?: HomeFragment.newInstance()

    override fun initComponent(saveInstantState: Bundle?) {
        if (checkNetworkConnection(Context.CONNECTIVITY_SERVICE).not()) {
            showInformationDialog()
        }
        viewModel.apply {}
        addFragment(
            fragmentHome,
            R.id.frame_container,
            Constant.TAG_HOME_FRAGMENT,
            false
        )
        setEvenBottomNavigation()
    }

    private fun setEvenBottomNavigation() {
        val fragmentFavorite =
            supportFragmentManager.findFragmentByTag(Constant.TAG_FAVORITE_FRAGMENT)
                ?: FavoriteFragment.newInstance()

        navigation_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(
                        fragmentHome,
                        R.id.frame_container,
                        Constant.TAG_HOME_FRAGMENT,
                        false
                    )
                }
                R.id.navigation_favorite -> replaceFragment(
                    fragmentFavorite,
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
