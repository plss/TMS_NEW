package mibh.mis.tmsland.activity;

import android.content.res.Configuration;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yayandroid.locationmanager.LocationConfiguration;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProviderType;

import java.util.Calendar;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.fragment.FuelFragment;
import mibh.mis.tmsland.fragment.ImageTypeFragment;
import mibh.mis.tmsland.fragment.MainFragment;
import mibh.mis.tmsland.fragment.PaySlipFragment;
import mibh.mis.tmsland.fragment.PlanFragment;
import mibh.mis.tmsland.fragment.WorkFragment;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.manager.PreferencesManage;
import mibh.mis.tmsland.task.GetLocationName;
import mibh.mis.tmsland.task.RefreshData;
import mibh.mis.tmsland.utils.MyDebug;

public class MainActivity extends LocationBaseActivity implements RefreshData.OnRefreshComplete {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
        getLocation();

    }

    private void initInstance() {
        setToolbar();
        setDrawer();

        if (!isCheckedStatus())
            selectedPosition = 5;
        else if (isWork())
            selectedPosition = 1;
        else selectedPosition = 0;

        Menu menu = navigationView.getMenu();
        menu.getItem(selectedPosition).setChecked(true);
        selectedItem(selectedPosition);

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setDrawer() {
        navigationView = (NavigationView) findViewById(R.id.navView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        View headerLayout = navigationView.getHeaderView(0);

        TextView empId = (TextView) headerLayout.findViewById(R.id.drawerEmpId);
        TextView empName = (TextView) headerLayout.findViewById(R.id.drawerEmpName);
        TextView truckId = (TextView) headerLayout.findViewById(R.id.drawerTruckId);

        empId.setText("รหัสพนักงาน " + PreferencesManage.getInstance().getDriverId());
        empName.setText(PreferencesManage.getInstance().getFirstName() + " " + PreferencesManage.getInstance().getLastName());
        truckId.setText("เบอร์รถ " + PreferencesManage.getInstance().getTruckId());

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.drawer_plan:
                        selectedPosition = 0;
                        break;
                    case R.id.drawer_work:
                        selectedPosition = 1;
                        break;
                    case R.id.drawer_fuel:
                        selectedPosition = 2;
                        break;
                    case R.id.drawer_cash:
                        selectedPosition = 3;
                        break;
                    case R.id.drawer_slip:
                        selectedPosition = 4;
                        break;
                    case R.id.drawer_check:
                        selectedPosition = 5;
                        break;
                    case R.id.drawer_cam:
                        selectedPosition = 6;
                        break;
                    case R.id.drawer_scanqr:
                        selectedPosition = 7;
                        break;
                    case R.id.drawer_weight:
                        selectedPosition = 8;
                        break;
                    case R.id.drawer_hrms:
                        selectedPosition = 9;
                        break;
                    case R.id.drawer_logout:
                        //Snackbar.make(mContentFrame, "Log Out", Snackbar.LENGTH_SHORT).show();
                        selectedPosition = 10;
                        break;
                    default:
                        break;
                }
                selectedItem(selectedPosition);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void selectedItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                toolbar.setTitle(R.string.menu_plan);
                fragment = PlanFragment.newInstance();
                break;
            case 1:
                toolbar.setTitle(R.string.menu_work);
                fragment = WorkFragment.newInstance();
                break;
            case 2:
                toolbar.setTitle(R.string.menu_fuel);
                fragment = FuelFragment.newInstance();
                break;
            case 3:
                toolbar.setTitle(R.string.menu_cash);
                //fragment = ImageTypeFragment.newInstance();
                //break;
            case 4:
                toolbar.setTitle(R.string.menu_slip);
                fragment = PaySlipFragment.newInstance();
                break;
            case 5:
                toolbar.setTitle(R.string.menu_check);
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.contentContainer, fragment).commit();
            drawerLayout.closeDrawers();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId()) {
            case R.id.refresh:
                new RefreshData(MainActivity.this, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return new LocationConfiguration()
                .keepTracking(true)
                .askForGooglePlayServices(true)
                .setMinAccuracy(200.0f)
                .setWaitPeriod(ProviderType.GOOGLE_PLAY_SERVICES, 5 * 1000)
                .setWaitPeriod(ProviderType.GPS, 10 * 1000)
                .setWaitPeriod(ProviderType.NETWORK, 5 * 1000)
                .setGPSMessage("ต้องการเปิด GPS ?")
                .setRationalMessage("Gimme the permission!");
    }

    @Override
    public void onLocationFailed(int failType) {
        switch (failType) {
            case FailType.PERMISSION_DENIED: {
                Toast.makeText(this, "Couldn't get location, because user didn't give permission!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.GP_SERVICES_NOT_AVAILABLE:
            case FailType.GP_SERVICES_CONNECTION_FAIL: {
                Toast.makeText(this, "Couldn't get location, because Google Play Services not available!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.NETWORK_NOT_AVAILABLE: {
                Toast.makeText(this, "Couldn't get location, because network is not accessible!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.TIMEOUT: {
                Toast.makeText(this, "Couldn't get location, and timeout!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.GP_SERVICES_SETTINGS_DENIED: {
                Toast.makeText(this, "Couldn't get location, because user didn't activate providers via settingsApi!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.GP_SERVICES_SETTINGS_DIALOG: {
                Toast.makeText(this, "Couldn't display settingsApi dialog!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        if (MyDebug.LOG)
            Log.d("Test Location", "onLocationChanged: " + location.getLatitude() + " " + location.getLongitude());
        new GetLocationName(location.getLatitude(), location.getLongitude()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private boolean isCheckedStatus() {
        if (Calendar.getInstance().getTimeInMillis() / (24 * 60 * 60 * 1000) != PreferencesManage.getInstance().getLastCheckStatus() / (24 * 60 * 60 * 1000))
            return false;
        else return true;
    }

    private boolean isWork() {
        return DataManager.getInstance().getWorkList() != null && DataManager.getInstance().getWorkList().size() > 0;
    }

    @Override
    public void onRefreshComplete() {
        if (!isCheckedStatus())
            selectedPosition = 5;
        else if (isWork())
            selectedPosition = 1;
        else selectedPosition = 0;

        Menu menu = navigationView.getMenu();
        menu.getItem(selectedPosition).setChecked(true);
        selectedItem(selectedPosition);
    }
}
