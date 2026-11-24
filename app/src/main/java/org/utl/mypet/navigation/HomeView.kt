package org.utl.mypet.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.utl.mypet.data.remote.RetrofitInstance
import org.utl.mypet.data.repository.RazaRepository
import org.utl.mypet.screens.MascotasScreen
import org.utl.mypet.screens.RazasScreen
import org.utl.mypet.ui.theme.Verde40
import org.utl.mypet.viewmodel.MascotaViewModel
import org.utl.mypet.viewmodel.RazasViewModel
import org.utl.mypet.viewmodel.RazasViewModelFactory
import org.utl.mypet.screens.RegistrosScreen

@Composable
fun HomeView() {
    val mascotaViewModel: MascotaViewModel = viewModel()
    val razaRepository = remember {
        RazaRepository(RetrofitInstance.api)
    }
    val razaFactory = remember { RazasViewModelFactory(razaRepository) }
    val razasViewModel: RazasViewModel = viewModel(factory = razaFactory)
    val mascotas by mascotaViewModel.mascotas.collectAsStateWithLifecycle()
    val razasState by razasViewModel.state.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                Text("MyPet", color = Verde40, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text("Cuida e identifica a tus mascotas", style = MaterialTheme.typography.bodyMedium)
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Text("🐾") },
                    label = { Text("Mis mascotas") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Text("🐶") },
                    label = { Text("Perros") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Text("📝") },
                    label = { Text("Registros") }
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> MascotasScreen(
                mascotas = mascotas,
                onGuardar = { mascota, alGuardar -> mascotaViewModel.guardar(mascota, alGuardar) },
                onEliminar = { mascotaViewModel.eliminar(it) },
                modifier = Modifier.padding(innerPadding)
            )
            1 -> RazasScreen(
                state = razasState,
                onRetry = { razasViewModel.cargarRazas() },
                modifier = Modifier.padding(innerPadding)
            )
            2 -> RegistrosScreen(
                mascotas = mascotas,
                onEliminar = { mascotaViewModel.eliminar(it) },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
