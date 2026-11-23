package org.utl.mypet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.utl.mypet.data.AppDatabase
import org.utl.mypet.data.MascotaRepository
import org.utl.mypet.data.model.Mascota

class MascotaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MascotaRepository(
        AppDatabase.obtenerInstancia(application).mascotaDao()
    )

    val mascotas = repository.obtenerTodas().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun guardar(mascota: Mascota, alGuardar: () -> Unit = {}) {
        viewModelScope.launch {
            repository.insertar(mascota)
            alGuardar()
        }
    }

    fun eliminar(mascota: Mascota) {
        viewModelScope.launch { repository.eliminar(mascota) }
    }
}
