package com.proysol.househero.ui.home

import androidx.lifecycle.ViewModel
import com.proysol.househero.ui.home.adapter.MenuOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {

    private var _menuOptions = MutableStateFlow<List<MenuOption>>(emptyList())
    val menuOptions: StateFlow<List<MenuOption>> get() = _menuOptions

    init {
        _menuOptions.value = listOf(
            MenuOption("1","Habitaciones asignadas"),
            MenuOption("2","Estadísticas"),
            MenuOption("3","Configuración"),
            MenuOption("1","Habitaciones asignadas"),
            MenuOption("2","Estadísticas"),
            MenuOption("3","Configuración"),
            MenuOption("1","Habitaciones asignadas"),
            MenuOption("2","Estadísticas"),
            MenuOption("3","Configuración"),
        )
    }

}