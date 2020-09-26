package com.milen.androidcodecamping.onboarding.ui.view_pager.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import com.airbnb.lottie.LottieAnimationView
import com.milen.androidcodecamping.R
import com.milen.androidcodecamping.onboarding.data.Screen
import com.milen.androidcodecamping.onboarding.ui.OnboardingConstants.DEFAULT_ANIMATION
import com.milen.utils.addTime
import com.milen.utils.animateFromUrlOrDefault
import com.milen.utils.beGone
import com.milen.utils.loadUrl
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
            setTextOrHide(it.onboarding_present_title, screen?.title)
            setTextOrHide(it.onboarding_present_text, screen?.text)
            setAnimationOrHide(it.onboarding_present_animation, screen?.animationUrl)
            setImageOrHide(it.onboarding_present_image, screen?.imageUrl)
            setTimePickerOrHide(it.onboarding_present_picker, screen?.reminderTime)
            setTextOrHide(it.onboarding_present_bottom_text, screen?.title)
        }

    }

    private fun setTimePickerOrHide(timePicker: TimePicker?, reminderTime: String?) {
        if(isNullOrEmpty(reminderTime?.trim())){
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

    private fun setImageOrHide(imageView: ImageView?, imageUrl: String?) {
        if(isNullOrEmpty(imageUrl?.trim())){
            imageView?.beGone()
        }else{
            imageView?.loadUrl(imageUrl!!)
        }
    }

    private fun setAnimationOrHide(animationView: LottieAnimationView?, animationUrl: String?) {
        if(isNullOrEmpty(animationUrl?.trim())){
            animationView?.beGone()
        }else{
            animationView?.animateFromUrlOrDefault(animationUrl, DEFAULT_ANIMATION)
        }
    }

    private fun setTextOrHide( textView: TextView?, text: String?) {
        if(isNullOrEmpty(text?.trim())){
            textView?.beGone()
        }else{
            textView?.text = text
        }
    }

    private fun isNullOrEmpty(text: String?): Boolean {
        return text == null || text.isEmpty()
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