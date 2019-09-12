package com.mustafayusef.holidaymaster.tickets.FareRules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Adapters.FareDescription
import com.mustafayusef.holidaymaster.Models.ResultRule
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_description.*

class description : AppCompatActivity() {
    var description: ResultRule?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        description=intent.getSerializableExtra("des") as ResultRule
        FareDesc.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
        FareDesc.adapter= FareDescription(this,description)
    }
}
