package com.framgia.kotlintraining.moviedb.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: ViewModel

    abstract fun initComponent(saveInstantState: Bundle?)

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayout())
        initComponent(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    internal fun addFragment(
        fragment: Fragment, container: Int, TAG: String,
        addToBackStack: Boolean, transit: Int = -1
    ) {
        val transaction = supportFragmentManager.beginTransaction()
            .add(container, fragment)

        addToBackStack?.let { if (it) transaction.addToBackStack(TAG) }
        transit?.let { if (it != -1) transaction.setTransition(it) }
        transaction.commit()

    }

    internal fun replaceFragment(
        fragment: Fragment, container: Int, TAG: String?,
        addToBackStack: Boolean, transit: Int = -1
    ) {
        val transaction = supportFragmentManager!!.beginTransaction()
            .replace(container, fragment)

        addToBackStack?.let { if (it) transaction.addToBackStack(TAG) }
        transit?.let { if (it != -1) transaction.setTransition(it) }
        transaction.commit()
    }
}
