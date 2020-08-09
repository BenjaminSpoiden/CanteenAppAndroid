package be.technifutur.devmob9.projet_cantinapp_android

import android.app.Application
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.AuthManager
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.ContactPageManager
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.MenusManager
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.ContactPageRepository
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.MenusRepository
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.UserRepository
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.AuthViewModelFactory
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.ContactPageVMFactory
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.HomeViewModelFactory
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.MenuVMFactory
import com.google.firebase.iid.FirebaseInstanceId
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CantinappApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CantinappApplication))
        bind() from singleton { AuthManager() }
        bind() from singleton { ContactPageManager() }
        bind() from singleton { MenusManager() }
        bind() from singleton { UserRepository(instance()) }
        bind() from singleton { ContactPageRepository(instance()) }
        bind() from singleton { MenusRepository(instance()) }
        bind() from provider {
            AuthViewModelFactory(
                instance()
            )
        }
        bind() from provider {
            HomeViewModelFactory(instance())
        }

        bind() from provider {
            ContactPageVMFactory(instance())
        }

        bind() from provider {
            MenuVMFactory(instance())
        }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        FirebaseInstanceId.getInstance().instanceId
    }
}