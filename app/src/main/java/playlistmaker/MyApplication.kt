package playlistmaker

import android.app.Application

import org.koin.dsl.module
import playlistmaker.ui.view_model.SearchViewModel



import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import playlistmaker.data.di.repositoryModule




class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            val viewModelModule = module {
                viewModel {
                    SearchViewModel(get())
                }
            }
            modules(repositoryModule, viewModelModule)
        }
    }

   // private fun startKoin(function: Any) {}
}