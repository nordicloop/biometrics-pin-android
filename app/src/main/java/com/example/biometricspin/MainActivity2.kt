package com.example.biometricspin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.biometricspin.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding : ActivityMain2Binding

    private var numbersList = mutableListOf<String>()
    private var passCode = ""
    var num01 = ""
    var num02 = ""
    var num03 = ""
    var num04 = ""
    var num05 = ""
    var num06 = ""

    private lateinit var btn01: Button
    private lateinit var btn02: Button
    private lateinit var btn03: Button
    private lateinit var btn04: Button
    private lateinit var btn05: Button
    private lateinit var btn06: Button
    private lateinit var btn07: Button
    private lateinit var btn08: Button
    private lateinit var btn09: Button
    private lateinit var btn00: Button
    private lateinit var btnClear: Button
    private lateinit var btnFingerPrint: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initializedComponents()



    }

    private fun initializedComponents() {
        btn01 = binding.btn01
        btn02 = binding.btn02
        btn03 = binding.btn03
        btn04 = binding.btn04
        btn05 = binding.btn05
        btn06 = binding.btn06
        btn07 = binding.btn07
        btn08 = binding.btn08
        btn09 = binding.btn09
        btn00 = binding.btn00
        btnClear = binding.btnClear
        btnFingerPrint = binding.btnFingerPrint

        btn01.setOnClickListener(this)
        btn02.setOnClickListener(this)
        btn03.setOnClickListener(this)
        btn04.setOnClickListener(this)
        btn05.setOnClickListener(this)
        btn06.setOnClickListener(this)
        btn07.setOnClickListener(this)
        btn08.setOnClickListener(this)
        btn09.setOnClickListener(this)
        btn00.setOnClickListener(this)
        btnClear.setOnClickListener(this)
        btnFingerPrint.setOnClickListener(this)
    }

    //match the pass, if are the same go to new activity else toast with retry again
    private fun matchPassCode(){
        if(getPassCode() == passCode){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this,"PassCode doesn't match please retry again!", Toast.LENGTH_SHORT).show()
        }
    }

    //save the pass
    private fun savePassCode(passCode: String): SharedPreferences.Editor{
        val preferences = getSharedPreferences("passcode-pref", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("passcode",passCode)
        editor.apply()

        return editor
    }

    //return the pass
    private fun getPassCode(): String {
        val preferences = getSharedPreferences("passcode-pref", Context.MODE_PRIVATE)
        return preferences.getString("passcode", "").toString()
    }

    //I paint the circle when a circle have a value
    private fun passNumber(numberList: MutableList<String>) {
        if(numberList.size == 0){
            binding.view01.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view02.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view03.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view04.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view05.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view06.setBackgroundResource(R.drawable.bg_view_bordo_oval)
        }else{
            when(numberList.size){
                0 -> {
                    num01 = numbersList[0]
                    binding.view01.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                1 -> {
                    num02 = numbersList[1]
                    binding.view02.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                2 -> {
                    num03 = numbersList[2]
                    binding.view03.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                3 -> {
                    num04 = numbersList[3]
                    binding.view04.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                4 -> {
                    num05 = numbersList[4]
                    binding.view05.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                5 -> {
                    num06 = numberList[5]
                    binding.view06.setBackgroundResource(R.drawable.bg_view_grey_oval)
                    passCode = num01 + num02 + num03 + num04 + num05 + num06
                    if(getPassCode().isEmpty()){
                        savePassCode(passCode)
                    } else {
                        matchPassCode()
                    }
                }
            }
            //END WHEN
        }
    }


    //capture one click and add your value
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_01 -> {
                numbersList.add("1")
                passNumber(numbersList)
            }
            R.id.btn_02 -> {
                numbersList.add("2")
                passNumber(numbersList)
            }
            R.id.btn_03 -> {
                numbersList.add("3")
                passNumber(numbersList)
            }
            R.id.btn_04 -> {
                numbersList.add("4")
                passNumber(numbersList)
            }
            R.id.btn_05 -> {
                numbersList.add("5")
                passNumber(numbersList)
            }
            R.id.btn_06 -> {
                numbersList.add("6")
                passNumber(numbersList)
            }
            R.id.btn_07 -> {
                numbersList.add("7")
                passNumber(numbersList)
            }
            R.id.btn_08 -> {
                numbersList.add("8")
                passNumber(numbersList)
            }
            R.id.btn_09 -> {
                numbersList.add("9")
                passNumber(numbersList)
            }
            R.id.btn_00 -> {
                numbersList.add("0")
                passNumber(numbersList)
            }
            R.id.btn_clear -> {
                numbersList.clear()
                passNumber(numbersList)
            }
            R.id.btn_finger_print -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}