package com.example.messegview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.messegview.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {

    private val args: SecondFragmentArgs by navArgs()
    private val adapter = StrAdapter()
    private val binding: FragmentSecondBinding by viewBinding()
    private val viewModel: SecondModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        bindDataRequest()
    }

    private fun bindDataRequest() {
        viewModel.dataResponse.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.makeDataRequest(args.dataCount)
    }

    private fun initRecycler() {
        with(binding.secondFragmentRecycler) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = this@SecondFragment.adapter
        }
    }
}