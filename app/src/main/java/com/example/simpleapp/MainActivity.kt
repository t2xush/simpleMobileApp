package com.example.simpleapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simpleapp.ui.theme.SimpleappTheme
import com.example.simpleapp.viewmodel.ExcuseViewModel
import com.example.simpleapp.Excuse



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldAApp()
                }
            }
        }
    }
}

@Composable
fun ScaffoldAApp(){
   val navController= rememberNavController()
    NavHost(
        navController=navController,
        startDestination="Home"
    ){
       composable(route="Home"){
           MainScreen(navController )
       }
        composable(route="Info"){
            InfoScreen(navController )
        }
        composable(route="Settings"){
            SettingScreen(navController )
        }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title:String,navController:NavController){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title) },

        actions = {
            IconButton(
                onClick = {
                expanded=!expanded
                /*TODO*/ }
            )  {
                Icon(Icons.Filled.MoreVert , contentDescription =null )

            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded=false }) {
                DropdownMenuItem({ Text("Info") },
                    onClick = { navController.navigate("Info")/*TODO*/ })
                DropdownMenuItem({ Text("Settings") },
                    onClick = {navController.navigate("Settings") /*TODO*/ })
            }
        }


    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title:String,navController: NavController){
    TopAppBar(
        title = { Text(title) /*TODO*/ },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() },
            ) {
                Icon(Icons.Filled.ArrowBack , contentDescription = null)
            }
        },
    )
}




@Composable
fun MainScreen(navController:NavController){
    Scaffold (
        topBar={MainTopBar("My APP",navController)},
        content = {
            Text(text = "Dice roller game",
                modifier = Modifier.padding(it).size(200.dp))
            DiceRollerApp()


        }
    )
}






@Composable
fun InfoScreen(navController:NavController){
    Scaffold (
        topBar={ScreenTopBar("My APP",navController)},
        content = {

            Text(text = "content for info screen",
                modifier = Modifier.padding(it))
        }
    )

}



@Composable
fun SettingScreen(navController:NavController){
    Scaffold(
        topBar={ScreenTopBar("Settings",navController)},
        content = {
            Text(text = "content for setting screen",
                modifier = Modifier.padding(it))
        }
    )
}


@Preview
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage()
}

@Composable
fun DiceWithButtonAndImage(
    modifier: Modifier= Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
)
{
    var result by remember {
        mutableStateOf(1)
    }
    val imageResource= when(result){
        1->R.drawable.dice_1
        2->R.drawable.dice_2
        3->R.drawable.dice_3
        4->R.drawable.dice_4
        5->R.drawable.dice_5
        else->R.drawable.dice_6
    }
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = imageResource),
            contentDescription =result.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result=(1..6).random()}) {
            Text(stringResource(R.string.roll))
        }
    }
}


@Composable
fun ExcuseScreen(excuseViewModel: ExcuseViewModel= viewModel()){
    ExcuseList(excuseViewModel.excuses)
}


@Composable
fun ExcuseList(excuses:List<Excuse>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(excuses) { excuse ->
            Text(
                text = excuse.excuse,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
            Divider(color = Color.LightGray, thickness = 1.dp)

        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleappTheme {
        ScaffoldAApp()
    }
}