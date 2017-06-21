package cn.gridlife.teletext.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gridlife.teletext.R;

/**
 * cn.gridlife.teletext.activity
 * TeleText
 * Created by BEI on 2017/6/21.
 */

public class SplashActivity extends Activity {
	@BindView(R.id.btn_jump_splash)
	Button btnJump;
	private CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
		@Override
		public void onTick(long millisUntilFinished) {
			btnJump.setText("跳过(" + (millisUntilFinished / 1000) + ")s");
		}

		@Override
		public void onFinish() {
			btnJump.setText("跳过(" + 0 + ")s");
			gotoLoginOrMainActivity();
		}
	};


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ButterKnife.bind(this);
		checkSDCardPermission();
		startClock();
	}

	private void checkSDCardPermission() {

	}

	@OnClick({R.id.sp_bg, R.id.btn_jump_splash})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.sp_bg:
				gotoWebActivity();
				break;
			case R.id.btn_jump_splash:
				gotoLoginOrMainActivity();
				break;
		}
	}

	/**
	 * Splash页 跳转到广告
	 */
	private void gotoWebActivity() {
		Toast.makeText(this, "跳转广告", Toast.LENGTH_SHORT).show();
		this.finish();
	}

	private void gotoLoginOrMainActivity() {
		startActivity(new Intent(this, MainActivity.class));
	}

	private void startClock() {
		btnJump.setVisibility(View.VISIBLE);
		countDownTimer.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}
}
