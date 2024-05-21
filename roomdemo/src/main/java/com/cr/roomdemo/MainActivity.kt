package com.cr.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cr.roomdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity_CJ"
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.btnSave.setOnClickListener {

            val loginAccount = getLoginAccount()
            var accountBean0 =
                AccountDataBase.accountDb.accountDao.findAccountByLoginAccount(loginAccount)
            accountBean0?.let {
                Toast.makeText(this, "账号已存在", Toast.LENGTH_LONG).show()

                if (it.loginPassword == getLoginPWD()) {
                    Log.d(TAG, "密码相同")

                } else {
                    Log.d(TAG, "密码不同")
                    it.loginPassword = getLoginPWD()
                    AccountDataBase.accountDb.accountDao.updateAccount(accountBean0)
                }


            } ?: let {

                // 例子：return a ?: b, 如果a为null返回b，a不为null，返回a
                Toast.makeText(this, "账号不存在", Toast.LENGTH_LONG).show()
                val accountBean = AccountBean(
                    loginAccount = getLoginAccount(),
                    loginPassword = getLoginPWD(),
                    loginIpAddress = getLoginIpAd()
                )
                AccountDataBase.accountDb.accountDao.insertAccount(accountBean)
            }
        }

        activityMainBinding.btnQuery.setOnClickListener {
            var list = AccountDataBase.accountDb.accountDao.loadAccountList()
            list?.let {
                activityMainBinding.tvResult.text = ""
                for (index in it.indices) {
                    activityMainBinding.tvResult.append("账号：${it[index].loginAccount}\t")
                    activityMainBinding.tvResult.append("密码：${it[index].loginPassword}\t")
                    activityMainBinding.tvResult.append("IP：${it[index].loginIpAddress}\n")
                }
            }
        }

        activityMainBinding.btnDelete.setOnClickListener {
            var list = AccountDataBase.accountDb.accountDao.loadAccountList()
            list?.let {
                for (i in it.indices) {
                    AccountDataBase.accountDb.accountDao.deleteAccount(it[i])
                }
            }
        }
    }

    private fun getLoginIpAd():String{
        return activityMainBinding.etLoginIP.text.toString().trim()
    }

    private fun getLoginPWD(): String {

        return activityMainBinding.etLoginPassword.text.toString().trim()
    }

    private fun getLoginAccount(): String {

        return activityMainBinding.etLoginAccount.text.toString().trim()
    }
}