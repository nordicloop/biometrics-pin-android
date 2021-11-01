package com.example.biometricspin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.biometric.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.biometricspin.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {

    //BIOMETRIC
    private lateinit var biometricManager : BiometricManager
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val TAG = MainActivity::getLocalClassName.toString()
    private lateinit var binding: ActivityMain2Binding

    //LIST WHIT THE PASSCODE
    private val numbersList: MutableList<String>  = ArrayList()

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

        //VALUES FOR THE FINGERPRINT
        val executor = ContextCompat.getMainExecutor(this)
        biometricManager = BiometricManager.from(this)
        checkBiometricStatus(biometricManager)
        promptInfo = createPromptInfo()

        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    //if any error come
                    notifyUser("Authentication error! :$errString")
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    //if is successful
                    notifyUser("Authentication Succes!")
                    super.onAuthenticationSucceeded(result)
                    //you are in the system
                    startActivity(Intent(this@MainActivity2,MainActivity::class.java))
                }

                override fun onAuthenticationFailed() {
                    //if there are any failure
                    notifyUser("Authentication Falied!")
                    super.onAuthenticationFailed()
                }
            })

        //CAPTURE CLICK PASSCODE
        binding.btn01.setOnClickListener{
            numbersList.add("1")
            passNumber(numbersList)
        }
        binding.btn02.setOnClickListener {
            numbersList.add("2")
            passNumber(numbersList)
        }
        binding.btn03.setOnClickListener {
            numbersList.add("3")
            passNumber(numbersList)
        }
        binding.btn04.setOnClickListener {
            numbersList.add("4")
            passNumber(numbersList)
        }
        binding.btn05.setOnClickListener {
            numbersList.add("5")
            passNumber(numbersList)
        }
        binding.btn06.setOnClickListener {
            numbersList.add("6")
            passNumber(numbersList)
        }
        binding.btn07.setOnClickListener {
            numbersList.add("7")
            passNumber(numbersList)
        }
        binding.btn08.setOnClickListener {
            numbersList.add("8")
            passNumber(numbersList)
        }
        binding.btn09.setOnClickListener {
            numbersList.add("9")
            passNumber(numbersList)
        }
        binding.btn00.setOnClickListener {
            numbersList.add("0")
            passNumber(numbersList)
        }
        binding.btnClear.setOnClickListener {
            numbersList.clear()
            passNumber(numbersList)
        }
        binding.btnFingerPrint.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }




    //match the pass, if are the same go to new activity else toast with retry again
    private fun matchPassCode() {
        if (getPassCode() == passCode) {
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
        if (numberList.size == 0){
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

    //BIOMETRIC
    private fun notifyUser(message: String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

    private fun createPromptInfo():BiometricPrompt.PromptInfo {
        //Setup title, subtitle and description on authentication dialog
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authentication required")
            .setDescription("Touch the fingerprint sensor")
            .setNegativeButtonText("PIN")
            .build()
    }
    private fun checkBiometricStatus(biometricManager: BiometricManager){
        when(biometricManager.canAuthenticate()){
            BiometricManager.BIOMETRIC_SUCCESS ->
                Log.d(TAG,"checkBiometricStatus: App can use biometric authenticate")

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Log.d(TAG, "checkBiometricStatus: No biometric features available in this device")

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Log.d(TAG, "checkBiometricStatus: Biometric features currently unavailable")

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Log.d(TAG, "checkBiometricStatus: The user hasn't enrolled with any biometric configuration in this device")

        }

    }



/*
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

    }*/
}

