package com.work.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.work.list.databinding.FragmentFifthBinding
import com.work.list.viewmodels.SharedViewModel

class FifthFragment : Fragment() {

    lateinit var binding: FragmentFifthBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root

    }
}