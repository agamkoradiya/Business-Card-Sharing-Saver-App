package com.example.businesscard.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.businesscard.R
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.fragment_qr_code.*

class QrCodeFragment : Fragment(R.layout.fragment_qr_code) {


    private val args: QrCodeFragmentArgs by navArgs()
    private val TAG = "QrCodeFragment"
    private lateinit var content: String


    private lateinit var cardDesignCode: String
    private lateinit var companyName: String
    private lateinit var jobTitle: String
    private lateinit var ownerName: String
    private lateinit var phoneNumber: String
    private lateinit var mailAddress: String
    private lateinit var websiteUrl: String
    private lateinit var address: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, args.SendQrCodeData.toString())

        cardDesignCode = args.SendQrCodeData.cardDesignCode
        companyName = args.SendQrCodeData.companyName
        jobTitle = args.SendQrCodeData.jobTitle
        ownerName = args.SendQrCodeData.ownerName
        phoneNumber = args.SendQrCodeData.phoneNumber
        mailAddress = args.SendQrCodeData.mailAddress
        websiteUrl = args.SendQrCodeData.websiteUrl
        address = args.SendQrCodeData.address

        content = "code.fun" + "`~" +
                cardDesignCode + "`~" +
                companyName + "`~" +
                jobTitle + "`~" +
                ownerName + "`~" +
                phoneNumber + "`~" +
                mailAddress + "`~" +
                websiteUrl + "`~" +
                address + "`~" +
                "fun.code"

        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 512, 512)

        imageView.setImageBitmap(bitmap)
    }
}