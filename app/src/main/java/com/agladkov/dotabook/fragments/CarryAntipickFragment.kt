package com.agladkov.dotabook.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.agladkov.domain.models.Hero
import com.agladkov.dotabook.R
import com.agladkov.dotabook.helpers.Keys
import com.bumptech.glide.Glide

class CarryAntipickFragment : Fragment() {

    companion object {
        fun getInstance(arguments: Bundle): CarryAntipickFragment {
            return CarryAntipickFragment().also {
                it.arguments = arguments
            }
        }
    }

    private var heroArgs: Hero? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        heroArgs = arguments?.getParcelable(Keys.Hero.title) as? Hero
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carry_antipick, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureView(view)
    }

    /// Internal logic
    private fun configureView(rootView: View) {
        val txtCarryAntipickHeroName =
            rootView.findViewById<TextView>(R.id.txtCarryAntipickHeroName)
        val txtCarryAntipickHeroRole =
            rootView.findViewById<TextView>(R.id.txtCarryAntipickHeroRole)
        val imgCarryAntipickAvatar = rootView.findViewById<ImageView>(R.id.imgCarryAntipickAvatar)

        heroArgs?.let { hero ->
            txtCarryAntipickHeroName.text = hero.name
                .replace("npc_dota_hero_", "")
                .replace("_", " ")
                .capitalize()
            txtCarryAntipickHeroRole.text = hero.heroType.name

            if (hero.avatar.isNotBlank() && hero.avatar.isNotEmpty())
                Glide.with(imgCarryAntipickAvatar).load(hero.avatar).into(imgCarryAntipickAvatar)
        }
    }
}
