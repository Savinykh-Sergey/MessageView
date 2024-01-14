package com.example.messegview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.messegview.databinding.FragmentAllSenderMsgBinding

class AllSenderMsgFragment : Fragment(R.layout.fragment_all_sender_msg) {
    private val args: AllSenderMsgFragmentArgs by navArgs()
    private val adapter = StrAdapter()
    private val binding: FragmentAllSenderMsgBinding by viewBinding()
    private val viewModel: AllSenderMsgViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        bindDataRequest()
    }

    private fun bindDataRequest() {
        viewModel.dataResponse.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.makeDataRequest(args.msges.toList())
    }

    private fun initRecycler() {
        with(binding.allMessageRecyclerView) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = this@AllSenderMsgFragment.adapter
        }
    }
}

class AllSenderMsgViewModel: ViewModel() {
    private val _dataResponse = MutableLiveData<List<String>>()
    val dataResponse: LiveData<List<String>>
        get() = _dataResponse

    fun makeDataRequest(list: List<String>) {
        _dataResponse.postValue(list)
    }
}
