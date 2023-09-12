package com.bupware.ricardogdztest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.bupware.ricardogdztest.core.navigation.Destinations

@Preview
@Composable
fun PreviewCharacterFile(){
    CharacterFile(1)
}

@Composable
fun CharacterFile(characterID:Int, viewModel: CharacterFileViewModel = hiltViewModel()){

    viewModel.initFile(characterID)

    CharacterFileBody()
}

@Composable
fun CharacterFileBody(viewModel: CharacterFileViewModel = hiltViewModel()){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Red)) {
        Text(text = viewModel.currentCharacter.name)

        Image(
            painter = rememberAsyncImagePainter(viewModel.currentCharacter.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}