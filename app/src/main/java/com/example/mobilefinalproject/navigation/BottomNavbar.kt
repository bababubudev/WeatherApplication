package com.example.mobilefinalproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.mobilefinalproject.dataclass.NavItem

@Composable
fun BottomNavbar(
  navController: NavController,
  selectedItem: Int,
  onItemSeleted: (Int) -> Unit
) {
  val items = listOf(
    NavItem(
      title = "Today",
      selectedIcon = Icons.Filled.Today,
      unselectedIcon = Icons.Outlined.Today,
      hasNews = false
    ),
    NavItem(
      title = "5 Days",
      selectedIcon = Icons.Filled.CalendarMonth,
      unselectedIcon = Icons.Outlined.CalendarMonth,
      hasNews = false,
    ),
    NavItem(
      title = "Settings",
      selectedIcon = Icons.Filled.Settings,
      unselectedIcon = Icons.Outlined.Settings,
      hasNews = false
    ),
  )

  val noAnimationNavOptions = NavOptions.Builder()
    .setEnterAnim(0)
    .setExitAnim(0)
    .setPopEnterAnim(0)
    .setPopExitAnim(0)
    .build()

  NavigationBar {
    items.forEachIndexed { ind, item ->
      NavigationBarItem(
        selected = selectedItem == ind,
        onClick = {
          onItemSeleted(ind)
          navController.navigate(item.title, noAnimationNavOptions)
        },
        label = {
          Text(text = item.title)
        },
        icon = {
          BadgedBox(badge = {
            if (item.badgeCount != null) {
              Badge {
                Text(text = item.badgeCount.toString())
              }
            } else if (item.hasNews) {
              Badge()
            }
          }) {
            Icon(
              imageVector = if (ind == selectedItem) item.selectedIcon else item.unselectedIcon,
              contentDescription = "Selection icon"
            )
          }
        }
      )
    }
  }
}