package com.example.mycalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.renderscript.Double2;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button cb;
    private EditText et;
    private CheckBox nan;
    private CheckBox nv;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb= (Button) findViewById(R.id.jisuan);
        et= (EditText) findViewById(R.id.weight);
        nan=(CheckBox)findViewById(R.id.man);
        nv= (CheckBox) findViewById(R.id.woman);
        tv=(TextView)findViewById(R.id.jieguo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        //按钮注册事件
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否已经填写了体重数据
                if (et.getText().toString().trim().equals("")){
                    //判断是否已选择性别
                    if (nan.isChecked()||nv.isChecked()){
                        Double weight=Double.parseDouble(et.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        sb.append("---------评估结果---------");
                        if(nan.isChecked()){
                            sb.append("男性标准身高：");
                            //进行计算身高
                            double result=countHeight(weight,"男");
                            sb.append((int) result+"(厘米)");
                        }
                        if(nv.isChecked()){
                            sb.append("女性标准身高");
                            double result=countHeight(weight,"女");
                            sb.append((int)result+"(厘米)");
                        }
                        tv.setText(sb.toString());
                    }else{
                        showMessage("请选择性别");
                    }
                }else{
                    showMessage("请输入体重！");
                }
            }
        });
    }

    private void showMessage(String s) {
        AlertDialog ad=new AlertDialog.Builder(this).create();
        ad.setTitle("系统信息");
        ad.setMessage(s);
        ad.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.show();
    }

    private double countHeight(Double weight, String sex) {
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    public boolean onCreateOptionMenu(Menu menu){
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionItenSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
