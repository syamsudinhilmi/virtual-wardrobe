package com.playdeadrespawn.virtualwardrobe.ui.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.playdeadrespawn.virtualwardrobe.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier){
        ProfileScreenContent(
            image = stringResource(R.string.profile),
            modifier = Modifier
                .padding(top = 144.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        )

    }

}

@Composable
fun ProfileScreenContent(
    image: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(256.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Hilmi Syamsudin",
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
        )
        Text(
            text = "hilmisyamsudin@students.unnes.ac.id",
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}