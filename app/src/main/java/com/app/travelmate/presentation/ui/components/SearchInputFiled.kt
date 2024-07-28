package com.app.travelmate.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.app.travelmate.R
import com.app.travelmate.presentation.theme.LocalCustomColorPalette


@Composable
fun SearchInputField(
    searchQuery: MutableState<String>,
    onSearchCallback: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val inputQuery = remember {
        derivedStateOf { searchQuery.value }
    }
    Column(
        modifier = Modifier
            .background(LocalCustomColorPalette.current.screenBackground)
            .padding(vertical = dimensionResource(id = R.dimen.dp_12))
    ) {
        OutlinedTextField(
            value = inputQuery.value,
            textStyle = MaterialTheme.typography.titleMedium,
            onValueChange = { input ->
                onSearchCallback(input)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp_4)))
                .height(dimensionResource(id = R.dimen.dp_50)),
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal,
                        color = LocalCustomColorPalette.current.darkGrey,
                    ),
                    maxLines = 1
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = LocalCustomColorPalette.current.darkGrey
                )
            },
            trailingIcon = {
                if (inputQuery.value.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onSearchCallback("")
                            keyboardController?.hide()
                        },
                        tint = LocalCustomColorPalette.current.darkGrey
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    onSearchCallback(inputQuery.value.lowercase().trim())
                }),
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = LocalCustomColorPalette.current.accentColor,
            )
        )
    }
}

@Preview
@Composable
fun SearchInputTextPreview() {
    SearchInputField(
        searchQuery = remember {
            mutableStateOf("Singapore")
        },
        onSearchCallback = { },
    )
}
