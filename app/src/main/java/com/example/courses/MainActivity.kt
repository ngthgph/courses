package com.example.courses

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseGrid(DataSource.topics, Modifier)
                }
            }
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(id = topic.imageResourseId),
                contentDescription = stringResource(id = topic.stringResourceId),
                modifier = Modifier
                    .width(68.dp)
                    .aspectRatio(1f)
            )

            Column() {
                Text(
                    text = stringResource(id = topic.stringResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.padding(start = 16.dp)) {
                    Icon(painter = painterResource(id = R.drawable.ic_grain), contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = topic.courseId.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                    )
                }
            }

        }
    }
}

@Composable
fun CourseGrid(topicList: List<Topic>, modifier: Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topicList) {
            topic -> TopicCard(topic = topic, modifier = modifier)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CoursePreview() {
    CourseGrid(DataSource.topics, Modifier)
}