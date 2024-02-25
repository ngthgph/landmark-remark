package com.example.landmarkremark.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.landmarkremark.R
import com.example.landmarkremark.screens.login.DescriptionButton
import com.example.landmarkremark.screens.login.InputTextField

@Composable
fun SignUpScreen(
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.padding_extra_large)),
    ) {
        Text(
            text = stringResource(R.string.sign_up_your_account),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Start,
            modifier = Modifier
        )
        Divider()
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(24.dp)
                .sizeIn(maxWidth = dimensionResource(id = R.dimen.text_field)),
        ) {
            InputTextField(
                onInput = {},
                placeholder = stringResource(R.string.email_address),
                modifier = Modifier
                    .sizeIn(maxWidth = dimensionResource(id = R.dimen.text_field) )
            )
            InputTextField(
                onInput = {},
                placeholder = stringResource(R.string.password)
            )
            InputTextField(
                onInput = {},
                placeholder = stringResource(R.string.confirm_password)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_large))
            ) {
                DescriptionButton(
                    enabled = true,
                    text = stringResource(R.string.cancel),
                    onClick = onCancel,
                )
                Spacer(modifier = modifier.weight(1f))
                DescriptionButton(
                    enabled = false,
                    text = stringResource(R.string.sign_up),
                    onClick = {}
                )
            }
        }
    }
}
