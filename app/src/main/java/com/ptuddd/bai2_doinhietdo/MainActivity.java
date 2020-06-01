package com.ptuddd.bai2_doinhietdo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner unit1,unit2;
    private EditText edt1,edt2;
    private TextView tvCongthuc;
    private Button btnminus,btndot,btndel;
    String[] unit = {"C","F","K"};
    ArrayAdapter aa;
    int currentSelectUnit1=0;
    int currentSelectUnit2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,unit);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit1.setAdapter(aa);
        unit2.setAdapter(aa);

        unit1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSelectUnit1=position;
                if(edt1.hasFocus()){
                    calculatUnit2();
                }else {
                    calculatUnit1();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        unit2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSelectUnit2=position;
                if(edt2.hasFocus()){
                    calculatUnit1();
                }else {
                    calculatUnit2();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final TextWatcher tw1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if(isNumeric(edt1.getText().toString())||edt1.getText().toString().equals("-")||edt1.getText().toString().equals(".")){
//                }else {
//                    edt1.setText(edt1.getText());
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(isNumeric(edt1.getText().toString())||edt1.getText().toString().equals("-")||edt1.getText().toString().equals(".")){

                    calculatUnit2();
//                }else {
//                    edt1.setText(edt1.getText());
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        final TextWatcher tw2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edt2.getText().toString().equals("")||edt2.getText().toString().equals("-")){
                    edt2.setText("0");
                }else {
                    calculatUnit1();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
//        edt1.addTextChangedListener(tw1);
//        edt2.addTextChangedListener(tw2);
        edt1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    edt1.addTextChangedListener(tw1);
                }else {
                    edt1.removeTextChangedListener(tw1);
                }
            }
        });
        edt2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    edt2.addTextChangedListener(tw2);
                }else {
                    edt2.removeTextChangedListener(tw2);
                }
            }
        });

    }

    private void initView() {
        unit1 = findViewById(R.id.sp_unit1);
        unit2 = findViewById(R.id.sp_unit2);
        edt1 = findViewById(R.id.edt_unit1);
        edt2= findViewById(R.id.edt_unit2);
        tvCongthuc = findViewById(R.id.tv_congthuc);
        btnminus= findViewById(R.id.btnminus);
        btnminus.setOnClickListener(this);
        btndot= findViewById(R.id.btndot);
        btndot.setOnClickListener(this);
        btndel= findViewById(R.id.btndel);
        btndel.setOnClickListener(this);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    private void calculatUnit2() {
        if(currentSelectUnit1==currentSelectUnit2){
            edt2.setText(edt1.getText().toString());
        }else {
            switch (unit[currentSelectUnit1]){
                case "C" :{
                    if(unit[currentSelectUnit2].equals("F"))
                        edt2.setText(cToF(Double.parseDouble(edt1.getText().toString()))+"");
                    if(unit[currentSelectUnit2].equals("K"))
                        edt2.setText(cToK(Double.parseDouble(edt1.getText().toString()))+"");
                   break;
                }
                case "F" :{
                    if(unit[currentSelectUnit2].equals("C"))
                        edt2.setText(fToC(Double.parseDouble(edt1.getText().toString()))+"");
                    if(unit[currentSelectUnit2].equals("K"))
                        edt2.setText(fToK(Double.parseDouble(edt1.getText().toString()))+"");
                    break;
                }
                case "K" :{
                    if(unit[currentSelectUnit2].equals("C"))
                        edt2.setText(kToC(Double.parseDouble(edt1.getText().toString()))+"");
                    if(unit[currentSelectUnit2].equals("F"))
                        edt2.setText(kToF(Double.parseDouble(edt1.getText().toString()))+"");
                    break;
                }
            }

        }
    }

    private void calculatUnit1() {
        if(currentSelectUnit1==currentSelectUnit2){
            edt1.setText(edt2.getText().toString());

        }else {
            switch (unit[currentSelectUnit2]){
                case "C" :{
                    if(unit[currentSelectUnit1].equals("F"))
                        edt1.setText(cToF(Double.parseDouble(edt2.getText().toString()))+"");
                    if(unit[currentSelectUnit1].equals("K"))
                        edt1.setText(cToK(Double.parseDouble(edt2.getText().toString()))+"");
                    break;
                }
                case "F" :{
                    if(unit[currentSelectUnit1].equals("C"))
                        edt1.setText(fToC(Double.parseDouble(edt2.getText().toString()))+"");
                    if(unit[currentSelectUnit1].equals("K"))
                        edt1.setText(fToK(Double.parseDouble(edt2.getText().toString()))+"");
                    break;
                }
                case "K" :{
                    if(unit[currentSelectUnit1].equals("C"))
                        edt1.setText(kToC(Double.parseDouble(edt2.getText().toString()))+"");
                    if(unit[currentSelectUnit1].equals("F"))
                        edt1.setText(kToF(Double.parseDouble(edt2.getText().toString()))+"");
                    break;
                }
            }

        }
    }
    private double cToF(double c){
        tvCongthuc.setText("F = (9.0/5.0)*c + 32");
        return (9.0/5.0)*c + 32;
    }
    private double cToK(double c){
        tvCongthuc.setText("K = c + 273.15");
        return (c + 273.15);
    }
    private double fToC(double f){
        tvCongthuc.setText("C = (f-32)*(0.5556)");
        return (f-32)*(0.5556);
    }
    private double fToK(double f){
        tvCongthuc.setText("K = 273.5 + ((f - 32.0) * (5.0/9.0))");
        return 273.5 + ((f - 32.0) * (5.0/9.0));
    }
    private double kToC(double k){
        tvCongthuc.setText("C = k-273.15");
        return k-273.15;
    }
    private double kToF(double k){
        tvCongthuc.setText("F =  ((k-273.15)*1.8)+32");
        return ((k-273.15)*1.8)+32;
    }

    private void addText(String string){
        if(edt1.hasFocus()){
            if(string=="-"){
                if(edt1.getText().toString().charAt(0)=='-'){
                    edt1.setText(edt1.getText().toString().substring(1));
                }else {
                    edt1.setText("-"+edt1.getText());
                }
            }
            if(string=="del"){
                edt1.setText(edt1.getText().toString().substring(0,edt1.getText().length()-1));
            }
            if(string=="."){
                if (!edt1.getText().toString().contains("."))
                    edt1.setText(edt1.getText().toString()+".");
            }
            edt1.setSelection(edt1.getText().length());

        }
        if(edt2.hasFocus()){
            if(string=="-"){
                if(edt2.getText().toString().charAt(0)=='-'){

                    edt2.setText(edt2.getText().toString().substring(1));
                }else {

                    edt2.setText("-"+edt2.getText());
                }
            }
            if(string=="del"){
                edt2.setText(edt2.getText().toString().substring(0,edt2.getText().length()-1));
            }
            if(string=="."){
                if (!edt2.getText().toString().contains("."))
                    edt2.setText(edt2.getText().toString()+".");
            }
            edt2.setSelection(edt2.getText().length());
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.btnminus :{
                addText("-");
                break;
            }
            case R.id.btndot :{
                addText(".");
                break;
            }
            case R.id.btndel :{
                addText("del");
                break;
            }
        }
    }
}
