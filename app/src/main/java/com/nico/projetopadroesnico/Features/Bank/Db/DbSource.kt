package com.mobologicplus.kotlinmvpandroid.db

import com.nico.projetopadroesnico.Features.Bank.Model.User
import io.reactivex.Observable

/**
 * Created by sunil on 5/30/2017.
 */
interface DbSource {

    fun saveFriendInList(friends: User) : Boolean
    fun deleteFriendInList(friends: User) : Boolean

    fun loadFriends() : Observable<List<User>>

    fun detailFriend(friendsId : String) : Observable<User>

    fun deleteFriend(friends: User) : Observable<Boolean>
}