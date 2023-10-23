package com.example.monprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Screen(windowClass: WindowSizeClass, navController: NavController){
    when(windowClass.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp,50.dp)

            ) {
                Image(
                    painterResource(id = R.drawable.photo_profil),
                    contentDescription = "chien de profil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)

                )
                Spacer(Modifier.height(25.dp))
                Text(
                    text = "Mélanie FERON",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Etudiante en deuxième année du cycle ingénieur",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Ecole d'ingénieur ISIS",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
                )
                Spacer(Modifier.height(60.dp))
                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painterResource(id = R.drawable.logo),
                        contentDescription = "logo linkedin",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(15.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "melanie.feron60@gmail.com",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painterResource(id = R.drawable.mail_logo),
                        contentDescription = "logo mail",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(15.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "www.linkedin.com/in/melanie-feron",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Spacer(Modifier.height(60.dp))
                Button(onClick = {navController.navigate("films")}){
                    Text(
                        text = "Démarrer"
                    )
                }
        }
    }
        else -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp,30.dp)

            ) {
                Column(){
                    Image(
                        painterResource(id = R.drawable.photo_profil),
                        contentDescription = "chien de profil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .align(CenterHorizontally)

                    )
                    Spacer(Modifier.height(25.dp))
                    Text(
                        text = "Mélanie FERON",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .align(CenterHorizontally)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Etudiante en deuxième année du cycle ingénieur",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .align(CenterHorizontally)
                    )
                    Text(
                        text = "Ecole d'ingénieur ISIS",
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                                .align(CenterHorizontally)
                    )
                    Spacer(Modifier.height(60.dp))
                }


                Column(horizontalAlignment = CenterHorizontally){Row(verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painterResource(id = R.drawable.logo),
                        contentDescription = "logo linkedin",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(15.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "melanie.feron60@gmail.com",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Image(
                            painterResource(id = R.drawable.mail_logo),
                            contentDescription = "logo mail",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(15.dp)
                        )
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = "www.linkedin.com/in/melanie-feron",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Spacer(Modifier.height(60.dp))
                    Button(onClick = {
                        navController.navigate("films")
                    }){
                        Text(
                            text = "Démarrer",
                        )

                    }}

            }
        }

    }

}
