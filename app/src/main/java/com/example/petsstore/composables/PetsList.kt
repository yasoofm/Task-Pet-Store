package com.example.petsstore.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.petsstore.models.Pet
import com.example.petsstore.viewmodel.PetViewModel

@Composable
fun PetsList(viewModel: PetViewModel){
    LazyColumn{
        val pets = viewModel.pets
        items(pets){
            PetItem(it)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun PetItem(pet: Pet){
    Card (modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)){
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(150.dp), verticalAlignment = Alignment.CenterVertically){
            AsyncImage(modifier = Modifier.size(100.dp),
                model = pet.image,
                contentDescription = "pet image")
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = pet.name)
                Text(text = pet.gender, overflow = TextOverflow.Ellipsis, maxLines = 3)
                Text(text = pet.age.toString())
            }
        }
    }
}