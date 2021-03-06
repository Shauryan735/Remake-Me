package com.example.remakeme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Javadoc Comment.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

  private final ArrayList<Fragment> fragmentList = new ArrayList<>();
  private final ArrayList<String> fragmentListTitle = new ArrayList<>();

  public PagerAdapter(@NonNull FragmentManager fm) {
    super(fm);
  }

  @NonNull

  @Override
  public Fragment getItem(int position) {
    return fragmentList.get(position);
  }

  @Override
  public int getCount() {
    return fragmentListTitle.size();
  }


  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return fragmentListTitle.get(position);
  }

  public void addFragment(Fragment fragment, String title) {
    fragmentList.add(fragment);
    fragmentListTitle.add(title);
  }
}
