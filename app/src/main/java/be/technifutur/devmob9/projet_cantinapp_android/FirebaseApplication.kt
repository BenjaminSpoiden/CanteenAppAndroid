package be.technifutur.devmob9.projet_cantinapp_android

import android.app.Application
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.FirebaseSource
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.UserRepository
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.AuthViewModelFactory
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.MainMenuViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class FirebaseApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@FirebaseApplication))

        bind() from singleton { FirebaseSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider {
            AuthViewModelFactory(
                instance()
            )
        }
        bind() from provider {
            MainMenuViewModelFactory(instance())
        }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}