package com.arshapshap.hotelapp.feature.hotel.impl.presentation.screen.rooms

import com.arshapshap.hotelapp.core.presentation.BaseFragment
import com.arshapshap.hotelapp.feature.hotel.impl.databinding.FragmentRoomsBinding
import com.arshapshap.hotelapp.feature.hotel.impl.presentation.screen.rooms.roomsrecyclerview.RoomsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class RoomsFragment : BaseFragment<FragmentRoomsBinding, RoomsViewModel>(
    FragmentRoomsBinding::inflate
) {

    override val viewModel: RoomsViewModel by viewModel()

    override fun initViews() {
        with(binding) {
            recyclerViewRooms.adapter = RoomsAdapter(
                onSelectRoom = viewModel::selectRoom,
                onOpenDetails = viewModel::openDetails
            )
        }
    }

    override fun subscribe() {
        viewModel.loadData()

        viewModel.rooms.observe(viewLifecycleOwner) {
            getRoomsAdapter().setList(it)
        }
    }

    private fun getRoomsAdapter() =
        binding.recyclerViewRooms.adapter as RoomsAdapter
}