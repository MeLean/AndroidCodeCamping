package com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.base_adapters

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapterDelegate<T, VH : BaseViewHolder, M> {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    @CallSuper
    open fun bindViewHolder(holder: VH, model: M, position: Int, listener: OnItemClickListener<M>) {
        //unused
    }
}
