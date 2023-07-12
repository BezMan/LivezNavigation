package com.example.liveznav.utils

class Consts {
    companion object {
        val jsonRaw = """
            {"name":"tal","country":[{"country_id":"IL","probability":0.927},{"country_id":"PA","probability":0.007},{"country_id":"TH","probability":0.006},{"country_id":"CA","probability":0.003},{"country_id":"AU","probability":0.003}]}
        """.trimIndent()

        const val BASE_URL = "https://api.nationalize.io/?name="
    }
}