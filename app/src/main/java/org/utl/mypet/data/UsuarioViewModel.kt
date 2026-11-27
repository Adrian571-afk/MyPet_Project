package org.utl.mypet.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {
    fun registrar(usuario: Usuario) {
        viewModelScope.launch {
            repository.registroUsuario(usuario)
        }
    }

    fun iniciarSesion(usuario: String, contrasena: String, onSucces: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            val user = repository.validarLogin(usuario, contrasena)

            if (user != null) {
                onSucces()
            } else {
                onError()
            }
        }
    }
}