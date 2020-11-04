package com.example.composetest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            MaterialTheme {
                Login()
            }

        }

    }
    private val isIdError = MutableLiveData<Boolean>()
    private val isPwError = MutableLiveData<Boolean>()

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

        val errorState = isIdError.observeAsState()
        TextField(
            value = textFieldValue,
            isErrorValue = errorState.value ?: false,
            onValueChange = { onValueChange(it) },
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            modifier = Modifier.width(300.dp),
            onTextInputStarted = {},
            label = { Text("ID") },
            leadingIcon = {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_baseline_person_24)
                )
            }
        )
    }

    @Composable
    fun LoginPasswordText(
        textFieldValue: TextFieldValue,
        onValueChange: (TextFieldValue) -> Unit
    ) {
        var visibleState by remember{mutableStateOf(false)}
        val errorState = isPwError.observeAsState()
        TextField(
            value = textFieldValue,
            isErrorValue = errorState.value?:false,
            onValueChange = { onValueChange(it) },
            keyboardType = KeyboardType.Password,
            visualTransformation = if(visibleState) VisualTransformation.None else PasswordVisualTransformation(),
            imeAction = ImeAction.Done,
            modifier = Modifier.width(300.dp),
            onTextInputStarted = {},
            label = { Text("Password") },
            leadingIcon = {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_baseline_lock_24)
                )
            },
            trailingIcon = {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                    modifier = Modifier.clickable(onClick = {
                        visibleState = !visibleState
                    })
                )
            }
        )

    }
    @Composable
    fun SubmitButton(idState: TextFieldValue, passwordState: TextFieldValue){
        Button(
            onClick = {
                isIdError.value = idState.text.isEmpty()
                isPwError.value = passwordState.text.isEmpty()
                Toast.makeText(this, "id: ${idState.text}, password: ${passwordState.text}", Toast.LENGTH_LONG).show()
            },
            content = { Text("Confirm") },
            modifier = Modifier.width(300.dp)
        )
    }

}