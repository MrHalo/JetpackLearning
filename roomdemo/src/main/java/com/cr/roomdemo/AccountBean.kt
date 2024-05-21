package com.cr.roomdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Account")
data class AccountBean(
    @PrimaryKey(autoGenerate = true)
    val accountId: Int = 0,
    @ColumnInfo(name = "_loginAccount")
    var loginAccount:String,
    @ColumnInfo(name = "_loginPassword")
    var loginPassword:String,
    @ColumnInfo(name="_loginIpAddress")
    var loginIpAddress:String

)