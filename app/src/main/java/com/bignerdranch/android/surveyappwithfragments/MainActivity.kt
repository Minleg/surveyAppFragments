package com.bignerdranch.android.surveyappwithfragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.setFragmentResultListener(RESULT_SENT, this) {
            requestKey, bundle ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ResultFragment.newInstance(), "RESULT")
                .addToBackStack("RESULTFRAGMENT")
                .commit()
        }
    }
}
