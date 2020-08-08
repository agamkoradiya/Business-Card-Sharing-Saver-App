package com.example.businesscard.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import com.example.businesscard.R
import kotlinx.android.synthetic.main.card_design_layout.*

class SelectDesignFragment : Fragment(R.layout.fragment_select_design) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardDesign1.visibility = View.VISIBLE
        cardDesign2.visibility = View.VISIBLE
        cardDesign3.visibility = View.VISIBLE
        cardDesign4.visibility = View.VISIBLE

        companyName1.isSelected = true
        jobTitle1.isSelected = true
        ownerName1.isSelected = true
        phoneNumber1.isSelected = true
        mailAddress1.isSelected = true
        websiteUrl1.isSelected = true
        address1.isSelected = true

        companyName2.isSelected = true
        jobTitle2.isSelected = true
        ownerName2.isSelected = true
        phoneNumber2.isSelected = true
        mailAddress2.isSelected = true
        websiteUrl2.isSelected = true
        address2.isSelected = true

        companyName3.isSelected = true
        jobTitle3.isSelected = true
        ownerName3.isSelected = true
        phoneNumber3.isSelected = true
        mailAddress3.isSelected = true
        websiteUrl3.isSelected = true
        address3.isSelected = true

        companyName4.isSelected = true
        jobTitle4.isSelected = true
        ownerName4.isSelected = true
        phoneNumber4.isSelected = true
        mailAddress4.isSelected = true
        websiteUrl4.isSelected = true
        address4.isSelected = true


        cardDesign1.setOnClickListener {
            val action =
                SelectDesignFragmentDirections.actionSelectDesignFragmentToFormDataCollectionFragment(
                    "1"
                )
            view.findNavController().navigate(action)
        }

        cardDesign2.setOnClickListener {
            val action =
                SelectDesignFragmentDirections.actionSelectDesignFragmentToFormDataCollectionFragment(
                    "2"
                )
            view.findNavController().navigate(action)
        }

        cardDesign3.setOnClickListener {
            val action =
                SelectDesignFragmentDirections.actionSelectDesignFragmentToFormDataCollectionFragment(
                    "3"
                )
            view.findNavController().navigate(action)
        }

        cardDesign4.setOnClickListener {
            val action =
                SelectDesignFragmentDirections.actionSelectDesignFragmentToFormDataCollectionFragment(
                    "4"
                )
            view.findNavController().navigate(action)
        }
    }
}