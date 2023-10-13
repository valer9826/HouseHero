package com.proysol.househero.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.proysol.househero.databinding.ItemHomeMenuBinding

class HomeAdapter(private var listMenuOptions: List<MenuOption> = emptyList()) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    fun update(newList: List<MenuOption>) {
        val listMenuOptionDiff = MenuOptionsDiff(listMenuOptions, newList)
        val result = DiffUtil.calculateDiff(listMenuOptionDiff)
        listMenuOptions = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(listMenuOptions[position])
    }

    override fun getItemCount(): Int {
        return listMenuOptions.size
    }

    class HomeViewHolder(private val binding: ItemHomeMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(menuOption: MenuOption) {
            binding.tvMenuOption.text = menuOption.name
        }
    }

    class MenuOptionsDiff(
        private val oldList: List<MenuOption>,
        private val newList: List<MenuOption>
    ) : DiffUtil.Callback() {
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


data class MenuOption(val id: String, val name: String)