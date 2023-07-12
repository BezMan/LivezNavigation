package com.example.liveznav.data

import com.example.liveznav.utils.Consts
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

object ImplFactory {

    internal val impl by lazy {
        create()
    }

    private fun create(): ApiImpl {
//        return FakeArticlesApi()
        return ArticlesApi()
    }

}

internal interface ApiImpl {
    suspend fun getItems(name: String): CountriesPerName
}


private class ArticlesApi : ApiImpl {

    val client = OkHttpClient()


    override suspend fun getItems(name: String): CountriesPerName {
        val url = Consts.baseUrl + name
        val jsonString = getRequest(url)
        val countriesPerName = Gson().fromJson(jsonString, CountriesPerName::class.java)
        return countriesPerName

    }

    private fun getRequest(sUrl: String): String? {
        var result: String? = null
        try {
            // Create URL
            val url = URL(sUrl)
            // Build request
            val request = Request.Builder().url(url).build()
            // Execute request
            val response = client.newCall(request).execute()
            result = response.body?.string()
        } catch (err: Error) {
            print("Error when executing get request: " + err.localizedMessage)
        }
        return result
    }

}

private class FakeArticlesApi : ApiImpl {

    override suspend fun getItems(name: String): CountriesPerName {
        // Get the JSON data
        val jsonString = Consts.jsonRaw

        val countriesPerName = Gson().fromJson(jsonString, CountriesPerName::class.java)
        return countriesPerName
    }


}

