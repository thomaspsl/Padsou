package com.example.padsou.layouts.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.padsou.data.Tips
import com.example.padsou.models.HomeViewModel
import com.example.padsou.ui.components.ShortPlanCard
import com.example.padsou.ui.theme.*

@Composable
fun LastHome(navComposable: NavController){
    // Variables
    val viewModel = viewModel<HomeViewModel>()
    val plans: State<List<Tips>> = viewModel.tips.collectAsState()

    // Content
    Row() {
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 20.dp)
            ) {
                Text(
                    text = LastHomeTitle,
                    color = PadsouDark,
                    style = LabelIntegralExtraBold14
                )
                Text(
                    text = LastHomeSubTitle,
                    color = PadsouSalmon,
                    style = LabelIntegralBold14
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                userScrollEnabled = true,
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                for(p in plans.value){
                    item {
                        var uriLo by remember { mutableStateOf("") }

                        viewModel. getPicturesOfTipsWithNames(p.image) { uri ->
                            uriLo = uri
                        }

                        ShortPlanCard(navComposable, p, uriLo)
                    }
                }
            }
        }
    }
}