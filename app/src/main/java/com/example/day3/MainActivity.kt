package com.example.day3

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var topBtn2: Button
    private lateinit var name: TextInputLayout
    private lateinit var mail: TextInputLayout
    private lateinit var contact: TextInputLayout
    private lateinit var pass: TextInputLayout
    private lateinit var conpass: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        name = this.findViewById(R.id.sectxt)
        val Ename = this.findViewById<TextInputEditText>(R.id.Esectext)
        mail = this.findViewById(R.id.thirdtxt)
        contact = findViewById(R.id.sixtxt)
        pass =findViewById(R.id.fortxt)
        conpass = findViewById(R.id.fiftxt)

        fun passStrength(password: String): Any? {
            val lengthCriteria = password.length >= 8
            val uppercaseCriteria = password.any { it.isUpperCase() }
            val lowercaseCriteria = password.any { it.isLowerCase() }
            val digitCriteria = password.any { it.isDigit() }
            val specialCharCriteria = password.any { !it.isLetterOrDigit() }

            return when {
                lengthCriteria && uppercaseCriteria && lowercaseCriteria && digitCriteria && specialCharCriteria -> pass.error="Strong"
                lengthCriteria && (uppercaseCriteria || lowercaseCriteria) && digitCriteria -> pass.error="Medium"
                lengthCriteria -> pass.error="Weak"
                else -> pass.error="Very Weak"
            }
        }

        Ename.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { if(p0.toString().isEmpty()) name.error="Please Enter a Name!"
            else{
                name.error= null
                name.isErrorEnabled= false
            }}
            override fun afterTextChanged(p0: Editable?) {}
        })




        mail.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(mail.editText?.text?.isEmpty() == true) {
                    mail.error = "Please Enter a Email!"
                }
                else{
                    mail.error= null
                    mail.isErrorEnabled= false
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        contact.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(contact.editText?.text?.isEmpty() == true) {
                    contact.error = "Enter valid Number!"
                }
                else{
                    contact.error= null
                    contact.isErrorEnabled= false
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        fun passmatch(): Boolean {
            val len = pass.editText?.text?.length
            val len1 = conpass.editText?.text?.length
            if(len == len1){
                if (len != null) {
                    if(pass.editText?.text.toString() !=conpass.editText?.text.toString()){
                        return true
                    }
                }
                return false
            }
            return true
        }

        conpass.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(passmatch()) {
                    conpass.error = "Password is not matching!"
                }
                if(conpass.editText?.text?.isEmpty() == false){
                    pass.error= null
                    pass.isErrorEnabled= false
                    conpass.error= null
                    conpass.isErrorEnabled= false
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        pass.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(pass.editText?.text?.isEmpty() == true) {
                    pass.error = "Please Enter a Password!"
                }
                else{
                    pass.error= null
                    pass.isErrorEnabled= false
                }
                passStrength(pass.editText?.text.toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        fun checkdetails() : Boolean{
            if(name.editText?.text?.isEmpty()==true){
                return false
            }
            if(mail.editText?.text?.isEmpty()==true || mail.editText?.text?.length!! <=8){
                return false
            }
            if(contact.editText?.text?.isEmpty()==true || mail.editText?.text?.length!! <=9){
                return false
            }
            if(pass.editText?.text?.isEmpty()==true){
                return false
            }
            if(passmatch()){
                return false
            }
            return true
        }

        findViewById<Button>(R.id.signupBtn).setOnClickListener {
            if(checkdetails()){
                Intent(this, View_2::class.java).also {
                    it.putExtra("naan",name.editText?.text?.toString())
                    it.putExtra("email",mail.editText?.text?.toString())
                    it.putExtra("number",contact.editText?.text?.toString())
                    startActivity(it)
                }
            }
        }
        topBtn2 = this.findViewById(R.id.topBtn2)
        topBtn2.setOnClickListener{
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)

        }
    }
}