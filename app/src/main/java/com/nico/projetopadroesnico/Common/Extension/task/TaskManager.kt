package br.com.livetouch.base.tasks

import android.app.Activity
import android.support.v4.app.Fragment
import android.util.Log


/**
 * Created by Avell 1513 on 13/06/2017.
 */
object TaskManager {

    val TAG = TaskManager::class.java.simpleName

    val tasks = mutableMapOf<Activity, MutableList<Task>>()
    val tasksFrag = mutableMapOf<Fragment, MutableList<Task>>()

    fun cancelTasks(force: Boolean, activity: Activity) {
        try {
            val listTasks = tasks[activity]
            cancelTasks(listTasks, force)
        } finally {

        }
    }

    fun cancelTasks(force: Boolean, fragment: Fragment) {
        try {
            val listTasks = tasksFrag[fragment]
            cancelTasks(listTasks, force)
        } finally {

        }
    }

    private fun cancelTasks(listTasks: MutableList<Task>?, force: Boolean) {
        listTasks?.forEach {
            val executando = !it.future.isDone
            if (executando) {
                if (!force && it.keepAlive) {
                    Log.v(TAG, "Task keepAlive: ${it.javaClass.simpleName}")
                } else {
                    Log.v(TAG, "Canceling task: ${it.javaClass.simpleName}")
                    it.future.cancel(true)
                    listTasks.remove(it)
                }
            }
        }
    }

    fun onDestroy(activity: Activity) {
        cancelTasks(true, activity)
    }

    fun onDestroy(fragment: Fragment) {
        cancelTasks(true, fragment)
    }
}