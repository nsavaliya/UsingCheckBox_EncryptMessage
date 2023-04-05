package com.mdev.secretmessage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.navigation.findNavController

class MessageFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        val next = view.findViewById<Button>(R.id.next)
       val checkb= view.findViewById<CheckBox>(R.id.encrypt_checkbox)
        val message= view.findViewById<EditText>(R.id.message)

        next.setOnClickListener {
            val message = message.text.toString()
            view.findNavController().navigate(R.id.action_messageFragment_to_encryptFragment2)
            val reversedMessage = if (checkb.isChecked) message.reversed() else message
            val bundle = Bundle()
            bundle.putString("message", reversedMessage)

            val messageDisplayFragment = EncryptFragment()
            messageDisplayFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, messageDisplayFragment)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}