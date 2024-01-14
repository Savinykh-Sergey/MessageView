package com.example.messegview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.messegview.databinding.FragmentFourthBinding

class FourthFragment: Fragment(R.layout.fragment_fourth) {
    private val binding: FragmentFourthBinding by viewBinding()
    private val adapter = StrAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}