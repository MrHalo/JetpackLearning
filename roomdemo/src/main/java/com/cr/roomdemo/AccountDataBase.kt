package com.cr.roomdemo

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [AccountBean::class], version = 2)
abstract class AccountDataBase:RoomDatabase() {
    //
    abstract val accountDao:AccountDao

    companion object{
        val accountDb:AccountDataBase by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            Room.databaseBuilder(MyApplication.instance,
            AccountDataBase::class.java, "account.db").allowMainThreadQueries().
                addMigrations(migration_1_2).build()
            // fallbackToDestructiveMigration会删除原数据，开发过程中慎用
            // addMigrations 方法传入的是一个数组，如果之后数据库从版本2升级到了版本3，新增一个migration策略即可
        }

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("Alter table Account add column _loginIpAddress TEXT not null default ''")
            }

        }
    }



}