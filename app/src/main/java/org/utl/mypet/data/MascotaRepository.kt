package org.utl.mypet.data

import kotlinx.coroutines.flow.Flow
import org.utl.mypet.data.model.Mascota

class MascotaRepository(private val mascotaDAO: MascotaDAO) {
    fun obtenerTodas(): Flow<List<Mascota>> = mascotaDAO.obtenerTodas()

    suspend fun insertar(mascota: Mascota) = mascotaDAO.insertar(mascota)

    suspend fun eliminar(mascota: Mascota) = mascotaDAO.eliminar(mascota)
}
