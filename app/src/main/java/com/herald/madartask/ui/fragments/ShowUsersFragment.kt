package com.herald.madartask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.herald.madartask.databinding.FragmentShowUsersBinding
import com.herald.madartask.ui.UsersViewModel

class ShowUsersFragment: Fragment() {
    private val binding by lazy { FragmentShowUsersBinding.inflate(layoutInflater) }
    private val usersViewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}
