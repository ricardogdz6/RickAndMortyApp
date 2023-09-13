package com.bupware.ricardogdztest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bupware.ricardogdztest.core.DTO.Character
import com.bupware.ricardogdztest.core.composables.CharacterBox
import com.bupware.ricardogdztest.core.composables.CharacterBoxBig
import com.bupware.ricardogdztest.core.composables.TextFieldNoPadding
import com.bupware.ricardogdztest.core.utils.systemBarController.SystemBarColor
import com.bupware.ricardogdztest.ui.theme.Lexend
import com.bupware.ricardogdztest.ui.theme.LightBlack

var initExecuted = false
var randomIndex = (0..39).random()

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()){

    if (!initExecuted){
        val context = LocalContext.current
        viewModel.initHomeScreen(context)
        initExecuted = true
    }

    SystemBarColor(color = LightBlack)

    HomeScreenBody(navController)

}

@Composable
fun HomeScreenBody(navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()){

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .background(LightBlack)) {

        //region SEARCH BAR
        Column(
            Modifier
                .height(85.dp)
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(Modifier.clip(RoundedCornerShape(20.dp)).background(Color.White)) {
                TextFieldNoPadding(
                    modifier = Modifier.fillMaxSize(),
                    value = viewModel.searchingTerm,
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent, focusedIndicatorColor = Color.White, cursorColor = Color.Black),
                    placeholder = { Text(text = "Search character by name", fontFamily = Lexend, color = Color.Black) },
                    onValueChange = { viewModel.searchTerm(it, context) },
                    textStyle = TextStyle(fontFamily = Lexend),
                )
            }
        }
        //endregion

        if (viewModel.characterListSearch.isEmpty()){
            HomeContent(navController = navController)
        } else {
            Column(Modifier.fillMaxSize()) {

                CharacterListSearch(characters = viewModel.characterListSearch, navController)

            }
        }

    }


}

@Composable
fun HomeContent(viewModel: HomeScreenViewModel = hiltViewModel(), navController: NavController){
    if (viewModel.characterList.isEmpty()){
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {

        Column(
            Modifier
                .padding(top = 0.dp, start = 20.dp, end = 20.dp)
                .fillMaxSize()
        ) {
            //region CHARACTER LIST
            Text(
                text = "Character List",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = Lexend,
                fontSize = 25.sp,
                modifier = Modifier.padding(bottom = 3.dp)
            )

            if (viewModel.characterList.isNotEmpty()) CharacterList(
                characters = viewModel.characterList.subList(
                    0, 20
                ), navController = navController
            )
            //endregion

            //region OUTSTANDING CHARACTER
            Column(Modifier.padding(top = 10.dp, bottom = 10.dp)) {
                CharacterBoxBig(character = viewModel.characterList[randomIndex], navController = navController)
            }
            //endregion

            //region RANDOM CHARACTER LIST
            Text(
                text = "Random Character List",
                color = Color.White,
                modifier = Modifier.padding(bottom = 3.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = Lexend,
                fontSize = 25.sp,
            )


            if (viewModel.characterList.isNotEmpty()) CharacterList(
                characters = viewModel.characterList.subList(
                    20, 39
                ), navController
            )
            //endregion
        }

    }
}

@Composable
fun CharacterList(characters: List<Character>, navController: NavController){

    LazyRow(Modifier.clip(RoundedCornerShape(6.dp))){
        items(characters.size){ index ->  

            CharacterBox(character =  characters[index], navController = navController)
            Spacer(modifier = Modifier.padding(end = 8.dp))
            
        }
    }

}

@Composable
fun CharacterListSearch(characters: List<Character>, navController: NavController){

    LazyColumn(contentPadding = PaddingValues(20.dp)){
        items(characters.size){index->
            CharacterBoxBig(character = characters[index], navController = navController)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }

}