package com.example.learningdiary1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningdiary1.ui.theme.LearningDiary1Theme
import org.jetbrains.annotations.NotNull

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningDiary1Theme {
                // A surface container using the 'background' color from the theme
                Surface(

                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}
var array = Array(4) { i -> (0..9).random()}
var compareInput= arrayOf<String>()
var compareRandom = arrayOf<String>()
var numberGuessed = 0
var orderGuessed = 0
var saved = ""
var win = false
var saved2 = ""
var saved3 = ""

@Composable
fun CreateBizCard() {
    var isVisible = false
    var count = 0
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    val input = remember { mutableStateOf("0000")}


    generateRandomNumber()

    var stringArray=""

    for(i in 0..3){
        stringArray += array[i].toString();
    }

    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 4.dp) {
            Column(modifier = Modifier
                .height(300.dp)
                .padding(14.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {



                CreateInfo()

                TextField(value = input.value , onValueChange = {
                    input.value=it
                }, modifier = Modifier.padding(4.dp))

                Text(text = stringArray , modifier = Modifier.padding(3.dp), textAlign = TextAlign.Center )

                Button(
                    onClick = {
                        numberGuessed = 0
                        orderGuessed = 0
                        compareInput = input.value.toCharArray().map { it.toString() }.toTypedArray()
                        compareRandom = array.map { it.toString() }.toTypedArray()
                        for ( i in 0..3){
                            if (compareInput[i] == compareRandom[i]){
                                orderGuessed++
                            }
                            for (j in 0..3){
                                if(compareInput[i]!=saved && compareInput[i]!= saved2 && compareInput[i]!= saved3 ) {
                                    if (compareInput[i] == compareRandom[j]) {
                                        numberGuessed++
                                        if(saved=="") {
                                            saved = compareInput[i]
                                        }
                                        else if(saved2==""){
                                            saved2 = compareInput [i]
                                        }
                                        else{
                                            saved3 = compareInput [i]
                                        }
                                    }
                                }
                            }

                        }
                        count++
                        if (numberGuessed == 4 && orderGuessed == 4) {
                            win = true

                        }
                        saved = ""
                        saved2 = ""
                        saved3 = ""
                    }
                ) {
                    Text(" See Result",
                        style = MaterialTheme.typography.button)

                }
                isVisible = count >= 0
                if (count<1){
                    isVisible = false
                }
                else {
                    isVisible = true
                }
                if( isVisible){
                    Text(text = numberGuessed.toString() +":"+ orderGuessed.toString(), Modifier.padding(4.dp))
                }
                if (win){
                    Text(text = "Congratulations, You won!!!", Modifier.padding(7.dp), color = Color.Green, style = MaterialTheme.typography.h5 )
                    Text(text = "Be so kind to restart the app :)", Modifier.padding(4.dp), color = Color.Green, style = MaterialTheme.typography.h5)
                }

            }
        }
    }
}
@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Number Guessing Game", style = MaterialTheme.typography.h5, color = MaterialTheme.colors.error)
        Row(modifier =  Modifier.padding(7.dp)){
            Text(text = "Enter the 4-digit number below and then press 'Guess' Button: " , modifier = Modifier.padding(3.dp), textAlign = TextAlign.Center )

        }
    }
}
@Composable
fun generateRandomNumber(){
    array = Array(4) { i -> (0..9).random()}
    while (array[0] == array[1] || array[1] == array[2] || array[1] == array[3]){
        array[1] = (0..9).random()
    }
    while (array[2] == array[0] || array[2] == array[3] || array[2] == array[1]){
        array[2] = (0..9).random()
    }
    while (array[3] == array[0] || array[3] == array[2] || array[3] == array[1]){
        array[3] = (0..9).random()
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearningDiary1Theme {
        CreateBizCard()
    }
}