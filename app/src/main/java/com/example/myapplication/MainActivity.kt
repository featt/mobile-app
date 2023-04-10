package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

data class Lesson(val lessonName: String, val time: String, val theme: String)
data class NavItem(val label: String, val icon: ImageVector)
data class SheduleItem(val lessonName: String, val time: String, val theme: String)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedItem by remember { mutableStateOf(0) }
            val items = listOf(
                NavItem("Уроки сегодня", Icons.Filled.List),
                NavItem("Рассписание недели", Icons.Filled.Check),
            )

            val shedulesMath = listOf(
                SheduleItem("Математика", "10:40 - 11:25", "Решение уравнений"),
                SheduleItem("Математика", "11:30 - 12:15", "Решение уравнений"),
                SheduleItem("Математика", "12:20 - 13:05", "Решение уравнений"),
            )

            val changeSelected = { newIndex: Int -> selectedItem = newIndex}
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar(title=items[selectedItem].label) },
                    bottomBar = { BottomBar(items, selectedItem, changeSelected) }
                ) { padding ->
                    if (selectedItem == 0) {
                        LessonView(
                            lessons = listOf(
                                Lesson("Математика", "10:40 - 11:25",  "Решение уравнений"),
                                Lesson("Русский язык","10:40 - 11:25",  "Решение уравнений"),
                                Lesson("Биология", "10:40 - 11:25",  "Решение уравнений"),
                                Lesson("Литература", "10:40 - 11:25",  "Решение уравнений"),
                            ), paddingValues = padding
                        )
                    } else {
                        ShedulePage(paddingValues = padding)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShedulePage(paddingValues: PaddingValues) {
    LazyColumn(contentPadding = paddingValues) {
        items(count = 1) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Понедельник", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "1. Математика")
                    Text(text = "2. Русский язык")
                    Text(text = "3. Литература")
                }
            }
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Вторник", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "1. Биология")
                    Text(text = "2. Русский язык")
                    Text(text = "3. Химия")
                }
            }
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Среда", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "1. Английский язык")
                    Text(text = "2. Русский язык")
                    Text(text = "3. Химия")
                }
            }
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Четверг", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "1. Технология")
                    Text(text = "2. Информатика")
                    Text(text = "3. Химия")
                }
            }
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Пятница", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "1. Технология")
                    Text(text = "2. Информатика")
                    Text(text = "3. Химия")
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec = rememberSplineBasedDecay()
    )
    SmallTopAppBar(
        title = { Text(text = title) },
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun BottomBar(navItems: List<NavItem>, selectedItem: Int, changeSelected: (Int) -> Unit) {
    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = { changeSelected(index) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonCard(
    lessonName: String,
    time: String,
    theme: String,
    changeSelected: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = lessonName, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = time, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = theme, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {changeSelected(1)},
                modifier = Modifier.fillMaxWidth()
            ) { Text(text = "Открыть") }
        }
    }
}

@Composable
fun LessonView(lessons: List<Lesson>, paddingValues: PaddingValues) {
    LazyColumn(contentPadding = paddingValues) {
        items(count = lessons.size) {
            LessonCard(
                lessonName = lessons[it].lessonName,
                time = lessons[it].time,
                theme = lessons[it].theme
            ) {
            }
        }
    }
}

