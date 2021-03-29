package com.ben.recipestore

import android.app.Application
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.ben.recipestore.di.RecipeStoreModule
import com.ben.recipestore.di.RecipeStoreNetworkModule
import com.ben.recipestore.network.RecipeStoreService
import com.ben.recipestore.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidTest
@UninstallModules(RecipeStoreNetworkModule::class)
class RetrofitTest {

    @get:Rule
        var hiltRule = HiltAndroidRule(this)


    private lateinit var mockWebServer: MockWebServer

    @Inject lateinit var recipeStoreService: RecipeStoreService

    lateinit var instrumentationContext: Context

    @Before
    fun init() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        mockWebServer = MockWebServer()
        mockWebServer.start()
        hiltRule.inject()
    }

    @Test
    fun someTest() {
        assertNotNull(recipeStoreService)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object TestRecipeStoreNetworkModule {
        @Singleton
        @Provides
        fun provideRetrofitInstance(): RecipeStoreService = Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
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

    private fun getJsonContent(fileNameId: Int) =
        InputStreamReader(instrumentationContext.resources.openRawResource(fileNameId)).use {
            it.readText()
        }

    @Test
    fun testReader() {
        val text = getJsonContent(R.raw.success_response)
        assertNotNull(text)
    }

    @Test
    fun testSuccessfulResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(200).setBody(getJsonContent(R.raw.success_response))
            }
        }
    }

    @Test
    fun testFailureResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(400).throttleBody(1024, 5, TimeUnit.SECONDS).setBody(getJsonContent(R.raw.failure_response))
            }
        }
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }
}