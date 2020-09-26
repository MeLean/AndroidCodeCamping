package com.milen.androidcodecamping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.milen.androidcodecamping.onboarding.ui.OnboardingActivity
import com.milen.utils.animateFromUrlOrDefault
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationView.animateFromUrlOrDefault(
            "https://assets7.lottiefiles.com/packages/lf20_cepjdast.json",
            "idb_logo.json"
        )

        goOnboarding.setOnClickListener {
            startActivity(Intent(this, OnboardingActivity::class.java))
        }
    }
}