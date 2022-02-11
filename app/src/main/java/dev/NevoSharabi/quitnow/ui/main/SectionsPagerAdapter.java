package dev.NevoSharabi.quitnow.ui.main;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.fragment_tips_and_symptoms1;
import dev.NevoSharabi.quitnow.fragment_tips_and_symptoms2;
import dev.NevoSharabi.quitnow.page3;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[] {R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    return  new fragment_tips_and_symptoms1();
                case 1:
                    return  new fragment_tips_and_symptoms2();

                case 2:
                    return  new page3();
                default:
                    return  new PlaceholderFragment();
            }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}