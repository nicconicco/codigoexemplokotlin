package com.nico.projetopadroesnico.Features.Mockito.Presenter

import com.nico.projetopadroesnico.Common.Presenter.BasePresenter
import com.nico.projetopadroesnico.Common.Presenter.BaseView

class ChatContract {
    interface View : BaseView {
        fun addMessage(msg: String?)
        fun enableSendButton()
        fun disableSendButton()
        fun clearMessageInput()
    }

    interface Presenter : BasePresenter<View>{
        fun sendMessage(msg: String?)
        fun messageInputTextChanged(msg: String?)
    }
}