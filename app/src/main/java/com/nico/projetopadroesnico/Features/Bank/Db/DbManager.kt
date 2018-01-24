package com.mobologicplus.kotlinmvpandroid.db

import android.content.Context
import br.com.livroandroid.carros.extensions.toJson
import com.nico.projetopadroesnico.Application.NicoApplication
import com.nico.projetopadroesnico.Features.Bank.Db.DbHelper
import com.nico.projetopadroesnico.Features.Bank.Model.User
import io.reactivex.Observable
import org.jetbrains.anko.db.*
import timber.log.Timber

/**
 * Created by sunil on 5/30/2017.
 */
class DbManager(var dbHelper: DbHelper = DbHelper(NicoApplication.getInstance())):  DbSource{

    var idSet = 0L

    override fun detailFriend(friendsId: String): Observable<User> {
        var user : User? = null
        dbHelper.use {
            select(FriendsTable.TABLE_NAME)
                    .whereSimple("${FriendsTable.FRIEND_ID} = ?", friendsId)
                    .parseOpt(object : MapRowParser<User> {
                        override fun parseRow(columns: Map<String, Any?>): User {

                                val id = columns.getValue("friend_id")
                                val name = columns.getValue("name")
                                val email = columns.getValue("email")
                                user = User(id = id.toString(), name = name.toString(), email = email.toString())

                              return user!!
                        }
                    })
        }
        return Observable.just(user)
    }
    override fun deleteFriend(friends: User): Observable<Boolean> {
       var isDeleted : Boolean? = null
       dbHelper.use {
                try {
                    beginTransaction()
                    val result = delete(FriendsTable.TABLE_NAME, "${FriendsTable.FRIEND_ID} = {friend_id}", "friend_id" to friends.id!!) > 0

                    if (result) {
                        setTransactionSuccessful()
                        isDeleted = true
                    } else {
                        isDeleted = false
                    }

                } catch (e : Throwable) {
                    Timber.e(e)
                    isDeleted = true

                } finally {
                    endTransaction()
                }
            }
       return Observable.just(isDeleted)
    }

    override fun loadFriends(): Observable<List<User>> {
        var listFrindsUser = ArrayList<User>()
        dbHelper.use {
            select(FriendsTable.TABLE_NAME).parseList(object : MapRowParser<List<User>> {

                override fun parseRow(columns : Map<String, Any?>) : ArrayList<User> {

                    val id = columns.getValue("friend_id")
                    val name = columns.getValue("name")
                    val email = columns.getValue("email")
                    val users = User(id = id.toString(), name = name.toString(), email = email.toString())
                    listFrindsUser.add(users)

                    return listFrindsUser
                }
            })
        }
        return Observable.just(listFrindsUser)
    }


    override fun deleteFriendInList(friends: User): Boolean {
        try {
            val context = dbHelper.context
            return deletePost(context, friends)
        } catch (e : Throwable) {
            Timber.e(e)
        }

        return false
    }

    private fun deletePost(context: Context, user: User): Boolean = dbHelper.use {

        try {
            beginTransaction()
            val result = delete(FriendsTable.TABLE_NAME,
                    "${FriendsTable.NAME} = {${user.name}} AND ${FriendsTable.EMAIL} = {${user.email}}")

            if (result > 0) {
                setTransactionSuccessful()
               true
            } else {
                false
            }

        } catch (e : Throwable) {
            Timber.e(e)
        } finally {
            endTransaction()
        }

        false
    }

    override fun saveFriendInList(friends: User): Boolean {
            try {
                val context = dbHelper.context
                return insertPost(context, friends)
            } catch (e : Throwable) {
                Timber.e(e)
            }

        return false
    }

    private fun updatePost(context: Context, friends: User, idSet: Long): Boolean {
        val a = dbHelper.use {
            select(FriendsTable.TABLE_NAME)
                    .whereSimple("${FriendsTable.FRIEND_ID} = ?", idSet.toString())
                    .parseOpt(object : MapRowParser<User> {
                        override fun parseRow(columns: Map<String, Any?>): User {

                            val id = columns.getValue("friend_id")
                            val name = columns.getValue("name")
                            val email = columns.getValue("email")
                            val user = User(id = id.toString(), name = name.toString(), email = email.toString())
                            return user
                        }
                    })
        }

        a?.let {
            Timber.v("achou = ${a.toJson()}")
            friends.id = idSet.toString()

            if(deletePost(context, friends)) {
                return insertPost(context, friends)
            }

            return false
        }


        return false
    }

    fun insertPost(context: Context, friends: User) : Boolean = dbHelper.use {
        try {
            beginTransaction()
            val insertedId = insert(FriendsTable.TABLE_NAME, FriendsTable.FRIEND_ID to friends.id, FriendsTable.NAME to friends.name, FriendsTable.EMAIL to friends.email)
            if (insertedId != -1L) {
                idSet = insertedId
                setTransactionSuccessful()
                true
            } else {
                false
                throw RuntimeException("Fail to insert")
            }
        } finally {
            endTransaction()
        }
    }

    fun updateUser(context: Context, user: User, oldUser: User) {
        val oldUserFind = dbHelper.use {
            select(FriendsTable.TABLE_NAME)
                    .whereSimple("${FriendsTable.NAME} = ? AND ${FriendsTable.EMAIL} = ?", oldUser.name.toString(), oldUser.email.toString())
                    .parseOpt(object : MapRowParser<User> {
                        override fun parseRow(columns: Map<String, Any?>): User {

                            val id = columns.getValue("friend_id")
                            val name = columns.getValue("name")
                            val email = columns.getValue("email")
                            return  User(id = id.toString(), name = name.toString(), email = email.toString())
                        }
                    })
        }

        oldUserFind?.let {
            if(deletePost(context, it)) {
                if(insertPost(context, user)) {
                    Timber.v("add novo objeto")
                }
            }
        }
    }
}