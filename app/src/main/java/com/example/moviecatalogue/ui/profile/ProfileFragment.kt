package com.example.moviecatalogue.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.databinding.FragmentProfileBinding
import com.example.moviecatalogue.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE_NUMBER = "extra_phone_number"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }

    private val bottomSheetDialogFragment = BottomSheetDialogFragment()
    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentProfileBinding.inflate(inflater).apply {
            viewModel = this@ProfileFragment.viewModel
            lifecycleOwner = this@ProfileFragment

            editName.setOnClickListener {
                setName()
            }

            editEmail.setOnClickListener {
                setEmail()
            }

            editPhoneNumber.setOnClickListener {
                setPhoneNumber()
            }

            buttonlogout.setOnClickListener {
                viewModel?.onClear()
                logout()
            }
        }.root
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(activity, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
        activity?.finish()
    }

    private fun setName() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            bundle.putString(EXTRA_NAME, it.name ?: "")
            bottomSheetDialogFragment.arguments = bundle
        })
        activity?.supportFragmentManager?.let { supportFragment ->
            bottomSheetDialogFragment.show(
                supportFragment,
                bottomSheetDialogFragment.tag
            )
        }
    }

    private fun setEmail() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            bundle.putString(EXTRA_EMAIL, it.email ?: "")
            bottomSheetDialogFragment.arguments = bundle
        })
        activity?.supportFragmentManager?.let { supportFragment ->
            bottomSheetDialogFragment.show(
                supportFragment,
                bottomSheetDialogFragment.tag
            )
        }
    }

    private fun setPhoneNumber() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            bundle.putString(EXTRA_PHONE_NUMBER, it.phoneNumber ?: "")
            bottomSheetDialogFragment.arguments = bundle
        })
        activity?.supportFragmentManager?.let { supportFragment ->
            bottomSheetDialogFragment.show(
                supportFragment,
                bottomSheetDialogFragment.tag
            )
        }
    }
}
