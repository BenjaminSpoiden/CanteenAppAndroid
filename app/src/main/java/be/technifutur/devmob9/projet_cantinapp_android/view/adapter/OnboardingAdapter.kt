package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.OnboardingData
import kotlinx.android.synthetic.main.view_item_onboarding.view.*

class OnboardingAdapter(private val items: List<OnboardingData>, private val context: Context): RecyclerView.Adapter<OnboardingAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.view_item_onboarding, parent, false)
        return CustomViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val result: OnboardingData = items[position]
        holder.itemView.imgview.setImageResource(result.pic)
        holder.itemView.txttitle.text = result.title
        holder.itemView.txtdesc.text = result.desc
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private lateinit var imgView: ImageView
        private lateinit var textTitle: TextView
        private lateinit var textDesc: TextView


        override fun onClick(v: View?) {
            if (v != null) {
                this.imgView = v.findViewById(R.id.imgview)
                this.textTitle = v.findViewById(R.id.txttitle)
                this.textDesc = v.findViewById(R.id.txtdesc)
            }
        }
    }
}


