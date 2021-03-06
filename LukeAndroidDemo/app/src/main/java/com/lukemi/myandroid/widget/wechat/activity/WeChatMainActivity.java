package com.lukemi.myandroid.widget.wechat.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lukemi.myandroid.widget.wechat.fragment.WeChatContactFragment;
import com.lukemi.myandroid.widget.wechat.fragment.WeChatDiscoverFragment;
import com.lukemi.myandroid.widget.wechat.fragment.WeChatMSGFragment;
import com.lukemi.myandroid.widget.wechat.fragment.WeChatMineFragment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * 仿照微信主界面
 */
public class WeChatMainActivity extends AppCompatActivity {

    private ViewPager mainViewPager;
    private TabLayout navigateTabLayout;
    private MainAdapter mainAdapter;
    private String[] titles = new String[]{"微信", "通讯录", "发现", "我"};
    private int[] drawableIDs = new int[]{com.lukemi.myandroid.R.drawable.selector_wechat_main_message
            , com.lukemi.myandroid.R.drawable.selector_wechat_main_contact
            , com.lukemi.myandroid.R.drawable.selector_wechat_main_discover
            , com.lukemi.myandroid.R.drawable.selector_wechat_main_mine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_we_chat_main);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.lukemi.myandroid.R.menu.menu_wechat_main, menu);
        menu.add(0, Menu.FIRST + 2, 2, "个人信息").setIcon(android.R.drawable.ic_menu_send);
        return true;
    }


    /**
     * 通过反射，设置menu显示icon
     *
     * @param view
     * @param menu
     * @return
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case com.lukemi.myandroid.R.id.action_search:
                Toast.makeText(this, "R.id.action_search", Toast.LENGTH_SHORT).show();
                break;
            case com.lukemi.myandroid.R.id.action_group_chat:
                Toast.makeText(this, "R.id.action_group_chat", Toast.LENGTH_SHORT).show();
                break;
            case com.lukemi.myandroid.R.id.action_add_friend:
                Toast.makeText(this, "R.id.action_add_friend", Toast.LENGTH_SHORT).show();
                break;
            case com.lukemi.myandroid.R.id.action_scan:
                Toast.makeText(this, "R.id.action_scan", Toast.LENGTH_SHORT).show();
                break;
            case com.lukemi.myandroid.R.id.action_feed:
                Toast.makeText(this, "R.id.action_feed", Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST + 2:
                Toast.makeText(this, "Menu.FIRST + 2", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mainViewPager = ((ViewPager) findViewById(com.lukemi.myandroid.R.id.mainViewPager));
        navigateTabLayout = ((TabLayout) findViewById(com.lukemi.myandroid.R.id.bottomNavigationBar));
        initMainViewPager();
        initNavigateTabLayout();
    }

    /**
     * 初始化底部导航栏
     */
    private void initNavigateTabLayout() {

   /*     navigateTabLayout.addTab(navigateTabLayout.newTab().setIcon(R.drawable.selector_wechat_main_message).setText(titles[0]));
        navigateTabLayout.addTab(navigateTabLayout.newTab().setIcon(R.drawable.selector_wechat_main_contact).setText(titles[1]));
        navigateTabLayout.addTab(navigateTabLayout.newTab().setIcon(R.drawable.selector_wechat_main_discover).setText(titles[2]));
        navigateTabLayout.addTab(navigateTabLayout.newTab().setIcon(R.drawable.selector_wechat_main_mine).setText(titles[3]));
*/
        navigateTabLayout.setupWithViewPager(mainViewPager);////将TabLayout和ViewPager关联起来(问题导致icon不能显示)。
//setupWithViewPager后遗症解决
//方法一
        for (int i = 0; i < navigateTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = navigateTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mainAdapter.getTabView(i));
            }
            if (i == 0) {
                navigateTabLayout.getTabAt(0).getCustomView().setSelected(true);
            }
        }

    /*     //方法二
    for (int i = 0; i < navigateTabLayout.getChildCount(); i++) {
            navigateTabLayout.getTabAt(i).setIcon(drawableIDs[i]).setText(titles[i]);//默认Icon 与 title GAP 8dp 不可更改
            //
        }*/
    }

    /**
     * 初始化ViewPager
     */
    private void initMainViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(WeChatMSGFragment.newInstance("", ""));
        fragmentList.add(WeChatContactFragment.newInstance("", ""));
        fragmentList.add(WeChatDiscoverFragment.newInstance("", ""));
        fragmentList.add(WeChatMineFragment.newInstance("", ""));
        mainAdapter = new MainAdapter(getSupportFragmentManager(), this, titles, drawableIDs, fragmentList);
        mainViewPager.setAdapter(mainAdapter);

    }


    class MainAdapter extends FragmentPagerAdapter {

        private String[] titles;
        private List<Fragment> fragmentList;
        private Context context;
        private LayoutInflater mInflater;

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        public MainAdapter(FragmentManager fm, Context context, String[] itemNames, int[] drawableIDs, List<Fragment> fragmentList) {
            super(fm);
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.titles = itemNames;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            if (fragmentList != null && fragmentList.size() > 0) {
                return fragmentList.get(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public View getTabView(int position) {
            View view = View.inflate(context, com.lukemi.myandroid.R.layout.item_wechat_main_navigation, null);
            ImageView imageView = (ImageView) view.findViewById(com.lukemi.myandroid.R.id.icon);
            TextView textView = (TextView) view.findViewById(com.lukemi.myandroid.R.id.title);
            imageView.setImageResource(drawableIDs[position]);
            textView.setText(titles[position]);

            return view;
        }

    /*    @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }*/


    }

}
