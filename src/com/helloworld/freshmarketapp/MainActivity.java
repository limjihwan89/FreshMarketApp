package com.helloworld.freshmarketapp;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	 
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;
    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          
          fragmentManager = getSupportFragmentManager();
          // Initializing
          dataList = new ArrayList<DrawerItem>();
          mTitle = mDrawerTitle = getTitle();
          
          mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
          mDrawerList = (ListView) findViewById(R.id.left_drawer);

          mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                      GravityCompat.START);

          // Add Drawer Item to dataList
          dataList.add(new DrawerItem("지도", R.drawable.map3));
          dataList.add(new DrawerItem("물품검색", R.drawable.search3));
          dataList.add(new DrawerItem("물품등록", R.drawable.add3_2));
          dataList.add(new DrawerItem("거래현황", R.drawable.deal3));
          dataList.add(new DrawerItem("설정", R.drawable.setting3));

          adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                      dataList);

          mDrawerList.setAdapter(adapter);

          mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

          getActionBar().setDisplayHomeAsUpEnabled(true);
          getActionBar().setHomeButtonEnabled(true);
          getActionBar().setDisplayShowHomeEnabled(true);//erase app icon
          
          mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                      R.drawable.ic_drawer, R.string.drawer_open,
                      R.string.drawer_close) {
                public void onDrawerClosed(View view) {
                      getActionBar().setTitle(mTitle);
                      invalidateOptionsMenu(); // creates call to
                                                                // onPrepareOptionsMenu()
                }

                public void onDrawerOpened(View drawerView) {
                      getActionBar().setTitle(mDrawerTitle);
                      invalidateOptionsMenu(); // creates call to
                                                                // onPrepareOptionsMenu()
                }
          };

          mDrawerLayout.setDrawerListener(mDrawerToggle);

          if (savedInstanceState == null) {
                SelectItem(0);
          }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.searchview, menu);
    	 
        /** Get the action view of the menu item whose id is search */
        View v = (View) menu.findItem(R.id.search).getActionView();
 
        /** Get the edit text from the action view */
        EditText txtSearch = ( EditText ) v.findViewById(R.id.txt_search);
 
        /** Setting an action listener */
        txtSearch.setOnEditorActionListener(new OnEditorActionListener() {
 

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				Toast.makeText(getBaseContext(), "Search : " + v.getText(), Toast.LENGTH_SHORT).show();
				return false;
			}
        });
 
        return super.onCreateOptionsMenu(menu);
    
    }

    public void SelectItem(int position) {

          android.app.Fragment fragment = null;
          Bundle args = new Bundle();
          switch (position) {
          case 0:
                fragment = new FragmentTwo();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(position)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(position)
                            .getImgResID());
                break;
          case 1:
                fragment = new FragmentOne();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(position)
                            .getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(position)
                            .getImgResID());
                break;
          case 2:
                fragment = new FragmentThree();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(position)
                            .getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(position)
                            .getImgResID());
                break;
          case 3:
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(position)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(position)
                            .getImgResID());
                break;
          case 4:
                fragment = new FragmentTwo();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(position)
                            .getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(position)
                            .getImgResID());
                break;
          
          default:
                break;
          }

          fragment.setArguments(args);
          android.app.FragmentManager frgManager = getFragmentManager();
          frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                      .commit();

          mDrawerList.setItemChecked(position, true);
          setTitle(dataList.get(position).getItemName());
          mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
          mTitle = title;
          getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
          super.onPostCreate(savedInstanceState);
          // Sync the toggle state after onRestoreInstanceState has occurred.
          mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
          super.onConfigurationChanged(newConfig);
          // Pass any configuration change to the drawer toggles
          mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          // The action bar home/up action should open or close the drawer.
          // ActionBarDrawerToggle will take care of this.
          if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
          }

          return false;
    }

      private class DrawerItemClickListener implements
                ListView.OnItemClickListener {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position,
                      long id) {
                SelectItem(position);

          }

    }

}