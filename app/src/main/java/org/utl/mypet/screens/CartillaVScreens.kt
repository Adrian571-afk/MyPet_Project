package org.utl.mypet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.utl.mypet.ui.theme.TextoPrincipal
import org.utl.mypet.ui.theme.Turquesa
import org.utl.mypet.ui.theme.VerdeEstado


@Composable
fun CartillaVacunacionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9F8))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Barra superior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "🐾 MyPet",
                color = Turquesa,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text("👤", fontSize = 20.sp)
        }

        Text(
            text = "Mi Mascota: Diagnóstico y Vacunas.",
            color = TextoPrincipal,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
        )

        // Tarjeta de información de la mascota
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .background(Color(0xFFE7D7B8), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🐕", fontSize = 38.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Buddy",
                        color = TextoPrincipal,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp
                    )
                    Text("5 años", color = TextoPrincipal, fontSize = 13.sp)
                    Text(
                        "Raza: Golden Retriever | Peso: 32 kg",
                        color = Color.Gray,
                        fontSize = 11.sp
                    )
                }
            }
        }

        Text(
            text = "Cartilla de Vacunación",
            color = TextoPrincipal,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)
        )

        VacunaCard(
            emoji = "💉",
            nombre = "Vacuna Contra la Rabia",
            estado = "Completada",
            fecha = "15/01/2023",
            vencimiento = "15/01/2024",
            colorFondo = Color(0xFFD9EEF8),
            completada = true
        )

        VacunaCard(
            emoji = "💊",
            nombre = "Vacuna Polivalente (DHPPi)",
            estado = "Completada",
            fecha = "10/02/2023",
            vencimiento = "10/02/2024",
            colorFondo = Color(0xFFD9F3E9),
            completada = true
        )

        VacunaCard(
            emoji = "▦",
            nombre = "Desparasitación Externa",
            estado = "Pendiente",
            fecha = "Programada: 15/07/2023",
            vencimiento = "",
            colorFondo = Color(0xFFE8EAEB),
            completada = false
        )
    }
}

@Composable
private fun VacunaCard(
    emoji: String,
    nombre: String,
    estado: String,
    fecha: String,
    vencimiento: String,
    colorFondo: Color,
    completada: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(containerColor = colorFondo),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 23.sp)
            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = nombre,
                    color = TextoPrincipal,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = if (completada) "●  $estado" else estado,
                    color = if (completada) VerdeEstado else Color(0xFFB58A37),
                    fontSize = 12.sp
                )

                Text(
                    text = if (vencimiento.isNotEmpty()) {
                        "$fecha | Vence: $vencimiento"
                    } else {
                        fecha
                    },
                    color = TextoPrincipal,
                    fontSize = 11.sp
                )
            }


        }
    }
}

@Preview
@Composable
fun PantallaPrincipal(){
    CartillaVacunacionScreen()
}
