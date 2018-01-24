package br.com.livetouch.base.tasks

import java.util.*
import java.util.concurrent.Future

/**
 * Created by Avell 1513 on 13/06/2017.
 */
class Task(val future: Future<Unit>, val keepAlive: Boolean, val id: Long = Date().time)