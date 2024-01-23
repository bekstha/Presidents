package fi.metropolia.bibeks.presidents.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fi.metropolia.bibeks.presidents.President

@Composable
fun MainScreen(
    presidentList: MutableList<President>,
    navController: NavController,
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ){
        val presidentsCount = presidentList.size
        items(presidentsCount){
            PresidentList(
                itemIndex = it,
                president = presidentList,
                navController = navController,
                modifier
            )
        }
    }
}

@Composable
fun PresidentList     (
    itemIndex: Int,
    president: MutableList<President>,
    navController: NavController,
    modifier: Modifier) {

    Card(modifier = Modifier
        .padding(10.dp)
        .wrapContentSize()
        .clickable {
            navController.navigate(route= "DetailScreen/$itemIndex")
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(text = president[itemIndex].name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
    }
}