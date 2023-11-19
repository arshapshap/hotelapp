package com.arshapshap.hotelapp.feature.booking.presentation.screen.success

import com.arshapshap.hotelapp.core.presentation.BaseFragment
import com.arshapshap.hotelapp.feature.booking.R
import com.arshapshap.hotelapp.feature.booking.databinding.FragmentSuccessBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class FragmentSuccess : BaseFragment<FragmentSuccessBinding, SuccessViewModel>(
    FragmentSuccessBinding::inflate
) {

    override val viewModel: SuccessViewModel by viewModel { parametersOf(getOrderId()) }

    override fun initViews() {
        binding.buttonSuper.setOnClickListener {
            viewModel.clickSuper()
        }
    }

    override fun subscribe() {
        viewModel.order.observe(viewLifecycleOwner) {
            binding.textViewDescription.text = resources.getString(R.string.order_accepted_description, it)
        }
    }

    private fun getOrderId() = arguments?.getInt(
        FragmentSuccessHelper.ORDER_ID_KEY,
        0
    )
}