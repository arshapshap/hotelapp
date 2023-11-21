package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.touristsrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat.getString
import androidx.core.view.isGone
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arshapshap.hotelapp.designsystem.extensions.setError
import com.arshapshap.hotelapp.feature.booking.R
import com.arshapshap.hotelapp.feature.booking.databinding.LayoutBookingTouristInfoBinding
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.BookingError
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTourist
import com.arshapshap.hotelapp.feature.booking.presentation.screen.booking.model.EditableTouristField
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(list[position])

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
                editTextName.setNewText(tourist.name)
                editTextSurname.setNewText(tourist.surname)
                editTextBirthday.setNewText(tourist.birthday?.format() ?: "")
                editTextCitizenship.setNewText(tourist.citizenship)
                editTextPassportNumber.setNewText(tourist.passportNumber?.toString() ?: "")
                editTextPassportValidityPeriod.setNewText(tourist.passportValidityPeriod?.format() ?: "")

                textInputName.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongName(tourist.id)), getString(binding.root.context, R.string.wrong_name))
                textInputSurname.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongSurname(tourist.id)), getString(binding.root.context, R.string.wrong_surname))
                textInputBirthday.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongBirthday(tourist.id)), getString(binding.root.context, R.string.wrong_birthday))
                textInputCitizenship.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongCitizenship(tourist.id)), getString(binding.root.context, R.string.wrong_citizenship))
                textInputPassportNumber.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongPassportNumber(tourist.id)), getString(binding.root.context, R.string.wrong_passport_number))
                textInputPassportValidityPeriod.setError(binding.root.context, tourist.errors.contains(BookingError.Tourist.WrongPassportValidityPeriod(tourist.id)), getString(binding.root.context, R.string.wrong_passport_validity_period))
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

        private fun LocalDate.format(): String {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            return this.format(formatter)
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
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
