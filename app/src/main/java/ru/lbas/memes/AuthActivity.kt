package ru.lbas.memes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import ru.lbas.memes.network.NetworkService
import ru.lbas.memes.network.ServerApi
import ru.lbas.memes.network.model.LoginRequest
import ru.lbas.memes.network.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes

class AuthActivity : AppCompatActivity() {
    private lateinit var authBtn: Button
    private lateinit var tokenView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var passTextFieldBoxes: TextFieldBoxes
    private lateinit var textFieldBoxes: TextFieldBoxes
    private lateinit var editPassword_edit: ExtendedEditText
    private lateinit var textfieldLogin_edit: ExtendedEditText
    companion object{
       private var count: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authBtn = findViewById(R.id.b_buttonlogin)
        progressBar = findViewById(R.id.progress_bar)
        editPassword_edit = findViewById(R.id.editPassword_edit)
        textfieldLogin_edit = findViewById(R.id.textfieldLogin_edit)
        textFieldBoxes = findViewById(R.id.textfieldLogin)
        authBtn.setOnClickListener {


            if (textfieldLogin_edit.length() == 0 && editPassword_edit.length() != 0) {
             textFieldBoxes.setError("Поле не может быть пустым", false)
         }
            else if (textfieldLogin_edit.length() != 0 && editPassword_edit.length() == 0) {
                passTextFieldBoxes.setError("Поле не может быть пустым", false)
                return@setOnClickListener
            }
            if (editPassword_edit.length() == 0 && textfieldLogin_edit.length() != 0) {
                passTextFieldBoxes.setError("Поле не может быть пустым", false)
            }
            else if (editPassword_edit.length() != 0 && textfieldLogin_edit.length() == 0){
                textFieldBoxes.setError("Поле не может быть пустым", false)
                return@setOnClickListener
            }
            if (textfieldLogin_edit.length() == 0 && editPassword_edit.length() == 0){
                textFieldBoxes.setError("Поле не может быть пустым", false)
                passTextFieldBoxes.setError("Поле не может быть пустым", false)
                return@setOnClickListener
            }

            authorization()
            Handler().postDelayed({ progressBarNo() }, 300)
            }
        tokenView = findViewById(R.id.tv_tokenview)
        passTextFieldBoxes = findViewById(R.id.textfieldPassword)
        // passTextFieldBoxes.setHelperText("serwe")
        // passTextFieldBoxes.isShown()

        passTextFieldBoxes.endIconImageButton.setOnClickListener{

            if (count == 1) {
                passTextFieldBoxes.setEndIcon(R.drawable.baseline_visibility_white_24dp)
                editPassword_edit.setTransformationMethod(null)
                editPassword_edit.setSelection(editPassword_edit.length())
                count--
            }
            else {
                passTextFieldBoxes.setEndIcon(R.drawable.baseline_invisibility_white_24dp)
                editPassword_edit.setTransformationMethod(PasswordTransformationMethod())
                editPassword_edit.setSelection(editPassword_edit.length())
                count++
            }
        }
    }

    fun progressBarView(){
        progressBar.setVisibility(ProgressBar.VISIBLE)
    }
    fun progressBarNo(){
        progressBar.setVisibility(ProgressBar.INVISIBLE)
    }

    private fun authorization() {
        val retroServise = NetworkService.createInstance().create(ServerApi::class.java)
        progressBarView()
        retroServise.autorization(LoginRequest("login", "password"))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                }
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    tokenView.text = response.body()?.accessToken
                }
            })
    }
}
