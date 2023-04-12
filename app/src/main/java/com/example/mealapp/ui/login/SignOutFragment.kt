package com.example.mealapp.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mealapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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


        //FireBase Sign Out Custom Login
        Firebase.auth.signOut()

        //Google SignOut

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        googleSignInClient.signOut()
        val builder = AlertDialog.Builder(context)
        builder.setTitle("SignOut")
        builder.setMessage("SignOut Complete.See you soon")
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
        }
//                builder.setNegativeButton("Cancel") { dialog, which ->
//                    // Do something when the "Cancel" button is clicked
//                }
        builder.show()

        //nav to login page
        findNavController().navigate(
            R.id.action_signOutFragment_to_loginFragment
        )
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_out, container, false)

    }
}