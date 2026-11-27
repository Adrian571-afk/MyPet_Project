package org.utl.mypet.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insertarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE correo = :usuario AND :contrasena")
    suspend fun validarLogin(usuario: String, contrasena: String): Usuario?
}