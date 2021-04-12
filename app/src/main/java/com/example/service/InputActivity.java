package com.example.service;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.servicetestaidl.R;
import java.util.Random;

public class InputActivity extends Activity {
    private Button confirmBtn,next;
    private TextView input_num,title;
    private EditText input_answer;
    int num1 ,num2;
    int count = 1,right = 0,error = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        initView();
    }

    void initView(){
        confirmBtn = (Button) findViewById(R.id.input);
        next = (Button) findViewById(R.id.next);
        input_num = (TextView) findViewById(R.id.input_num);
        title = (TextView) findViewById(R.id.title);
        input_answer = (EditText) findViewById(R.id.input_answer);
        updateRandomUI();
        confirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String answer = input_answer.getText().toString();
                if(TextUtils.isEmpty(answer)){
                    Toast.makeText(InputActivity.this,"请输入答案在提交",Toast.LENGTH_SHORT).show();
                }else{
                    int result = Integer.parseInt(answer);
                    if(result ==  num1-num2){
                        Toast.makeText(InputActivity.this,"你真棒答对了",Toast.LENGTH_SHORT).show();
                        next.setVisibility(View.VISIBLE);
                        right++;
                        updateTitle();
                    }else{
                        Toast.makeText(InputActivity.this,"请认真思考呦！",Toast.LENGTH_SHORT).show();
                        resetInput();
                        error++;
                        updateTitle();
                    }
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                count++;
                resetInput();
                updateRandomUI();
            }
        });

    }
    int getRandomNum(int start,int end){
        Random random = new Random();
        //return  random.nextInt(start);
        int randNumber =random.nextInt(end - start + 1) + start; // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
        return randNumber;
    }

    void updateRandomUI(){
        AnswerBean answerBean;
        if(count%2==0){
            num1 = getRandomNum(8,20);
            num2 = getRandomNum(0,num1);
            input_num.setText(num1 +" - " +num2 +" = ?");
            answerBean = new AnswerBean(num1,num2,num1-num2,false);
        }else{
            num1 = getRandomNum(0,20);
            num2 = getRandomNum(0,20-num1);
            input_num.setText(num1 +" + " +num2 +" = ?");
            answerBean = new AnswerBean(num1,num2,num1=num2,true);
        }
        next.setVisibility(View.GONE);
    }
    void resetInput(){
        input_answer.setText("");
    }
    void updateTitle(){
        title.setText("总共"+count+"道题，"+"答对" +right +"道题。");
    }

    class AnswerBean{
        int num1,num2,answer;
        boolean isAdd;
        AnswerBean(int num1,int num2,int answer,boolean isAdd){
            this.num1 = num1;
            this.num2 = num2;
            this.answer = answer;
            this.isAdd = isAdd;

        }

    }
}
