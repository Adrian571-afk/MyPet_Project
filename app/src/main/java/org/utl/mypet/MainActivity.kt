package org.utl.mypet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.utl.mypet.navigation.HomeView
import org.utl.mypet.ui.theme.MyPetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPetTheme {
                HomeView()
            }
        }
    }
}
