package com.proysol.househero.ui.dailyTasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.proysol.househero.R
import com.proysol.househero.databinding.FragmentRoomDetailBinding
import com.proysol.househero.ui.dailyTasks.adapter.RoomDetailAdapter
import kotlinx.coroutines.launch

class RoomDetailFragment : Fragment() {

    private var _binding: FragmentRoomDetailBinding? = null
    val binding = _binding
    private val viewModel: RoomDetailViewModel by viewModels()

    private lateinit var roomDetailAdapter: RoomDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomDetailBinding.inflate(inflater)
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
            viewModel.roomTasks.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                roomDetailAdapter.update(it)
            }
        }
    }

    private fun initList() {
        roomDetailAdapter = RoomDetailAdapter()
        binding?.rvTasks?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = roomDetailAdapter
        }
    }


}