package com.app.travelmate.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.travelmate.R
import com.app.travelmate.presentation.model.BottomSheetDetails
import com.app.travelmate.presentation.theme.LocalCustomColorPalette


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    bottomSheetDetails: BottomSheetDetails,
    onDismiss: () -> Unit,
    modalBottomSheetState: SheetState,
) {

    ModalBottomSheet(
        modifier = Modifier.height(dimensionResource(id = R.dimen.dp_400)),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = LocalCustomColorPalette.current.screenBackground
    ) {
        Column(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.margin_large),
                start = dimensionResource(id = R.dimen.horizontal_margin_medium)
            )
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.number_of_cities),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = bottomSheetDetails.cityItemCount.toString(),
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            Text(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_medium)),
                text = stringResource(id = R.string.top_three_characters),
                style = MaterialTheme.typography.headlineLarge
            )

            Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_small))) {
                repeat(bottomSheetDetails.topCharacters.size) { index ->
                    Row(Modifier.padding(top = dimensionResource(id = R.dimen.margin_small))) {
                        Text(
                            text = stringResource(id = R.string.bullet).plus(
                                bottomSheetDetails.topCharacters[index].first.toString()
                                    .plus(stringResource(id = R.string.arrow))
                            ),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = bottomSheetDetails.topCharacters[index].second.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewBottomSheet() {
    BottomSheet(
        bottomSheetDetails = BottomSheetDetails(
            5,
            topCharacters = listOf(Pair('e', 2), Pair('t', 5))
        ),
        onDismiss = { },
        modalBottomSheetState = rememberModalBottomSheetState()
    )
}