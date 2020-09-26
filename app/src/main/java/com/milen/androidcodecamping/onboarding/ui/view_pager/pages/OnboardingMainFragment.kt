package com.milen.androidcodecamping.onboarding.ui.view_pager.pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.milen.androidcodecamping.R
import com.milen.androidcodecamping.onboarding.data.OnboardingOption
import com.milen.androidcodecamping.onboarding.ui.OnboardingActivity
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.ItemOffsetDecoration
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.OptionsAdapter
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.base_adapters.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_onboarding_main.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [OnboardingMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnboardingMainFragment : Fragment(), OnItemClickListener<OnboardingOption>{
    private var options: List<OnboardingOption>? = null
    private val adapter = OptionsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            options = it.getParcelableArrayList(ARG_OPTIONS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_onboarding_main, container, false)

        setUI(view)

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun setUI(view: View?) {
        val mainText : TextView? = view?.onboaridn_main_text
        val flavorName = "IDB" //TODO get it from flavor
        mainText?.text = "How could $flavorName help you?"

        initRecyclerView(view)

    }

    private fun initRecyclerView(view: View?) {
        val recycler : RecyclerView? = view?.onboarding_main_recycler
        recycler?.let {
            val data = options ?: listOf()
            adapter.setData(data)
            it.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            it.addItemDecoration(ItemOffsetDecoration(activity!!, R.dimen.rw_offset))
            it.setHasFixedSize(true)
            it.adapter = adapter
        }
    }

    companion object {

        const val ARG_OPTIONS = "AVAILABLE_OPTIONS"
        const val TAG = "MainOnboardingFragment"

        @JvmStatic
        fun newInstance(options: List<Parcelable>) =
            OnboardingMainFragment().apply {
                val parsableList = arrayListOf<Parcelable>()
                options.forEach { option -> parsableList.add(option) }
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_OPTIONS, parsableList)
                }
            }
    }

    override fun onItemClick(view: View, selectedItem: OnboardingOption) {
        //TODO USE EVENT BUSS
        val curActivity =  activity as OnboardingActivity
        curActivity.onOptionSelected(selectedItem)

    }
}