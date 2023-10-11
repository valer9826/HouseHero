package com.proysol.househero.ui.dailyTasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.proysol.househero.databinding.FragmentRoomListBinding
import com.proysol.househero.ui.dailyTasks.adapter.RoomListAdapter
import kotlinx.coroutines.launch

class RoomsFragment : Fragment() {

    private lateinit var roomAdapter: RoomListAdapter
    private val viewModel: RoomsViewModel by viewModels()

    private var _binding: FragmentRoomListBinding? = null
    val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.roomList.collect {
                    roomAdapter.update(it)
                }
            }
        }
    }

    private fun initList() {
        roomAdapter = RoomListAdapter()
        binding?.rvRooms?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = roomAdapter
        }
    }

}