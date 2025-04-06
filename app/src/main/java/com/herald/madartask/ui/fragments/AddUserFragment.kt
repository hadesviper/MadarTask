package com.herald.madartask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.herald.madartask.R
import com.herald.madartask.data.UserModel
import com.herald.madartask.databinding.FragmentAddUserBinding
import com.herald.madartask.ui.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment: Fragment() {
    private val binding by lazy { FragmentAddUserBinding.inflate(layoutInflater) }
    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        buttonsSettingUp()
        return binding.root
    }
    private fun buttonsSettingUp(){
        binding.run {
            layoutContainer.setOnClickListener { clearFocus() }
            btnSaveUser.setOnClickListener {
                if (validateInputs()) showInvalidInputsToast()
                else saveUser()
            }
            btnShowAllUsers.setOnClickListener {
                findNavController().navigate(R.id.action_addUserFragment_to_showUsersFragment)
            }
        }
    }

    private fun showInvalidInputsToast() {
        Toast.makeText(requireContext(),"All fields are required!",Toast.LENGTH_SHORT).show()
    }

    private fun validateInputs(): Boolean{
        binding.run {
            return (edtName.text.isBlank() || edtAge.text.isBlank() || edtJob.text.isBlank() || edtGender.text.isBlank())
        }
    }

    private fun clearAllFields() {
        binding.run {
            edtName.text.clear()
            edtAge.text.clear()
            edtJob.text.clear()
            edtGender.text.clear()
            clearFocus()
        }
        Toast.makeText(requireContext(),"User Added Successfully!",Toast.LENGTH_SHORT).show()
    }

    private fun clearFocus(){
        binding.run {
            edtName.clearFocus()
            edtAge.clearFocus()
            edtJob.clearFocus()
            edtGender.clearFocus()
        }
    }

    private fun saveUser(){
        binding.run {
            usersViewModel.addUser(UserModel(name = edtName.text.toString(), age = edtAge.text.toString().toInt(), jobTitle = edtJob.text.toString(), gender = edtGender.text.toString()))
            clearAllFields()
        }
    }
}
