package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.animation.LayoutTransition
import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.GlideApp
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentDetailsBinding
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.PicturesManager
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.hashMapOfAllergies
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesItem
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.DetailViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment: BaseFragment() {

    companion object {
        const val KEY = "key"
        fun getInstance(foodItem: Food) = DetailsFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(KEY, foodItem)
            }
        }
    }

    private var fragmentListener: FragmentListener? = null

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var allergiesRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<AllergiesItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    private var allergiesInfoBoxToggle: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailsBinding>(inflater, R.layout.fragment_details, container, false)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.detailViewModel = detailViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback.fragmentTitle("Details")
        detail_description.movementMethod = ScrollingMovementMethod()

        arguments?.let { bundle ->
            bundle.getParcelable<Food>(KEY)?.apply {
                detailViewModel.setFoodItem(this)
                Log.d(FIREBASE_TAG, "${this.allergens}")
                this.allergens?.forEach {
                    itemAdapter.add(AllergiesItem(hashMapOfAllergies[it]))
                }
                GlideApp.with(requireContext())
                    .load(PicturesManager().fetchDishesPictures(this.picture_path))
                    .error(R.drawable.no_picture_found)
                    .centerCrop()
                    .into(detail_illustration)

//                val scale = resources.displayMetrics
//                val dp = 90f
//                val fPixel = scale.density * dp
//                val pixel = (fPixel + (dp * 2)).toInt()
//
//                sugar_amount_vw.layoutParams.height = pixel
//                sugar_amount_vw.scaleType = ImageView.ScaleType.CENTER_CROP

//                val drawable = resources.getDrawable(R.drawable.ic_nutrient_vector, resources.newTheme())
//                val bitmap = drawable.toBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight)
//                val newDrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 70, 91, true)) as Drawable
//                red_amount.setImageDrawable(newDrawable)
//                red_amount.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

        allergiesRecyclerView = view.findViewById(R.id.allergies_recyclerview)
        allergiesRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        fastAdapter.onClickListener = { _, _, item, position ->
            item.allergyIllustration?.let { allergies_image.setImageResource(it) }
            container.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

            togglingVisibility()

            allergies_detail.setOnClickListener {
                Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
                fragmentListener?.openAllergiesFragment(AllergiesInfoFragment.getInstance())
            }

            true
        }
    }

    private fun togglingVisibility() {
        if(allergiesInfoBoxToggle) {
            allergy_infobox_container.visibility = View.VISIBLE
            allergiesInfoBoxToggle = false
        }else {
            allergy_infobox_container.visibility = View.GONE
            allergiesInfoBoxToggle = true
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener) fragmentListener = context
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        allergiesRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
}