package com.bupware.ricardogdztest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.bupware.ricardogdztest.R
import com.bupware.ricardogdztest.core.navigation.Destinations
import com.bupware.ricardogdztest.core.utils.systemBarController.SystemBarColor
import com.bupware.ricardogdztest.ui.theme.Lexend
import com.bupware.ricardogdztest.ui.theme.LightBlack

@Preview
@Composable
fun PreviewCharacterFile(){
    CharacterFile(rememberNavController(),2)
}

@Composable
fun CharacterFile(navController: NavController,characterID:Int, viewModel: CharacterFileViewModel = hiltViewModel()){

    val context = LocalContext.current

    viewModel.initFile(characterID, context)

    SystemBarColor(color = LightBlack)

    CharacterFileBody(navController = navController)
}

@Composable
fun CharacterFileBody(navController: NavController,viewModel: CharacterFileViewModel = hiltViewModel()){
    Column(
        Modifier
            .fillMaxSize()) {

        Box(Modifier.fillMaxSize()) {

            Image(
                painter = rememberAsyncImagePainter(viewModel.currentCharacter.image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
            )
            
            Column(
                Modifier
                    .height(500.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .fillMaxWidth()
                    .background(LightBlack), horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = viewModel.currentCharacter.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Lexend,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = 3.dp, top = 5.dp))

                Text(
                    text = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Lexend,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(20.dp))

            }

            IconButton(
                modifier = Modifier.padding(top = 3.dp, bottom = 3.dp, start = 6.dp, end = 6.dp),
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                    tint = Color.Black,
                    contentDescription = "return"
                )
            }
            
        }

    }
}