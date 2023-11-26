package com.playdeadrespawn.virtualwardrobe.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.playdeadrespawn.virtualwardrobe.ui.theme.Shapes
import com.playdeadrespawn.virtualwardrobe.ui.theme.VirtualWardrobeTheme

@Composable
fun WardrobeItem(
    image: String,
    name: String,
    price: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(170.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = "Rp. $price",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }


}

@Preview(showBackground = true)
@Composable
fun WardrobeItemPreview() {
    VirtualWardrobeTheme {
        WardrobeItem(
            image = "https://images.tokopedia.net/img/cache/900/VqbcmM/2023/4/8/48d3bc02-b3d9-4e49-acf6-e26f5efaa5ef.jpg",
            name = "INAZAWA 1986 WINDBREAKER JACKET",
            price = 200000
        )
    }

}