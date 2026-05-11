package com.shilpakala.showcase.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.shilpakala.showcase.ui.theme.*

data class NavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onAboutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavItem("Home", Icons.Filled.Home, Icons.Outlined.Home, "home"),
        NavItem("Heritage", Icons.Filled.Home, Icons.Outlined.Home, "heritage"),
        NavItem("About", Icons.Filled.Info, Icons.Outlined.Info, "about")
    )

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Surface,
        shadowElevation = 8.dp,
        tonalElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = when (item.route) {
                    "home" -> currentRoute == "home"
                    "heritage" -> currentRoute == "heritage"
                    else -> false
                }

                if (item.route == "about") {
                    NavBarItem(
                        item = item,
                        isSelected = false,
                        onClick = onAboutClick
                    )
                } else {
                    NavBarItem(
                        item = item,
                        isSelected = isSelected,
                        onClick = { onNavigate(item.route) }
                    )
                }
            }
        }
    }
}

@Composable
private fun NavBarItem(
    item: NavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val containerColor = if (isSelected) SecondaryContainer else Surface
    val contentColor = if (isSelected) OnSecondaryContainer else OnSurfaceVariant

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        color = containerColor,
        contentColor = contentColor
    ) {
        Column(
            modifier = Modifier.padding(horizontal = if (isSelected) 20.dp else 12.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (item.route == "heritage") {
                    if (isSelected) Icons.Filled.Home else Icons.Outlined.Home
                } else {
                    if (isSelected) item.selectedIcon else item.unselectedIcon
                },
                contentDescription = item.label,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = item.label,
                style = MaterialTheme.typography.labelMedium,
                color = contentColor
            )
        }
    }
}
