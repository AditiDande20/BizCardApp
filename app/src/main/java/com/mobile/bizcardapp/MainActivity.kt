package com.mobile.bizcardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.bizcardapp.ui.theme.BizCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val buttonClicked = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()){
        Card(modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .padding(15.dp),
             elevation = 10.dp,
             shape = RoundedCornerShape(corner = CornerSize(20.dp))) {

            Column(modifier = Modifier.height(400.dp),
                   verticalArrangement = Arrangement.Top,
                   horizontalAlignment = Alignment.CenterHorizontally) {
                CreateImageProfile()
                Divider(thickness = 1.dp,
                        modifier = Modifier.padding(10.dp),
                        color = Color.LightGray)

                CreateInfo()

                Button(onClick = {
                                 buttonClicked.value = !buttonClicked.value
                                 },
                       modifier = Modifier.padding(5.dp)) {
                    Text(text = "PORTFOLIO",style = MaterialTheme.typography.button)
                }

                if(buttonClicked.value){
                    Content()
                }
                else{
                    Box{}
                }
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Maria Steel",
            modifier = Modifier
                .padding(5.dp)
                .height(50.dp),
            color = MaterialTheme.colors.primaryVariant,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )

        Text(
            text = "Android Compose Programmer",
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = "mariaSteel@gmail.com",
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.subtitle1
        )

    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(100.dp)
            .padding(5.dp),
        shape = CircleShape,
        elevation = 10.dp,
        //color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_account),
            contentDescription = "Profile Image",
            modifier = modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )

    }
}

@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            PortFolio(data = listOf("Project 1","Project 2","Project 3","Project 4","Project 5","Project 6"))
        }
    }
}

@Composable
fun PortFolio(data: List<String>) {
    LazyColumn{
        items(data){item ->
            Card(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
                 shape = RectangleShape,
                 elevation = 3.dp) {

                Row(modifier = Modifier
                    .padding(1.dp)
                    .background(MaterialTheme.colors.surface)) {
                    CreateImageProfile(modifier = Modifier.size(70.dp))

                    Column(modifier = Modifier.padding(3.dp).align(alignment = CenterVertically)) {
                        Text(text = item,fontWeight = FontWeight.Bold)
                        Text(text = "Great Projects take time",style = MaterialTheme.typography.body2)

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardAppTheme {
        CreateBizCard()
    }
}