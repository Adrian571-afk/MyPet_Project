package org.utl.mypet.data.model

import com.google.gson.annotations.SerializedName

data class DogBreedsResponse(
    val data: List<DogBreed> = emptyList()
)

data class DogBreed(
    val id: String = "",
    val attributes: DogBreedAttributes = DogBreedAttributes()
)

data class DogBreedAttributes(
    val name: String = "Raza sin nombre",
    val description: String? = null,
    val images: List<DogBreedImage> = emptyList()
)

data class DogBreedImage(
    val id: String = "",
    val url: String = "",
    val thumb: String? = null,
    val medium: String? = null,
    val attribution: ImageAttribution = ImageAttribution()
)

data class ImageAttribution(
    val author: String = "Autor desconocido",
    val license: String = "",
    @SerializedName("source_url") val sourceUrl: String = ""
)
