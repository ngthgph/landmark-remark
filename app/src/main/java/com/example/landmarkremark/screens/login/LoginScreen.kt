package com.example.landmarkremark.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.landmarkremark.R

@Composable
fun LoginScreen(
    signUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.padding_extra_large)),
    ) {
        Text(
            text = stringResource(R.string.sign_in_to_your_account),
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
                initial = uiState.email,
                onInput = { viewModel.onEmailChange(it) },
                placeholder = stringResource(R.string.email_address),
                modifier = Modifier
                    .sizeIn(maxWidth = dimensionResource(id = R.dimen.text_field) )
            )
            InputTextField(
                initial = uiState.password,
                onInput = { viewModel.onPasswordChange(it) },
                placeholder = stringResource(R.string.password)
            )
            Text(
                text = stringResource(R.string.please_register_or_login_to_be_able_to_save_your_own_data),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
            DescriptionButton(
                enabled = true,
                text = stringResource(R.string.sign_in),
                onClick = { viewModel.onSignInClick() }
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            ClickableText(
                enabled = true,
                text = stringResource(R.string.forget_password),
                onClick = { viewModel.onForgotPasswordClick() }
            )
            Text(text = " / ")
            ClickableText(
                enabled = true,
                text = stringResource(R.string.sign_up),
                onClick = signUp
            )
        }
    }
}
@Composable
fun ClickableText(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit,
) {
    var isClicking by remember { mutableStateOf(false) }
    Text(
        text = text,
        textDecoration = if(isClicking) TextDecoration.Underline else null,
        color = if(!isClicking && enabled) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier.clickable(
            enabled = enabled,
            onClick = {
                isClicking = !isClicking
                onClick()
            }
        )
    )
}
@Composable
fun DescriptionButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.outlineVariant,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        onClick = onClick
    ) {
        Text(
            text = text
        )
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputTextField(
    onInput:((String) -> Unit)? = null,
    modifier: Modifier = Modifier,
    onValueChange: ((String) -> Unit)? = null,
    initial: String = "",
    placeholder: String = "",
    enabled: Boolean = true,
) {
    var input by remember { mutableStateOf(initial) }
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        value = input,
        onValueChange = {
            input = it
            onValueChange?.let { it1 -> it1(it) }
                        },
        placeholder = {
            Text(
                text = placeholder.replaceFirstChar { it.uppercase() },
                color = MaterialTheme.colorScheme.outline
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onInput?.let { it(input) }
                keyboardController?.hide()
            }
        ),
        maxLines = 1,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small))
    )
}

