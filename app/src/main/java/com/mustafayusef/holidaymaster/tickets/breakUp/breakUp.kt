package com.mustafayusef.holidaymaster.tickets.breakUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_break_up.*

class breakUp : AppCompatActivity() {
     var adult=""
    var price=""
    var AdultBaseFare:String?=null
    var AdultTaxFare=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_break_up)

       adult= intent.getStringExtra("adult")
        AdultBaseFare= intent.getStringExtra("AdultBaseFare")
        AdultTaxFare= intent.getStringExtra("AdultTaxFare")
        price= intent.getStringExtra("price")

        PriceBreak.text=price.toString()+" $"
        AdultTax.text=AdultBaseFare.toString()+" ×($adult)"
        TaxFare.text=AdultTaxFare +" ×($adult)"
    }
}
