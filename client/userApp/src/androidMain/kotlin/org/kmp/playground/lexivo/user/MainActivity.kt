package org.kmp.playground.lexivo.user

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import org.kmp.playground.lexivo.user.feature.core.CoreApp
import org.kmp.playground.lexivo.user.feature.root.UserAppRoot
import org.kmp.playground.lexivo.user.feature.root.UserAppRootImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = UserAppRootImpl(componentContext = defaultComponentContext())
        setContent {
            setContent {
                CoreApp(root = root)
            }
        }
    }
}
