package fi.metropolia.bibeks.presidents.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.metropolia.bibeks.presidents.President
import fi.metropolia.bibeks.presidents.R

@Composable
fun DetailScreen(
    presidentList: MutableList<President>,
    itemIndex: Int,
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier
        .padding(10.dp)
        .wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Column(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                text = presidentList[itemIndex].name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(
                    R.string.tenure,
                    presidentList[itemIndex].startYear,
                    presidentList[itemIndex].endYear
                ),
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = presidentList[itemIndex].description,
                fontSize = 18.sp
            )
        }
    }

}
