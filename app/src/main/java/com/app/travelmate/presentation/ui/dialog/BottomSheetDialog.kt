package com.app.travelmate.presentation.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.travelmate.databinding.ViewBottomSheetDialogBinding
import com.app.travelmate.presentation.model.BottomSheetDetails
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog(private val bottomSheetDetails: BottomSheetDetails) :
    BottomSheetDialogFragment() {

    private lateinit var mBinding: ViewBottomSheetDialogBinding

    companion object {
        const val TAG = "ModalBottomSheetDialog"
        const val BULLET = "• "
        const val ARROW = " -> "
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = ViewBottomSheetDialogBinding.inflate(
            inflater,
            container,
            false
        )
        setContent()
        return mBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { view ->
                val behavior = BottomSheetBehavior.from(view)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    private fun setContent() {
        bottomSheetDetails.cityItemCount.takeIf { it != null }?.let {
            mBinding.tvItemCount.tvTextView.text = it.toString()
        } ?: {
            mBinding.tvItemCount.root.visibility = View.GONE
        }

        bottomSheetDetails.topCharacters.getOrNull(0)?.let { pair ->
            mBinding.tvFirstTopCharacter.lblTextView.text =
                BULLET.plus(pair.first.toString().plus(ARROW))
            mBinding.tvFirstTopCharacter.tvTextView.text = pair.second.toString()
        } ?: {
            mBinding.tvFirstTopCharacter.root.visibility = View.GONE
        }

        bottomSheetDetails.topCharacters.getOrNull(1)?.let { pair ->
            mBinding.tvSecondTopCharacter.lblTextView.text =
                BULLET.plus(pair.first.toString().plus(ARROW))
            mBinding.tvSecondTopCharacter.tvTextView.text = pair.second.toString()
        } ?: {
            mBinding.tvSecondTopCharacter.root.visibility = View.GONE
        }

        bottomSheetDetails.topCharacters.getOrNull(2)?.let { pair ->
            mBinding.tvThirdTopCharacter.lblTextView.text =
                BULLET.plus(pair.first.toString().plus(ARROW))
            mBinding.tvThirdTopCharacter.tvTextView.text = pair.second.toString()
        } ?: {
            mBinding.tvThirdTopCharacter.root.visibility = View.GONE
        }
    }
}