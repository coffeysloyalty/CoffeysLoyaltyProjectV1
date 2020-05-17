package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.chatOwner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alicearmstrong.coffeysloyaltyprojectv1.ChatFragments.ChatsFragment;
import com.alicearmstrong.coffeysloyaltyprojectv1.ChatFragments.CustomersFragment;
import com.alicearmstrong.coffeysloyaltyprojectv1.R;

import java.util.ArrayList;

public class ChatOwnerFragment extends Fragment
{


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate( R.layout.fragment_chat_owner, container, false);

        TabLayout tabLayout = root.findViewById( R.id.tab_layout );
        ViewPager viewPager = root.findViewById( R.id.view_pager );

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter( getChildFragmentManager() );

        // Set fragments
        viewPagerAdapter.addFragment( new ChatsFragment(), "Chats" );
        viewPagerAdapter.addFragment( new CustomersFragment(), "Customer's" );

        viewPager.setAdapter( viewPagerAdapter );

        tabLayout.setupWithViewPager( viewPager );

        return root;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragmentArrayList;
        private ArrayList<String> titlesArrayList;

        ViewPagerAdapter(FragmentManager fragmentManager) {
            super( fragmentManager );
            this.fragmentArrayList = new ArrayList<>(  );
            this.titlesArrayList = new ArrayList<>(  );
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentArrayList.get( i );
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        public void addFragment ( Fragment fragment, String title)
        {
            fragmentArrayList.add( fragment );
            titlesArrayList.add( title );
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return  titlesArrayList.get( position );
        }
    }

}