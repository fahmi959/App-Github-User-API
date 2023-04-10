package com.fahmi.userInterface_apkFahmi.halaman_utama


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fahmi.semua_data_dan_model.FahmiUserFahmi
import com.fahmi.databinding.ListDataUserApkFahmiBinding


class FahmiUserAdapterFahmi : RecyclerView.Adapter<FahmiUserAdapterFahmi.UserViewHolder>(){

   private val list = ArrayList<FahmiUserFahmi>()
    private var onItemClickCallback: OnItemClickCallback? = null


    fun setList(users:ArrayList<FahmiUserFahmi>){
        list.clear()
        list.addAll(users)
       notifyDataSetChanged()
    }

fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
    this.onItemClickCallback = onItemClickCallback
}


    inner class UserViewHolder(val binding: ListDataUserApkFahmiBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: FahmiUserFahmi){

            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {   Glide.with(itemView)
                .load(user.avatar_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(imagefummy)
                teksviewUsername.text = user.login
                teksviewUrl.text = "github.com/${user.login}"

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ListDataUserApkFahmiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder((view))
    }



    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
      holder.bind(list[position])
    }
    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: FahmiUserFahmi)
    }

}