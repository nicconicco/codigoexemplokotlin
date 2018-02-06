package com.nico.projetopadroesnico.Features.RobotEletronic.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nico.projetopadroesnico.R
import android.content.Intent
import kotlinx.android.synthetic.main.activity_robot_electronic.*
import com.nico.projetopadroesnico.Features.Login.Activity.LoginActivity




class RobotElectronicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robot_electronic)


        login.setOnClickListener {
            startActivity(Intent(this@RobotElectronicActivity, LoginActivity::class.java))
        }
    }
}
