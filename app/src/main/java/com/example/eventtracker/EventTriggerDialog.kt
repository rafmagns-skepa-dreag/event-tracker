package com.example.eventtracker

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class EventTriggerDialogFragment : DialogFragment() {
    internal lateinit var listener: EventTriggerDialogListener

    interface EventTriggerDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as EventTriggerDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement EventTriggerDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            // Inflate and set the layout view for the dialog
            // Pass null as the parent view because it's going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_extra_data, null))
                .setPositiveButton(R.string.button_send,
                    DialogInterface.OnClickListener { dialog, id ->
                        // send data
                        print(id)
                        listener.onDialogPositiveClick(this)
                        getDialog()?.dismiss()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogNegativeClick(this)
                        getDialog()?.cancel()
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}