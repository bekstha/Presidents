package fi.metropolia.bibeks.presidents.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class WikipediaSearchResponse(
    val query: WikipediaQuery
)

data class WikipediaQuery(
    val searchinfo: WikipediaSearchInfo
)

data class WikipediaSearchInfo(
    val totalhits: Int
)

interface WikipediaService {
    @GET("w/api.php?action=query&format=json&list=search")
    suspend fun search(@Query("srsearch") query: String): WikipediaSearchResponse
}

fun provideWikipediaService(): WikipediaService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://en.wikipedia.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(WikipediaService::class.java)
}


class WikiRepository(private val wikipediaService: WikipediaService) {
    suspend fun hitCountCheck(name: String): WikipediaSearchResponse {
        return wikipediaService.search(name)
    }
}






