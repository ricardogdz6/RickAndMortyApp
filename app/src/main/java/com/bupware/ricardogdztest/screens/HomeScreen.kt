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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bupware.ricardogdztest.core.DTO.Character
import com.bupware.ricardogdztest.core.composables.CharacterBox
import com.bupware.ricardogdztest.core.composables.TextFieldNoPadding
import com.bupware.ricardogdztest.core.utils.systemBarController.SystemBarColor
import com.bupware.ricardogdztest.ui.theme.LightBlack

var initExecuted = false

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

@OptIn(ExperimentalMaterial3Api::class)
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
            Column(Modifier.clip(RoundedCornerShape(20.dp))) {
                TextFieldNoPadding(modifier = Modifier.fillMaxSize(),
                    value = viewModel.searchingTerm,
                    onValueChange = { viewModel.searchTerm(it, context) })
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
        //region CHARACTER EXPLORER
        Column(
            Modifier
                .padding(top = 0.dp, start = 20.dp, end = 20.dp)
                .fillMaxSize()
        ) {

            Text(
                text = "Character List",
                color = Color.White,
                modifier = Modifier.padding(bottom = 3.dp)
            )

            //CARATULAS
            if (viewModel.characterList.isNotEmpty()) CharacterList(
                characters = viewModel.characterList.subList(
                    0, 20
                ), navController = navController
            )

            Text(
                text = "Random Character List",
                color = Color.White,
                modifier = Modifier.padding(bottom = 3.dp)
            )

            //CARATULAS
            if (viewModel.characterList.isNotEmpty()) CharacterList(
                characters = viewModel.characterList.subList(
                    20, 39
                ), navController
            )
        }
        //endregion
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

    LazyRow(Modifier.clip(RoundedCornerShape(6.dp))){
        items(characters.size){ index ->

            CharacterBox(character =  characters[index], navController = navController)
            Spacer(modifier = Modifier.padding(end = 8.dp))

        }
    }

}