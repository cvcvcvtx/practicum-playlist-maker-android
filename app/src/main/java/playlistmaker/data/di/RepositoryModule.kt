package playlistmaker.data.di

import org.koin.dsl.module
import playlistmaker.data.NetworkClient
import playlistmaker.data.TracksRepositoryImpl
import playlistmaker.data.network.RetrofitNetworkClient
import playlistmaker.domain.api.TrackSearchInteractor
import playlistmaker.domain.api.TracksRepository
import playlistmaker.domain.impl.TrackSearchInteractorImpl

val repositoryModule = module {
    factory<NetworkClient> {
        RetrofitNetworkClient()
    }
    factory<TracksRepository> {
        TracksRepositoryImpl(get())
    }
}