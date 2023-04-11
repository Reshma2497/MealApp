package com.example.mealapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mealapp.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignOutFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Firebase.auth.signOut()

        //nav to login page
        findNavController().navigate(
            R.id.action_signOutFragment_to_loginFragment
        )
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_out, container, false)

    }
}