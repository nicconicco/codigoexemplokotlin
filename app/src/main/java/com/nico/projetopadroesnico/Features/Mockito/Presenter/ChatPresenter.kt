package com.nico.projetopadroesnico.Features.Mockito.Presenter

class ChatPresenter : ChatContract.Presenter {

    private var view : ChatContract.View? = null

    override fun detachView(view: ChatContract.View) {
        this.view = null
    }

    override fun attachView(view: ChatContract.View) {
        this.view = view
    }

    override fun destroy() {
    }

    override fun sendMessage(msg: String?) {
        msg?.let {
            if(it.isNotEmpty()) {
                val msg = it
                view?.let {
                    view!!.addMessage(msg)
                }
            }
        }
    }

    override fun messageInputTextChanged(msg: String?) {
        if(msg == null || msg.isEmpty()) {
            view?.let {
                it.disableSendButton()
            }
        } else {
            view?.let {
                it.enableSendButton()
            }
        }
    }
}