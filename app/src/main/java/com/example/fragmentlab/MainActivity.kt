package com.example.fragmentlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var isFragmentDisplayed = false

    val STATE_FRAGMENT = "state_of_fragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                open_button.setText(R.string.close)
            }
        }
        // Set the click listener for the button.
        open_button.setOnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()
            }
        }
    }

    private fun displayFragment(): Unit {
        val simpleFragment = SimpleFragment.newInstance()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()

        fragmentTransaction.add(
            R.id.fragment_container,
            simpleFragment
        ).addToBackStack(null).commit()

        open_button.setText(R.string.close)
        isFragmentDisplayed = true
    }

    fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val simpleFragment = fragmentManager
            .findFragmentById(R.id.fragment_container) as SimpleFragment
        if (simpleFragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }
        open_button.setText(R.string.open)
        isFragmentDisplayed = false
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }
}
