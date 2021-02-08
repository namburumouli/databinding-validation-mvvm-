package com.example.databinding

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.databinding.databinding.ActivityMainBinding

class ViewModel(binding: ActivityMainBinding) {
        var binding=binding

    fun isValidate(): Boolean =  validateEmail() && validatePassword()

    fun setupListeners() {
        binding.run {
            email.addTextChangedListener(TextFieldValidation(email))
            password.addTextChangedListener(TextFieldValidation(password))
        }
    }


    private fun validateEmail(): Boolean {
        if (binding.email.text.toString().trim().isEmpty()) {
            binding.emailTextInputLayout.error = "Required Field!"
            binding.email.requestFocus()
            return false
        } else if (!FieldValidators.isValidEmail(binding.email.text.toString())) {
            binding.emailTextInputLayout.error = "Invalid Email!"
            binding.email.requestFocus()
            return false
        } else {
            binding.emailTextInputLayout.isErrorEnabled = false
        }
        return true
    }


    private fun validatePassword(): Boolean {
        if (binding.password.text.toString().trim().isEmpty()) {
            binding.passwordTextInputLayout.error = "Required Field!"
            binding.password.requestFocus()
            return false
        } else if (binding.password.text.toString().length < 6) {
            binding.passwordTextInputLayout.error = "password can't be less than 6"
            binding.password.requestFocus()
            return false
        } else if (!FieldValidators.isStringContainNumber(binding.password.text.toString())) {
            binding.passwordTextInputLayout.error = "Required at least 1 digit"
            binding.password.requestFocus()
            return false
        } else if (!FieldValidators.isStringLowerAndUpperCase(binding.password.text.toString())) {
            binding.passwordTextInputLayout.error =
                "Password must contain upper and lower case letters"
            binding.password.requestFocus()
            return false
        } else if (!FieldValidators.isStringContainSpecialCharacter(binding.password.text.toString())) {
            binding.passwordTextInputLayout.error = "1 special character required"
            binding.password.requestFocus()
            return false
        } else {
            binding.passwordTextInputLayout.isErrorEnabled = false
        }
        return true
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // checking ids of each text field and applying functions accordingly.
            when (view.id) {
                R.id.email -> {
                    validateEmail()
                }
                R.id.password -> {
                    validatePassword()
                }
            }
        }
    }
}