package com.proysol.househero.ui.home

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
import com.proysol.househero.R
import com.proysol.househero.databinding.FragmentHomeBinding
import com.proysol.househero.ui.home.adapter.HomeAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val binding get()= _binding

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeMenuAdapter:HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initMenuOptions()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.menuOptions.collect{
                    homeMenuAdapter.update(it)
                }
            }
        }
    }

    private fun initMenuOptions() {
        homeMenuAdapter = HomeAdapter()
        binding?.rvHomeMenu?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeMenuAdapter
        }
    }

}