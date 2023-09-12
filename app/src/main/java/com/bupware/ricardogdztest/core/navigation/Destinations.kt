package com.bupware.ricardogdztest.core.navigation

sealed class Destinations (val ruta : String) {

    object HomeScreen: Destinations("HomeScreen")
    object CharacterFile: Destinations("CharacterFile")

}