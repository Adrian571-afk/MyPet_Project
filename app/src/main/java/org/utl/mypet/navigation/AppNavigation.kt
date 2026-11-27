package org.utl.mypet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.utl.mypet.data.UsuarioViewModel
import org.utl.mypet.screens.RegistroView


@Composable
fun AppNavigation(usuarioViewModel: UsuarioViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login"){
            LoginView(
                navController = navController,
                viewModel = usuarioViewModel,
                onLoginSuccess = {
                    navController.navigate("home"){
                        popUpTo("login"){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable("home"){
            HomeView()
        }

        composable("Registro"){
            RegistroView(
                navController = navController,
                viewModel = usuarioViewModel
            )
        }
    }
}