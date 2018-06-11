package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.MainActivity;


/**
 * Created by noel on 2018/6/9.
 */

public abstract class BasicFragment extends Fragment {
    public MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            activity = (MainActivity) context;
        }
    }
    //-------------


    /**
     * 第一層 箱子 container用
     */
    public void replaceBasicFragment(int layoutId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getChildFragmentManager().executePendingTransactions();
    }


    /**
     * 第一層 箱子 有 bundle
     */
    public void replaceBasicFragment(int layoutId, Fragment fragment, boolean addToBackStack, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getChildFragmentManager().executePendingTransactions();
    }
    //-------------

    /**
     * 第二層以後用
     * 無bundle
     */
    public void replaceBasicFragment2(int layoutId, Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = getParentFragment().getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getParentFragment().getChildFragmentManager().executePendingTransactions();
    }
    //-------------

    /**
     * 第二層以後用
     * 有bundle
     */
    public void replaceBasicFragment3(int layoutId, Fragment fragment, boolean addToBackStack, Bundle bundle) {
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getParentFragment().getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getParentFragment().getChildFragmentManager().executePendingTransactions();
    }

    //-------------

    public boolean popFragment() {
        boolean isPop = false;
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            getChildFragmentManager().popBackStack();
        }
        return isPop;
    }

    public void onBack() {
        activity.onBackPressed();
    }

    //------------------------------
    // todo 以下 inner fragment 用
    //------------------------------
    // 替換inner的container為外層的用  無bundle
    public void replaceInnerFragment(int layoutId, Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = getParentFragment().getParentFragment().getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getParentFragment().getParentFragment().getChildFragmentManager().executePendingTransactions();
    }

    //------------------------------
    // 替換inner的container為外層的用  有bundle
    public void replaceInnerFragment(int layoutId, Fragment fragment, boolean addToBackStack, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getParentFragment().getParentFragment().getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getParentFragment().getParentFragment().getChildFragmentManager().executePendingTransactions();
    }
}
