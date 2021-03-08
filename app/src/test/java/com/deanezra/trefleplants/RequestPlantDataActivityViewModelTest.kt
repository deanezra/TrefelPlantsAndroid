
package com.deanezra.trefleplants

import MockResponseFileReader
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.deanezra.di.modules.NetworkModule
import com.deanezra.network.model.BasePlants
import com.deanezra.ui.PlantDataActivityViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class RequestPlantDataActivityViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Inject
    private lateinit var networkModule: NetworkModule

    @InjectMocks
    private var viewModel = PlantDataActivityViewModel()

    @Mock
    private lateinit var apiPlantsObserver: Observer<BasePlants>

    private lateinit var mockWebServer: MockWebServer


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = PlantDataActivityViewModel()
        viewModel.networkLiveData.observeForever(apiPlantsObserver)

        viewModel.fetchPlants()

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `read sample success json file`(){
        val reader = MockResponseFileReader("success_response.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `fetch details and check response Code 200 returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        // Act

        val plantDataApiService = networkModule.providePlantDataApi(networkModule.provideRetrofitInterface())

        val getPlantsApiCall =
            plantDataApiService.getPlants()

        val  actualResponse = getPlantsApiCall.execute()
        // Assert
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }

    @Test
    fun `fetch details and check response success returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()
        // Act
        val plantDataApiService = networkModule.providePlantDataApi(networkModule.provideRetrofitInterface())

        val getPlantsApiCall =
            plantDataApiService.getPlants()

        val  actualResponse = getPlantsApiCall.execute()
        // Assert
        assertEquals(mockResponse?.let { `parse mocked JSON response`(it) }, actualResponse.body()?.plantlist)
    }

    private fun `parse mocked JSON response`(mockResponse: String): String {
        val reader = JSONObject(mockResponse)
        return reader.getString("data")
    }

    // TODO: Populate the failed_response.json in resources and re-enable this test:
    /*
    @Test
    fun `fetch details for failed response 400 returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(MockResponseFileReader("failed_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val plantDataAPIService =
            networkModule.providePlantAPIService(networkModule.provideRetrofitInterface())

        val getPlantsApiCall =
            plantDataAPIService.getPlants()

        val  actualResponse = getPlantsApiCall.execute()
        // Assert
        assertEquals(response.toString().contains("400"),actualResponse.toString().contains("400"))
    }
     */

    @After
    fun tearDown() {
        viewModel.networkLiveData.removeObserver(apiPlantsObserver)
        mockWebServer.shutdown()
    }
}
