package com.example.appdrivinglience.navigaion.graphs

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.appdrivinglience.R
import com.example.appdrivinglience.feature.type_driving_lience.TypeDrivingLicenseScreen
import com.example.appdrivinglience.feature.home_screen.HomeScreen
import com.example.appdrivinglience.feature.home_screen.HomeViewModel
import com.example.appdrivinglience.feature.learn_wrong.LearnWrongViewModel
import com.example.appdrivinglience.feature.model.StudyTheoryModel
import com.example.appdrivinglience.feature.model.TypeDriving
import com.example.appdrivinglience.feature.notification_screen.CreateNotificationScreen
import com.example.appdrivinglience.feature.notification_screen.NotificationScreen
import com.example.appdrivinglience.feature.setting.SettingScreen
import com.example.appdrivinglience.feature.study_theory_screen.ExaminationScreen
import com.example.appdrivinglience.feature.study_theory_screen.StudyTheoryScreen
import com.example.appdrivinglience.feature.trick_screen.TrickViewScreen
import com.example.appdrivinglience.navigaion.BottomBarScreen
import com.example.appdrivinglience.navigaion.Graph
import com.example.appdrivinglience.navigaion.Graph.TypeLicenseGraph
import com.example.appdrivinglience.navigaion.LEARN
import com.example.appdrivinglience.navigaion.LearnTrickScreen
import com.example.appdrivinglience.navigaion.LearnWrongScreen
import com.example.appdrivinglience.navigaion.MainScreen
import com.example.appdrivinglience.navigaion.NotificationScreen
import com.example.appdrivinglience.navigaion.TypeLicenseScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    homeViewModel: HomeViewModel,
    navController: NavHostController = rememberNavController(),
    onChangedTheme: (Boolean) -> Unit,
) {
    val multiplePermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberMultiplePermissionsState(permissions = listOf(
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.SCHEDULE_EXACT_ALARM,
            Manifest.permission.USE_EXACT_ALARM,

            ))
    } else {
//        rememberMultiplePermissionsState(permissions = listOf(
//            Manifest.permission.INTERNET))
        TODO()
    }
    var screenCurrentState by remember {
        mutableStateOf(BottomBarScreen.Home.title)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if(event == Lifecycle.Event.ON_START) {
                    multiplePermission.launchMultiplePermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )


//    val navController = rememberNavController()
    val screensList = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Theory,
        BottomBarScreen.TestMock,
        BottomBarScreen.Signature,
        BottomBarScreen.Settings
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            if (screenCurrentState != BottomBarScreen.Home.title) {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(id = R.color.green_primary),
                        titleContentColor = colorResource(id = R.color.white)
                    ),
                    title = {
                        Text(text = screenCurrentState)
                    },
                    navigationIcon = {
                        if (screenCurrentState == TypeLicenseScreen.TypeScreen.title ||
                            screenCurrentState == BottomBarScreen.Theory.title ||
                            screenCurrentState == LearnTrickScreen.LearnTrick.title ||
                            screenCurrentState == NotificationScreen.CreateNotification.title ||
                            screenCurrentState == NotificationScreen.ListNotification.title
                        ) {

                            IconButton(onClick = {
                                val destination = navController.previousBackStackEntry?.destination?.route
                                if (destination == BottomBarScreen.Home.route) {
                                    screenCurrentState = BottomBarScreen.Home.title
                                }else {
                                    val preScreen = screensList.singleOrNull { it.route == destination }
                                    preScreen?.let {
                                        screenCurrentState = it.title
                                    }?: run {
                                        val preScreen = NotificationScreen.CreateNotification.route
                                        if (destination == preScreen){
                                            screenCurrentState = NotificationScreen.CreateNotification.title
                                        }
                                    }
                                }
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (screensList.any { it.title == screenCurrentState }) {
                BottomBar(screensList = screensList, navController = navController) {
                    screenCurrentState = it.title;
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            route = Graph.MainScreenGraph,
            startDestination = MainScreen.HomeScreen.route
        ) {

            composable(MainScreen.HomeScreen.route) {
                HomeScreen(
                    homeViewModel = homeViewModel,
                    modifier = Modifier.fillMaxWidth(),
                    selectedLicenseType = {
                        screenCurrentState = TypeLicenseScreen.TypeScreen.title
                        navController.navigate(TypeLicenseScreen.TypeScreen.route)
                    }
                ) {
                    when (it) {
                        LEARN.THEORY -> {
                            screenCurrentState = BottomBarScreen.Theory.title
                            navController.navigate(MainScreen.TheoryScreen.route)
                        }

                        LEARN.EXAM -> {

                        }

                        LEARN.WRONG -> {
                            screenCurrentState = LearnWrongScreen.LearnWrong.title
                            navController.navigate(LearnWrongScreen.LearnWrong.route)
                        }

                        LEARN.LEARN_SIGN -> {
                        }

                        LEARN.TRICK -> {
                            screenCurrentState = LearnTrickScreen.LearnTrick.title
                            navController.navigate(LearnTrickScreen.LearnTrick.route)
                        }
                    }
                }
            }
            composable(MainScreen.TheoryScreen.route) {
                val listTheory = listOf(
                    StudyTheoryModel(
                        nameTheory = "Tất cả câu lý thuyết",
                        iconRes = R.drawable.book,
                        colorTheory = R.color.second_2
                    ),
                    StudyTheoryModel(
                        nameTheory = "Câu hỏi điểm liệt",
                        iconRes = R.drawable.danger,
                        colorTheory = R.color.second_4
                    ),
                    StudyTheoryModel(
                        nameTheory = "Khái niệm và quy tắc",
                        iconRes = R.drawable.note,
                        colorTheory = R.color.second_3
                    ),
                    StudyTheoryModel(
                        nameTheory = "Văn hóa và đạo đức lái xe",
                        iconRes = R.drawable.car,
                        colorTheory = R.color.second_4
                    ),
                    StudyTheoryModel(
                        nameTheory = "Kỹ thuật lái xe",
                        iconRes = R.drawable.group,
                        colorTheory = R.color.second_1
                    ),
                    StudyTheoryModel(
                        nameTheory = "Cấu tạo và sửa chữa",
                        iconRes = R.drawable.edit,
                        colorTheory = R.color.second_2
                    ),
                    StudyTheoryModel(
                        nameTheory = "Biển báo đường bộ",
                        iconRes = R.drawable.traffic,
                        colorTheory = R.color.second_3
                    ),
                    StudyTheoryModel(
                        nameTheory = "Sa hình",
                        iconRes = R.drawable.picture_frame,
                        colorTheory = R.color.second_4
                    ),
                )

                StudyTheoryScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                    listStudyTheoryModel = listTheory
                )
            }
            composable(MainScreen.TestMockScreen.route) {

            }
            composable(MainScreen.SignScreen.route) {

            }
            composable(MainScreen.SettingScreen.route) {
                SettingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                    onNotificationNext = {
                        screenCurrentState = NotificationScreen.CreateNotification.title
                        navController.navigate(NotificationScreen.CreateNotification.route)
                    }
                ) {
                    onChangedTheme(it)
                }
            }
            composable(LearnTrickScreen.LearnTrick.route) {
                TrickViewScreen(modifier = Modifier.fillMaxSize())
            }
            composable(LearnWrongScreen.LearnWrong.route) {
                val learnWrongViewModel = hiltViewModel<LearnWrongViewModel>()
                ExaminationScreen(
                    listQuestionModel = listOf(),
                    modifier = Modifier.fillMaxSize(),
                    viewModel = learnWrongViewModel
                )
            }
            composable(NotificationScreen.CreateNotification.route) {
                CreateNotificationScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(top = 90.dp)
                        .background(color = MaterialTheme.colorScheme.background)
                ){
                    screenCurrentState = NotificationScreen.ListNotification.title
                    navController.navigate(NotificationScreen.ListNotification.route)
                }
            }
            composable(NotificationScreen.ListNotification.route){
                NotificationScreen(modifier = Modifier.fillMaxSize())
            }
//            navigation(
//                startDestination = NotificationScreen.CreateNotification.route,
//                route = NotificationGraph
//            ) {
//
//            }

            navigation(
                startDestination = TypeLicenseScreen.TypeScreen.route,
                route = TypeLicenseGraph
            ) {
                composable(TypeLicenseScreen.TypeScreen.route) {
                    TypeDrivingLicenseScreen(
                        listTypeDrivingLicense = listOf(
                            TypeDriving(
                                "1",
                                "A1",
                                "Xe mô tô 2 bánh có dung tích xi lanh dưới 175 cm3"
                            ),
                            TypeDriving(
                                "2",
                                "A2",
                                "Xe mô tô 2 bánh có dung tích xi lanh dưới 175 cm3"
                            ),
                            TypeDriving("3", "A3", "Xe mô tô 3 bánh, xe 3 gác, xe lam, xích lô..."),
                            TypeDriving("4", "A4", "Xe máy léo nhỏ có trọng tải đến 1000kg"),
                            TypeDriving(
                                "5",
                                "B1",
                                "Ô tô chở người đến 9 chỗ ngồi, Ô tô tải trọng dưới 3,5..."
                            ),
                            TypeDriving("6", "B2", "Tương tự B1, dành cho người hành nghề lái xe"),
                        )
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    screensList: List<BottomBarScreen>,
    navController: NavHostController,
    onNavigate: (BottomBarScreen) -> Unit
) {

    NavigationBar {
        screensList.forEach {
            AddItem(
                screensList,
                screen = it,
                navController = navController
            ) {
                onNavigate(it)
            }
        }

    }

}

@Composable
fun RowScope.AddItem(
    screensList: List<BottomBarScreen>,
    screen: BottomBarScreen,
    navController: NavHostController,
    onNavigate: (BottomBarScreen) -> Unit
) {
    val route = navController.currentBackStackEntry?.destination?.route
    NavigationBarItem(
        selected = screensList.any { it.route == route },
        label = { Text(text = screen.title) },
        onClick = {
            onNavigate(screen)
            navController.navigate(screen.route)
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = null
            )
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBar(title: String) {

    val currentDestination =
        TopAppBar(title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
}