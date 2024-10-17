package com.gmbaloch898.muetin.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmbaloch898.muetin.Models.User
import com.gmbaloch898.muetin.R
import com.gmbaloch898.muetin.SignUpActivity
import com.gmbaloch898.muetin.databinding.ActivityLoginBinding
import com.gmbaloch898.muetin.databinding.FragmentProfileBinding
import com.gmbaloch898.muetin.utils.USER_NODE
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso
import androidx.fragment.app.FragmentPagerAdapter
import com.gmbaloch898.muetin.adaptors.ViewPagerAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

@Suppress("UNREACHABLE_CODE")
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(inflater,container,false)
        binding.editProfileBTN.setOnClickListener {
            val intent= Intent( activity , SignUpActivity::class.java)
                intent.putExtra("MODE",1)
                activity?.startActivity(intent)
                activity?.finish();
        }
        viewPagerAdapter=ViewPagerAdapter(requireActivity().supportFragmentManager)
        viewPagerAdapter.addFragments(MyPostFragment(),"MY POSTS")
        viewPagerAdapter.addFragments(MyReelsFragment(),"MY REELS")
        binding.viewPager.adapter=viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)


        return binding.root
    }

    companion object {

    }

    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {

            val user: User=it.toObject<User>()!!
            binding.name.text=user.name
            binding.bio.text=user.email

            if (!user.image.isNullOrEmpty()){

                Picasso.get().load(user.image).into(binding.profileImage)

            }
        }
    }
}