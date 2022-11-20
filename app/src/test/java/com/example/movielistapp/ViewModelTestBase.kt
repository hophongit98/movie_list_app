package com.example.movielistapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
open class ViewModelTestBase {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}