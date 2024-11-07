package com.example.learningclickbehavior

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningclickbehavior.ui.theme.LearningClickBehaviorTheme
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningClickBehaviorTheme {
                Scaffold (modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Lemonade",
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }) {innerPadding->
                    LemonadeImageWithButton(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LemonadeImageWithButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var currentScreen by remember { mutableIntStateOf(1) }
    var lemonadeSqueez by remember { mutableIntStateOf(0) }
    var howManyTimesSqueez=1

    val img=when(currentScreen){
        1->{
            painterResource(R.drawable.lemon_tree)
        }2->{
            painterResource(R.drawable.lemon_squeeze)
        }3->{
            painterResource(R.drawable.lemon_drink)
        }else->{
            painterResource(R.drawable.lemon_restart)
        }
    }
    val txt=when(currentScreen){
        1->{
            stringResource(R.string.d_s1)
        }2->{
            stringResource(R.string.d_s2)
        }3->{
            stringResource(R.string.d_s3)
        }else->{
            stringResource(R.string.d_s4)
        }
    }

    val cd=when(currentScreen){
        1->{
            stringResource(R.string.cd_s1)
        }2->{
            stringResource(R.string.cd_s2)
        }3->{
            stringResource(R.string.cd_s3)
        }else->{
            stringResource(R.string.cd_s4)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = img,
            contentDescription = cd,
            modifier = Modifier
                .width(220.dp)
                .aspectRatio(1F)
                .clip(shape = RoundedCornerShape(100.0f))
                .background(Color(0xFFC3ECD2))
                .padding(8.dp)
                .clickable {
                    if (currentScreen == 1) {
                        lemonadeSqueez = (2..4).random()
                        howManyTimesSqueez = 0
                        currentScreen++
                        Log.d("ahmad", "LemonadeImageWithButton1: $howManyTimesSqueez, $lemonadeSqueez")
                    } else if (currentScreen == 2) {
                        if (lemonadeSqueez == howManyTimesSqueez) {
                            currentScreen++
                        } else {
                            howManyTimesSqueez++
                        }
                        Log.d("ahmad", "LemonadeImageWithButton2: $howManyTimesSqueez/$lemonadeSqueez")

                    } else if (currentScreen == 4) {
                        currentScreen = 1
                    } else {
                        currentScreen++
                    }
                }
        )
        Text(
            text = txt,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(18.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Lemonade() {
    LearningClickBehaviorTheme {
        LemonadeImageWithButton()
    }
}