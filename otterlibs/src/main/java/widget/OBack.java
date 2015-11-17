package widget;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Wilbur on 15/10/6.
 */
public class OBack extends View {

    private static OnClickListener sOnClickListener;

    static {
        sOnClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                Activity activity = (Activity) v.getContext();
                try {
                    if (activity instanceof FragmentActivity) {

                        FragmentActivity frgmntActvty = (FragmentActivity) activity;
                        int fragments =
                                frgmntActvty.getSupportFragmentManager().getBackStackEntryCount();

                        if (fragments > 1) {
                            frgmntActvty.getSupportFragmentManager().popBackStack();
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                activity.finish();
            }
        };
    }

    public OBack(Context context) {
        super(context);
        init();
    }

    public OBack(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                ((Activity) getContext()).onBackPressed();
            }
        });
    }
}
