package org.utl.mypet.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.utl.mypet.data.model.Mascota
import org.utl.mypet.ui.theme.FondoPet
import org.utl.mypet.ui.theme.TextoPet
import org.utl.mypet.ui.theme.Verde40

@Composable
fun MascotasScreen(
    mascotas: List<Mascota>,
    onGuardar: (Mascota, () -> Unit) -> Unit,
    onEliminar: (Mascota) -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by remember { mutableStateOf("") }
    var nacimiento by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("Perro") }
    var raza by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var responsable by remember { mutableStateOf("") }
    var microchip by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Mi mascota", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Verde40)
            Text("Crea su identificación y conserva sus datos en este dispositivo.", color = TextoPet)
        }
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = FondoPet),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Nueva identificación", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Campo("Nombre *", nombre) { nombre = it; mensaje = "" }
                    Campo("Fecha de nacimiento (DD/MM/AAAA) *", nacimiento, KeyboardType.Text) { nacimiento = it; mensaje = "" }
                    Campo("Especie *", especie) { especie = it; mensaje = "" }
                    Campo("Raza", raza) { raza = it }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Campo("Sexo", sexo, modifier = Modifier.weight(1f)) { sexo = it }
                        Campo("Color", color, modifier = Modifier.weight(1f)) { color = it }
                    }
                    Campo("Nombre del responsable", responsable) { responsable = it }
                    Campo("Microchip (opcional)", microchip) { microchip = it }
                    if (mensaje.isNotEmpty()) Text(mensaje, color = Verde40)
                    Button(
                        onClick = {
                            if (nombre.isBlank() || nacimiento.isBlank() || especie.isBlank()) {
                                mensaje = "Completa nombre, fecha de nacimiento y especie."
                            } else {
                                val mascota = Mascota(
                                    nombre = nombre.trim(),
                                    especie = especie.trim(),
                                    raza = raza.trim(),
                                    fechaNacimiento = nacimiento.trim(),
                                    sexo = sexo.trim(),
                                    color = color.trim(),
                                    nombreResponsable = responsable.trim(),
                                    microchip = microchip.trim()
                                )
                                onGuardar(mascota) {
                                    nombre = ""
                                    nacimiento = ""
                                    especie = "Perro"
                                    raza = ""
                                    sexo = ""
                                    color = ""
                                    responsable = ""
                                    microchip = ""
                                    mensaje = "Identificación guardada."
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar mascota")
                    }
                }
            }
        }
        if (mascotas.isEmpty()) {
            item { Text("Aún no tienes mascotas registradas.", modifier = Modifier.padding(vertical = 8.dp)) }
        }
    }
}


@Composable
private fun Campo(
    etiqueta: String,
    valor: String,
    tipo: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    alCambiar: (String) -> Unit
) {
    OutlinedTextField(
        value = valor,
        onValueChange = alCambiar,
        label = { Text(etiqueta) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = tipo)
    )
}
