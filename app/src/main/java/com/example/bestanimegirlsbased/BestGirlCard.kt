package com.example.bestanimegirlsbased

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.bestanimegirlsbased.model.BestGirl
import com.example.bestanimegirlsbased.ui.theme.BestAnimeGirlsBasedTheme

@Composable
fun BestGirlCard(bestGirl: BestGirl, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = (-4).dp
        )
    ) {
        val spacerWeight by animateFloatAsState(targetValue = if (expanded) 0.1f else 1f, label = "Spacer weight")

        Box(
            modifier = Modifier
                .clickable { expanded = !expanded }
        ) {
            Row {
                Spacer(modifier = Modifier.weight(spacerWeight))

                BestGirlItem(
                    bestGirl = bestGirl,
                    expanded = expanded,
                    modifier = Modifier.weight(2.5f)
                )

                Spacer(modifier = Modifier.weight(spacerWeight))
            }

            Text(
                text = stringResource(if (expanded) R.string.tap_to_collapse else R.string.tap_to_expand),
                modifier = Modifier
                    .rotate(-90f)
                    .padding(bottom = 52.dp)
                    .align(Alignment.CenterStart),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                letterSpacing = TextUnit(0.5f, TextUnitType.Sp),
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
fun BestGirlItem(
    bestGirl: BestGirl,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .animateContentSize(
                animationSpec = spring(stiffness = Spring.StiffnessLow)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val width by animateDpAsState(targetValue = if (expanded) 100.dp else 60.dp, label = "Image width")
        val height by animateDpAsState(targetValue = if (expanded) 140.dp else 100.dp, label = "Image height")

        Row(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
                ),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = bestGirl.imageRes),
                contentDescription = stringResource(id = bestGirl.nameRes),
                modifier = Modifier
                    .padding(4.dp)
                    .size(
                        width = width,
                        height = height
                    )
                    .clip(if (expanded) MaterialTheme.shapes.large else MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )

            if (expanded) {
                Column {
                    Text(
                        text = stringResource(id = bestGirl.nameRes),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(id = R.string.from) + stringResource(id = bestGirl.animeRes),
                        modifier = Modifier.padding(bottom = 8.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(id = bestGirl.descriptionRes),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        if (!expanded) {
            Text(
                text = stringResource(id = bestGirl.nameRes),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.from) + stringResource(id = bestGirl.animeRes),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun BestGirlCardPrev() {
    BestAnimeGirlsBasedTheme {
        BestGirlCard(
            bestGirl = BestGirl(
                nameRes = R.string.bestgirl_name_1,
                animeRes = R.string.anime_1,
                descriptionRes = R.string.bestgirl_des_1,
                imageRes = R.drawable.yuigahama_yui
            )
        )
    }
}

@Composable
@Preview
fun BestGirlCardDarkThemePrev() {
    BestAnimeGirlsBasedTheme(darkTheme = true) {
        BestGirlCard(
            bestGirl = BestGirl(
                nameRes = R.string.bestgirl_name_1,
                animeRes = R.string.anime_1,
                descriptionRes = R.string.bestgirl_des_1,
                imageRes = R.drawable.yuigahama_yui
            )
        )
    }
}