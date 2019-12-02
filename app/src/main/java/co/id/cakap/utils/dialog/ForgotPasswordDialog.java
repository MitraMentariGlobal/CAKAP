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

public class ForgotPasswordDialog {
    private Dialog dialog;
    private Context context;

    public Dialog showDialog(Context context) {
        if (!Utils.isAppInBackground(context)) {
            this.context = context;
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
            dialog.setContentView(R.layout.forgot_password);
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
}