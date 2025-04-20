package com.herald.madartask.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
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
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!
    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonsSettingUp()
        genderSpinnerSettingUp()
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
    private fun genderSpinnerSettingUp(){
        val options = arrayOf("Male", "Female")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.run {
            adapter = spinnerAdapter
        }
    }

    private fun showInvalidInputsToast() {
        Toast.makeText(requireContext(),"All fields are required!",Toast.LENGTH_SHORT).show()
    }

    private fun validateInputs(): Boolean{
        binding.run {
            return (edtName.text.isBlank() || edtAge.text.isBlank() || edtJob.text.isBlank())
        }
    }

    private fun clearAllFields() {
        binding.run {
            edtName.text.clear()
            edtAge.text.clear()
            edtJob.text.clear()
            spinnerGender.setSelection(0)
            clearFocus()
        }
        Toast.makeText(requireContext(),"User Added Successfully!",Toast.LENGTH_SHORT).show()
    }

    private fun clearFocus(){
        binding.run {
            edtName.clearFocus()
            edtAge.clearFocus()
            edtJob.clearFocus()
        }
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun saveUser(){
        binding.run {
            usersViewModel.addUser(UserModel(name = edtName.text.toString(), age = edtAge.text.toString().toInt(), jobTitle = edtJob.text.toString(), gender = spinnerGender.selectedItem.toString()))
            clearAllFields()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
