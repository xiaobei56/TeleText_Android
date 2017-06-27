package cn.gridlife.teletext;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * cn.gridlife.teletext
 * TeleText
 * Created by BEI on 2017/6/21.
 */

public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}
		LeakCanary.install(this);
	}
}
