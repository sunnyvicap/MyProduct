package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, CheckedChangeListner {
    private ActivityMainBinding bd;

    private String companyName;
    private ArrayAdapter<String> companyArrayAapter;
    private List<String> companyList;
    private List<Model> modelList;

    private List<Model> deletModelLists;

    private ModelAdpater modelAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        modelList = new ArrayList<>();
        deletModelLists = new ArrayList<>();
        companyList = Arrays.asList(getResources().getStringArray(R.array.company));


        companyArrayAapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, companyList);
        companyArrayAapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bd.dropDownCompany.setAdapter(companyArrayAapter);

        bd.dropDownCompany.setOnItemSelectedListener(this);
        bd.saveData.setOnClickListener(this);
        bd.deletDate.setOnClickListener(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        bd.recyclerView.setLayoutManager(linearLayoutManager);

        modelAdpater = new ModelAdpater(this, modelList, this);
        bd.recyclerView.setAdapter(modelAdpater);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveData:

                saveDate();
                break;

            case R.id.deletDate:
                deleteData();
                break;
        }
    }

    private void deleteData() {
        try {

            if (modelList != null && !modelList.isEmpty()) {


                        modelList.removeAll(deletModelLists);


                modelAdpater.notifyDataSetChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void saveDate() {

        String name = bd.mEDTName.getText().toString();
        String mobile = bd.mEDTMobile.getText().toString();

        if (TextUtils.isEmpty(name)) {

            showToast(this.getResources().getString(R.string.name_error));

        } else if (TextUtils.isEmpty(mobile)) {
            showToast(this.getResources().getString(R.string.mobile_error));


        } else if (bd.dropDownCompany.getSelectedItemPosition() == 0) {

            showToast(this.getResources().getString(R.string.comapny_error));

        } else {

            Model model = new Model(name, mobile, companyName);


            modelList.add(model);

            Log.e("ModelSize", "" + modelList.size());

            bd.recyclerView.setItemViewCacheSize(modelList.size());
            modelAdpater.notifyDataSetChanged();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position > 0) {

            companyName = companyList.get(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void getCheckedPosition(int postion, boolean isChecked) {

        if (isChecked) {

            deletModelLists.add(modelList.get(postion));
        } else {
            deletModelLists.remove(modelList.get(postion));
        }
    }
}