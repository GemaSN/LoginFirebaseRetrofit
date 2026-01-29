package com.example.loginfirebaseretrofit.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET




// La clse sirve para convertir de JSON a objeto de Kotlin

private const val BASE_URL = "https://thesimpsonsapi.com"
/*private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL).build() // Construye el objeto*/

/** Si ponemos esto ignora los campos que no se han usado en el
* programa pero que si tiene la API
*/
private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL).build()

// Interface --> Define todos los metodos de comunicación con la API
interface LSApiService {
    @GET("api/characters") // Esto es como el /photos de una URL. EJEMPLO --> https://android-kotlin-fun-mars-server.appspot.com/photos
    suspend fun getCharacters(): LSInfoChar // Fucion suspendida ya que accedo a una fuente externa
}

object LSApi {
    val retrofitService : LSApiService by lazy {
        retrofit.create(LSApiService::class.java) // Hasta que no pongo esto no se crea la instancia del build
    }
}