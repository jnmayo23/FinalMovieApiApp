package edu.quinnipiac.movieapiapp

/*
    @author Jordan Mayo
    SettingsFragment that allows user to change the background color of application
 */

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.quinnipiac.movieapiapp.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    //View binding
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        //Calls the changeBackgroundColor function in MainActivity with the selected color as parameter
        binding.red.setOnClickListener {
            (activity as MainActivity).changeBackgroundColor(Color.rgb(233,0,0))
        }
        binding.orange.setOnClickListener {
            (activity as MainActivity).changeBackgroundColor(Color.rgb(255,161,0))
        }
        binding.yellow.setOnClickListener {
            (activity as MainActivity).changeBackgroundColor(Color.rgb(239,222,4))
        }
        binding.green.setOnClickListener {
            (activity as MainActivity).changeBackgroundColor(Color.rgb(22,158,0))
        }
        binding.blue.setOnClickListener {
            (activity as MainActivity).changeBackgroundColor(Color.rgb(0,96,158))
        }
        binding.purple.setOnClickListener {
            (activity as MainActivity).changeBackgroundColor(Color.rgb(106,0,185))
        }
        binding.pink.setOnClickListener {
            (activity as MainActivity).changeBackgroundColor(Color.rgb(255,95,241))
        }

        return view
    }


}