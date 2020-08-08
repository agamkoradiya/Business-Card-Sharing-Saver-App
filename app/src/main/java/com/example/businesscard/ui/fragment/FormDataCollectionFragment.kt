package com.example.businesscard.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.businesscard.ui.MainActivity
import com.example.businesscard.R
import com.example.businesscard.db.BusinessCard
import com.example.businesscard.ui.BusinessCardViewModel
import kotlinx.android.synthetic.main.fragment_form_data_collection.*

class FormDataCollectionFragment : Fragment(R.layout.fragment_form_data_collection) {

    private val args: FormDataCollectionFragmentArgs by navArgs()
    private val TAG = "FormDataCollection"

    private lateinit var viewModel: BusinessCardViewModel

    private lateinit var companyNameEditTxt: String
    private lateinit var jobTitleEditTxt: String
    private lateinit var ownerNameEditTxt: String
    private lateinit var phoneNumberEditTxt: String
    private lateinit var mailAddressEditTxt: String
    private lateinit var websiteUrlEditTxt: String
    private lateinit var addressEditTxt: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        Log.d(TAG, "onViewCreated: ---> " + args.cardDesignCode)


        submitBtn.setOnClickListener {
            companyNameEditTxt = companyNameTxt.text.toString().trim()
            jobTitleEditTxt = jobTitleTxt.text.toString().trim()
            ownerNameEditTxt = ownerNameTxt.text.toString().trim()
            phoneNumberEditTxt = phoneNumberTxt.text.toString().trim()
            mailAddressEditTxt = mailAddressTxt.text.toString().trim()
            websiteUrlEditTxt = websiteUrlTxt.text.toString().trim()
            addressEditTxt = addressTxt.text.toString().trim()

            when {
                companyNameEditTxt.isEmpty() -> {
                    companyNameTxt.error = "Company Name required"
                    companyNameTxt.requestFocus()
                    return@setOnClickListener
                }
                jobTitleEditTxt.isEmpty() -> {
                    jobTitleTxt.error = "Job Title required"
                    jobTitleTxt.requestFocus()
                    return@setOnClickListener
                }
                ownerNameEditTxt.isEmpty() -> {
                    ownerNameTxt.error = "Owner Name required"
                    ownerNameTxt.requestFocus()
                    return@setOnClickListener
                }
                phoneNumberEditTxt.isEmpty() -> {
                    phoneNumberTxt.error = "Phone Number required"
                    phoneNumberTxt.requestFocus()
                    return@setOnClickListener
                }
                mailAddressEditTxt.isEmpty() -> {
                    mailAddressTxt.error = "Mail required"
                    mailAddressTxt.requestFocus()
                    return@setOnClickListener
                }
                websiteUrlEditTxt.isEmpty() -> {
                    websiteUrlTxt.error = "Website required"
                    websiteUrlTxt.requestFocus()
                    return@setOnClickListener
                }
                addressEditTxt.isEmpty() -> {
                    addressTxt.error = "Address required"
                    addressTxt.requestFocus()
                    return@setOnClickListener
                }

                else -> {
                    viewModel.insert(
                        BusinessCard(
                            args.cardDesignCode,
                            true,
                            companyNameEditTxt,
                            jobTitleEditTxt,
                            ownerNameEditTxt,
                            phoneNumberEditTxt,
                            mailAddressEditTxt,
                            websiteUrlEditTxt,
                            addressEditTxt
                        )
                    )

                    findNavController().navigate(R.id.action_formDataCollectionFragment_to_myCardsFragment)
                }
            }
        }
    }
}