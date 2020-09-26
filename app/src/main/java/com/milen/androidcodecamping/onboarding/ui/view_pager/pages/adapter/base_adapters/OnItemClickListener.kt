package com.milen.androidcodecamping.onboarding.ui.view_pager.pages.adapter.base_adapters

import android.view.View

interface OnItemClickListener<T> {

    fun onItemClick(view: View, selectedItem: T)

}
