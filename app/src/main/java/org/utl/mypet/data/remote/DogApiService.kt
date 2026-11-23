package org.utl.mypet.data.remote

import org.utl.mypet.data.model.DogBreedsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {
    @GET("breeds")
    suspend fun obtenerRazas(
        @Query("page[number]") pagina: Int,
        @Query("page[size]") tamanoPagina: Int
    ): DogBreedsResponse
}
