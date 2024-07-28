package com.afivadnan.y

import androidx.lifecycle.ViewModel

class FriendViewModel (private val friendDao: FriendDao) : ViewModel(){
    fun getFriend() = friendDao.getAll()
    suspend fun insertFriend(data:Friend){
        friendDao.insert(data)
    }

    suspend fun updateFriend(data: Friend) {
        friendDao.update(data)
    }

    suspend fun deleteFriend(data: Friend) {
        friendDao.delete(data)
    }
}