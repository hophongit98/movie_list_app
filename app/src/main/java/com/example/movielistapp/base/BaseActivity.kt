package com.example.movielistapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialise()

        observeViewModel()
    }

    protected open fun initialise() {}

    protected open fun observeViewModel() {}
}