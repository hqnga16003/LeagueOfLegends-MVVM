package com.example.leagueoflegendsapp.detail

import ChampionDetailModel
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.leagueoflegendsapp.KtorClient
import com.example.leagueoflegendsapp.detail.model.PassiveModel
import com.example.leagueoflegendsapp.detail.model.SkinModel
import com.example.leagueoflegendsapp.detail.model.SpellModel

@Composable
fun ChampionDetailScreen(championName: String) {
    val viewModel = hiltViewModel<ChampionDetailViewModel>()
    val champion = viewModel.champion.value
    viewModel.get(championName)
    Scaffold { innerPadding ->
        if (champion == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            ChampionDetailView(
                champion, modifier = Modifier
                    .fillMaxSize()
                    .padding()
            )
        }
    }
}

@Composable
fun ChampionDetailView(champion: ChampionDetailModel, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {

        item {
            ChampionImage(
                champion.name, champion.skins, Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            ChampionHeader(
                champion, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            )
            ChampionLore(
                champion.lore, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            )
            ChampionPassive(
                champion.passive, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            )
            champion.spells.forEach { spell ->
                ChampionSpell(
                    spell, modifier = Modifier.padding(
                        horizontal = 6.dp, vertical = 10.dp
                    )
                )
            }

        }
    }
}

@Composable
fun ChampionImage(championName: String, skins: List<SkinModel>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = {
        skins.size
    })
    HorizontalPager(state = pagerState) { page ->
        AsyncImage(
            model = KtorClient.IMAGE_SPLASH_URL + "${championName}_${page}.jpg",
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
        )
    }

}

@Composable
fun ChampionHeader(champion: ChampionDetailModel, modifier: Modifier) {
    ListItem(headlineContent = {
        Text(text = champion.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }, supportingContent = {
        Text(
            text = champion.tags.firstOrNull() ?: ""
        )

    }, leadingContent = {
        AsyncImage(
            model = KtorClient.IMAGE_SQUARE_URL + "${champion.name}.png",
            contentDescription = null,
            modifier = Modifier.size(30.dp)

        )
    }, trailingContent = {
        Text(
            text = champion.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium

        )
    }, modifier = modifier)
}

@Composable
fun ChampionLore(
    lore: String, modifier: Modifier = Modifier
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.clickable {
            isExpanded = !isExpanded
        }) {
        Text(
            text = "Lore",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = lore,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = if (isExpanded) 1000 else 3,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 22.sp,
            modifier = Modifier.animateContentSize()
        )

        Text(
            text = if (isExpanded) "Show less" else "Show more",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ChampionSpell(spell: SpellModel, modifier: Modifier = Modifier) {
    ListItem(
        headlineContent = {
            Text(text = spell.name)
        }, supportingContent = {
            Text(text = spell.description.replace("<br>", ""))
        }, leadingContent = {
            AsyncImage(
                model = KtorClient.IMAGE_ABILITY_URL + "${spell.id}.png",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }, modifier = modifier
    )
}

@Composable
fun ChampionPassive(passive: PassiveModel, modifier: Modifier = Modifier) {
    Log.d("XXX",KtorClient.IMAGE_PASSIVE_URL + passive.image.full)
    ListItem(
        headlineContent = {
            Text(text = passive.name)
        }, supportingContent = {
            Text(text = passive.description.replace("<br>", "\n"))
        }, leadingContent = {
            AsyncImage(
                model = KtorClient.IMAGE_PASSIVE_URL + passive.image.full,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }, modifier = modifier
    )
}