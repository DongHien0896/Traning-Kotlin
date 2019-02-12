package com.framgia.kotlintraining.moviedb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    lateinit var viewModel: ViewModel

    abstract val layoutRes: Int

    abstract fun initComponent(viewBinding: ViewBinding)

    internal fun replaceFragment(
        fragment: Fragment,
        container: Int,
        TAG: String?,
        addToBackStack: Boolean = false,
        transit: Int = -1
    ) {
        activity?.supportFragmentManager!!.beginTransaction().apply {
            replace(container, fragment)
            addToBackStack(TAG)
            setTransition(FragmentTransaction.TRANSIT_NONE)
            commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewBinding: ViewBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        val view = viewBinding.root
        viewBinding.apply {
            setLifecycleOwner(viewLifecycleOwner)
            root.isClickable = true
        }
        initComponent(viewBinding)
        lifecycle.addObserver(viewModel)
        return view
    }

    open fun onBackPress() {}
}