package com.milen.androidcodecamping.onboarding.services

import android.content.Context
import com.google.gson.Gson
import com.milen.androidcodecamping.onboarding.data.OnboardingScenario
import java.io.IOException
import java.io.InputStream

class MockedBackEnd(private val context: Context) {

    fun getOnboardingScenario(): OnboardingScenario {
        val json = loadJSONFromAsset(context,"onboarding.json") // your json value here
        return Gson().fromJson(json, OnboardingScenario::class.java)
    }

    fun loadJSONFromAsset(context: Context, fileName : String): String? {
        val json: String?
        json = try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}