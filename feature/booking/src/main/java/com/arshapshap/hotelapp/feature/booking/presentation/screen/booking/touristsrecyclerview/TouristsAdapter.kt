package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.touristsrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isGone
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
        ViewHolder(
            binding = getBinding(parent),
            onClickExpand = onClickExpand,
            onFieldChanged = onFieldChanged)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(list[position])

    private fun getBinding(parent: ViewGroup): LayoutBookingTouristInfoBinding =
        LayoutBookingTouristInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    class ViewHolder(
        private val binding: LayoutBookingTouristInfoBinding,
        private val onClickExpand: (Int) -> Unit,
        private val onFieldChanged: (Int, EditableTouristField, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var touristId: Int? = null

        init {
            setTextChangedListeners()
        }

        fun onBind(tourist: EditableTourist) {
            touristId = tourist.id

            setHeader(tourist)
            setContent(tourist)
            setContentExpanded(tourist.isExpanded)
        }

        private fun setHeader(tourist: EditableTourist) {
            with(binding) {
                textViewTouristNumber.text = root.context.resources.getString(
                    R.string.tourist_number,
                    formatAsOrdinal(tourist.id + 1)
                )
                imageButtonExpandTouristInfo.setOnClickListener {
                    onClickExpand(tourist.id)
                }
            }
        }

        private fun setContentExpanded(isExpanded: Boolean) {
            with(binding) {
                imageButtonExpandTouristInfo.rotation = if (isExpanded) 270f else 90f
                textInputName.isGone = !isExpanded
                textInputSurname.isGone = !isExpanded
                textInputBirthday.isGone = !isExpanded
                textInputCitizenship.isGone = !isExpanded
                textInputPassportNumber.isGone = !isExpanded
                textInputPassportValidityPeriod.isGone = !isExpanded
            }
        }

        private fun setContent(tourist: EditableTourist) {
            with(binding) {
                //textInputName.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongName))
                editTextName.setNewText(tourist.name)

                //textInputSurname.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongSurname))
                editTextSurname.setNewText(tourist.surname)

                //textInputBirthday.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongBirthday))
                editTextBirthday.setNewText(tourist.birthday?.toString() ?: "")

                //textInputCitizenship.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongCitizenship))
                editTextCitizenship.setNewText(tourist.citizenship)

                //textInputPassportNumber.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongPassportNumber))
                editTextPassportNumber.setNewText(tourist.passportNumber?.toString() ?: "")

                //textInputPassportValidityPeriod.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongPassportValidityPeriod))
                editTextPassportValidityPeriod.setNewText(
                    tourist.passportValidityPeriod?.toString() ?: ""
                )
            }
        }

        private fun LayoutBookingTouristInfoBinding.formatAsOrdinal(number: Int) =
            root.context.resources.getStringArray(R.array.ordinal_number)[number - 1]

        private fun EditText.setNewText(value: String) {
            if (text.toString() != value)
                setText(value)
        }

        private fun setTextChangedListeners() {
            with(binding) {
                editTextName.doAfterTextChanged {
                    touristId?.let { id ->
                        onFieldChanged(id, EditableTouristField.Name, it.toString())
                    }
                }
                editTextSurname.doAfterTextChanged {
                    touristId?.let { id ->
                        onFieldChanged(id, EditableTouristField.Surname, it.toString())
                    }
                }
                editTextBirthday.doAfterTextChanged {
                    touristId?.let { id ->
                        onFieldChanged(id, EditableTouristField.Birthday, it.toString())
                    }
                }
                editTextCitizenship.doAfterTextChanged {
                    touristId?.let { id ->
                        onFieldChanged(id, EditableTouristField.Citizenship, it.toString())
                    }
                }
                editTextPassportNumber.doAfterTextChanged {
                    touristId?.let { id ->
                        onFieldChanged(id, EditableTouristField.PassportNumber, it.toString())
                    }
                }
                editTextPassportValidityPeriod.doAfterTextChanged {
                    touristId?.let { id ->
                        onFieldChanged(id, EditableTouristField.PassportValidityPeriod, it.toString())
                    }
                }
            }
        }
    }

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
