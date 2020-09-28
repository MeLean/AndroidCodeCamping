package com.milen.androidcodecamping.onboarding.ui.view_pager.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import com.milen.androidcodecamping.R
import com.milen.androidcodecamping.onboarding.data.Screen
import com.milen.utils.*
import kotlinx.android.synthetic.main.fragment_onboarding_presentation.view.*

private const val ARG_SCREEN = "onboarding_screen"

/**
 * A simple [Fragment] subclass.
 * Use the [OnboardingPresentationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnboardingPresentationFragment : Fragment() {
    private var screen: Screen? = null
    private var pickerMinute : Int?= null
    private var pickerHour : Int?= null
    //TODO SEND  pickerHour pickerMinute for reminder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            screen = it.getParcelable(ARG_SCREEN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View? = inflater.inflate(R.layout.fragment_onboarding_presentation, container, false)
        setData(view, screen)
        return view
    }

    private fun setData(view: View?, screen: Screen?) {
        view?.let{
            SetDataUtils.setTextOrHide(it.onboarding_present_title, screen?.title)
            SetDataUtils.setTextOrHide(it.onboarding_present_text, screen?.text)
            SetDataUtils.setImageOrHide(it.onboarding_present_image, screen?.imageUrl)
            SetDataUtils.setAnimateUrlOrHide(it.onboarding_present_animation, screen?.animationUrl)
            SetDataUtils.setTextOrHide(it.onboarding_present_bottom_text, screen?.title)
            setTimePickerOrHide(it.onboarding_present_picker, screen?.reminderTime)
        }

    }

    private fun setTimePickerOrHide(timePicker: TimePicker?, reminderTime: String?) {
        if(SetDataUtils.isNullOrEmpty(reminderTime?.trim())){
            timePicker?.beGone()
        }else{
            setTimePickerTime(timePicker, reminderTime!!)
        }
    }

    private fun setTimePickerTime(timePicker: TimePicker?, reminderTime: String, ) {
        var hour : Int? = null
        var minute : Int? = null

        val delimiter = ":"
        if(reminderTime.contains(delimiter)){
            val timeArray = reminderTime.split(delimiter)
            hour = timeArray[0].trim().toIntOrNull()
            minute = timeArray[1].toIntOrNull()
        }

        timePicker?.addTime(hour ?: DEFAULT_HOUR,  minute ?: DEFAULT_MINUTE)

        timePicker?.setIs24HourView(true)
        timePicker?.setOnTimeChangedListener { _, hourOfDay, min ->
            this.pickerMinute = min
            this.pickerHour = hourOfDay
        }
    }


    companion object {
        private const val DEFAULT_HOUR = 19
        private const val DEFAULT_MINUTE = 0

        @JvmStatic
        fun newInstance(screen: Screen) =
            OnboardingPresentationFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SCREEN, screen)
                }
            }
    }
}