package org.utl.mypet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.utl.mypet.data.model.DogBreed
import org.utl.mypet.data.repository.RazaRepository
import org.utl.mypet.data.repository.UiState

class RazasViewModel(private val repository: RazaRepository) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<DogBreed>>>(UiState.Loading)
    val state: StateFlow<UiState<List<DogBreed>>> = _state.asStateFlow()

    init {
        cargarRazas()
    }

    fun cargarRazas() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                _state.value = UiState.Success(repository.obtenerRazas())
            } catch (exception: Exception) {
                _state.value = UiState.Error(
                    exception.message ?: "No fue posible cargar las razas de perros."
                )
            }
        }
    }
}
