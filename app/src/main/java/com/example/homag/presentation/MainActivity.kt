package com.example.homag.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homag.App
import com.example.homag.R
import com.example.homag.presentation.components.*
import com.example.homag.presentation.ui.theme.*
import com.example.homag.utils.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.ViewModelFactory(
            (applicationContext as App).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MApp(viewModel)
        }
    }
}

@Composable
private fun MApp(viewModel: MainViewModel) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    HomagTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()

            systemUiController.setStatusBarColor(
                color = MaterialTheme.colors.primary
            )

            NavHost(navController = navController, startDestination = MAIN) {
                composable(route = MAIN) { Logon(navController, screenWidth) }
                composable(route = "tabs/{tab}") {
                    TabsChoice(
                        viewModel,
                        it.arguments?.getString("tab"),
                        navController
                    )
                }
            }
        }
    }
}


@Composable
private fun Logon(navController: NavHostController, screenWidth: Dp) {
    Scaffold(
        topBar = {
            TopAppBar(elevation = 1.dp, contentColor = secondary) {
                TopBar(navController)
            }
        })
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Title()
                Margin(size = 20)
                Text(
                    stringResource(R.string.order_parts),
                    style = Typography.subtitle1
                )
                Margin(size = 96)
                Text(
                    stringResource(R.string.if_you_resgistred),
                    style = Typography.subtitle2
                )
                Margin(size = 26)
                Button(
                    onClick = { navController.navigate("tabs/auth") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = secondary,
                        contentColor = Color.White
                    ),
                ) {
                    Text(stringResource(R.string.sign_in), color = Color.White, modifier = Modifier.padding(vertical = 3.dp))
                }
                Margin(size = 26)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(width = screenWidth / 4, height = 1.dp)
                            .background(semiWhiteTextColor)
                    )
                    Margin(size = 12)
                    Text(stringResource(R.string.or), style = Typography.subtitle2)
                    Margin(size = 12)
                    Box(
                        modifier = Modifier
                            .size(width = screenWidth / 4, height = 1.dp)
                            .background(semiWhiteTextColor)
                    )
                }
                Margin(size = 26)
                Button(
                    onClick = { navController.navigate("tabs/reg") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = secondary
                    ),
                ) {
                    Text(stringResource(R.string.registration), modifier = Modifier.padding(vertical = 3.dp))
                }
            }
        }
    }
}

@Composable
private fun Registration(viewModel: MainViewModel) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Column(Modifier.padding(horizontal = 24.dp, vertical = 30.dp)) {
            viewModel.regName.value =
                editText(title = stringResource(R.string.fio), label = stringResource(R.string.name_surname))
            GenderRadioButton(viewModel)
            viewModel.regBirthday.value =
                editText(
                    title = stringResource(R.string.birthday),
                    label = stringResource(R.string.dd_mm_yyyy),
                    options = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = MaskTransformation(BIRTHDAY_MASK),
                    length = 8
                )
            viewModel.regMobile.value = editText(
                title = stringResource(R.string.phone),
                label = stringResource(R.string.placeholder_phone),
                options = KeyboardOptions(keyboardType = KeyboardType.Phone),
                visualTransformation = MaskTransformation(PHONE_MASK),
                length = 10
            )
            viewModel.regEmail.value = editText(
                label = stringResource(R.string.email),
                options = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            viewModel.regWorkCompany.value =
                editText(label = stringResource(R.string.your_company), title = stringResource(R.string.name_company))
            viewModel.regUfServiceNumber.value =
                editText(
                    label = stringResource(R.string.placeholder_service_number),
                    title = stringResource(R.string.service_number),
                    options = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            viewModel.regLogin.value = editText(label = stringResource(R.string.login))
            viewModel.regPassword.value = editText(label = stringResource(R.string.password))
            viewModel.regConfirmPassword.value =
                editText(label = stringResource(R.string.password), title = stringResource(R.string.confirm_password))
            var isAgree by rememberSaveable { mutableStateOf(false) }
            Row {
                RadioButton(
                    selected = isAgree,
                    onClick = { isAgree = !isAgree },
                    colors = RadioButtonDefaults.colors(unselectedColor = textFieldBackgroundColor),
                    modifier = Modifier
                        .background(
                            textFieldBackgroundColor, shape = CircleShape
                        )
                )
                Margin(12)
                Text(stringResource(R.string.agree_text))
            }
            Margin(20)
            Button(
                enabled = isAgree,
                onClick = { viewModel.reg() },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = secondary,
                    contentColor = Color.White,
                    disabledBackgroundColor = disabledSecondary
                ),
            ) {
                Text(
                    stringResource(R.string.sign_up),
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }
        }
    }
}

