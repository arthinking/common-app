package com.example.featuredimage;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    public static final int REQUEST_CODE_UPDATE_FRIENDS_TIMELINE_COMMENT_REPOST_COUNT = 0;

    public static final int REQUEST_CODE_UPDATE_MENTIONS_WEIBO_TIMELINE_COMMENT_REPOST_COUNT = 1;

    public static final int REQUEST_CODE_UPDATE_MY_FAV_TIMELINE_COMMENT_REPOST_COUNT = 2;

    // private AccountBean accountBean;

    // private NewMsgInterruptBroadcastReceiver newMsgInterruptBroadcastReceiver;

    // private MusicReceiver musicReceiver;

    private ScrollableListFragment currentFragment;

    private TextView titleText;

    private View clickToTop;

    public static interface ScrollableListFragment {

        public void scrollToTop();
    }

    public void setTitle(String title) {
    	titleText.setText(title);
    	/*
        if (TextUtils.isEmpty(title)) {
            titleText.setVisibility(View.GONE);
        } else {
            titleText.setText(title);
            titleText.setVisibility(View.VISIBLE);
        }
        */
    }

    public void setTitle(int res) {
        setTitle(getString(res));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // outState.putParcelable("account", accountBean);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init use info
//        if (savedInstanceState != null) {
//            accountBean = savedInstanceState.getParcelable("account");
//        } else {
//            Intent intent = getIntent();
//            accountBean = intent
//                    .getParcelableExtra(BundleArgsConstants.ACCOUNT_EXTRA);
//        }

//        if (accountBean == null) {
//            accountBean = GlobalContext.getInstance().getAccountBean();
//        }

//        GlobalContext.getInstance().setGroup(null);
//        GlobalContext.getInstance().setAccountBean(accountBean);
//        SettingUtility.setDefaultAccountId(accountBean.getUid());
        // render view
        buildInterface(savedInstanceState);
    }

    private void buildInterface(Bundle savedInstanceState) {
        // getActionBar().setTitle(GlobalContext.getInstance().getCurrentAccountName());
    	getActionBar().setTitle("推荐");  // 设置action bar标题
        getWindow().setBackgroundDrawable(null);  // 设置背景图片
        setContentView(R.layout.menu_right);  // 设置主题布局
        boolean phone = findViewById(R.id.menu_frame) == null;  // 获取sliding menu 中的menu frame
        // 配置 SlidingMenu
        buildPhoneInterface(savedInstanceState);
        // 设置自定义ActionBar的标题
        buildCustomActionBarTitle(savedInstanceState);
        
        // 初始化
        /*
        if (savedInstanceState == null) {
            initFragments();
            FragmentTransaction secondFragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            secondFragmentTransaction
                    .replace(R.id.menu_frame, getMenuFragment(), LeftMenuFragment.class.getName());
            getSlidingMenu().showContent();
            secondFragmentTransaction.commit();
        }
        // 配置sliding menu
         */
        // configSlidingMenu(phone);
    }

    private void initFragments() {
    	FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    	/*
        Fragment friend = getFriendsTimeLineFragment();

        if (!friend.isAdded()) {
            fragmentTransaction
                    .add(R.id.menu_right_fl, friend, FriendsTimeLineFragment.class.getName());
            fragmentTransaction.hide(friend);
        }
        */

        if (!fragmentTransaction.isEmpty()) {
            fragmentTransaction.commit();
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    private void configSlidingMenu(boolean phone) {
    	/*
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        slidingMenu.setShadowDrawable(R.drawable.shadow_slidingmenu);
        if (phone) {
            slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        } else {
            slidingMenu.setBehindOffset(Utility.getScreenWidth());
        }

        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setOnPageScrollListener(new SlidingMenu.OnPageScrollListener() {
            @Override
            public void onPageScroll() {
                LongClickableLinkMovementMethod.getInstance().setLongClickable(false);
                (getFriendsTimeLineFragment()).clearActionMode();
                (getFavFragment()).clearActionMode();
                (getCommentsTimeLineFragment()).clearActionMode();
                (getMentionsTimeLineFragment()).clearActionMode();
                (getMyProfileFragment()).clearActionMode();

                if (GlobalContext.getInstance().getAccountBean().isBlack_magic()) {
                    (getSearchFragment()).clearActionMode();
                    (getDMFragment()).clearActionMode();
                }
            }
        });

        slidingMenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {
            @Override
            public void onClosed() {
                LongClickableLinkMovementMethod.getInstance().setLongClickable(true);
                LocalBroadcastManager.getInstance(MainTimeLineActivity.this)
                        .sendBroadcast(new Intent(AppEventAction.SLIDING_MENU_CLOSED_BROADCAST));
            }
        });
        */
    }

    private void buildCustomActionBarTitle(Bundle savedInstanceState) {
    	// LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化；
    	// 而findViewById()是找xml布局文件下的具体widget控件(如Button、TextView等)
    	View title = getLayoutInflater().inflate(R.layout.mainactivity_title_layout, null);
    	/*
        titleText = (TextView) title.findViewById(R.id.tv_title);
        clickToTop = title.findViewById(R.id.tv_click_to_top);
        clickToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollCurrentListViewToTop();
            }
        });
        View write = title.findViewById(R.id.btn_write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WriteWeiboActivity
                        .newIntent(GlobalContext.getInstance().getAccountBean());
                startActivity(intent);
            }
        });
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.RIGHT);
        getActionBar().setCustomView(title, layoutParams);
        getActionBar().setDisplayShowCustomEnabled(true);
        */
    }

    private void buildPhoneInterface(Bundle savedInstanceState) {
    	setBehindContentView(R.layout.menu_frame);  // 设置SlidingMenu使用的布局
    	getSlidingMenu().setSlidingEnabled(true);  // 设置上方视图是否能够滑动
    	// 设置SlidingMenu 的手势模式  
        // TOUCHMODE_FULLSCREEN 全屏模式，在整个content页面中，滑动，可以打开SlidingMenu  
        // TOUCHMODE_MARGIN 边缘模式，在content页面中，如果想打开SlidingMenu,你需要在屏幕边缘滑动才可以打开SlidingMenu  
        // TOUCHMODE_NONE 不能通过手势打开SlidingMenu 
    	getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    	// 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
    	getActionBar().setDisplayHomeAsUpEnabled(true);  
    	getSlidingMenu().setMode(SlidingMenu.LEFT); //设置左滑菜单
    	getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
    }


    private void scrollCurrentListViewToTop() {
        if (this.currentFragment != null) {
            this.currentFragment.scrollToTop();
        }
    }

    public View getClickToTopView() {
        return clickToTop;
    }

    public void setCurrentFragment(ScrollableListFragment fragment) {
        this.currentFragment = fragment;
    }

//    @Override
//    protected void onResumeFragments() {
//        super.onResumeFragments();
//        if (SettingUtility.isClickToTopTipFirstShow()) {
//            ViewTarget target = new ViewTarget(getClickToTopView());
//            ShowcaseView.insertShowcaseView(target, this, R.string.tip,
//                    R.string.click_to_top_tip);
//        }
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        DatabaseManager.close();
//    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        AccountBean intentAccountBean = intent
//                .getParcelableExtra(BundleArgsConstants.ACCOUNT_EXTRA);
//        if (intentAccountBean == null) {
//            return;
//        }
//
//        if (accountBean.equals(intentAccountBean)) {
//            accountBean = intentAccountBean;
//            GlobalContext.getInstance().setAccountBean(accountBean);
//            setIntent(intent);
//        } else {
//            finish();
//            overridePendingTransition(0, 0);
//            startActivity(intent);
//            overridePendingTransition(0, 0);
//        }
//
//    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        GlobalContext.getInstance().getBitmapCache().evictAll();
//        finish();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSlidingMenu().showMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    public UserBean getUser() {
//        return accountBean.getInfo();
//
//    }


//    public AccountBean getAccount() {
//        return accountBean;
//    }


//    private void readClipboard() {
//        ClipboardManager cm = (ClipboardManager) getApplicationContext().getSystemService(
//                Context.CLIPBOARD_SERVICE);
//        ClipData cmContent = cm.getPrimaryClip();
//        if (cmContent == null) {
//            return;
//        }
//        ClipData.Item item = cmContent.getItemAt(0);
//        if (item != null) {
//            String url = item.coerceToText(this).toString();
//            boolean a = !TextUtils.isEmpty(url) && !url
//                    .equals(SettingUtility.getLastFoundWeiboAccountLink());
//            boolean b = Utility.isWeiboAccountIdLink(url) || Utility.isWeiboAccountDomainLink(url);
//            if (a && b) {
//                OpenWeiboAccountLinkDialog dialog = new OpenWeiboAccountLinkDialog(url);
//                dialog.show(getSupportFragmentManager(), "");
//                SettingUtility.setLastFoundWeiboAccountLink(url);
//            }
//        }
//    }

    /*
    public static class OpenWeiboAccountLinkDialog extends DialogFragment {

        private String url;

        public OpenWeiboAccountLinkDialog() {

        }

        public OpenWeiboAccountLinkDialog(String url) {
            this.url = url;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putString("url", url);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                this.url = savedInstanceState.getString("url");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.find_weibo_account_link)
                    .setMessage(url)
                    .setPositiveButton(R.string.open, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Utility.isWeiboAccountIdLink(url)) {
                                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                                intent.putExtra("id", Utility.getIdFromWeiboAccountLink(url));
                                startActivity(intent);
                            } else if (Utility.isWeiboAccountDomainLink(url)) {
                                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                                intent.putExtra("domain",
                                        Utility.getDomainFromWeiboAccountLink(url));
                                startActivity(intent);
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            return builder.create();
        }
    }
    */

//    @Override
//    protected void onResume() {
//        super.onResume();
//        IntentFilter filter = new IntentFilter(AppEventAction.NEW_MSG_PRIORITY_BROADCAST);
//        filter.setPriority(1);
//        newMsgInterruptBroadcastReceiver = new NewMsgInterruptBroadcastReceiver();
//        Utility.registerReceiverIgnoredReceiverHasRegisteredHereException(this,
//                newMsgInterruptBroadcastReceiver, filter);
//        musicReceiver = new MusicReceiver();
//        Utility.registerReceiverIgnoredReceiverHasRegisteredHereException(this,
//                musicReceiver,
//                AppEventAction.getSystemMusicBroadcastFilterAction());
//        readClipboard();
//        //ensure timeline picture type is correct
//        ConnectionChangeReceiver.judgeNetworkStatus(this, false);
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Utility.unregisterReceiverIgnoredReceiverNotRegisteredException(this,
//                newMsgInterruptBroadcastReceiver);
//        Utility.unregisterReceiverIgnoredReceiverNotRegisteredException(this, musicReceiver);
//
//        if (isFinishing()) {
//            saveNavigationPositionToDB();
//        }
//    }

//    public void saveNavigationPositionToDB() {
//        int navPosition = getMenuFragment().getCurrentIndex() * 10;
//        ActionBar actionBar = getActionBar();
//        int second = 0;
//        if (actionBar.getNavigationMode() != ActionBar.NAVIGATION_MODE_STANDARD) {
//            second = actionBar.getSelectedNavigationIndex();
//        }
//        int result = navPosition + second;
//        GlobalContext.getInstance().getAccountBean().setNavigationPosition(result);
//        AccountDBTask
//                .updateNavigationPosition(GlobalContext.getInstance().getAccountBean(), result);
//    }

    /*
    public LeftMenuFragment getMenuFragment() {
        LeftMenuFragment fragment = ((LeftMenuFragment) getSupportFragmentManager()
                .findFragmentByTag(
                        LeftMenuFragment.class.getName()));
        if (fragment == null) {
            fragment = LeftMenuFragment.newInstance();
        }
        return fragment;
    }


    public FriendsTimeLineFragment getFriendsTimeLineFragment() {
        FriendsTimeLineFragment fragment = ((FriendsTimeLineFragment) getSupportFragmentManager()
                .findFragmentByTag(
                        FriendsTimeLineFragment.class.getName()));
        if (fragment == null) {
            fragment = FriendsTimeLineFragment.newInstance(getAccount(), getUser(), getToken());
        }
        return fragment;
    }
	*/

//    public MentionsTimeLine getMentionsTimeLineFragment() {
//        MentionsTimeLine fragment = ((MentionsTimeLine) getSupportFragmentManager()
//                .findFragmentByTag(
//                        MentionsTimeLine.class.getName()));
//        if (fragment == null) {
//            fragment = MentionsTimeLine.newInstance();
//        }
//        return fragment;
//    }

//    public CommentsTimeLine getCommentsTimeLineFragment() {
//        CommentsTimeLine fragment = ((CommentsTimeLine) getSupportFragmentManager()
//                .findFragmentByTag(
//                        CommentsTimeLine.class.getName()));
//        if (fragment == null) {
//            fragment = CommentsTimeLine.newInstance();
//        }
//        return fragment;
//    }

//    public SearchMainParentFragment getSearchFragment() {
//        SearchMainParentFragment fragment = ((SearchMainParentFragment) getSupportFragmentManager()
//                .findFragmentByTag(
//                        SearchMainParentFragment.class.getName()));
//        if (fragment == null) {
//            fragment = SearchMainParentFragment.newInstance();
//        }
//        return fragment;
//    }

//    public DMUserListFragment getDMFragment() {
//        DMUserListFragment fragment = ((DMUserListFragment) getSupportFragmentManager()
//                .findFragmentByTag(
//                        DMUserListFragment.class.getName()));
//        if (fragment == null) {
//            fragment = DMUserListFragment.newInstance();
//        }
//        return fragment;
//    }

//    public MyFavListFragment getFavFragment() {
//        MyFavListFragment fragment = ((MyFavListFragment) getSupportFragmentManager()
//                .findFragmentByTag(
//                        MyFavListFragment.class.getName()));
//        if (fragment == null) {
//            fragment = MyFavListFragment.newInstance();
//        }
//        return fragment;
//    }

//    public UserInfoFragment getMyProfileFragment() {
//        UserInfoFragment fragment = ((UserInfoFragment) getSupportFragmentManager()
//                .findFragmentByTag(
//                        UserInfoFragment.class.getName()));
//        if (fragment == null) {
//            fragment = UserInfoFragment.newInstance(
//                    GlobalContext.getInstance().getAccountBean().getInfo(),
//                    GlobalContext.getInstance().getSpecialToken());
//        }
//        return fragment;
//    }

    //todo
    /*
    private class NewMsgInterruptBroadcastReceiver extends RecordOperationAppBroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            AccountBean intentAccount = intent
                    .getParcelableExtra(BundleArgsConstants.ACCOUNT_EXTRA);
            if (accountBean.equals(intentAccount)) {
                MessageListBean mentionsWeibo = intent
                        .getParcelableExtra(BundleArgsConstants.MENTIONS_WEIBO_EXTRA);
                CommentListBean mentionsComment = intent
                        .getParcelableExtra(BundleArgsConstants.MENTIONS_COMMENT_EXTRA);
                CommentListBean commentsToMe = intent
                        .getParcelableExtra(BundleArgsConstants.COMMENTS_TO_ME_EXTRA);
                int unreadCount = (mentionsWeibo != null ? mentionsWeibo.getSize() : 0) + (
                        mentionsComment != null ? mentionsComment.getSize() : 0) + (
                        commentsToMe != null ? commentsToMe
                                .getSize() : 0);
                String tip = String.format(context.getString(R.string.you_have_new_unread_count),
                        String.valueOf(unreadCount));
                Toast.makeText(MainTimeLineActivity.this, tip,
                        Toast.LENGTH_LONG).show();
                abortBroadcast();
            }
        }
    }
	*/

//    public void setMentionsWeiboCount(int count) {
//        LeftMenuFragment fragment = getMenuFragment();
//        fragment.setMentionWeiboUnreadCount(count);
//    }
//
//    public void setMentionsCommentCount(int count) {
//        LeftMenuFragment fragment = getMenuFragment();
//        fragment.setMentionCommentUnreadCount(count);
//    }
//
//    public void setCommentsToMeCount(int count) {
//        LeftMenuFragment fragment = getMenuFragment();
//        fragment.setCommentUnreadCount(count);
//    }
}
