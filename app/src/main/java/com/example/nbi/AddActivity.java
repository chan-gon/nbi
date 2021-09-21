package com.example.nbi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AddActivity extends Activity {

    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 미사용
        setContentView(R.layout.adtivity_add);

/*        // UI 객체생성
        txtText = (TextView)findViewById(R.id.txtText);

        // 데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        txtText.setText(data)*/;

        // 날짜 가져오기
        txtText = findViewById(R.id.spend_date);
        Calendar cal = Calendar.getInstance();
        txtText.setText(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE));

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                txtText = findViewById(R.id.spend_date);
                txtText.setText(String.format("%d-%d-%d", yy,mm+1,dd));
            }
        };

        // 날짜 EditText 터치 이벤트
        txtText = findViewById(R.id.spend_date);
        txtText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    new DatePickerDialog(AddActivity.this, onDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
                }
                return false;
            }
        });

    }

    // 확인 버튼 클릭
    public void onAdd(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("이벤트 테스트").setMessage("확인 버튼 클릭!");

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    // 취소 버튼 클릭
    public void onClose(View view){

        // 데이터 전달
        Intent intent = new Intent();
        intent.putExtra("result","Close Popup");
        setResult(RESULT_OK, intent);

        // 액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        // 바깥 레이어 클릭 시 안닫히게
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    
    @Override
    public void onBackPressed(){
        // 안드로이드 백버튼 막기
        return;
    }
}
