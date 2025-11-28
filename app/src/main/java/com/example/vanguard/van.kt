package com.example.vanguard.model

data class Van(
    val id: Long = 0,
    val modelo: String,
    val placa: String,
    val motorista: String,
    val precoMensal: Double,
    val telefone: String,
    val imagemResId: Int? = null // Para o POC, podemos usar ícones locais
)