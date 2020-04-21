package com.example.moviecatalogue.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.databinding.FragmentProfileBinding
import com.example.moviecatalogue.ui.login.LoginActivity
import com.example.moviecatalogue.ui.profile.bottomsheet.EmailBottomSheet
import com.example.moviecatalogue.ui.profile.bottomsheet.NameBottomSheet
import com.example.moviecatalogue.ui.profile.bottomsheet.PhoneNumberBottomSheet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    companion object {
        const val RESULT_LOAD_IMAGE = 1003
        val TAG = ProfileFragment::class.java.simpleName
        const val KEY_SF = "key_sf"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentProfileBinding.inflate(inflater).apply {
            viewModel = this@ProfileFragment.viewModel
            lifecycleOwner = this@ProfileFragment

            val sharedPreferences = activity?.getSharedPreferences(TAG, Context.MODE_PRIVATE)
            switchNotif.isChecked = sharedPreferences?.getBoolean(KEY_SF, false) ?: false

            editName.setOnClickListener {
                setName()
            }

            editEmail.setOnClickListener {
                setEmail()
            }

            editPhoneNumber.setOnClickListener {
                setPhoneNumber()
            }

            imgUser.setOnClickListener {
                openGallery()
            }

            buttonlogout.setOnClickListener {
                viewModel?.onClear()
                logout()
            }

            switchNotif.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("movie")
                    activity?.getSharedPreferences(TAG, Context.MODE_PRIVATE)?.edit()?.apply {
                        putBoolean(KEY_SF, true)
                        apply()
                    }
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("movie")
                    activity?.getSharedPreferences(TAG, Context.MODE_PRIVATE)?.edit()?.apply {
                        putBoolean(KEY_SF, false)
                        apply()
                    }
                }
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
        val bottomSheetDialogFragment =
            NameBottomSheet()
        activity?.supportFragmentManager?.let { supportFragment ->
            bottomSheetDialogFragment.show(
                supportFragment,
                bottomSheetDialogFragment.tag
            )
        }
    }

    private fun setEmail() {
        val bottomSheetDialogFragment =
            EmailBottomSheet()
        activity?.supportFragmentManager?.let { supportFragment ->
            bottomSheetDialogFragment.show(
                supportFragment,
                bottomSheetDialogFragment.tag
            )
        }
    }

    private fun setPhoneNumber() {
        val bottomSheetDialogFragment =
            PhoneNumberBottomSheet()
        activity?.supportFragmentManager?.let { supportFragment ->
            bottomSheetDialogFragment.show(
                supportFragment,
                bottomSheetDialogFragment.tag
            )
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, RESULT_LOAD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE) {
            val uri = data?.data
            viewModel.onUpdateImage(uri.toString())
        }
    }
}
