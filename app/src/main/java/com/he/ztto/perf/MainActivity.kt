package com.he.ztto.perf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.he.ztto.perf.annotations.Monitor


class MainActivity : AppCompatActivity() {
    @Monitor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}