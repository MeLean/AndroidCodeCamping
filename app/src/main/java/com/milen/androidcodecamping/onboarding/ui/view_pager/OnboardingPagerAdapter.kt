package com.milen.androidcodecamping.onboarding.ui.view_pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.milen.androidcodecamping.onboarding.data.Screen
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.OnboardingPresentationFragment

class OnboardingPagerAdapter(
    fm: FragmentManager,
    private val screens : List<Screen>,
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return screens.size
    }

    override fun getItem(position: Int): Fragment {
        return OnboardingPresentationFragment.newInstance(screens[position])
    }
}