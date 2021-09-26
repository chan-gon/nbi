package com.example.nbi;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nbi.ui.main.SectionsPagerAdapter;
import com.example.nbi.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 탭 이동에 따른 버튼 숨기기/보이기
                Button button = (Button) findViewById(R.id.add_button);
                if(position == 1 || positionOffset > 0.8){
                    System.out.println("정산내역 페이지");
                    button.setVisibility(View.INVISIBLE);

                }
                else if(positionOffset < 0.2){
                    System.out.println("N분의일 페이지");
                    button.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //액티비티 콜백 함수
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent intent = result.getData();
                            int CallType = intent.getIntExtra("CallType", 0);
                            if(CallType == 0){
                                //실행될 코드
                            }else if(CallType == 1){
                                //실행될 코드
                            }else if(CallType == 2){
                                //실행될 코드
                            }
                        }
                    }
                });

        // 데이터 입력값 받기
        Intent receive_intent = getIntent();

        if(!TextUtils.isEmpty(receive_intent.getStringExtra("spendDate"))){
            String spendDate = receive_intent.getStringExtra("spendDate");
            String location = receive_intent.getStringExtra("location");
            String spendMoney = receive_intent.getStringExtra("spendMoney");
            String participant = receive_intent.getStringExtra("participant");

            Sub sub = new Sub(getApplicationContext());
            LinearLayout con = (LinearLayout) findViewById(R.id.add_view_area);
            con.addView(sub);

            TextView textView_spendDate = (TextView) findViewById(R.id.add_spend_date);
            textView_spendDate.setText(spendDate);

            TextView textView_location = (TextView) findViewById(R.id.add_location);
            textView_location.setText(location);

            TextView textView_spendMoney = (TextView) findViewById(R.id.add_spend_money);
            textView_spendMoney.setText(spendMoney);

            TextView textView_participant = (TextView) findViewById(R.id.add_participant);
            textView_participant.setText(participant);

        } else{
            TextView no_data = (TextView) findViewById(R.id.no_data_indicator);
            String msg = "No Data";
            no_data.setText(msg);
        }


    }

    // Add 버튼
    public void mOnPopupClick(View v){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("CallType", 1);
        resultLauncher.launch(intent);
    }
}

https://javapp.tistory.com/39?category=348168