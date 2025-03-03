package com.example.kotlinplayground

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDateTime(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm:ss")
    return current.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MobileTopup(navController: NavController,modifier: Modifier = Modifier , viewModel: TodoViewModel) {
    val phoneNumberUtil = MyanmarPhoneNumberUtil()
    val operatorName = remember { mutableStateOf("")}
    val phoneNumber = remember { mutableStateOf(TextFieldValue())}
    val formattedDateTime = getCurrentDateTime()
    val topupOptions = listOf(
        "phoneBill1" to 1000,
        "phoneBill2" to 2000,
        "phoneBill3" to 3000,
        "phoneBill4" to 5000,
        "phoneBill5" to 10000,
        "phoneBill6" to 20000
    )
    val dataOptions = listOf(
        "1212 MB" to 999,
        "2024 MB" to 1999,
        "3072 MB" to 2999,
        "5120 MB" to 3999,
        "10240 MB" to 5999
    )

    val initAmount = 15000
    val selectedTopupOption = remember { mutableStateOf<Pair<String, Int>?>(null) }
    val selectedDataOption = remember { mutableStateOf<Pair<String, Int>?>(null) }
    val showDialog = remember { mutableStateOf(false) }
    var alertMessage = remember { mutableStateOf("")}

//    var selectedAmount by remember { mutableStateOf(0) }
//    var totalBalance =  initAmount - selectedAmount


    Column (modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text( text = "Mobile Topup",fontSize = 24.sp, color = Color.Black,
            fontWeight = FontWeight.Bold)
        Text("Total Amount: ${initAmount} MMK", fontSize = 12.sp, color = Color.Black)
        TextField(
            modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp),
            value = phoneNumber.value,
            onValueChange = {
                phoneNumber.value = it
                if (it.text.isNotEmpty()) {
                    alertMessage.value = ""
                    operatorName.value = phoneNumberUtil.getTelecomName(it.text)
                } else {
                    operatorName.value = ""

                }
            },
            label = {Text("Enter Phone Number")},
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text( text = "Bill",fontSize = 18.sp, color = Color.Black,fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        )
        topupOptions.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowItems.forEach { (packageName, price) ->
                    val isSelected = selectedTopupOption.value == (packageName to price)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .background(if (isSelected) Color.Blue else Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .clickable() {
                                selectedTopupOption.value = packageName to price
                                if(selectedTopupOption.value != null) {
                                    showDialog.value = true
                                }
                            }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                            Text(text = packageName, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Text(text = "$price MMK", fontSize = 11.sp,
                                color = if (isSelected) Color.White else Color.Black)
                        }
                    }
                }
                repeat(3 - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text( text = "Data",fontSize = 18.sp, color = Color.Black,fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp))
        dataOptions.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowItems.forEach { (packageName, price) ->
                    val isSelected = selectedDataOption.value == (packageName to price)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .background(  if (isSelected) Color.Blue else Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .clickable() { selectedDataOption.value = packageName to price
                                selectedTopupOption.value = null
                                if(selectedDataOption.value !=null) {
                                    showDialog.value = true
                                }
                            }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = packageName, fontSize = 12.sp, fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else Color.Black)
                            Text(text = "$price MMK", fontSize = 11.sp,
                                color = if (isSelected) Color.White else Color.Black)
                        }
                    }
                }
                repeat(3 - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Modal Dialog
        if (showDialog.value) {

            val topupOption = selectedTopupOption.value
            val dataOption = selectedDataOption.value

            // Use topup option if available, otherwise use data option
            val packageName = topupOption?.first ?: dataOption?.first ?: "Unknown"
            val price = topupOption?.second ?: dataOption?.second ?: "0"

            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Confirm Purchase") },
                text = {
                    Column {
                        if(alertMessage.value.isNotEmpty()){
                            Text(text = alertMessage.value)
                        }else{
                            Text(text = "Selected Package: $packageName")
                            Text(text = "Price: $price MMK")
                            Text(text = formattedDateTime)}
                    }

                },
                confirmButton = {
                    TextButton(onClick = {
                        if(operatorName.value.isNotEmpty() && operatorName.value != "Unknown"){
                            viewModel.addTodo(
                                packageName = packageName,
                                operatorName = operatorName.value,
                                price = price.toString(),
                                phoneNumber = phoneNumber.value.text
                            )
                            alertMessage.value = ""
                            showDialog.value = false
                            navController.navigate(Routes.successScreen + "/${packageName}/${price}/${operatorName.value}/${phoneNumber.value.text}")
                        }else{
                            if( phoneNumber.value.text.isEmpty() ||operatorName.value == "Unknown"){
                                alertMessage.value = "Please Enter Valid Number!"
                            }
                        }
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog.value = false
                        selectedTopupOption.value = null
                        selectedDataOption.value = null
                        alertMessage.value = ""
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }

        Button(onClick = {
            navController.navigate(Routes.topupHistory)
        }) {
            Text( text = "History")
        }
        if (operatorName.value.isNotEmpty()) {
            Text("Operator: ${operatorName.value}")
        }
    }
}