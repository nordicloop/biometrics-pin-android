package com.example.biometricspin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.biometricspin.databinding.ActivityMain2Binding


class Pin : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding : ActivityMain2Binding

    private var numbersList : MutableList<String> = mutableListOf()
    private var passCode = ""
    var num01 = ""
    var num02 = ""
    var num03 = ""
    var num04 = ""
    var num05 = ""
    var num06 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    //match the pass, if are the same go to new activity else toast with retry again
    private fun matchPassCode(){
        if(getPassCode().equals(passCode)){
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
        editor.commit()

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
                1 -> {
                    num01 = numbersList.get(0)
                    binding.view01.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                2 -> {
                    num02 = numbersList.get(1)
                    binding.view02.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                3 -> {
                    num03 = numbersList.get(2)
                    binding.view03.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                4 -> {
                    num04 = numbersList.get(3)
                    binding.view04.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                5 -> {
                    num05 = numbersList.get(4)
                    binding.view05.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                6 -> {
                    num06 = numberList.get(5)
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
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_01-> {
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