package com.gmbaloch898.muetin.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmbaloch898.muetin.Post.PostActivity
import com.gmbaloch898.muetin.Post.ReelsActivity
import com.gmbaloch898.muetin.R
import com.gmbaloch898.muetin.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddFragment : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddBinding.inflate(inflater, container, false)

        binding.post.setOnClickListener{
            activity?.startActivity(Intent(requireContext(),PostActivity::class.java))
            activity?.finish()
        }
        binding.reel.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),ReelsActivity::class.java))
        }


        return binding.root
    }

    companion object {

    }
}