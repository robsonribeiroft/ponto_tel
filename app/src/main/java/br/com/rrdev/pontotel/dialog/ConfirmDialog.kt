package br.com.rrdev.pontotel.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.rrdev.pontotel.R
import kotlinx.android.synthetic.main.dialog_confirm.*

class ConfirmDialog: DialogFragment() {

    var confirmResult: (result: Boolean)->Unit = {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_confirm.setOnClickListener {
            confirmResult(true)
            dismiss()
        }

        btn_cancel.setOnClickListener {
            confirmResult(false)
            dismiss()
        }
    }
}