@Composable
private fun GenderRadioButton(viewModel: MainViewModel) {
    val gender = listOf(stringResource(R.string.male), stringResource(R.string.female))
    val selectedTag = rememberSaveable { mutableStateOf<String?>(null) }
    Text(stringResource(R.string.gender), style = Typography.h3)
    Margin(12)
    Row {
        gender.forEach { text ->
            RadioButton(
                selected = text == selectedTag.value,
                onClick = {
                    selectedTag.value = text
                    viewModel.regGender.value = text
                },
                colors = RadioButtonDefaults.colors(unselectedColor = textFieldBackgroundColor),
                modifier = Modifier
                    .background(
                        textFieldBackgroundColor, shape = CircleShape
                    )
            )
            Margin(8)
            Text(text = text, style = Typography.h4)
            Margin(16)
        }
    }
    Margin(20)
}

@Composable
private fun Authentication(viewModel: MainViewModel) {
    Card(backgroundColor = Color.White, modifier = Modifier.padding(horizontal = 16.dp)) {
        Column(
            Modifier.padding(horizontal = 24.dp, vertical = 30.dp)
        ) {
            viewModel.authLogin.value = editText(label =stringResource(R.string.login) )
            viewModel.authPassword.value = editText(label = stringResource(R.string.password), isPassword = true)
            Button(
                onClick = { viewModel.auth() },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = secondary,
                    contentColor = Color.White
                ),
            ) {
                Text(stringResource(R.string.log_in), color = Color.White, modifier = Modifier.padding(vertical = 3.dp))
            }
        }
    }
}

@Composable
private fun TabsChoice(
    viewModel: MainViewModel,
    tab: String?,
    navController: NavHostController
) {
    val showDialogState: Boolean by viewModel.showDialog.collectAsState()

    var selectedIndex by rememberSaveable {
        mutableStateOf(
            when (tab) {
                AUTH -> 0
                REG -> 1
                else -> 0
            }
        )
    }

    val tabTitles = listOf(stringResource(R.string.log_in), stringResource(R.string.registration))
    Scaffold(
        topBar = {
            TopAppBar(elevation = 1.dp, contentColor = secondary) {
                TopBar(navController)
            }
        })
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AlertDialog(
                viewModel = viewModel,
                show = showDialogState,
                onConfirm = viewModel::onDialogConfirm,
                message = viewModel.message.value,
                navController = navController
            )
            Title()
            Margin(64)
            ScrollableTabRow(
                selectedTabIndex = selectedIndex,
                indicator = {},
                edgePadding = 0.dp,
                divider = {}
            ) {
                tabTitles.forEachIndexed { index, text ->
                    val selected = selectedIndex == index
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Tab(
                            selected = selected,
                            onClick = { selectedIndex = index },
                            text = {
                                Text(
                                    text = text,
                                    style = Typography.body2,
                                    color = if (selected) Color.Black else semiWhiteTextColor
                                )
                            },
                        )
                        if (index == 0) {
                            Margin(4)
                            Box(
                                modifier = Modifier
                                    .height(16.dp)
                                    .width(1.dp)
                                    .background(semiWhiteTextColor)
                            )
                        }
                    }
                }
            }
            Margin(36)
            when (selectedIndex) {
                0 -> Authentication(viewModel)
                1 -> Registration(viewModel)
            }
        }
    }
}



