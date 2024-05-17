package com.cr.viewbindingdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T:ViewBinding> : AppCompatActivity() {

    /**
     * 1. BaseActivity中声明一个泛型T且父类为ViewBinding的变量，该变量与具体的xml相关，所以BaseActivity中
     * 提供一个名为getViewBinding的抽象方法，并将其交给子类去实现，且在onCreate中为Activity设置了对应的根布局
     *
     *
     *
     *
     */
    lateinit var mViewBinding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
        setContentView(mViewBinding.root)
    }

    abstract fun getViewBinding() : T
}