package com.agladkov.dotabook.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agladkov.domain.models.Hero
import com.agladkov.dotabook.R

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
        heroArgs = arguments?.getParcelable("hero") as? Hero
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carry_antipick, container, false)
    }


}
