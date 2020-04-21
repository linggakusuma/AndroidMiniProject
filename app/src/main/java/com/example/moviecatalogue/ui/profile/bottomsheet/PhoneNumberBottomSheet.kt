package com.example.moviecatalogue.ui.profile.bottomsheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.databinding.BottomSheetBinding
import com.example.moviecatalogue.ui.profile.ProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PhoneNumberBottomSheet : BottomSheetDialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val vieweModel by viewModels<ProfileViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return BottomSheetBinding.inflate(inflater).apply {
            this@PhoneNumberBottomSheet.vieweModel.user.observe(
                viewLifecycleOwner,
                Observer {
                    editUser.setText(it.phoneNumber)
                })
            buttonSave.setOnClickListener {
                this@PhoneNumberBottomSheet.vieweModel.onUpdatePhoneNumber(editUser.text.toString())
                dismiss()
            }
        }.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}