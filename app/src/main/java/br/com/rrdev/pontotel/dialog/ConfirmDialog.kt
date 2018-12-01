package br.com.rrdev.pontotel.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rrdev.pontotel.R
import br.com.rrdev.pontotel.listener.DialogListener
import kotlinx.android.synthetic.main.dialog_confirm.*

class ConfirmDialog: DialogFragment() {

    var listener: DialogListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_confirm.setOnClickListener {
            listener?.confirmResult(true)
            dismiss()
        }

        btn_cancel.setOnClickListener {
            listener?.confirmResult(false)
            dismiss()
        }
    }
}