package com.nestor.spacex.ui.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nestor.spacex.R
import com.nestor.spacex.databinding.FragmentLaunchesBinding
import com.nestor.spacex.ui.launches.adapter.LaunchesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LaunchesFragment : Fragment() {
    lateinit var mBinding: FragmentLaunchesBinding
    private val viewModel: LaunchesViewModel by viewModels()
    lateinit var mLaunchesListAdapter: LaunchesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLaunchesListAdapter = LaunchesListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding =
            FragmentLaunchesBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        mBinding.vm = viewModel
        mBinding.launchesRv.adapter = mLaunchesListAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.launches.collectLatest {
                mLaunchesListAdapter.submitData(it)
            }
        }

    }
}