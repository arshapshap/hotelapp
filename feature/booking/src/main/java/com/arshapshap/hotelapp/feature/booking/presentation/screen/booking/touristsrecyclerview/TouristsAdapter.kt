package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.touristsrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isGone
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arshapshap.hotelapp.feature.booking.R
import com.arshapshap.hotelapp.feature.booking.databinding.LayoutBookingTouristInfoBinding
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTourist
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTouristField


internal class TouristsAdapter(
    private val list: MutableList<EditableTourist> = mutableListOf(),
    private val onClickExpand: (Int) -> Unit,
    private val onFieldChanged: (Int, EditableTouristField, String) -> Unit
) : RecyclerView.Adapter<TouristsAdapter.ViewHolder>() {

    fun setList(newList: List<EditableTourist>) {
        val diffCallback = DiffCallback(list, newList)
        val diffCourses = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(newList)
        diffCourses.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(getBinding(parent))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.binding.run {
        (list[position]).let { tourist ->
            setHeader(tourist)
            setContentExpanded(tourist.isExpanded)
            setContent(tourist)
        }
    }

    private fun getBinding(parent: ViewGroup): LayoutBookingTouristInfoBinding =
        LayoutBookingTouristInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    private fun LayoutBookingTouristInfoBinding.setHeader(tourist: EditableTourist) {
        textViewTouristNumber.text = root.context.resources.getString(
            R.string.tourist_number,
            formatAsOrdinal(tourist.id + 1)
        )
        imageButtonExpandTouristInfo.setOnClickListener {
            onClickExpand(tourist.id)
        }
    }

    private fun LayoutBookingTouristInfoBinding.setContentExpanded(isExpanded: Boolean) {
        imageButtonExpandTouristInfo.rotation = if (isExpanded) 270f else 90f
        textInputName.isGone = !isExpanded
        textInputSurname.isGone = !isExpanded
        textInputBirthday.isGone = !isExpanded
        textInputCitizenship.isGone = !isExpanded
        textInputPassportNumber.isGone = !isExpanded
        textInputPassportValidityPeriod.isGone = !isExpanded
    }

    private fun LayoutBookingTouristInfoBinding.setContent(tourist: EditableTourist) {
        editTextName.configureTextInput(
            tourist.id,
            tourist.name,
            EditableTouristField.Name)

        editTextSurname.configureTextInput(
            tourist.id,
            tourist.surname,
            EditableTouristField.Surname)

        editTextBirthday.configureTextInput(
            tourist.id,
            tourist.birthday?.toString() ?: "",
            EditableTouristField.Birthday
        )

        editTextCitizenship.configureTextInput(
            tourist.id,
            tourist.citizenship,
            EditableTouristField.Citizenship
        )

        editTextPassportNumber.configureTextInput(
            tourist.id,
            tourist.passportNumber?.toString() ?: "",
            EditableTouristField.PassportNumber
        )

        editTextPassportValidityPeriod.configureTextInput(
            tourist.id,
            tourist.passportValidityPeriod?.toString() ?: "",
            EditableTouristField.PassportValidityPeriod
        )
    }

    private fun LayoutBookingTouristInfoBinding.formatAsOrdinal(number: Int) =
        root.context.resources.getStringArray(R.array.ordinal_number)[number - 1]

    private fun EditText.configureTextInput(touristId: Int, value: String, touristField: EditableTouristField) {
        if (text.toString() != value)
            setText(value)
        doAfterTextChanged {
            onFieldChanged(touristId, touristField, it.toString())
        }
    }

    class ViewHolder(val binding: LayoutBookingTouristInfoBinding) : RecyclerView.ViewHolder(binding.root)

    private class DiffCallback(
        private val oldList: List<EditableTourist>,
        private val newList: List<EditableTourist>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
                    && oldList[oldItemPosition].surname == newList[newItemPosition].surname
                    && oldList[oldItemPosition].birthday == newList[newItemPosition].birthday
                    && oldList[oldItemPosition].citizenship == newList[newItemPosition].citizenship
                    && oldList[oldItemPosition].passportNumber == newList[newItemPosition].passportNumber
                    && oldList[oldItemPosition].passportValidityPeriod == newList[newItemPosition].passportValidityPeriod
                    && oldList[oldItemPosition].isExpanded == newList[newItemPosition].isExpanded
        }
    }
}
