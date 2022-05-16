package com.arifwidayana.challangechapter6.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "name_user")
    var name: String?,
    @ColumnInfo(name = "profile_user")
    var profile: String?,
    @ColumnInfo(name = "email_user")
    var email: String?,
    @ColumnInfo(name = "age")
    var age: Int?,
    @ColumnInfo(name = "phone_number_user")
    var phone_number: String?,
    @ColumnInfo(name = "username_user")
    var username: String?,
    @ColumnInfo(name = "password_user")
    var password: String?
)