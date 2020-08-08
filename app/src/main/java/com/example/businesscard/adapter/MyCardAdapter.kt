package com.example.businesscard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.R
import com.example.businesscard.db.BusinessCard
import kotlinx.android.synthetic.main.card_design_layout.view.*


class MyCardAdapter : RecyclerView.Adapter<MyCardAdapter.MyViewHolder>() {

    var myBusinessCardList = emptyList<BusinessCard>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_design_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return myBusinessCardList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myBusinessCardList[position]

        when (currentItem.cardDesignCode) {
            "1" -> {
                holder.itemView.cardDesign1.visibility = View.VISIBLE
                holder.itemView.companyName1.text = currentItem.companyName
                holder.itemView.companyName1.isSelected = true
                holder.itemView.jobTitle1.text = currentItem.jobTitle
                holder.itemView.jobTitle1.isSelected = true
                holder.itemView.ownerName1.text = currentItem.ownerName
                holder.itemView.ownerName1.isSelected = true
                holder.itemView.phoneNumber1.text = currentItem.phoneNumber
                holder.itemView.phoneNumber1.isSelected = true
                holder.itemView.mailAddress1.text = currentItem.mailAddress
                holder.itemView.mailAddress1.isSelected = true
                holder.itemView.websiteUrl1.text = currentItem.websiteUrl
                holder.itemView.websiteUrl1.isSelected = true
                holder.itemView.address1.text = currentItem.address
                holder.itemView.address1.isSelected = true

                holder.itemView.cardDesign2.visibility = View.GONE
                holder.itemView.cardDesign3.visibility = View.GONE
                holder.itemView.cardDesign4.visibility = View.GONE
            }

            "2" -> {
                holder.itemView.cardDesign2.visibility = View.VISIBLE
                holder.itemView.companyName2.text = currentItem.companyName
                holder.itemView.companyName2.isSelected = true
                holder.itemView.jobTitle2.text = currentItem.jobTitle
                holder.itemView.jobTitle2.isSelected = true
                holder.itemView.ownerName2.text = currentItem.ownerName
                holder.itemView.ownerName2.isSelected = true
                holder.itemView.phoneNumber2.text = currentItem.phoneNumber
                holder.itemView.phoneNumber2.isSelected = true
                holder.itemView.mailAddress2.text = currentItem.mailAddress
                holder.itemView.mailAddress2.isSelected = true
                holder.itemView.websiteUrl2.text = currentItem.websiteUrl
                holder.itemView.websiteUrl2.isSelected = true
                holder.itemView.address2.text = currentItem.address
                holder.itemView.address2.isSelected = true

                holder.itemView.cardDesign1.visibility = View.GONE
                holder.itemView.cardDesign3.visibility = View.GONE
                holder.itemView.cardDesign4.visibility = View.GONE
            }

            "3" -> {
                holder.itemView.cardDesign3.visibility = View.VISIBLE
                holder.itemView.companyName3.text = currentItem.companyName
                holder.itemView.companyName3.isSelected = true
                holder.itemView.jobTitle3.text = currentItem.jobTitle
                holder.itemView.jobTitle3.isSelected = true
                holder.itemView.ownerName3.text = currentItem.ownerName
                holder.itemView.ownerName3.isSelected = true
                holder.itemView.phoneNumber3.text = currentItem.phoneNumber
                holder.itemView.phoneNumber3.isSelected = true
                holder.itemView.mailAddress3.text = currentItem.mailAddress
                holder.itemView.mailAddress3.isSelected = true
                holder.itemView.websiteUrl3.text = currentItem.websiteUrl
                holder.itemView.websiteUrl3.isSelected = true
                holder.itemView.address3.text = currentItem.address
                holder.itemView.address3.isSelected = true

                holder.itemView.cardDesign2.visibility = View.GONE
                holder.itemView.cardDesign1.visibility = View.GONE
                holder.itemView.cardDesign4.visibility = View.GONE
            }

            "4" -> {
                holder.itemView.cardDesign4.visibility = View.VISIBLE
                holder.itemView.companyName4.text = currentItem.companyName
                holder.itemView.companyName4.isSelected = true
                holder.itemView.jobTitle4.text = currentItem.jobTitle
                holder.itemView.jobTitle4.isSelected = true
                holder.itemView.ownerName4.text = currentItem.ownerName
                holder.itemView.ownerName4.isSelected = true
                holder.itemView.phoneNumber4.text = currentItem.phoneNumber
                holder.itemView.phoneNumber4.isSelected = true
                holder.itemView.mailAddress4.text = currentItem.mailAddress
                holder.itemView.mailAddress4.isSelected = true
                holder.itemView.websiteUrl4.text = currentItem.websiteUrl
                holder.itemView.websiteUrl4.isSelected = true
                holder.itemView.address4.text = currentItem.address
                holder.itemView.address4.isSelected = true

                holder.itemView.cardDesign2.visibility = View.GONE
                holder.itemView.cardDesign3.visibility = View.GONE
                holder.itemView.cardDesign1.visibility = View.GONE
            }
        }
    }

    fun setData(myBusinessCard: List<BusinessCard>) {
        this.myBusinessCardList = myBusinessCard
        notifyDataSetChanged()
    }
}