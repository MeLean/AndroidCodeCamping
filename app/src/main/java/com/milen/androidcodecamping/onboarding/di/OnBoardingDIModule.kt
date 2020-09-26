package com.milen.androidcodecamping.onboarding.di

import android.content.Context
import com.milen.androidcodecamping.onboarding.services.MockedBackEnd
import com.milen.androidcodecamping.onboarding.ui.view_pager.OnboardingPagerAdapter
import dagger.Module
import dagger.Provides

@Module
class OnBoardingDIModule(private val context: Context) {

    @Provides
    fun providesMockedBackend(): MockedBackEnd {
        return MockedBackEnd(context)
    }
}