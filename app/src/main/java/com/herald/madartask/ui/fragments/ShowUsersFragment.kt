package com.herald.madartask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.herald.madartask.databinding.FragmentShowUsersBinding
import com.herald.madartask.ui.UsersAdapter
import com.herald.madartask.ui.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowUsersFragment: Fragment() {
    private var _binding: FragmentShowUsersBinding? = null
    private val binding get() = _binding!!

    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowUsersBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUsersDataRecyclerView()
    }
    private fun initUsersDataRecyclerView() {
        lifecycleScope.launch {
            usersViewModel.allUsers.collect { users ->
                binding.rvUsers.run{
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = UsersAdapter(users)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
