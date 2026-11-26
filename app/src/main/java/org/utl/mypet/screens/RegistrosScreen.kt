package org.utl.mypet.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.utl.mypet.data.model.Mascota

@Composable
fun RegistrosScreen(
    mascotas: List<Mascota>,
    onEliminar: (Mascota) -> Unit,
    modifier: Modifier = Modifier
) {
    if (mascotas.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Aún no hay registros guardados")
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(mascotas, key = { it.id }) { mascota ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Nombre: ${mascota.nombre}")
                            Text("Fecha de nacimiento: ${mascota.fechaNacimiento}")
                            Text("Especie: ${mascota.especie}")
                            Text("Raza: ${mascota.raza}")
                            Text("Color: ${mascota.color}")
                            Text("Persona responsable: ${mascota.nombreResponsable}")
                            Text("Sexo: ${mascota.sexo}")
                            Text("Microchip: ${mascota.microchip}")
                        }
                        IconButton(onClick = { onEliminar(mascota) }) {
                            Text("🗑️")
                        }
                    }
                }
            }
        }
    }
}