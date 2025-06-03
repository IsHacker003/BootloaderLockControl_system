package com.ishacker.bootloaderlockcontrol

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class Dialer : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        // Declare Here your launcher activity in Intent
        val i : Intent = Intent(context, MainActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context!!.startActivity(i);
    }
}
