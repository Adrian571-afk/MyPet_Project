package org.utl.mypet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import org.utl.mypet.data.AppDatabase
import org.utl.mypet.data.UsuarioRepository
import org.utl.mypet.data.UsuarioViewModel
import org.utl.mypet.navigation.AppNavigation
import org.utl.mypet.navigation.HomeView
import org.utl.mypet.ui.theme.MyPetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mypet_database"
        ).fallbackToDestructiveMigration().build()

        val usuarioRepository = UsuarioRepository(db.usuarioDao())

        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>) : T {
                return UsuarioViewModel(usuarioRepository) as T
            }
        }

        enableEdgeToEdge()
        setContent {
            val usuarioViewModel: UsuarioViewModel = viewModel(factory = factory)
            AppNavigation(usuarioViewModel = usuarioViewModel)
        }
    }
}
