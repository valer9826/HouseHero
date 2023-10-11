package com.proysol.househero.ui.dailyTasks

import androidx.lifecycle.ViewModel
import com.proysol.househero.ui.dailyTasks.adapter.RoomDetail
import kotlinx.coroutines.flow.MutableStateFlow

class RoomDetailViewModel : ViewModel() {

    private var _roomTasks = MutableStateFlow<List<RoomDetail>>(emptyList())
    val roomTasks get() = _roomTasks

}