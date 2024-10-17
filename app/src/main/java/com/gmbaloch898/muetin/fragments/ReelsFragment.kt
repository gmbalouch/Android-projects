package com.gmbaloch898.muetin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmbaloch898.muetin.Models.Reel
import com.gmbaloch898.muetin.R
import com.gmbaloch898.muetin.adaptors.MyReelsAdapter
import com.gmbaloch898.muetin.adaptors.ReelAdapter
import com.gmbaloch898.muetin.databinding.FragmentReelsBinding
import com.gmbaloch898.muetin.utils.REELS
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import java.util.zip.Inflater

class ReelsFragment : Fragment() {
    private lateinit var binding: FragmentReelsBinding
    lateinit var adapter: ReelAdapter
    var reelList= ArrayList<Reel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentReelsBinding.inflate(inflater, container, false)
        adapter= ReelAdapter(requireContext(),reelList)
        binding.viewPager.adapter=adapter

        Firebase.firestore.collection(REELS).get().addOnSuccessListener {
            var tempList=ArrayList<Reel>()
            reelList.clear()

            for(i in it.documents){
                var reel=i.toObject<Reel>()!!
                tempList.add(reel)

            }
            reelList.addAll(tempList)
            reelList.reverse()
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    companion object {

    }
}