package cn.gridlife.mydemo.b;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.chenenyu.router.annotation.Route;

import butterknife.BindView;
import cn.gridlife.mydemo.R;

/**
 * ProjectName TeleText_Android
 * PackageName cn.gridlife.mydemo.second
 * Created by damaren_bzb on 2017/6/30.
 */
@Route("MyPopupWindow")
public class MyPopuWindow extends Activity {
    @BindView(R.id.btn_b_action)
    Button btnAction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_popupwindow);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow popupWindow=new PopupWindow();
                popupWindow.setContentView(findViewById(R.layout.view_b_popupwindow));
//                popupWindow.setAnimationStyle();

            }
        });
    }
}
