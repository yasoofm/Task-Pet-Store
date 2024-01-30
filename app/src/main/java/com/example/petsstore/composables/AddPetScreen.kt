package com.example.petsstore.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petsstore.models.Pet
import com.example.petsstore.viewmodel.PetViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddPetScreen(petViewModel: PetViewModel = viewModel(), onDismiss: () -> Unit) {

    // State variables for input fields
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(0) }
    var imageUrl by remember { mutableStateOf("") }
    var gender by remember {
        mutableStateOf("")
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add a New Book",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            InputField(
                value = name,
                onValueChange = { name = it },
                label = "Name"
            )
            InputField(
                value = gender,
                onValueChange = { gender = it },
                label = "Gender"
            )
            InputField(
                value = age.toString(),
                onValueChange = { age = it.toInt() },
                label = "Age",
                keyboardType = KeyboardType.Number
            )
            InputField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = "Image URL"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val pet = Pet(
                        id = 0,
                        name = name,
                        age = age,
                        image = imageUrl,
                        gender = gender
                        )
                    petViewModel.addPet(pet)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Pet")
            }
        }
    }
    if (petViewModel.addSuccess){
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(
                    text = "Pet added successfully",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }
        }

        petViewModel.closeDialog(2000, onDismiss)
    }
}

@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String, keyboardType: KeyboardType = KeyboardType.Text) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}