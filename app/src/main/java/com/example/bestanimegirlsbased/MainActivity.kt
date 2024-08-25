package com.example.bestanimegirlsbased

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bestanimegirlsbased.data.DataSource
import com.example.bestanimegirlsbased.model.BestGirl
import com.example.bestanimegirlsbased.ui.theme.BestAnimeGirlsBasedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BestAnimeGirlsBasedTheme {
                BestGirlApp()
            }
        }
    }
}

@Composable
fun BestGirlApp(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier) { innerPadding ->
        BestGirlList(
            bestGirlList = DataSource.bestGirlList,
            paddingValues = innerPadding,
        )
    }
}

@Composable
fun BestGirlList(bestGirlList: List<BestGirl>, paddingValues: PaddingValues, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(bestGirlList) { bestGirl ->
            BestGirlCard(bestGirl = bestGirl)
        }
    }
}

@Composable
@Preview
fun BestGirlAppPrev() {
    BestAnimeGirlsBasedTheme {
        BestGirlApp()
    }
}

@Composable
@Preview
fun BestGirlAppDarkThemePrev() {
    BestAnimeGirlsBasedTheme(darkTheme = true) {
        BestGirlApp()
    }
}