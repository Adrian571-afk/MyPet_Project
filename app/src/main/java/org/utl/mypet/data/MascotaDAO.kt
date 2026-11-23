package org.utl.mypet.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.utl.mypet.data.model.Mascota

@Dao
interface MascotaDAO {
    @Query("SELECT * FROM mascotas ORDER BY nombre")
    fun obtenerTodas(): Flow<List<Mascota>>

    @Insert
    suspend fun insertar(mascota: Mascota)

    @Delete
    suspend fun eliminar(mascota: Mascota)
}
