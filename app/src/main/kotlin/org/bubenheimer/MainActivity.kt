package org.bubenheimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { Screen() }
    }
}

@Composable
fun Screen() {
    var counter by remember { mutableStateOf(0) }

    Bar(Foo::asStringFun)
    Bar(Foo::asStringVal)

    LaunchedEffect(counter) {
        println("Recomposition $counter")
        if (counter < 10) ++counter
    }
}

@Composable
fun Bar(stringProvider: () -> String) {
    SideEffect { println("Recomposing: ${stringProvider()}") }
}

@Immutable
object Foo

fun Foo.asStringFun(): String = "$this foo fun"

@Stable
@get:Stable
val Foo.asStringVal: String
    get() = "$this foo val"
