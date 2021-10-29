package com.example.biometricspin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.biometricspin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    
    private lateinit var binding : ActivityMainBinding
    private val TAG = MainActivity::getLocalClassName.toString()
    private lateinit var biometricManager : BiometricManager
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        biometricManager = BiometricManager.from(this)
        checkBiometricStatus(biometricManager)

        val executor = ContextCompat.getMainExecutor(this)

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
                    goToHomeActivity()
                }

                override fun onAuthenticationFailed() {
                    //if there are any failure
                    notifyUser("Authentication Falied!")
                    super.onAuthenticationFailed()
                }
            })

        promptInfo = createPromptInfo()

        binding.btnLogin.setOnClickListener{
            biometricPrompt.authenticate(promptInfo)    
        }

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
                Log.d(TAG, "checkBiometricStatus: The user hasn't enrolled with any biometric configuration" +
                " in this device")
        }

    }

    private fun goToHomeActivity(){
        val intent = Intent(this, Pin::class.java)
        startActivity(intent)
    }

    private fun notifyUser(message: String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        val intent = Intent(this, Pin::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View) {
        TODO("Not yet implemented")
    }
}

