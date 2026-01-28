package com.example.loginfirebaseretrofit.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET




// La clse sirve para convertir de JSON a objeto de Kotlin

private const val BASE_URL = "https://hp-api.onrender.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL).build() // Construye el objeto

// Interface --> Define todos los metodos de comunicación con la API
interface HPApiService {
    @GET("api/characters") // Esto es como el /photos de una URL. EJEMPLO --> https://android-kotlin-fun-mars-server.appspot.com/photos
    suspend fun getCharacters():List<HPChar> // Fucion suspendida ya que accedo a una fuente externa
}
object HPApi {
    val retrofitService : HPApiService by lazy {
        retrofit.create(HPApiService::class.java) // Hasta que no pongo esto no se crea la instancia del build
    }
}