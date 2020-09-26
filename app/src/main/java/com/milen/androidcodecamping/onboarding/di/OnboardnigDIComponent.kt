package com.milen.androidcodecamping.onboarding.di

import com.milen.androidcodecamping.onboarding.ui.OnboardingActivity
import dagger.Component

@Component(modules = [OnBoardingDIModule::class])
interface OnboardingDIComponent {
    fun inject(app: OnboardingActivity)
}
