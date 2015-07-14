package com.example.volley_captech_blog.utopia;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.example.volley_captech_blog.R;
import com.example.volley_captech_blog.common.VolleyCaptechApplication;
import com.example.volley_captech_blog.utopia.VolleyDemo.FragmentConstructor;
import com.example.volley_captech_blog.utopia.VolleyDemo.ImageRequestFragment;
import com.example.volley_captech_blog.utopia.VolleyDemo.MyBaseFragment;
import com.example.volley_captech_blog.utopia.VolleyDemo.StringRequestFragment;

public class MyDemoActivity extends Activity implements DemoAdapter.OnItemClickListener, MyBaseFragment.OnFragmentInteractionListener {

    private CharSequence mTitle;
    private String[] mDemoTitles;

    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private final FragmentManager mFragmentManager = getFragmentManager();
    private final int mContentLayout = R.id.content_layout;
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_demo);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        mRequestQueue = ((VolleyCaptechApplication) this.getApplicationContext()).getQueue();

        mTitle = getTitle();
        mDemoTitles = getResources().getStringArray(R.array.demo_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView) findViewById(R.id.left_drawer);

        // improve performance by indicating the list if fixed size.
        mDrawerList.setHasFixedSize(true);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new DemoAdapter(this, mDemoTitles));

    }

    private void onSelectItem(int position) {
        onFragmentInteraction(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            mRequestQueue.getCache().clear();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Override method of DemoAdapter.OnItemClickListener
    @Override
    public void onClick(View view, int position) {
        onSelectItem(position);
    }

    @Override
    public void onFragmentInteraction(Object... obj) {
        int position = Integer.parseInt(obj[0].toString());
        setTitle(mDemoTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
        switch (position) {
            case 0:
                new FragmentConstructor(StringRequestFragment.newInstance(), mFragmentManager,
                        mContentLayout).commit();
                break;
            case 1:
                new FragmentConstructor(ImageRequestFragment.newInstance("null", "null"), mFragmentManager,
                        mContentLayout).commit();
                break;

        }

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mDrawerList))
            mDrawerLayout.closeDrawer(mDrawerList);
        else
            super.onBackPressed();
    }
}
