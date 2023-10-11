package com.proysol.househero.ui.dailyTasks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.proysol.househero.R
import com.proysol.househero.databinding.ListTaskItemBinding

class RoomDetailAdapter(private var roomTasksList: List<RoomDetail> = emptyList()) :
    RecyclerView.Adapter<RoomDetailAdapter.RoomDetailViewHolder>() {

    fun update(newRoomTasksList: List<RoomDetail>) {
        val roomDetailDiff = RoomDetailDiffUtil(roomTasksList, newRoomTasksList)
        val result = DiffUtil.calculateDiff(roomDetailDiff)
        roomTasksList = newRoomTasksList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoomDetailAdapter.RoomDetailViewHolder {
        return RoomDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_task_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RoomDetailAdapter.RoomDetailViewHolder, position: Int) {
        holder.bind(roomTasksList[position])
    }

    override fun getItemCount(): Int {
        return roomTasksList.size
    }

    class RoomDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ListTaskItemBinding.bind(view)
        fun bind(roomDetail: RoomDetail) {
            val context = binding.tvTaskNumber.context
            binding.tvTaskNumber.text = roomDetail.id
            binding.tvTaskDescription.text = roomDetail.description
        }

    }

    class RoomDetailDiffUtil(
        private val oldRoomTasksList: List<RoomDetail>,
        private val newRoomTasksList: List<RoomDetail>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldRoomTasksList.size
        }

        override fun getNewListSize(): Int {
            return newRoomTasksList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldRoomTasksList[oldItemPosition] == newRoomTasksList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldRoomTasksList[oldItemPosition].id == newRoomTasksList[newItemPosition].id
        }


    }

}

data class RoomDetail(val id: String, val description: String)