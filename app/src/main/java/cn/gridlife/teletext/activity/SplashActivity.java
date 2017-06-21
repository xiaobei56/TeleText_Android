package cn.gridlife.teletext.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gridlife.bzblibrary.utils.BzbToast;
import cn.gridlife.teletext.R;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.content.ContentValues.TAG;

/**
 * cn.gridlife.teletext.activity
 * TeleText
 * Created by BEI on 2017/6/21.
 */

public class SplashActivity extends Activity implements EasyPermissions.PermissionCallbacks{
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
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		// Forward results to EasyPermissions
		EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

	}

	@Override
	public void onPermissionsGranted(int requestCode, List<String> list) {
		// Some permissions have been granted
		// ...
	}

	@Override
	public void onPermissionsDenied(int requestCode, List<String> list) {
		// Some permissions have been denied
		// ...
		Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + list.size());

		// (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
		// This will display a dialog directing them to enable the permission in app settings.
		if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
			new AppSettingsDialog.Builder(this).build().show();
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
			// Do something after user returned from app settings screen, like showing a Toast.
			Toast.makeText(this, "", Toast.LENGTH_SHORT)
					.show();
		}
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
		BzbToast.showToast(this,"广告");
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
		if (countDownTimer != null)
			countDownTimer.cancel();
		finish();
	}
}
