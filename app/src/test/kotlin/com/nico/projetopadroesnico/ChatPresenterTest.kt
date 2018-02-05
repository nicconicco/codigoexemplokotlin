package com.nico.projetopadroesnico

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.any
import com.nico.projetopadroesnico.Features.Mockito.Presenter.ChatContract
import com.nico.projetopadroesnico.Features.Mockito.Presenter.ChatPresenter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

@Suppress("IllegalIdentifier")
class ChatPresenterTest {

    val MESSAGE = "Este eh um texto normal"

    lateinit var chatPresenter : ChatContract.Presenter
    lateinit var chatView : ChatContract.View

    @Before
    fun setup() {
        chatView = mock()
        chatPresenter = mock()
        chatPresenter = ChatPresenter()
        chatPresenter.attachView(chatView)
    }

    @After
    fun dispose() {
        chatPresenter.detachView(chatView)
    }

    @Test
    fun `sendMessage_NullString_NoMessageSent`(){
        chatPresenter.sendMessage(null)
        verify(chatView, never()).addMessage(null)
    }

    @Test
    fun `sendMessage_EmptyString_NoMessageSent`(){
        chatPresenter.sendMessage("")
        verify(chatView, never()).addMessage(any())
    }

    @Test
    fun `sendMessage_NormalString_MessageSent`(){
        chatPresenter.sendMessage(MESSAGE)
        verify(chatView).addMessage(MESSAGE)
    }

    //----------------------------------------------------------//
    @Test
    fun `messageInputTextChanged_NullString_SendButtonDisable`(){
        chatPresenter.messageInputTextChanged(null)
        verify(chatView).disableSendButton()
    }

    @Test
    fun `messageInputTextChanged_EmptyString_SendButtonDisable`(){
        chatPresenter.messageInputTextChanged("")
        verify(chatView).disableSendButton()
    }

    @Test
    fun `messageInputTextChanged_NormalString_SendButtonEnable`(){
        chatPresenter.messageInputTextChanged(MESSAGE)
        verify(chatView).enableSendButton()
    }
}