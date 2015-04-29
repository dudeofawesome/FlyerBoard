package com.dudeofawesome.flyerboard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.widget.Toolbar;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;


public class MainActivity extends ActionBarActivity implements FlyerFragment.OnFragmentInteractionListener {
    public static Toolbar toolbar;

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        // Create the AccountHeader
        AccountHeader.Result headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                    new ProfileDrawerItem().withName("Bob Robertson").withEmail("brobertson@psu.edu").withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                    return false;
                    }
                })
                .build();

        //Now create your drawer and pass the AccountHeader.Result
        Drawer.Result result = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                     new PrimaryDrawerItem().withName(R.string.drawer_item_home),
                     new PrimaryDrawerItem().withName(R.string.drawer_item_post),
                     new PrimaryDrawerItem().withName(R.string.drawer_item_mod),
                     new DividerDrawerItem(),
                     new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                    if (drawerItem instanceof Nameable) {
                        toolbar.setTitle(((Nameable) drawerItem).getNameRes());
                        switchView(((Nameable) drawerItem).getNameRes());
                    }
                    }
                })
                .build();

        refreshFlyers();
    }

    private void switchView (int view) {
        switch (view) {
            case R.string.drawer_item_home:
                viewFlipper.setDisplayedChild(0);
                break;
            case R.string.drawer_item_post:
                viewFlipper.setDisplayedChild(1);
                break;
            case R.string.drawer_item_mod:
                viewFlipper.setDisplayedChild(2);
                break;
            case R.string.drawer_item_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
        }
    }

    private void refreshFlyers () {
        System.out.println(InterfaceServer.getFlyers("Pennsylvania State University"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Intent intent = new Intent(this, FlyerActivity.class);
        startActivity(intent);
    }
}
