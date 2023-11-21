package com.arshapshap.hotelapp.feature.booking.presentation.screen.booking

import android.widget.EditText
import androidx.core.widget.doOnTextChanged

internal class PhoneMaskHelper {

    fun addPhoneMask(editText: EditText, onPhoneChanged: (String) -> Unit) {
        editText.addPhoneNumberMask(onPhoneChanged)
    }

    private fun EditText.addPhoneNumberMask(onPhoneChanged: (String) -> Unit) {
        setOnFocusChangeListener { _, _ ->
            if (text.isEmpty())
                setText(formatPhoneNumber(""))
        }
        doOnTextChanged { text, start, before, count ->
            val currentPhone = getPhoneNumberFromString(text.toString().removeRange(start, start+count))
            when {
                (count in (before + 1)..1) && (currentPhone.length >= 10 || !text.toString()[start].isDigit()) -> {
                    setTextAndSelection(formatPhoneNumber(currentPhone), start)
                }

                (count in (before + 1)..1) && (currentPhone.length < 10) -> {
                    val position = getPositionInPhoneByIndexInString(start)
                    val newPhone = currentPhone.take(position) + text.toString()[start] + currentPhone.drop(position)
                    val selectionIndex = getIndexInStringPhoneByPositionInPhone(position + 1)
                    setTextAndSelection(formatPhoneNumber(newPhone), selectionIndex)
                    onPhoneChanged(newPhone)
                }

                before in (count + 1)..1 -> {
                    val newPhone = getPhoneNumberFromString(text.toString())
                    setTextAndSelection(formatPhoneNumber(newPhone), start)
                    onPhoneChanged(newPhone)
                }
                else -> { }
            }
        }
    }

    private fun EditText.setTextAndSelection(text: String, position: Int) {
        setText(text)
        setSelection(position)
    }

    private fun formatPhoneNumber(phone: String) : String {
        val result = StringBuilder("+7 (***) ***-**-**")

        var k = 4
        for (i in phone.indices) {
            result[k] = phone[i]
            k = when {
                (k == 6) -> {
                    k + 3
                }
                (k == 11) || (k == 14) -> {
                    k + 2
                }
                else -> k + 1
            }
        }

        return result.toString()
    }

    private fun getPhoneNumberFromString(phoneString: String) : String {
        val result = StringBuilder("")

        for (i in phoneString.indices) {
            if (!phoneString[i].isDigit() || i <= 2) continue
            result.append(phoneString[i])
        }

        return result.toString()
    }

    private fun getPositionInPhoneByIndexInString(index: Int): Int {
        return when (index) {
            in 0..4 -> 0
            5 -> 1
            6 -> 2
            in 7..9 -> 3
            10 -> 4
            11 -> 5
            in 12..13 -> 6
            14 -> 7
            15 -> 8
            in 16..17 -> 9
            else -> 10
        }
    }

    private fun getIndexInStringPhoneByPositionInPhone(position: Int): Int {
        return when (position) {
            0 -> 4
            1 -> 5
            2 -> 6
            3 -> 9
            4 -> 10
            5 -> 11
            6 -> 13
            7 -> 14
            8 -> 16
            9 -> 17
            else -> 18
        }
    }
}