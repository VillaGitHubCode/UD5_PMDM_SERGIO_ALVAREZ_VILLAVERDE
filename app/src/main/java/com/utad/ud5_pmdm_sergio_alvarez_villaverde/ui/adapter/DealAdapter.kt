package com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.R
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.network.model.Deal
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.databinding.ItemDealBinding

class DealAdapter(): ListAdapter<Deal, DealAdapter.DealViewHolder>(DealItemCallBack) {

    //Aqui inflamos la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDealBinding.inflate(inflater, parent, false)
        return DealViewHolder(binding)
    }

    //Aqui recuperamos el elemento que nos toca pintar
    override fun onBindViewHolder(holder: DealViewHolder, position: Int) {

        val deal = getItem(position)
        holder.binding.tvTitleDeal.text = deal.title
        holder.binding.tvOldPrice.text = deal.normalPrice
        holder.binding.tvNewPrice.text = deal.salePrice


        Glide.with(holder.binding.root).load(deal.thumb)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.ivImageDeal)

    }

    inner class DealViewHolder (val binding: ItemDealBinding): RecyclerView.ViewHolder(binding.root)
}


//ESTAS FUNCIONES REVISAN SI HA HABIDO ACTUALIZACIONES EN LOS DATOS QUE MUESTRA LA RECYCLER VIEW
object DealItemCallBack: DiffUtil.ItemCallback<Deal>(){
    override fun areItemsTheSame(oldItem: Deal, newItem: Deal): Boolean {
        return oldItem.gameID == newItem.gameID
    }

    override fun areContentsTheSame(oldItem: Deal, newItem: Deal): Boolean {
        return oldItem == newItem
    }

}