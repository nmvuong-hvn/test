import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appdrivinglience.feature.home_screen.HomeViewModel
import com.example.appdrivinglience.navigaion.Graph
import com.example.appdrivinglience.navigaion.graphs.MainScreen
import com.example.appdrivinglience.navigaion.graphs.onBoardingNavGraph

@SuppressLint("SuspiciousIndentation")
@Composable
fun RootNavGraph(homeViewModel: HomeViewModel = hiltViewModel(), onChangeTheme : (Boolean)-> Unit) {
    val onBoardingController = rememberNavController()
       NavHost(navController = onBoardingController,
           startDestination = Graph.OnBoardingScreen, route = Graph.RootGraph) {

           onBoardingNavGraph(onBoardingController)
           composable(route= Graph.MainScreenGraph){
               MainScreen(homeViewModel){
                   onChangeTheme(it)
               }
           }
       }
}

