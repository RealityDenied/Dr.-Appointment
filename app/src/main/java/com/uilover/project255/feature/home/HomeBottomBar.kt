package com.uilover.project255.feature.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uilover.project255.R

@Composable
fun HomeBottomBar(selected: Int, onSelect: (Int) -> Unit) {
    NavigationBar(
        containerColor = colorResource(R.color.lightGray),
        tonalElevation = 1.dp,
        modifier = Modifier
            .height(60.dp)
            .padding(bottom = 5.dp),
        windowInsets = WindowInsets(0)
    ) {
        val titles = listOf("Explorer", "Wishlist", "Settings", "Account")
        val icons = listOf(
            R.drawable.btn_1,
            R.drawable.btn_2,
            R.drawable.btn_3,
            R.drawable.btn_4,
        )
        titles.forEachIndexed { index, string ->
            NavigationBarItem(
                selected = selected == index,
                onClick = { onSelect(index) },
                icon = {
                    Icon(
                        painter = painterResource(icons[index]),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(text = string, fontSize = 10.sp, color = Color.Black)
                },
                alwaysShowLabel = true
            )
        }
    }
}

@Preview
@Composable
fun HomeBottomBarPreview() {
    HomeBottomBar(selected = 0, onSelect = {})
}
