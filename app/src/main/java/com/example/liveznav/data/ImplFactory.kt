package com.example.liveznav.data

import com.example.liveznav.utils.Consts
import com.google.gson.Gson

object ImplFactory {

    internal val impl by lazy {
        create()
    }

    private fun create(): ApiImpl {
        return FakeArticlesApi()
//        return ArticlesApi()
    }

}

internal interface ApiImpl {
    suspend fun getItems(name: String): CountriesPerName
}


private class ArticlesApi : ApiImpl {

    override suspend fun getItems(name: String): CountriesPerName {
        return CountriesPerName(listOf(), name)
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

