package com.gmail.aitama90p.ds

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class ContactListService : Service() {
    private val binder = ContactListBinder()

    private val contacts = listOf(Contact(
        id = "93486752",
        photo = R.drawable.user,
        name = "Вася",
        phone = "+79293939494",
        phoneTwo = "+79838383834",
        email = "email@.com",
        emailTwo = "email2@.com",
        description = "Единственный контакт(пока)"
    ))

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    inner class ContactListBinder: Binder() {
        fun getService():ContactListService = this@ContactListService
    }

    fun showContactList(contactListFragment: WeakReference<ContactListFragment>) {
        thread {
            contactListFragment.get()?.setData(contacts)
        }
    }

    fun showContactDetails(contactDetailsFragment: WeakReference<ContactDetailsFragment>) {
        thread {
            contactDetailsFragment.get()?.setData(contacts)
        }
    }

    interface ContactListServiceInterface {
        fun getService(): ContactListService?
    }
}