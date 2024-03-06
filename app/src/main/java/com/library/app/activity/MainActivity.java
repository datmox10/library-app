package com.library.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.library.app.R;
import com.library.app.databinding.ActivityMainBinding;
import com.library.app.fragment.NotifyFragment;
import com.library.app.fragment.HomeFragment;
import com.library.app.fragment.LibraryFragment;
import com.library.app.fragment.QrcodeFragment;
import com.library.app.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private TextView countNotify;

    private int pendingNotify = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavView.getMenu().findItem(R.id.home).setChecked(true);
        binding.bottomNavView.setBackground(null);

        itemSlected();
    }

    public void itemSlected(){

        binding.bottomNavView.setOnItemSelectedListener(item->{
            if(item.getItemId() == R.id.home){
                binding.bottomNavView.getMenu().findItem(R.id.home).setChecked(true);
                replaceFragment(new HomeFragment());

            }
            if(item.getItemId() == R.id.qrcode){
                binding.bottomNavView.getMenu().findItem(R.id.qrcode).setChecked(true);
                replaceFragment(new QrcodeFragment());

            }
            if(item.getItemId() == R.id.user){
                binding.bottomNavView.getMenu().findItem(R.id.user).setChecked(true);
                replaceFragment(new UserFragment());

            }
            if(item.getItemId() == R.id.notify){
                binding.bottomNavView.getMenu().findItem(R.id.notify).setChecked(true);
                replaceFragment(new NotifyFragment());

            }
            if(item.getItemId() == R.id.library){
                binding.bottomNavView.getMenu().findItem(R.id.library).setChecked(true);
                replaceFragment(new LibraryFragment());
            }
            return true;
        });

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HomeFragment());
                binding.bottomNavView.getMenu().findItem(R.id.qrcode).setChecked(true);
//                binding.floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,209,136)));
                ViewCompat.setBackgroundTintList(binding.floatingActionButton, ColorStateList.valueOf(Color.rgb(0,0,0)));
            }
        });
    }



    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_menu,menu);

        final MenuItem menuItem = menu.findItem(R.id.notify);

        View actionView = MenuItemCompat.getActionView(menuItem);
        countNotify = (TextView) findViewById(R.id.notify_badge);

        setUpBadge();
        return true;
    }

    private void setUpBadge() {
        if(countNotify!=null){
            if(pendingNotify ==0 ){
                if(countNotify.getVisibility()!= View.GONE){
                    countNotify.setVisibility(View.GONE);
                }
            }else{
                countNotify.setVisibility(Integer.parseInt(String.valueOf(Math.min(pendingNotify,99))));
                if(countNotify.getVisibility()!= View.VISIBLE){
                    countNotify.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}