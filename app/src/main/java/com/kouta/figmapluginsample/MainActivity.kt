package com.kouta.figmapluginsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.kouta.figmapluginsample.ui.theme.FigmaPluginSampleTheme
import com.kouta.figmapluginsample.ui.theme.LargeLabel
import com.kouta.figmapluginsample.ui.theme.MainBackground
import com.kouta.figmapluginsample.ui.theme.MainBlack
import com.kouta.figmapluginsample.ui.theme.MediumLabel

class MainActivity : ComponentActivity() {
    companion object {
        private val spots = listOf(
            Spot(
                image = R.drawable.skytree,
                name = "東京スカイツリー",
                postCode = "131-0045",
                address = "東京都墨田区押上１丁目１−２"
            ),
            Spot(
                image = R.drawable.skytree,
                name = "東京スカイツリー",
                postCode = "131-0045",
                address = "東京都墨田区押上１丁目１−２"
            ),
            Spot(
                image = R.drawable.skytree,
                name = "東京スカイツリー",
                postCode = "131-0045",
                address = "東京都墨田区押上１丁目１−２"
            ),
            Spot(
                image = R.drawable.skytree,
                name = "東京スカイツリー",
                postCode = "131-0045",
                address = "東京都墨田区押上１丁目１−２"
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FigmaPluginSampleTheme {
                ListScreen(spots)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    spots: List<Spot>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.padding(4.dp),
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp),
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = MainBlack
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Figma Plugin",
                        textAlign = TextAlign.Center,
                        color = MainBlack
                    )
                },
                actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp),
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            tint = MainBlack
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainBackground
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = spots.size
            ) { index ->
                spots.getOrNull(index)?.let {
                    CardAt(it)
                }
            }
        }
    }
}

@Composable
fun CardAt(
    spot: Spot
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (image, name, postCode, address, favorite) = createRefs()

            Image(
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top)
                    linkTo(parent.start, parent.end)
                    width = Dimension.fillToConstraints
                },
                painter = painterResource(spot.image),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )

            Text(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(image.bottom, 8.dp)
                    linkTo(parent.start, favorite.start, startMargin = 8.dp)
                    width = Dimension.fillToConstraints
                },
                text = spot.name,
                style = LargeLabel
            )

            Text(
                modifier = Modifier.constrainAs(postCode) {
                    top.linkTo(name.bottom)
                    linkTo(name.start, name.end)
                    width = Dimension.fillToConstraints
                },
                text = "〒${spot.postCode}",
                style = MediumLabel
            )

            Text(
                modifier = Modifier.constrainAs(address) {
                    top.linkTo(postCode.bottom)
                    bottom.linkTo(parent.bottom, 8.dp)
                    linkTo(name.start, parent.end)
                    width = Dimension.fillToConstraints
                },
                text = spot.address,
                style = MediumLabel
            )

            IconButton(
                modifier = Modifier.constrainAs(favorite) {
                    top.linkTo(image.bottom, 4.dp)
                    end.linkTo(parent.end, 4.dp)
                },
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp),
                    painter = painterResource(R.drawable.bookmark),
                    contentDescription = null,
                    tint = MainBlack
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    FigmaPluginSampleTheme {
        ListScreen(
            spots = listOf(
                Spot(
                    image = R.drawable.skytree,
                    name = "東京スカイツリー",
                    postCode = "131-0045",
                    address = "東京都墨田区押上１丁目１−２"
                )
            )
        )
    }
}