package com.arifwidayana.challangechapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arifwidayana.challangechapter6.model.data.UserEntity

class UserViewModel : ViewModel() {
    val user : MutableLiveData<UserEntity> = MutableLiveData()
    fun dataUser(userEntity: UserEntity){
        user.postValue(userEntity)
    }
}