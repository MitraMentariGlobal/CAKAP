package co.id.cakap.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import co.id.cakap.R;
import co.id.cakap.utils.Utils;

public class UserConfirmationDialog {
    private Dialog dialog;
    private Context context;

    public Dialog showDialog(Context context) {
        if (!Utils.isAppInBackground(context)) {
            this.context = context;
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
            dialog.setContentView(R.layout.user_confirmation);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            setCancelDialog(true);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            if (dialog.isShowing()) {
                dialog.hide();
                dialog.show();
            } else {
                dialog.show();
            }

            return dialog;
        }
        return null;
    }

    public void setCancelDialog(boolean param) {
        dialog.setCancelable(param);
    }

    public void setTitleDialog(String param) {
        TextView textView = dialog.findViewById(R.id.txt_title);
        textView.setVisibility(View.VISIBLE);
        textView.setText(context.getResources().getString(R.string.this_transaction, param));
    }

    public void setDescDialog(String param) {
        TextView textView = dialog.findViewById(R.id.txt_desc);
        textView.setVisibility(View.VISIBLE);
        textView.setText(param);
    }

    public void setTitleDialogInvalidAccount(String param) {
        TextView textView = dialog.findViewById(R.id.txt_title);
        textView.setVisibility(View.VISIBLE);
        textView.setText(param);
    }

    public void setTitleDialog() {
        TextView textView = dialog.findViewById(R.id.txt_title);
        textView.setVisibility(View.VISIBLE);
        textView.setText(context.getResources().getString(R.string.pleasee_check_again));
    }

    public void setTitleChangeProfileDialog() {
        TextView textView = dialog.findViewById(R.id.txt_title);
        textView.setVisibility(View.VISIBLE);
        textView.setText(context.getResources().getString(R.string.pleasee_check_again_profile));
    }

    public void setTitleLogout() {
        TextView textView = dialog.findViewById(R.id.txt_title);
        textView.setVisibility(View.VISIBLE);
        textView.setText(context.getResources().getString(R.string.dialog_log_out));
    }

    public void setTitleDeleteAllNotification() {
        TextView textView = dialog.findViewById(R.id.txt_title);
        textView.setVisibility(View.VISIBLE);
        textView.setText(context.getResources().getString(R.string.dialog_delete_notification));
    }

    public void setNegativeAction() {
        TextView txtNo = dialog.findViewById(R.id.no_act_btn);
        TextView txtYes = dialog.findViewById(R.id.yes_act_btn);

        txtNo.setBackground(context.getResources().getDrawable(R.drawable.background_no_confirmation_style));
        txtNo.setTextColor(context.getResources().getColor(R.color.white));

        txtYes.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
    }

    public void setPositiveAction() {
        TextView txtNo = dialog.findViewById(R.id.no_act_btn);
        TextView txtYes = dialog.findViewById(R.id.yes_act_btn);

        txtYes.setBackground(context.getResources().getDrawable(R.drawable.background_yes_confirmation_style));
        txtYes.setTextColor(context.getResources().getColor(R.color.white));

        txtNo.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
    }

    public void setNegativeActionGreen() {
        TextView txtNo = dialog.findViewById(R.id.no_act_btn);
        TextView txtYes = dialog.findViewById(R.id.yes_act_btn);

        txtNo.setBackground(context.getResources().getDrawable(R.drawable.background_yes_confirmation_style));
        txtNo.setTextColor(context.getResources().getColor(R.color.white));

        txtYes.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
    }

    public void setTitleDialogSingle(String param) {
        TextView textView = dialog.findViewById(R.id.txt_title);
        textView.setVisibility(View.VISIBLE);
        textView.setText(param);
    }

    public void setUpdateAction(boolean isCancelDialog) {
        setCancelDialog(isCancelDialog);
        TextView txtNo = dialog.findViewById(R.id.no_act_btn);
        TextView txtYes = dialog.findViewById(R.id.yes_act_btn);

        txtNo.setVisibility(View.GONE);

        txtYes.setBackground(context.getResources().getDrawable(R.drawable.background_yes_confirmation_style));
        txtYes.setTextColor(context.getResources().getColor(R.color.white));
        txtYes.setText("Perbarui");
    }
}
