package com.jeremyfeinstein.slidingmenu.example;

import java.net.URLEncoder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.crittercism.app.Crittercism;
import com.jeremyfeinstein.slidingmenu.example.anim.CustomScaleAnimation;
import com.jeremyfeinstein.slidingmenu.example.anim.CustomSlideAnimation;
import com.jeremyfeinstein.slidingmenu.example.anim.CustomZoomAnimation;
import com.jeremyfeinstein.slidingmenu.example.fragments.FragmentChangeActivity;
import com.jeremyfeinstein.slidingmenu.example.fragments.ResponsiveUIActivity;

public class GuitarGGActivity extends SherlockPreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		
//		Crittercism.init(getApplicationContext(), "508ab27601ed857a20000003");
		// this.addPreferencesFromResource(R.xml.main);
		// setContentView(R.xml.main);
		Class<?> cls = CommonAppActivity.class;
		Intent intent = new Intent(this, cls);
		startActivity(intent);
	}

}
