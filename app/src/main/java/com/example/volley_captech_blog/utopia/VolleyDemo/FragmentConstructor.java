package com.example.volley_captech_blog.utopia.VolleyDemo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;


/**
 * Created by Utopia on 6/16/15.
 */
public class FragmentConstructor {

    FragmentTransaction mFragmentTransaction;

    public FragmentConstructor(Fragment fragment, FragmentManager fragmentManager, int layoutId) {

        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.replace(layoutId, fragment);

    }

    public void commit() {
        mFragmentTransaction.commit();
    }

}
