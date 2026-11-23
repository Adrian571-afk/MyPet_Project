package org.utl.mypet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "mascotas")
data class Mascota(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val especie: String,
    val raza: String,
    val fechaNacimiento: String,
    val sexo: String,
    val color: String,
    val nombreResponsable: String,
    val microchip: String
)
