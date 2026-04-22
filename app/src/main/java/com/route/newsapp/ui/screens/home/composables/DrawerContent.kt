package com.route.newsapp.ui.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.route.newsapp.ui.theme.Black
import com.route.newsapp.ui.theme.NewsDarkTypography
import com.route.newsapp.ui.theme.White

@Composable
fun DrawerContent(onGOToHomeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight()
            .background(Black)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.2f)
                .background(White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "News App",
                style = NewsDarkTypography.titleSmall.copy(
                    color = Black,
                    fontWeight = Bold
                )
            )
        }

        DrawerRow(
            icon = Icons.Default.Home,
            title = "Go To Home",
            onClick = {
                onGOToHomeClick()
            }
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        DrawerRow(
            icon = Icons.Default.Create,
            title = "Themes",
            onClick = {}
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        DrawerRow(
            icon = Icons.Default.Info,
            title = "Language",
            onClick = {}
        )
    }
}

@Composable
fun DrawerRow(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = "",
            tint = White
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            style = NewsDarkTypography.bodyLarge.copy(color = White)
        )
    }
}