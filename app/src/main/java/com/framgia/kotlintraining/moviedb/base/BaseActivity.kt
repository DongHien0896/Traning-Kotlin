package com.framgia.kotlintraining.moviedb.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: ViewModel

    abstract fun initComponent(saveInstantState: Bundle?)

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayout())
        initComponent(savedInstanceState)
    }

    internal fun addFragment(
        fragment: Fragment, container: Int, TAG: String,
        addToBackStack: Boolean, transit: Int = -1
    ) {
        supportFragmentManager.beginTransaction().apply {
            add(container, fragment)
            addToBackStack(TAG)
            setTransition(FragmentTransaction.TRANSIT_NONE)
            commit()
        }
    }

    internal fun replaceFragment(
        fragment: Fragment, container: Int, TAG: String?,
        addToBackStack: Boolean, transit: Int = -1
    ) {
        supportFragmentManager.beginTransaction().apply {
            replace(container, fragment)
            addToBackStack(TAG)
            setTransition(FragmentTransaction.TRANSIT_NONE)
            commit()
        }
    }
}
