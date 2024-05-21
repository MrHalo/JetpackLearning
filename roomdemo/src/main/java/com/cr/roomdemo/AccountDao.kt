package com.cr.roomdemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccountDao {

    /**
     * 插入用户
     */
    @Insert
    fun insertAccount(accountBean: AccountBean)

    /***
     *
     * 获取用户列表
     *
     */
    @Query("select * from Account")
    fun loadAccountList():List<AccountBean>?

    /**
     * 是否已包含该用户
     *
     */
    @Query("select * from Account where _loginAccount == :loginAccount")
    fun findAccountByLoginAccount(loginAccount:String) : AccountBean?

    @Update
    fun updateAccount(accountBean: AccountBean)

    @Delete
    fun deleteAccount(accountBean: AccountBean)
}