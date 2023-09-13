package com.bupware.ricardogdztest.core.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.bupware.ricardogdztest.core.DTO.Character
import com.bupware.ricardogdztest.core.navigation.Destinations
import com.bupware.ricardogdztest.ui.theme.Lexend

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
                    .clickable { navController.navigate("${Destinations.CharacterFile.ruta}/${character.id}") },
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier.padding(5.dp),
            text = character.name,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify,
            lineHeight = 13.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Lexend,
        )
    }

}

@Composable
fun CharacterBoxBig(character: Character, navController: NavController){
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("${Destinations.CharacterFile.ruta}/${character.id}") }
            .height(160.dp)
            .background(Color.DarkGray, RoundedCornerShape(15.dp))) {

        Box(Modifier.weight(0.4f)) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
                ,
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Box(Modifier.weight(0.6f)) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(5.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = character.name,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Lexend,
                )


                Row(Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {

                    val color = when(character.status.lowercase()){
                        "alive" -> {Color.Green}
                        "dead" -> {Color.Red}
                        else -> {Color.Transparent}
                    }

                    if (color != Color.Transparent){
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(color)
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = character.status,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                        lineHeight = 13.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Lexend,

                    )

                    Text(
                        text = " - ",
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                        lineHeight = 13.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Lexend,
                    )

                    Text(
                        text = character.species,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                        lineHeight = 13.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Lexend,
                    )
                }



            }
        }

    }
}