package com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.milen.androidcodecamping.onboarding.data.OnboardingOption
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.base_adapters.BaseViewHolder
import com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.base_adapters.OnItemClickListener


class OptionsAdapter constructor(private val listener: OnItemClickListener<OnboardingOption>) : RecyclerView.Adapter<BaseViewHolder>() {

    private val optionItemDelegate = OptionItemDelegate()
    private val differ: AsyncListDiffer<OnboardingOption> = createAdapterDiffer()

    override fun getItemCount() = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val holder: RecyclerView.ViewHolder = optionItemDelegate.createViewHolder(parent)
        return holder as BaseViewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val viewModel = differ.currentList[position]
        optionItemDelegate.bindViewHolder(holder as OptionItemDelegate.OptionViewModel, viewModel, position, listener)
    }

    fun setData(data: List<OnboardingOption>) = differ.submitList(data)

    private fun createAdapterDiffer(): AsyncListDiffer<OnboardingOption> {
        return AsyncListDiffer(this, object : DiffUtil.ItemCallback<OnboardingOption>() {

            override fun areItemsTheSame(old: OnboardingOption, new: OnboardingOption): Boolean = old == new

            override fun areContentsTheSame(old: OnboardingOption, new: OnboardingOption): Boolean = old.id == new.id

        })
    }
}
