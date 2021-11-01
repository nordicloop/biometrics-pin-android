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


class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMain2Binding

    private val numbersList: MutableList<String>  = ArrayList()

    private var passCode = ""
    var num01 = ""
    var num02 = ""
    var num03 = ""
    var num04 = ""
    var num05 = ""
    var num06 = ""

    lateinit var btn_01 :Button
    lateinit var btn_02 :Button
    lateinit var btn_03 :Button
    lateinit var btn_04 :Button
    lateinit var btn_05 :Button
    lateinit var btn_06 :Button
    lateinit var btn_07 :Button
    lateinit var btn_08 :Button
    lateinit var btn_09 :Button
    lateinit var btn_00 :Button
    lateinit var btn_clear :Button
    lateinit var btn_finger_print :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.btn01.setOnClickListener{
            numbersList.add("1").toString()
            passNumber(numbersList)
        }
        binding.btn02.setOnClickListener {
            numbersList.add("2").toString()
            passNumber(numbersList)
        }
        binding.btn03.setOnClickListener {
            numbersList.add("3").toString()
            passNumber(numbersList)
        }
        binding.btn04.setOnClickListener {
            numbersList.add("4").toString()
            passNumber(numbersList)
        }
        binding.btn05.setOnClickListener {
            numbersList.add("5").toString()
            passNumber(numbersList)
        }
        binding.btn06.setOnClickListener {
            numbersList.add("6").toString()
            passNumber(numbersList)
        }
        binding.btn07.setOnClickListener {
            numbersList.add("7").toString()
            passNumber(numbersList)
        }
        binding.btn08.setOnClickListener {
            numbersList.add("8").toString()
            passNumber(numbersList)
        }
        binding.btn09.setOnClickListener {
            numbersList.add("9").toString()
            passNumber(numbersList)
        }
        binding.btn00.setOnClickListener {
            numbersList.add("0").toString()
            passNumber(numbersList)
        }
        binding.btnClear.setOnClickListener {
            numbersList.clear()
            passNumber(numbersList)
        }
        binding.btnFingerPrint.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/

        initialidezComponents()
    }

    private fun initialidezComponents() {
        btn_01 = findViewById(R.id.btn_01)
        btn_02 = findViewById(R.id.btn_02)
        btn_03 = findViewById(R.id.btn_03)
        btn_04 = findViewById(R.id.btn_04)
        btn_05 = findViewById(R.id.btn_05)
        btn_06 = findViewById(R.id.btn_06)
        btn_07 = findViewById(R.id.btn_07)
        btn_08 = findViewById(R.id.btn_08)
        btn_09 = findViewById(R.id.btn_09)
        btn_00 = findViewById(R.id.btn_00)
        btn_clear = findViewById(R.id.btn_clear)
        btn_finger_print = findViewById(R.id.btn_finger_print)

        btn_01.setOnClickListener(this)
        btn_02.setOnClickListener(this)
        btn_03.setOnClickListener(this)
        btn_04.setOnClickListener(this)
        btn_05.setOnClickListener(this)
        btn_06.setOnClickListener(this)
        btn_07.setOnClickListener(this)
        btn_08.setOnClickListener(this)
        btn_09.setOnClickListener(this)
        btn_00.setOnClickListener(this)
        btn_clear.setOnClickListener(this)
        btn_finger_print.setOnClickListener(this)

    }

    //match the pass, if are the same go to new activity else toast with retry again
    private fun matchPassCode() {
        if (getPassCode().equals(passCode)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "PassCode doesn't match please retry again!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    //save the pass
    private fun savePassCode(passCode: String): SharedPreferences.Editor {
        val preferences = getSharedPreferences("passcode-pref", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("passcode", passCode)
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
        if (numberList.isEmpty()) {
            binding.view01.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view02.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view03.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view04.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view05.setBackgroundResource(R.drawable.bg_view_bordo_oval)
            binding.view06.setBackgroundResource(R.drawable.bg_view_bordo_oval)
        } else {
            when (numberList.size) {
                0 -> {
                    num01 = numbersList[0].toString()
                    binding.view01.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                1 -> {
                    num02 = numbersList[1].toString()
                    binding.view02.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                2 -> {
                    num03 = numbersList[2].toString()
                    binding.view03.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                3 -> {
                    num04 = numbersList[3].toString()
                    binding.view04.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                4 -> {
                    num05 = numbersList[4].toString()
                    binding.view05.setBackgroundResource(R.drawable.bg_view_grey_oval)
                }
                5 -> {
                    num06 = numbersList[5].toString()
                    binding.view06.setBackgroundResource(R.drawable.bg_view_grey_oval)
                    passCode = num01 + num02 + num03 + num04 + num05 + num06
                    if (getPassCode().isEmpty()) {
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
    @Override
    override fun onClick(v: View){
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
                numbersList.add("3").toString()
                passNumber(numbersList)
            }
            R.id.btn_04 -> {
                numbersList.add("4").toString()
                passNumber(numbersList)
            }
            R.id.btn_05-> {
                numbersList.add("5").toString()
                passNumber(numbersList)
            }
            R.id.btn_06 -> {
                numbersList.add("6").toString()
                passNumber(numbersList)
            }
            R.id.btn_07 -> {
                numbersList.add("7").toString()
                passNumber(numbersList)
            }
            R.id.btn_08 -> {
                numbersList.add("8").toString()
                passNumber(numbersList)
            }
            R.id.btn_09 -> {
                numbersList.add("9").toString()
                passNumber(numbersList)
            }
            R.id.btn_00 -> {
                numbersList.add("0").toString()
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

