package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import be.technifutur.devmob9.projet_cantinapp_android.utils.BottomNavigationScreens

class MainPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val bottomFragmentList = arrayListOf<BottomNavigationScreens>()

    fun setItems(bottomScreens: List<BottomNavigationScreens>) {
        this.bottomFragmentList.apply {
            clear()
            addAll(bottomScreens)
            notifyDataSetChanged()
        }
    }

    fun getItems(): List<BottomNavigationScreens> = bottomFragmentList


    override fun getItemCount(): Int {
        return bottomFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return bottomFragmentList[position].fragment
    }
}