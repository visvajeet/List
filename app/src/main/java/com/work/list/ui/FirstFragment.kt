package com.work.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.work.list.R
import com.work.list.adapters.ItemsAdapter
import com.work.list.data.network.Status
import com.work.list.databinding.FragmentFirstBinding
import com.work.list.utils.CheckNetwork
import com.work.list.utils.showToast
import com.work.list.viewmodels.SharedViewModel

class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var adapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false).apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        setAdapter()

        binding.srl.setOnRefreshListener { refreshData() }

        sharedViewModel.items.observe(viewLifecycleOwner, {

            when (it.status) {

                Status.SUCCESS -> {
                    adapter.submitList(it.data?.dataItem?.items)
                    it.data?.error?.let { errorMessage -> requireContext().showToast(errorMessage) }
                }
                Status.ERROR -> requireContext().showToast(it.message.toString())
                else -> {}
            }

        })

        return binding.root

    }

    private fun refreshData() {
        if (!CheckNetwork.isNetworkConnected(requireContext())) {
            requireContext().showToast(requireContext().getString(R.string.no_internet))
            binding.srl.isRefreshing = false
            return
        }
        sharedViewModel.refreshData()
    }

    private fun setAdapter() {
        adapter = ItemsAdapter()
        binding.rv.adapter = adapter
    }
}