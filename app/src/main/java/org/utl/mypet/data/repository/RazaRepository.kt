package org.utl.mypet.data.repository

import org.utl.mypet.data.model.DogBreed
import org.utl.mypet.data.remote.DogApiService

class RazaRepository(private val api: DogApiService) {
    suspend fun obtenerRazas(): List<DogBreed> {
        return api.obtenerRazas(pagina = 1, tamanoPagina = 50).data
            .filter { it.attributes.images.isNotEmpty() }
    }
}
