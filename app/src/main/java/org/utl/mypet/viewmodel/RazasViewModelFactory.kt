package org.utl.mypet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.utl.mypet.data.repository.RazaRepository

class RazasViewModelFactory(private val repository: RazaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RazasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RazasViewModel(repository) as T
        }
        throw IllegalStateException("ViewModel desconocido")
    }
}
