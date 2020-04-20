package com.example.moviecatalogue.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BottomSheetDialogFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val vieweModel by viewModels<ProfileViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return BottomSheetBinding.inflate(inflater).apply {

            val name = arguments?.getString(ProfileFragment.EXTRA_NAME)
            val email = arguments?.getString(ProfileFragment.EXTRA_EMAIL)
            val phoneNumber = arguments?.getString(ProfileFragment.EXTRA_PHONE_NUMBER)

            when {
                !name.isNullOrEmpty() -> {
                    this@BottomSheetDialogFragment.vieweModel.user.observe(
                        viewLifecycleOwner,
                        Observer {
                            editUser.setText(it.name)
                        })
                    buttonSave.setOnClickListener {
                        this@BottomSheetDialogFragment.vieweModel.onUpdateName(editUser.text.toString())
                        dismiss()
                        onDestroy()
                    }
                }
                !email.isNullOrEmpty() -> {
                    this@BottomSheetDialogFragment.vieweModel.user.observe(
                        viewLifecycleOwner,
                        Observer {
                            editUser.setText(it.email)
                        })
                    buttonSave.setOnClickListener {
                        this@BottomSheetDialogFragment.vieweModel.onUpdateEmail(editUser.text.toString())
                        dismiss()
                    }
                }
                !phoneNumber.isNullOrEmpty() -> {
                    this@BottomSheetDialogFragment.vieweModel.user.observe(
                        viewLifecycleOwner,
                        Observer {
                            editUser.setText(it.phoneNumber)
                        })
                    buttonSave.setOnClickListener {
                        this@BottomSheetDialogFragment.vieweModel.onUpdatePhoneNumber(editUser.text.toString())
                        dismiss()
                    }
                }
            }
        }.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}