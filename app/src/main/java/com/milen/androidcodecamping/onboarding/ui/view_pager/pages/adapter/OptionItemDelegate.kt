package com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milen.androidcodecamping.R
import com.milen.androidcodecamping.onboarding.data.OnboardingOption
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.base_adapters.BaseAdapterDelegate
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.base_adapters.BaseViewHolder
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.base_adapters.OnItemClickListener
import kotlinx.android.synthetic.main.item_onboarding_option.view.*


class OptionItemDelegate: BaseAdapterDelegate<OptionItemDelegate.OptionViewModel, OptionItemDelegate.OptionViewModel, OnboardingOption>() {

    class OptionViewModel(parent: View) : BaseViewHolder(parent) {
        val name: TextView = parent.onboarding_option_text
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OptionViewModel(inflater.inflate(R.layout.item_onboarding_option, parent, false))
    }

    override fun bindViewHolder(holder: OptionViewModel,
                                model: OnboardingOption,
                                position: Int,
                                listener: OnItemClickListener<OnboardingOption>
    ) {

        holder.name.text = model.name
        holder.itemView.setOnClickListener{ view -> listener.onItemClick(view, model) }

        super.bindViewHolder(holder, model, position, listener)
    }
}