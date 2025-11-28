package com.example.vanguard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vanguard.adapter.VanAdapter
import com.example.vanguard.data.DatabaseHelper
import com.example.vanguard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Banco de Dados
        dbHelper = DatabaseHelper(this)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Buscar dados do SQLite
        val listaDeVans = dbHelper.getAllVans()

        val adapter = VanAdapter(listaDeVans) { van ->
            // Ação ao clicar em um item (abrir detalhes)
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", van.id)
                putExtra("modelo", van.modelo)
                putExtra("placa", van.placa)
                putExtra("motorista", van.motorista)
                putExtra("preco", van.precoMensal)
                putExtra("telefone", van.telefone)
            }
            startActivity(intent)
        }

        binding.recyclerViewVans.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewVans.adapter = adapter
    }
}