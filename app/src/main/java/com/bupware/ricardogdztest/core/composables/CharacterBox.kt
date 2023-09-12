package com.bupware.ricardogdztest.core.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.bupware.ricardogdztest.core.DTO.Character
import com.bupware.ricardogdztest.core.navigation.Destinations

@Composable
fun CharacterBox(character: Character, navController: NavController){

    Column(
        Modifier
            .height(180.dp)
            .width(110.dp)
    ) {

        Column(
            Modifier
                .height(130.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color.DarkGray)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable{navController.navigate("${Destinations.CharacterFile.ruta}/${character.id}")},
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Text(modifier = Modifier.padding(5.dp), text = character.name, color = Color.White, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Justify, lineHeight = 13.sp)
    }

}