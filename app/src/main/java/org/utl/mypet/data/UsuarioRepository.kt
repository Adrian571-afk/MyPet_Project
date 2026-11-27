package org.utl.mypet.data

class UsuarioRepository(private val usuarioDao: UsuarioDao){
    suspend fun registroUsuario(usuario: Usuario){
        usuarioDao.insertarUsuario(usuario)
    }

    suspend fun validarLogin(usuario: String, contrasena: String): Usuario? {
        return usuarioDao.validarLogin(usuario, contrasena)
    }
}