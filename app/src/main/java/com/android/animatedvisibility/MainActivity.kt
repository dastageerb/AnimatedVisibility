package com.android.animatedvisibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val expanded = remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .background(color = Color(0xFFECECEC))
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(36.dp))

                PersonComposable(
                    name = "John doe",
                    address = "500 George St. N. Peterborough", expanded = expanded.value
                ) {
                    expanded.value = !expanded.value
                }
            }
        }
    }
}


@Composable
@Preview
fun PersonPreview() {
    PersonComposable(
        name = "John doe",
        address = "500 George St. N. Peterborough", false
    ) {}
}


@Composable
@Preview
fun PersonExpandedPreview() {
    PersonComposable(
        name = "John doe",
        address = "500 George St. N. Peterborough", true
    ) {

    }
}


@Composable
fun PersonComposable(
    name: String,
    address: String,
    expanded: Boolean,
    onExpandIconClicked: () -> Unit
) {
    val rotateState = animateFloatAsState(targetValue = if (expanded) 0f else (-90f))
    Card(
        modifier = Modifier.fillMaxWidth(0.85f),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    color = Color.Black,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(alignment = Alignment.CenterStart)
                )

                Image(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Icon_Expand",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            onExpandIconClicked()
                        }
                        .rotate(rotateState.value)
                        .align(alignment = Alignment.CenterEnd)
                )
            }

//
//            if (expanded) {
//                Text(
//                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
//                    ,
//                    text = address,
//                    color = Color.Black,
//                    fontWeight = FontWeight.W500,
//                )
//            }

            AnimatedVisibility(
                visible = expanded
            ) {
                Text(
                    text = address,
                    color = Color.Black,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}