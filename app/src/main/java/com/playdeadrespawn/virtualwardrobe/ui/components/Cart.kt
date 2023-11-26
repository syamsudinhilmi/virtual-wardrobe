package com.playdeadrespawn.virtualwardrobe.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun ItemCart(
    id: Long,
    name: String,
    count: Int,
    price: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    image: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = name,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = "Price: $price",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        ProductCounter(
            orderId = id,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(id, count + 1) },
            onProductDecreased = { onProductCountChanged(id, count - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CartPreview() {
    VirtualWardrobeTheme{
        ItemCart(
            id = 2,
            name = "INAZAWA 1986 WINDBREAKER JACKET",
            count = 0,
            onProductCountChanged = {id, count ->  },
            price = 200000,
            image = "https://images.tokopedia.net/img/cache/900/VqbcmM/2023/4/8/48d3bc02-b3d9-4e49-acf6-e26f5efaa5ef.jpg"
        )
    }
}