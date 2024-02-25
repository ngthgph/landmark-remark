package com.example.landmarkremark.screens.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.landmarkremark.R

@Composable
fun AccountScreen(
    onReturn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.padding_extra_large)),
    ) {
        Text(
            text = if(uiState.isAnonymousAccount) "anonymous" else "User ${uiState.userId}",
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Start,
            modifier = Modifier,
            maxLines = 1,
            softWrap = false,
        )
        Divider()
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(24.dp)
                .sizeIn(maxWidth = dimensionResource(id = R.dimen.text_field)),
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.sign_out),
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    viewModel.onSignOutClick()
                    onReturn()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = stringResource(R.string.sign_out)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.delete_my_account),
                    color = Color.Red,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    viewModel.onDeleteMyAccountClick()
                    onReturn()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = Color.Red,
                        contentDescription = stringResource(R.string.delete_my_account)
                    )
                }
            }
        }
    }
}
