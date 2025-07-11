package com.ishacker.bootloaderlockcontrol

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.button)
    }


    fun unlockBl(v: View?) {
        Runtime.getRuntime().exec("dd if=/system/etc/seccfg.bin of=/dev/block/platform/mtk-msdc.0/11230000.msdc0/by-name/seccfg")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Bootloader successfully unlocked!")
        builder.setMessage("Please reboot the phone for changes to take effect. Your data will NOT be erased.")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("OK") { dialog, which ->
            Runtime.getRuntime().exec("svc power reboot")
        }

        builder.setNegativeButton("Later") { dialog, which ->

        }
        builder.show()
        }

    override fun onClick(v: View?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("WARNING")
        builder.setMessage("If your bootloader is locked, you will not be able to boot the phone if the charger is not connected. You will get the \"red state\" error. This is due to how the Nokia 3's bootloader works. For the same reason, you also can't boot into recovery or fastboot through button combinations if the phone is running on battery. You can disconnect the charger after the phone starts playing the boot animation with the Nokia logo.")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("Continue") { dialog, which ->
            Runtime.getRuntime().exec("dd if=/dev/zero of=/dev/block/platform/mtk-msdc.0/11230000.msdc0/by-name/seccfg")
            showDialog()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->

        }
        builder.show()
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Bootloader successfully locked!")
        builder.setMessage("Please connect your charger and reboot the phone for changes to take effect. Your data will NOT be erased.")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("OK") { dialog, which ->
            Runtime.getRuntime().exec("svc power reboot")
        }

        builder.setNegativeButton("Later") { dialog, which ->

        }
        builder.show()
    }
}



