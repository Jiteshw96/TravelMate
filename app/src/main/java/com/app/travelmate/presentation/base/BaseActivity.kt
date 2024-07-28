package com.app.travelmate.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var dataBinding: DB

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
    }

    private fun initViewBinding() {
        dataBinding = DataBindingUtil.setContentView(this, getLayout())
        setContentView(dataBinding.root)
        observeViewModel()
    }
}