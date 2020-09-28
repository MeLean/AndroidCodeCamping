package com.milen.androidcodecamping.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.milen.androidcodecamping.R
import com.milen.utils.SetDataUtils
import com.milen.utils.beGone
import kotlinx.android.synthetic.main.custom_dialog_fragment.view.*

class CustomDialogFragment : DialogFragment() {

    private lateinit var model: CustomDialogModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = arguments?.getParcelable(MODEL_KEY) ?: throw IllegalStateException("No model provided")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        val view = inflater.inflate(R.layout.custom_dialog_fragment, container, false)
        setData(view)
        return view
    }

    private fun setData(view: View?) {
        view?.let{
            SetDataUtils.setAnimateResOrHide(it.dialog_logo_background, model.dialogBackgroundAnimationUrl)
            SetDataUtils.setAnimateResOrHide(it.dialog_logo, model.dialogLogoAnimationUrl)
            SetDataUtils.setTextOrHide(it.dialog_title, model.dialogTitle)
            SetDataUtils.setTextOrHide(it.dialog_text, model.dialogText)

            setOnClickOrHide(it.dialog_center_btn, model.centerButton)
            setOnClickOrHide(it.dialog_left_btn, model.leftButton)
            setOnClickOrHide(it.dialog_right_btn, model.rightButton)
        }
    }

    private fun setOnClickOrHide(button: Button?, buttonData: Pair<String, View.OnClickListener>?) {
        if (buttonData != null){
            button?.let {
                it.text = buttonData.first
                it.setOnClickListener(buttonData.second)
            }
        }else{
            button?.beGone()
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        //val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
        dialog?.window?.attributes?.width = width
        dialog?.window?.attributes?.height = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    companion object {

        private const val MODEL_KEY = "model_key"
        const val FRAG_TAG = "dialogs.CustomDialogFragment"
        fun newInstance(
            model: CustomDialogModel
        ): CustomDialogFragment = CustomDialogFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MODEL_KEY, model)
            }
        }
    }

}