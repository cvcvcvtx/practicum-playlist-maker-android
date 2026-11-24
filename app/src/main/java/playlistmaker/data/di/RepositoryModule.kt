package playlistmaker.data.di

import org.koin.dsl.module
import playlistmaker.data.NetworkClient
import playlistmaker.data.repository.TracksRepositoryImpl
import playlistmaker.data.network.RetrofitNetworkClient
import playlistmaker.data.repository.Storage
import playlistmaker.domain.api.TracksRepository

val repositoryModule = module {
    single<NetworkClient> {
        RetrofitNetworkClient()
    }
    single<TracksRepository> {
        TracksRepositoryImpl(get(), Storage())
    }
}