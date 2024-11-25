package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp

@Composable
fun Profil(name: String, modifier: Modifier = Modifier, onStartClicked: () -> Unit) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            val boxWidth = maxWidth
            val boxHeight = maxHeight
            val imageSize: Dp = if (boxWidth < boxHeight) 250.dp else 150.dp

            Row(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable._669714147747),
                        contentDescription = "Ma Photo",
                        modifier = Modifier
                            .size(imageSize)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "MON COMPTE LINKEDIN :",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.linknedin_logo),
                        contentDescription = "LinkedIn Logo",
                        modifier = Modifier
                            .size(80.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/nabil-albazzou-b99732257/"))
                                context.startActivity(intent)
                            }
                    )
                    Text(
                        text = "Mon email :",
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "oscar.pradier@gmail.com",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Button(
                        onClick = onStartClicked,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Démarrer")
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = name,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable._669714147747),
                contentDescription = "Ma Photo",
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "MON COMPTE LINKEDIN :",
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.linknedin_logo),
                contentDescription = "LinkedIn Logo",
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/nabil-albazzou-b99732257/"))
                        context.startActivity(intent)
                    }
            )
            Text(
                text = "Mon email :",
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "oscar.pradier@gmail.com",
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(
                onClick = onStartClicked,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Démarrer")
            }
        }
    }
}
