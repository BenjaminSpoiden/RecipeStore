package com.ben.recipestore.di


import com.ben.recipestore.BuildConfig
import com.ben.recipestore.network.RecipeStoreService
import com.ben.recipestore.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeStoreNetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): RecipeStoreService = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().apply {
            if(BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                this.addInterceptor(logging)
            }
        }.build())
        .build()
        .create(RecipeStoreService::class.java)
}