package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.DetailActivity;

/**
 * Created by noel on 2018/6/17.
 */

public class BasicDetailFragment extends Fragment {
    public DetailActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailActivity) {
            activity = (DetailActivity) context;
        }
    }
    //----------------------

    /**
     * 無bundle
     */
    public void replaceDetailFragment(int layoutId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getFragmentManager().executePendingTransactions();
    }
    //----------------------

    /**
     * 有 bundle
     */
    public void replaceDetailFragment(int layoutId, Fragment fragment, boolean addToBackStack, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
        getFragmentManager().executePendingTransactions();
    }
}
