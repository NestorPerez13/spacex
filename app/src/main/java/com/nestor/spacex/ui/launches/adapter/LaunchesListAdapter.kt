package com.nestor.spacex.ui.launches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nestor.spacex.LaunchesListQuery
import com.nestor.spacex.databinding.LaunchesItemBinding

class LaunchesListAdapter :
    PagingDataAdapter<LaunchesListQuery.Launch, LaunchesListAdapter.ViewHolder>(MyDiffCallback) {

    class ViewHolder(val mBinding: LaunchesItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(item: LaunchesListQuery.Launch?) {
            with(mBinding) {
                item?.let {
                    site.text = it.site
                    id.text = it.id
                    it.mission?.missionPatch?.let { thumb -> image.load(thumb) }
                }
            }
        }
    }

    object MyDiffCallback : DiffUtil.ItemCallback<LaunchesListQuery.Launch>() {
        override fun areItemsTheSame(
            oldItem: LaunchesListQuery.Launch,
            newItem: LaunchesListQuery.Launch
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: LaunchesListQuery.Launch,
            newItem: LaunchesListQuery.Launch
        ) = oldItem == newItem

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        mBinding = LaunchesItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )
}