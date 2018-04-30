package com.example.realmdatabaseexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add.setOnClickListener(this)
        btn_update.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
        btn_read.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add -> {
                if (isBlankField()) {
                    insertDataIntoRealmDb()
                    clearViews()
                    visibleViews()
                }
            }
            R.id.btn_update -> {
                if (isBlankField()) {
                    updateDataIntoRealmDb()
                    clearViews()
                }
            }
            R.id.btn_delete -> {
                deleteDataFromRealmDb()
                clearViews()
            }
            R.id.btn_read -> {
                if (et_id.text.toString().trim().isNotBlank()) {
                    getDataFromRealmDb()
                } else {
                    et_id.error = resources.getString(R.string.enter_user_id)
                    return
                }
            }
        }
    }

    private fun visibleViews() {
        btn_update.visibility = View.VISIBLE
        btn_delete.visibility = View.VISIBLE
    }

    private fun isBlankField(): Boolean {
        when {
            et_id.text.isBlank() -> {
                et_id.error = resources.getString(R.string.enter_user_id)
                return false
            }
            et_name.text.isBlank() -> {
                et_name.error = resources.getString(R.string.enter_user_name)
                return false
            }
            et_emailId.text.isBlank() -> {
                et_emailId.error = resources.getString(R.string.enter_email_id)
                return false
            }
            et_contactNo.text.isBlank() -> {
                et_contactNo.error = resources.getString(R.string.enter_contact_no)
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun clearViews() {
        et_id.setText("")
        et_name.setText("")
        et_contactNo.setText("")
        et_emailId.setText("")
    }

    private fun insertDataIntoRealmDb() {
        val user = User()

        try {
            user.userId = et_id.text.toString().trim().toInt()
            user.userName = et_name.text.toString().trim()
            user.phoneNumber = et_contactNo.text.toString().trim()
            user.emailId = et_emailId.text.toString().trim()
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        }

        UserDataStoreUtility.getInstance().insertUserData(userData = user)
        Toast.makeText(this, R.string.data_saved_successfully, Toast.LENGTH_SHORT).show()
    }

    private fun updateDataIntoRealmDb() {
        val user = User()

        var userId: Int? = null
        try {
            user.userName = et_name.text.toString().trim()
            user.phoneNumber = et_contactNo.text.toString().trim()
            user.emailId = et_emailId.text.toString().trim()
            userId = et_id.text.toString().trim().toInt()
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        }

        UserDataStoreUtility.getInstance().updateUserData(user, userId)
        Toast.makeText(this, R.string.data_updated_successfully, Toast.LENGTH_SHORT).show()
    }

    private fun getDataFromRealmDb() {
        var user: User? = null
        try {
            user = UserDataStoreUtility.getInstance().getUserDataByUserId(et_id.text.toString().trim().toInt())
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        }

        tv_name.text = user?.userName
        tv_emailId.text = user?.emailId
        tv_contactNo.text = user?.phoneNumber
    }

    private fun deleteDataFromRealmDb() {
        if (et_id.text.toString().trim().isNotBlank()) {
            UserDataStoreUtility.getInstance().deleteUserDataByPhoneNumber(et_id.text.toString().trim().toInt())
            Toast.makeText(this, R.string.data_deleted, Toast.LENGTH_SHORT).show()
        } else {
            et_id.error = resources.getString(R.string.enter_contact_no)
            return
        }
    }
}

