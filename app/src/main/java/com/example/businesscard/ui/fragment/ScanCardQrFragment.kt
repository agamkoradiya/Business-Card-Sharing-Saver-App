package com.example.businesscard.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.businesscard.R
import com.example.businesscard.db.BusinessCard
import com.example.businesscard.ui.BusinessCardViewModel
import com.example.businesscard.ui.MainActivity

class ScanCardQrFragment : Fragment(R.layout.fragment_scan_card_qr) {

    private lateinit var viewModel: BusinessCardViewModel
    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView
    private var receivedContent: String = ""
    private val REQUEST_CODE = 111


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = (activity as MainActivity).viewModel
        scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        codeScanner = CodeScanner(requireContext(), scannerView)

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
        setupPermissions()
    }

    private fun scanNow() {
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            activity?.runOnUiThread {
                Log.d("Scan", "onViewCreated: " + it.text)
                receivedContent = it.text
                insertReceivedContentIntoDb()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            activity?.runOnUiThread {
                Log.d("TAG", "onViewCreated: " + it.message)
                Toast.makeText(requireContext(), "Try Again", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertReceivedContentIntoDb() {
        Log.d("Scan", "onViewCreated: " + receivedContent)

        val splitWords = receivedContent.split("`~")
        Log.d("Scan", splitWords.toString())

        // INSERTING NEW CARD FROM SCANNING
        if (splitWords[0] == "code.fun" && splitWords[9] == "fun.code") {
            viewModel.insert(
                BusinessCard(
                    splitWords[1],
                    false,
                    splitWords[2],
                    splitWords[3],
                    splitWords[4],
                    splitWords[5],
                    splitWords[6],
                    splitWords[7],
                    splitWords[8]
                )
            )

            //Go TO HOME PAGE :
            findNavController().navigate(R.id.action_scanCardQrFragment_to_allCardsFragment)

        } else {
            Toast.makeText(requireContext(), "Scan only our app's QR code", Toast.LENGTH_LONG)
                .show()
        }

    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
    // FOR PERMISSION

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        } else {
            codeScanner.startPreview()
            scanNow()
        }
    }

    private fun makeRequest() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", "Permission has been denied by user")
                    findNavController().navigate(R.id.action_scanCardQrFragment_to_allCardsFragment)

                } else {
                    Log.i("TAG", "Permission has been granted by user")
                    codeScanner.startPreview()
                    scanNow()
                }
            }
        }
    }
}