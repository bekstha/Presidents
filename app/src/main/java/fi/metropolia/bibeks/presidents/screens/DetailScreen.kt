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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.metropolia.bibeks.presidents.President
import fi.metropolia.bibeks.presidents.R
import fi.metropolia.bibeks.presidents.utils.WikiRepository
import fi.metropolia.bibeks.presidents.utils.provideWikipediaService
import fi.metropolia.bibeks.presidents.viewmodels.DetailViewModel

@Composable
fun DetailScreen(
    presidentList: MutableList<President>,
    itemIndex: Int,
    modifier: Modifier = Modifier,
) {
    val selectedPresident = presidentList[itemIndex]

    val wikipediaService = provideWikipediaService()
    val wikiRepository = WikiRepository(wikipediaService)
    val detailViewModel = DetailViewModel(wikiRepository)

    // Use the ViewModel to get hit counts
    LaunchedEffect(detailViewModel) {
        detailViewModel.getHits(selectedPresident.name)
    }

    val hitCount by detailViewModel.wikiUiState.collectAsState()

    Card(
        modifier = modifier
            .padding(10.dp)
            .wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
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

            Text(text = "Wikipedia Hits: ${hitCount ?: "N/A"}")

        }
    }
}


