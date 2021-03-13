package com.deanezra.di.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import com.deanezra.BuildConfig
import com.deanezra.network.PlantDataApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Module
class NetworkModule @Inject constructor(var application: Application, val baseUrl: String) {

    @Provides
    fun baseUrl(): String {
        return "https://trefle.io"
    }

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    fun application(): Application {
        return application
    }

    val isConnected : Boolean get(){
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            // Replacement code that works on API 23 upwarsds - including Android 10 (API 29):
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            capabilities?.hasCapability(NET_CAPABILITY_INTERNET) == true

            // OLD Broken code on api 29 (android 10 s9+):
            /*
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            */
        } else {
            connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
        }
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        // add token as a parameter to every request
        .addInterceptor { chain ->
            // Pull out of local.properties file so not in Version Control
            val apiToken = BuildConfig.API_TOKEN

            val url = chain
                .request()
                .url()
                .newBuilder()
                .addQueryParameter("token", apiToken)
                .build()
            chain.proceed(chain.request().newBuilder().url(url).build())
        }
        // add off line interceptor
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (isConnected)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
        // add on line interceptor
        .addNetworkInterceptor {

            val response: Response = it.proceed(it.request())
            val maxAge =
                60 // read from cache for 60 seconds even if there is internet connection

            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }
        .build()

    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))

    @Provides
    @Singleton
    fun provideRetrofitService(builder: Retrofit.Builder): PlantDataApiService = builder.baseUrl(baseUrl).build().create(
        PlantDataApiService::class.java)


    // No longer nmeeded - as part of service now:
    /*
    @Provides
    fun providePlantDataApi(retrofit: Retrofit): PlantDataAPI =
        retrofit.create(PlantDataAPI::class.java)

     */


}