package com.example.openweatherapp.model.enums

enum class Location(val description: String, val code: String, val lat: String, val long: String) {
    BUDAPEST("Budapest", "hu", "47.4980", "19.0399"),
    NYIREGYHAZA("Nyíregyháza", "hu", "47.9553", "21.7167"),
    DEBRECEN("Debrecen", "hu", "47.5333", "21.6333"),
    MISKOLC("Miskolc", "hu", "48.1055", "20.7833");

    override fun toString(): String {
        return description
    }
}