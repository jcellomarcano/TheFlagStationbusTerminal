package com.example.thefalgbusstop.utils

import android.content.Context
import android.graphics.Color
import cn.pedant.SweetAlert.SweetAlertDialog

object Utils {


    fun generateWarningTypeSweetAlertDialog(
        context: Context?, title: String?, content: String?,
        confirm_button_text: String?, cancel_button_text: String?,
        is_cancellable: Boolean, show_cancel_button: Boolean,
    ): SweetAlertDialog {
        val alertDialog = SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
        alertDialog.titleText = title
        alertDialog.contentText = content
        alertDialog.confirmText = confirm_button_text
        alertDialog.setCancelable(is_cancellable)
        if (!show_cancel_button) alertDialog.showCancelButton(false) else alertDialog.cancelText =
            cancel_button_text
        return alertDialog
    }

    fun progressDialog(context: Context, title: String): SweetAlertDialog {
        val pDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = title
        pDialog.setCancelable(false)
        pDialog.show()
        return pDialog
    }

    fun confirmDialof(context: Context, title: String, content: String, confirmBtnText: String):SweetAlertDialog{
        val cDialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
        cDialog.titleText = title
        cDialog.contentText = content
        cDialog.contentText = confirmBtnText
        return cDialog
    }

    fun ErrorAlertDialog(
        context: Context, title: String, content: String,
        confirm_button_text: String,
        is_cancellable: Boolean, show_cancel_button: Boolean,
    ): SweetAlertDialog {
        val errorAlertDialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
        errorAlertDialog.titleText = title
        errorAlertDialog.contentText = content
        errorAlertDialog.confirmText = confirm_button_text
        errorAlertDialog.setCancelable(is_cancellable)
        errorAlertDialog.showCancelButton(show_cancel_button)
        return errorAlertDialog
    }
}