package com.example.simpleapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Excuse(
    var id: Int,
    var excuse: String,
    var category: String
)

const val BASE_URL = "https://excuser-three.vercel.app"

interface ExcusesApi {
    @GET("/v1/excuse")
    suspend fun getExcuses(): List<Excuse>

    companion object {
        private var excuseService: ExcusesApi? = null

        fun getInstance(): ExcusesApi {
            if (excuseService == null) {
                excuseService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ExcusesApi::class.java)
            }
            return excuseService!!
        }
    }
}
