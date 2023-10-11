package com.proysol.househero.ui.dailyTasks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.proysol.househero.R
import com.proysol.househero.databinding.ListRoomItemBinding
import com.proysol.househero.domain.dailyTasks.model.Room

class RoomListAdapter(private var roomList: List<Room> = emptyList()) :
    RecyclerView.Adapter<RoomListAdapter.RoomsViewHolder>() {

    fun update(newRoomList: List<Room>){
//        this.roomList = newRoomList
//        notifyDataSetChanged()
        val roomListDiff = RoomsDiffUtil(roomList, newRoomList)
        val result = DiffUtil.calculateDiff(roomListDiff)
        roomList = newRoomList
        result.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        return RoomsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_room_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.bind(roomList[position])
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    class RoomsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ListRoomItemBinding.bind(view)
        fun bind(room: Room){
            val context = binding.tvRoomNumber.context
            binding.tvRoomNumber.text = room.id.toString()
            binding.tvFloorNumber.text = room.type
        }

    }

    class RoomsDiffUtil(
        private val oldList: List<Room>,
        private val newList: List<Room>
    ): DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

    }

}

