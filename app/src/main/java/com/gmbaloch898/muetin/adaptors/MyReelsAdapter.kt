package com.gmbaloch898.muetin.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gmbaloch898.muetin.Models.Reel
import com.gmbaloch898.muetin.databinding.MyPostRvDesignBinding
import com.gmbaloch898.muetin.utils.uploadImage
import com.squareup.picasso.Picasso

class MyReelsAdapter (var context: Context,var reelList:ArrayList<Reel>):RecyclerView.Adapter<MyReelsAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: MyPostRvDesignBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding= MyPostRvDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(reelList.get(position).reelUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.binding.postImage)

    }

}