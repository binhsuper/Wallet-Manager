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
    MenuItemSelected mMenuItemSelected;
    public Drawer mDrawer;

    public SideBar(Activity context, Toolbar toolbar, String name, String email, MenuItemSelected menuItemSelected) {
        mMenuItemSelected = menuItemSelected;

        final IProfile profile = new ProfileDrawerItem().withName(name).withEmail(email).withIcon(R.drawable.profile_img);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Lịch sử").withIcon(R.drawable.drawer_history);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Thống kê").withIcon(R.drawable.drawer_chart);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Tiết kiệm").withIcon(R.drawable.drawer_safe);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Sổ nợ").withIcon(R.drawable.drawer_debt);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Đăng xuất").withIcon(R.drawable.setting);

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
                        item4,
                        item5
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1)
                            mMenuItemSelected.onHistorySelected();
                        if (drawerItem.getIdentifier() == 2)
                            mMenuItemSelected.onChartSelected();
                        if (drawerItem.getIdentifier() == 3)
                            mMenuItemSelected.onSavingSelected();
                        if (drawerItem.getIdentifier() == 4)
                            mMenuItemSelected.onDebtSelected();
                        if (drawerItem.getIdentifier() == 5)
                            mMenuItemSelected.onLogoutSelected();

                        return true;
                    }
                })
                .build();
        mDrawer = result;

    }

    public interface MenuItemSelected {
        void onHistorySelected();
        void onChartSelected();
        void onSavingSelected();
        void onDebtSelected();
        void onLogoutSelected();
    }
}
