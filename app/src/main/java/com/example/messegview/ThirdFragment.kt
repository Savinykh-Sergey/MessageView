package com.example.messegview

import android.Manifest.permission.READ_SMS
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.messegview.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third) {
    private val binding: FragmentThirdBinding by viewBinding()
    private val messageModel: MessageModel by viewModels()
    private val adapter = MassageAdapter(::onMsgItemClick)
    private val stringAdapter = StrAdapter()
    private val smsMap: MutableMap<String, MutableList<String>> = mutableMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissions()
        initRecycler()
        bindDataRequest()
    }

    private fun bindDataRequest() {
        messageModel.liveData.observe(viewLifecycleOwner) {
            adapter.submitList(
                it.filter { i -> i.value.isNotEmpty() }
                    .map { (key, value) ->
                        MassegePreview(sender = key, lastMsg = value.first(), msges = value)
                    })
        }
        messageModel.makeDataRequest(smsMap)
    }

    private fun initRecycler() {
        with(binding.messageRecyclerView) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = this@ThirdFragment.adapter
        }
    }

    private fun readSMS() {
        val contentResolver: ContentResolver = requireActivity().contentResolver
        val cursor = contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        if (cursor?.moveToFirst() == true) {
            do {
                val address = cursor.getString(
                    cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)
                )
                val body = cursor.getString(
                    cursor.getColumnIndexOrThrow(Telephony.Sms.BODY)
                )
                if(smsMap.containsKey(address)){
                    smsMap[address]?.add(body)
                }
                else{
                    smsMap[address] = mutableListOf()
                }
            } while (cursor.moveToNext());
        }
        cursor?.close()
    }


    private fun checkPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            requireContext(),
            READ_SMS,
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) {
                if (it) {
                    readSMS()
                } else {

                }
            }.launch(READ_SMS)
        } else{
            readSMS()
        }
    }

    private fun onMsgItemClick(msges: List<String>){
        val directions = ThirdFragmentDirections.actionThirdFragmentToAllSenderMsg(msges.toTypedArray())
        findNavController().navigate(directions)
    }
}