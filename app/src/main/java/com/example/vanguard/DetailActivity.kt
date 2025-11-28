package com.example.vanguard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vanguard.databinding.ActivityDetailBinding
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar dados passados pela Intent
        val modelo = intent.getStringExtra("modelo")
        val placa = intent.getStringExtra("placa")
        val motorista = intent.getStringExtra("motorista")
        val preco = intent.getDoubleExtra("preco", 0.0)

        // Preencher a tela
        binding.detailModelo.text = modelo
        binding.detailPlaca.text = placa
        binding.detailMotorista.text = motorista

        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        binding.detailPreco.text = format.format(preco)

        // Ação do Botão Contratar
        binding.btnContratar.setOnClickListener {
            // Aqui você pode adicionar lógica para salvar o "contrato" no SQLite futuramente
            Toast.makeText(this, "Solicitação enviada para $motorista com sucesso!", Toast.LENGTH_LONG).show()
            finish() // Fecha a tela e volta para a lista
        }
    }
}