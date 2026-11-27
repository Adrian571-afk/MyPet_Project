package org.utl.mypet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.utl.mypet.data.model.Mascota

@Database(entities = [Mascota::class, Usuario::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mascotaDao(): MascotaDAO
    abstract fun usuarioDao(): UsuarioDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun obtenerInstancia(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mypet_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
