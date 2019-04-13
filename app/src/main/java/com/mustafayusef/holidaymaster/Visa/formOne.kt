package com.mustafayusef.holidaymaster.Visa

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mustafayusef.holidaymaster.R
import java.util.zip.Inflater

class formOne:Fragment(){
    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_form_one,container,false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }



    override fun onDestroy() {
        super.onDestroy()
    }


}