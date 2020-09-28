package com.milen.androidcodecamping.onboarding.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.milen.androidcodecamping.HomeActivity
import com.milen.androidcodecamping.R
import com.milen.androidcodecamping.dialogs.CustomDialogFragment
import com.milen.androidcodecamping.dialogs.CustomDialogModel
import com.milen.androidcodecamping.onboarding.data.OnboardingOption
import com.milen.androidcodecamping.onboarding.data.OnboardingScenario
import com.milen.androidcodecamping.onboarding.data.Screen
import com.milen.androidcodecamping.onboarding.di.DaggerOnboardingDIComponent
import com.milen.androidcodecamping.onboarding.di.OnBoardingDIModule
import com.milen.androidcodecamping.onboarding.services.MockedBackEnd
import com.milen.androidcodecamping.onboarding.ui.OnboardingConstants.INITIAL_ANIMATION
import com.milen.androidcodecamping.onboarding.ui.OnboardingConstants.INITIAL_OPTION_ID
import com.milen.androidcodecamping.onboarding.ui.OnboardingConstants.INITIAL_SCREEN_INDEX
import com.milen.androidcodecamping.onboarding.ui.view_pager.OnboardingPagerAdapter
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.OnboardingMainFragment
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.ViewPagerOnPageSelected
import com.milen.utils.beGone
import com.milen.utils.beVisible
import com.milen.utils.startIntentAndFinish

import kotlinx.android.synthetic.main.activity_onboarding.*
import javax.inject.Inject

class OnboardingActivity : AppCompatActivity(), View.OnClickListener{
    @Inject
    lateinit var mockedBackEnd: MockedBackEnd

    private var selectedOptionId: Int = INITIAL_OPTION_ID
    private var selectedScreen: Int = INITIAL_SCREEN_INDEX
    private lateinit var onboardingScenario: OnboardingScenario
    private lateinit var onboardingPagerAdapter: OnboardingPagerAdapter
    private var dialog : CustomDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        initDependencies()

        onboardingScenario = mockedBackEnd.getOnboardingScenario()

        setupUI()
    }

    override fun onStop() {
        super.onStop()
        dialog?.dismiss()
    }

    //TODO handle saved instance if needed

    private fun setMainFragment() {
        showMainFragment(true)

        val mainFragment: Fragment =
            supportFragmentManager.findFragmentByTag(OnboardingMainFragment.TAG)
                ?: OnboardingMainFragment.newInstance(onboardingScenario.onboardingOptions)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_holder, mainFragment, OnboardingMainFragment.TAG)
            .commit()
    }

    private fun setupUI() {
        setTopAnimation()
        when (selectedOptionId) {
            INITIAL_OPTION_ID -> setMainFragment()
            else -> setViewPager()
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        onboarding_btn_next.setOnClickListener{
            selectedScreen++

            if(onboardingPagerAdapter.count > selectedScreen){
                onboarding_pager?.setCurrentItem(selectedScreen, true)
            }else{
                //TODO show POPUP and string res / constants
                dialog = CustomDialogFragment.newInstance(CustomDialogModel(
                    "TITLE TEST",
                    "TEXT TEST",
                    "round_burst.json",
                    "logoloader.json",
                    null,
                    "Back to Tutorials" to this,
                    "Home menu" to this,
                ))
                dialog!!.show(supportFragmentManager, CustomDialogFragment.FRAG_TAG)
            }
        }

        onboarding_btn_skip.setOnClickListener {
            navigateToHomeScreen()
        }
    }

    private fun setViewPager() {
        showMainFragment(false)

        val screens : List<Screen> = getScreensByOptionId()
        onboardingPagerAdapter =
            OnboardingPagerAdapter(supportFragmentManager, screens)
        onboarding_pager?.adapter = onboardingPagerAdapter
        onboarding_pager?.addOnPageChangeListener(
            ViewPagerOnPageSelected(this@OnboardingActivity::onPageSelected)
        )

    }

    private fun showMainFragment(toShow: Boolean) {
        when (toShow) {
            true -> {
                main_fragment_holder?.beVisible()
                onboarding_pager?.beGone()
                onboarding_btn_next.beGone()
            }
            else -> {
                main_fragment_holder?.beGone()
                onboarding_pager?.beVisible()
                onboarding_btn_next.beVisible()
            }
        }
    }

    //used as reference in viewpager
    private fun onPageSelected(position: Int) {
        selectedScreen = position
    }

    private fun setTopAnimation(animation: String = INITIAL_ANIMATION, isRunning: Boolean = false) {
        dynamic_lottie?.setAnimation(animation)
        if (isRunning) dynamic_lottie?.playAnimation()
    }

    private fun initDependencies() {
        DaggerOnboardingDIComponent.builder()
            .onBoardingDIModule(OnBoardingDIModule(this))
            .build().inject(this)
    }

    private fun getScreensByOptionId() : List<Screen>{
        val option = (onboardingScenario.onboardingOptions.find {
            it.id == selectedOptionId
        })

        return option?.screens ?: listOf()
    }


    //TODO USE EVENT BUS
    fun onOptionSelected(option: OnboardingOption) {
        this.selectedOptionId = option.id
        this.selectedScreen = 0 //restart the screens

        dynamic_lottie.playAnimation()
        setViewPager()
    }

    //TODO USE EVENT BUS
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.dialog_left_btn -> showInitialOnboarding()
            R.id.dialog_right_btn -> navigateToHomeScreen()
            R.id.dialog_center_btn -> dialog?.dismiss()
        }
    }

    private fun showInitialOnboarding() {
        dialog?.dismiss()

        selectedOptionId = INITIAL_OPTION_ID
        selectedScreen = INITIAL_SCREEN_INDEX
        showMainFragment(true)
    }


    private fun navigateToHomeScreen() {
        dialog?.dismiss()

        startIntentAndFinish(HomeActivity::class.java)
    }
}