package com.bootcamp.walletmanager.Datamodel;

import android.app.Activity;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.bootcamp.walletmanager.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class SideBar {

    public SideBar(Activity context, Toolbar toolbar) {
        final IProfile profile = new ProfileDrawerItem().withName("Hoang The Binh").withEmail("hoangthebinh259@gmail.com").withIcon(R.drawable.drawer_user);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Lịch sử").withIcon(R.drawable.drawer_history);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(1).withName("Thống kê").withIcon(R.drawable.drawer_chart);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(1).withName("Tiết kiệm").withIcon(R.drawable.drawer_safe);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(1).withName("Sổ nợ").withIcon(R.drawable.drawer_debt);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(context)
                .withHeaderBackground(R.color.text)
                .addProfiles(
                        profile
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(context)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return true;
                    }
                })
                .build();
        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Cài đặt").withIcon(R.drawable.setting));
    }
}
