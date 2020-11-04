package com.example.composetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.frames.Frame
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{

            Login()

        }

    }
    @Preview
    @Composable
    private fun Login(){
        var idState by remember { mutableStateOf(TextFieldValue())}
        var passwordState by remember { mutableStateOf(TextFieldValue())}

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(MaterialTheme.colors.background).fillMaxHeight()
        ){
            AppBar("hi")
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ){
                Spacer(Modifier.size(16.dp))
                LoginIdText(
                    textFieldValue = idState,
                    onValueChange = { if (!it.text.contains("\n")) idState = it })
                Spacer(Modifier.size(16.dp))
                LoginPasswordText(
                    passwordState,
                    onValueChange = { if (!it.text.contains("\n")) passwordState = it })
                Spacer(Modifier.size(16.dp))
                SubmitButton(idState, passwordState)
            }
        }
    }
    @Composable
    private fun AppBar(str: String){
        Column{
            TopAppBar(

                title = { Row { Text(str) } },
                navigationIcon = {
                    Box{
                        Image(
                            asset = vectorResource(id = R.drawable.ic_launcher_background),
                            modifier = Modifier.padding(16.dp),
                        )
                        Image(
                            asset = vectorResource(id = R.drawable.ic_launcher_foreground),
                            modifier = Modifier.padding(16.dp),
                        )

                    }
                }
            )
            Divider()
        }
    }


    @Composable
    fun LoginIdText(
        textFieldValue: TextFieldValue,
        onValueChange: (TextFieldValue)->Unit
    ){

        TextField(
            value = textFieldValue,
            onValueChange = { onValueChange(it) },
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            modifier = Modifier.width(300.dp),
            onTextInputStarted = {},
            label = { Text("ID") }
        )
    }

    @Composable
    fun LoginPasswordText(
        textFieldValue: TextFieldValue,
        onValueChange: (TextFieldValue) -> Unit
    ) {

        TextField(
            value = textFieldValue,
            onValueChange = {onValueChange(it)},
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            imeAction = ImeAction.Done,
            modifier = Modifier.width(300.dp),
            onTextInputStarted = {},
            label = {Text("Password")}
        )

    }
    @Composable
    fun SubmitButton(idState: TextFieldValue, passwordState: TextFieldValue){
        Button(
            onClick = {
                Toast.makeText(this, "id: ${idState.text}, password: ${passwordState.text}", Toast.LENGTH_LONG).show()
            },
            content = { Text("submit") },
            modifier = Modifier.width(300.dp)
        )
    }

}