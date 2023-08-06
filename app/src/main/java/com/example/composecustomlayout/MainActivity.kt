package com.example.composecustomlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecustomlayout.ui.theme.ComposeCustomLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCustomLayoutTheme {

            }
        }
    }
}

@Composable
fun MainScreen() {
    Box(modifier = Modifier.size(120.dp, 80.dp)) {
        ColorBox(modifier = Modifier
            .customOffset(90, 50)
            .background(Color.Blue))
    }
}

@Composable
fun ColorBox(modifier: Modifier) {
    Box(
        Modifier
            .padding(1.dp)
            .size(width = 50.dp, height = 10.dp)
            .then(modifier))
}

// 모디파이어를 호출 했을 때, 새로운 좌표 오프셋을 전달하도록 구현
fun Modifier.customOffset (
    x: Int,
    y: Int
) = layout { measurable, constraints ->
    // 측정 가능한 자식을 측정
    // 측정 가능한 자식은 측정 제약 조건(constraints)을 사용하여 측정되고, 측정된 크기를 반환
    val placeable:Placeable = measurable.measure(constraints)

    layout(placeable.width, placeable.height) {
        // 측정 가능한 자식을 지정된 좌표로 배치
        placeable.placeRelative(x, y)
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}