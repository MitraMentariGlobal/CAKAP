package co.id.cakap.utils.dialog;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import co.id.cakap.R;
import co.id.cakap.utils.Utils;

public class BottomDialogActivity extends AppCompatActivity {

    public BottomSheetBehavior mBehavior;
    public BottomSheetDialog mBottomSheetDialog;

    public void bottomSheetAlert(Drawable drawable, String message) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        if(mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }

        final View view = getLayoutInflater().inflate(R.layout.activity_bottom_dialog, null);
        TextView textView = view.findViewById(R.id.message);
        TextView close = view.findViewById(R.id.close);
        textView.setText(message);
        close.setOnClickListener(view1 -> {
            if(mBottomSheetDialog != null) {
                mBottomSheetDialog.dismiss();
            }
        });

        LinearLayout linearLayout = view.findViewById(R.id.linearBackground);
        ImageView circleImageView = view.findViewById(R.id.vendor_image);
        circleImageView.setImageDrawable(drawable);

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // set background transparent
        ((View) view.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));


        if (!Utils.isAppInBackground(this)) {
            mBottomSheetDialog.show();
        }
        //mBottomSheetDialog.setOnDismissListener(dialog -> mBottomSheetDialog = null);
    }
}
