package com.example.messegview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FirstFragment : Fragment(R.layout.fragment_first) {

    //private val binding: FragmentFirstBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonNavigateSecond.setOnClickListener {
            val userInput = binding.edittextUserInput.text.toString()
            val direction =
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                    dataCount = userInput.toInt()
                )
            findNavController().navigate(direction)
        }
        binding.buttonNavigateThird.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_thirdFragment)
        }*/
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstFragment()
    }
}