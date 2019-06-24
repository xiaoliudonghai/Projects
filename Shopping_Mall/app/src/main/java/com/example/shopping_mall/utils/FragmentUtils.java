package com.example.shopping_mall.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

public class FragmentUtils {
    public  static void addFragment(FragmentManager fragmentManager, Class<? extends Fragment> zClass, int layoutId, Bundle bundle){
        String name = zClass.getName();
        Fragment fragment = fragmentManager.findFragmentByTag(name);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment==null){
            try {
                fragment = zClass.newInstance();

                fragment.setArguments(bundle);

                fragmentTransaction.add(layoutId,fragment);
                hideOtherFragment(fragmentManager,fragmentTransaction,fragment);
                fragmentTransaction.commit();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }else{
           if (fragment.isAdded()){
               if (fragment.isHidden()){
                   fragment.setArguments(bundle);
                   fragmentTransaction.show(fragment);
                   hideOtherFragment(fragmentManager,fragmentTransaction,fragment);
                   fragmentTransaction.commit();
               }
           }else{
               fragment.setArguments(bundle);
               fragmentTransaction.add(layoutId,fragment);
               hideOtherFragment(fragmentManager,fragmentTransaction,fragment);
               fragmentTransaction.commit();
           }
        }
    }
    public static void hideOtherFragment(FragmentManager manager, FragmentTransaction transaction, Fragment willShowFragment){
        List<Fragment> fragments = manager.getFragments();

        for (Fragment fragment: fragments) {
            if (fragment != willShowFragment && !fragment.isHidden()){
                transaction.hide(fragment);
            }
        }
    }
}
