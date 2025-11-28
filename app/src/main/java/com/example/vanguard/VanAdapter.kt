package com.example.vanguard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vanguard.databinding.ItemVanBinding
import com.example.vanguard.model.Van
import java.text.NumberFormat
import java.util.Locale

class VanAdapter(
    private val vans: List<Van>,
    private val onClick: (Van) -> Unit
) : RecyclerView.Adapter<VanAdapter.VanViewHolder>() {

    inner class VanViewHolder(val binding: ItemVanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VanViewHolder {
        val binding = ItemVanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VanViewHolder, position: Int) {
        val van = vans[position]
        with(holder.binding) {
            textModelo.text = van.modelo
            textMotorista.text = "Motorista: ${van.motorista}"

            val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            textPreco.text = "${format.format(van.precoMensal)} / mês"

            root.setOnClickListener { onClick(van) }
        }
    }

    override fun getItemCount() = vans.size
}