package com.example.businesscard.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.ui.MainActivity
import com.example.businesscard.R
import com.example.businesscard.adapter.MyCardAdapter
import com.example.businesscard.ui.BusinessCardViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_all_cards.*

class AllCardsFragment : Fragment(R.layout.fragment_all_cards) {

    lateinit var viewModel: BusinessCardViewModel
    val myCardAdapter = MyCardAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // BUSINESS CARD VIEW MODEL
        viewModel = (activity as MainActivity).viewModel
        viewModel.getYourCards()

        // SCAN FAB BUTTON CLICK LISTENER
        scanFabBtn.setOnClickListener {
            findNavController().navigate(R.id.action_allCardsFragment_to_scanCardQrFragment)
        }

        // RecyclerView :
        yourCardRecyclerView.adapter = myCardAdapter
        yourCardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getYourCards.observe(viewLifecycleOwner, Observer {
            myCardAdapter.setData(it)
        })

        // SWIPE LOGIC IMPLEMENT
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val businessCard = myCardAdapter.myBusinessCardList[position]

                // ACTION FOR SWIPE :
                if (direction == ItemTouchHelper.LEFT) {
                    viewModel.delete(businessCard)
                    Snackbar.make(view, "Successfully deleted", Snackbar.LENGTH_SHORT)
                        .apply {
                            setAction("Undo") {
                                viewModel.insert(businessCard)
                            }
                            show()
                        }
                } else {
                    val action = AllCardsFragmentDirections.actionAllCardsFragmentToQrCodeFragment(
                        businessCard
                    )
                    findNavController().navigate(action)
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(yourCardRecyclerView)
        }
    }
}