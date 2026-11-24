package playlistmaker.data.network

import playlistmaker.data.NetworkClient
import playlistmaker.data.dto.BaseResponse
import playlistmaker.data.dto.TracksSearchResponse

class RetrofitNetworkClient : NetworkClient {
    override fun doRequest(dto: Any): BaseResponse {
        return TracksSearchResponse(listOf())
    }
}