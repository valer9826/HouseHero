package com.proysol.househero.ui.dailyTasks

import androidx.lifecycle.ViewModel
import com.proysol.househero.domain.dailyTasks.model.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RoomsViewModel : ViewModel(){

    private var _roomList = MutableStateFlow<List<Room>>(emptyList())
    val roomList: StateFlow<List<Room>> get() = _roomList

    init {
        _roomList.value = listOf(
            Room(1,"matrimonial"),
            Room(2,"doble"),
            Room(3,"simple"),
            Room(1,"matrimonial"),
            Room(2,"doble"),
            Room(3,"simple"),
            Room(1,"matrimonial"),
            Room(2,"doble"),
            Room(3,"simple"),
            Room(1,"matrimonial"),
            Room(2,"doble"),
            Room(3,"simple")
        )
    }


}