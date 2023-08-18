package com.example.usbanks.apiService


import com.example.usbanks.model.NewsApiResponse
import com.example.usbanks.util.BaseApiResponse
import com.example.usbanks.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(private val service: NewsApiService): BaseApiResponse() {
    @Inject
    suspend fun getNews() = service.getNewshealines()

    suspend fun getNewsDetails(): Flow<NetworkResult<NewsApiResponse>> {
        return flow<NetworkResult<NewsApiResponse>> {
            emit(safeApiCall { service.getNewshealines() })
        }.flowOn(Dispatchers.IO)
    }
}