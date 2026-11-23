package org.utl.mypet.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import org.utl.mypet.data.model.DogBreed
import org.utl.mypet.data.repository.UiState
import org.utl.mypet.ui.theme.FondoPet
import org.utl.mypet.ui.theme.TextoPet
import org.utl.mypet.ui.theme.Verde40

@Composable
fun RazasScreen(
    state: UiState<List<DogBreed>>,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Conoce perros", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Verde40)
            Text("Fotos y nombres de razas desde Dog API. Es una galería, no una lista de perros en adopción.", color = TextoPet)
            Button(onClick = onRetry, modifier = Modifier.fillMaxWidth()) {
                Text("Actualizar galería")
            }
        }
        when (state) {
            UiState.Idle -> item { Text("Presiona actualizar para cargar las razas.") }
            UiState.Loading -> item {
                Box(Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Error -> item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(state.message, color = MaterialTheme.colorScheme.error)
                    Button(onClick = onRetry) { Text("Reintentar") }
                }
            }
            is UiState.Success -> {
                if (state.data.isEmpty()) {
                    item { Text("No hay razas para mostrar.") }
                } else {
                    item { Text("Razas disponibles: ${state.data.size}", fontWeight = FontWeight.SemiBold) }
                    items(state.data, key = { it.id }) { raza -> RazaCard(raza) }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun RazaCard(raza: DogBreed) {
    val image = raza.attributes.images.firstOrNull()
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = FondoPet)
    ) {
        Column {
            if (image != null) {
                GlideImage(
                    model = image.medium ?: image.url,
                    contentDescription = "${raza.attributes.name}",
                    modifier = Modifier.fillMaxWidth().height(220.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(raza.attributes.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Verde40)
                raza.attributes.description?.takeIf { it.isNotBlank() }?.let { Text(it, color = TextoPet) }
                if (image != null) {
                    Text(
                        "Foto: ${image.attribution.author} · ${image.attribution.license}",
                        style = MaterialTheme.typography.labelSmall
                    )
                    if (image.attribution.sourceUrl.isNotBlank()) {
                        TextButton(onClick = { uriHandler.openUri(image.attribution.sourceUrl) }) {
                            Text("Ver fuente de la foto")
                        }
                    }
                }
            }
        }
    }
}
