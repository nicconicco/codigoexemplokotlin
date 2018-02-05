package com.nico.projetopadroesnico.Features.Mockito.Activity

import android.os.Bundle
import com.nico.projetopadroesnico.Common.Activity.BaseActivity
import com.nico.projetopadroesnico.Features.Mockito.Presenter.ChatContract
import com.nico.projetopadroesnico.R

class ChatActivity : BaseActivity(), ChatContract.View {

    override fun addMessage(msg: String?) {
    }

    override fun enableSendButton() {
    }

    override fun disableSendButton() {
    }

    override fun clearMessageInput() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }
}
