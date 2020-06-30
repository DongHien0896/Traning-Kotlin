package com.framgia.kotlintraining.moviedb.screen.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.databinding.library.BuildConfig
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseActivity
import com.framgia.kotlintraining.moviedb.pagingsample.main.PagingDbFragment
import com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byItem.ui.PagingByItemFragment
import com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byPage.ui.PagingByPageKeyFragment
import com.framgia.kotlintraining.moviedb.screen.home.HomeFragment
import com.framgia.kotlintraining.moviedb.utils.checkNetworkConnection
import com.framgia.kotlintraining.moviedb.utils.constant.Constants
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayout(): Int = R.layout.activity_main

    override val viewModel by viewModel<MainViewModel>()

    private val fragmentHome = supportFragmentManager.findFragmentByTag(Constants.TAG_HOME_FRAGMENT)
        ?: HomeFragment.newInstance()

    override fun initComponent(saveInstantState: Bundle?) {
        if (checkNetworkConnection(Context.CONNECTIVITY_SERVICE).not()) {
            showInformationDialog()
        }
        if (saveInstantState == null) {
            viewModel.apply {}
            addFragment(
                fragmentHome,
                R.id.frame_container,
                Constants.TAG_HOME_FRAGMENT,
                false
            )
        }
        setEvenBottomNavigation()
    }

    private fun setEvenBottomNavigation() {
//        val fragmentFavorite =
//            supportFragmentManager.findFragmentByTag(Constant.TAG_FAVORITE_FRAGMENT)
//                ?: FavoriteFragment.newInstance()

        val fragmentDbFragment =
            supportFragmentManager.findFragmentByTag(Constants.TAG_PAGING_DB_FRAGMENT)
                ?: PagingDbFragment.newInstance(application)
        val fragmentPagingByitem =
            supportFragmentManager.findFragmentByTag(Constants.TAG_PAGING_BY_ITEM)
                ?: PagingByItemFragment.newInstance()
        val fragmentPage = supportFragmentManager.findFragmentByTag(Constants.TAG_PAGING_BY_PAGE)
            ?: PagingByPageKeyFragment.newInstance()

        navigation_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    navigation_bottom.menu.getItem(1).isChecked = false
                    navigation_bottom.menu.getItem(2).isChecked = false
                    navigation_bottom.menu.getItem(3).isChecked = false
                    it.isChecked = true
                    replaceFragment(
                        fragmentHome,
                        R.id.frame_container,
                        Constants.TAG_HOME_FRAGMENT,
                        false
                    )
                }
//                R.id.navigation_favorite -> {
//                    navigation_bottom.menu.getItem(0).isChecked = false
//                    it.isChecked = true
//                    replaceFragment(
//                        fragmentFavorite,
//                        R.id.frame_container,
//                        Constant.TAG_FAVORITE_FRAGMENT,
//                        false
//                    )
//                }

                R.id.navigation_paging_db -> {
                    navigation_bottom.menu.getItem(0).isChecked = false
                    navigation_bottom.menu.getItem(2).isChecked = false
                    navigation_bottom.menu.getItem(3).isChecked = false
                    it.isChecked = true
                    replaceFragment(
                        fragmentDbFragment,
                        R.id.frame_container,
                        Constants.TAG_FAVORITE_FRAGMENT,
                        false
                    )
                }

                R.id.navigation_paging_item_key -> {
                    navigation_bottom.menu.getItem(0).isChecked = false
                    navigation_bottom.menu.getItem(1).isChecked = false
                    navigation_bottom.menu.getItem(3).isChecked = false
                    it.isChecked = true
                    replaceFragment(
                        fragmentPagingByitem,
                        R.id.frame_container,
                        Constants.TAG_PAGING_BY_ITEM,
                        false
                    )
                }
                R.id.navigation_paging_page_key -> {
                    navigation_bottom.menu.getItem(0).isChecked = false
                    navigation_bottom.menu.getItem(1).isChecked = false
                    navigation_bottom.menu.getItem(2).isChecked = false
                    it.isChecked = true
                    replaceFragment(
                        fragmentPage,
                        R.id.frame_container,
                        Constants.TAG_PAGING_BY_PAGE,
                        false
                    )
                }
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

    override fun onResume() {
        super.onResume()
        registrationToken()
    }

    private fun registrationToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = Objects.requireNonNull<InstanceIdResult>(task.result).token
                Log.d("-------->", "MainActivity - registrationToken: $token")
            })
    }

    private fun getDynamicLinks() {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                }
                if (deepLink != null) {
                    if (BuildConfig.VERSION_CODE < pendingDynamicLinkData.minimumAppVersion) {
                        //to the Play Store to upgrade the app.
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                            )
                        )
                    } else {
                        Log.d("MainActivity", "getDynamicLinks: ")
                    }
                }
            }
            .addOnFailureListener(OnFailureListener {
                Log.d("MainActivity", "getDynamicLinks: ${it.message}")
            })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.run {
            if (intent.hasExtra(Constants.TITLE)) {
                Log.d("-------->", "onNewIntent: has title")
            }
            if (intent.hasExtra(Constants.NAME)) {
                Log.d("-------->", "onNewIntent: has name")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.hasExtra(Constants.TITLE)) {
            Log.d("-------->", "onCreate: has title")
        }
        if (intent.hasExtra(Constants.NAME)) {
            Log.d("-------->", "MainActivity - onCreate: has name")
        }
    }
}
