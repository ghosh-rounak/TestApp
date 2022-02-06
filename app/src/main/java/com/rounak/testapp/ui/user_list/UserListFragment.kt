package com.rounak.testapp.ui.user_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rounak.testapp.R
import com.rounak.testapp.data.db.entities.User
import com.rounak.testapp.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private val viewModel: UserListViewModel by viewModels()
    private lateinit var binding: FragmentUserListBinding
    @Inject
    lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.clickListener = { selectedItem: User, position: Int ->
            listItemClicked(
                selectedItem,
                position
            )
        }
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        setViewModelWithLifeCycleOwner()
        initRv()
        createObservers()
        setOnClicks()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setOnClicks() {
        binding.topAppBar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun createObservers() {
        viewModel.savedUsers.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("onCreate", "saved user list size: ${it.size}")
                if(it.isEmpty()){
                    adapter.differ.submitList(it)
                    binding.usersRv.isVisible = false
                    binding.noUserTv.isVisible = true
                }else{
                    binding.noUserTv.isVisible = false
                    binding.usersRv.isVisible = true
                    adapter.differ.submitList(it)
                }
            }
        }
    }

    private fun setViewModelWithLifeCycleOwner() {
        binding.userListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }


    private fun initRv() {
        binding.usersRv.layoutManager = LinearLayoutManager(requireActivity())
        binding.usersRv.adapter = adapter
    }


    //ITEM Click on each item of the Recycler View
    private fun listItemClicked(user: User, position: Int) {
        Log.d("onCreate", "listItem position clicked : $position")
        Log.d("onCreate", "listItemClicked: $user")
    }

}