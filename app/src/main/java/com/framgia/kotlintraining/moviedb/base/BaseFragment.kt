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

    abstract val viewModel: ViewModel

    abstract val layoutRes: Int

    abstract fun initComponent(viewBinding: ViewBinding)

    internal fun replaceFragment(
        fragment: Fragment,
        container: Int,
        TAG: String?,
        addToBackStack: Boolean
    ) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(container, fragment)
            if (addToBackStack) {
                addToBackStack(TAG)
            }
            setTransition(FragmentTransaction.TRANSIT_NONE)
            commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewBinding: ViewBinding =
            DataBindingUtil.inflate(inflater, layoutRes, container, false)
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