package org.qii.weiciyuan.ui.interfaces;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewConfiguration;
import android.widget.Toast;
import org.qii.weiciyuan.support.asyncdrawable.TimeLineBitmapDownloader;
import org.qii.weiciyuan.support.error.WeiboException;
import org.qii.weiciyuan.support.settinghelper.SettingUtility;
import org.qii.weiciyuan.support.utils.GlobalContext;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

/**
 * User: Jiang Qi
 * Date: 12-7-31
 */
public class AbstractAppActivity extends FragmentActivity {

    protected int theme = 0;

    @Override
    protected void onResume() {
        super.onResume();
        // 设置为当前活动Activity
        GlobalContext.getInstance().setCurrentRunningActivity(this);
        // 切换主题
        if (theme != SettingUtility.getAppTheme()) {
            reload();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (GlobalContext.getInstance().getCurrentRunningActivity() == this) {
            GlobalContext.getInstance().setCurrentRunningActivity(null);
        }
    }

    /**
     * This method is called before an activity may be killed so that 
     * when it comes back some time in the future it can restore its state.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// 初始化主题设置
        if (savedInstanceState == null) {
            theme = SettingUtility.getAppTheme();
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        setTheme(theme);
        super.onCreate(savedInstanceState);
        // 强制显示ActionBar
        forceShowActionBarOverflowMenu();
        initNFC();
        GlobalContext.getInstance().setActivity(this);
    }


    private void forceShowActionBarOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ignored) {

        }
    }


    private void initNFC() {
        NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            return;
        }

        mNfcAdapter.setNdefPushMessageCallback(new NfcAdapter.CreateNdefMessageCallback() {
            @Override
            public NdefMessage createNdefMessage(NfcEvent event) {
                String text = (GlobalContext.getInstance().getCurrentAccountName());

                NdefMessage msg = new NdefMessage(
                        new NdefRecord[]{createMimeRecord(
                                "application/org.qii.weiciyuan.beam", text.getBytes()), NdefRecord.createApplicationRecord(getPackageName())
                        });
                return msg;
            }
        }, this);

    }

    private NdefRecord createMimeRecord(String mimeType, byte[] payload) {
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
        NdefRecord mimeRecord = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
        return mimeRecord;
    }

    private void reload() {

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
        TimeLineBitmapDownloader.refreshThemePictureBackground();
    }

    public TimeLineBitmapDownloader getBitmapDownloader() {
        return TimeLineBitmapDownloader.getInstance();
    }

    protected void dealWithException(WeiboException e) {
        Toast.makeText(this, e.getError(), Toast.LENGTH_SHORT).show();
    }
}