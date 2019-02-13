package com.framgia.kotlintraining.moviedb.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: ViewModel

    abstract fun initComponent(saveInstantState: Bundle?)

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initComponent(savedInstanceState)
    }

    internal fun addFragment(fragment: Fragment, container: Int, TAG: String, addToBackStack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            add(container, fragment)
            if (addToBackStack) {
                addToBackStack(TAG)
            }
            setTransition(FragmentTransaction.TRANSIT_NONE)
            commit()
        }
    }

    internal fun replaceFragment(fragment: Fragment, container: Int, TAG: String?, addToBackStack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            replace(container, fragment)
            if (addToBackStack) {
                addToBackStack(TAG)
            }
            setTransition(FragmentTransaction.TRANSIT_NONE)
            commit()
        }
    }

    internal fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
