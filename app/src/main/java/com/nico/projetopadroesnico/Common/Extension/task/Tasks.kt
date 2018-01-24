@file:Suppress("DEPRECATION")

package com.cognizant.dor.Common.Extensions.task

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.support.v4.app.Fragment
import br.com.livetouch.base.tasks.Task
import br.com.livetouch.base.tasks.TaskManager
import com.nico.projetopadroesnico.Common.Extension.isNetworkAvailable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.lang.Exception


/**
 * Created by Avell 1513 on 12/06/2017.
 */

fun startTask(activity: Activity, keepAlive: Boolean = false, preExecute: () -> Unit = {},
              updateView: () -> Unit = {},
              execute: () -> Unit): Boolean {
    val running = true

    activity.runOnUiThread {

    }

    preExecute()

    try {
        val task = activity.doAsync {
            try {
                if (running) {
                    execute()
                }
                uiThread {
                    try {
                        if (!activity.isFinishing) {
                            updateView()
                        }
                    } catch (exception: Exception) {
//                        treatException(exception, onError, running)
                    } finally {
//                        hideProgress(progressDialog, fragment)
                    }
                }
            } catch (exception: Exception) {
//                treatException(exception, onError, running)
            } finally {
//                hideProgress(progressDialog, fragment)
            }
        }

        val libTask = Task(task, keepAlive)
        var tasks = TaskManager.tasks[activity]
        if (tasks == null) {
            tasks = mutableListOf<Task>()
        }
        tasks.add(libTask)
        TaskManager.tasks.put(activity, tasks)

    } catch (exception: Exception) {
//        hideProgress(progressDialog, fragment)
//        treatException(exception, onError, running)
    }

    return true
}