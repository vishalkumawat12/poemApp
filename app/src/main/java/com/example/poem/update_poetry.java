package com.example.poem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poem.Api.ApiClient;
import com.example.poem.Api.ApiInterface;
import com.example.poem.Responce.DeleteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class update_poetry extends AppCompatActivity {
    Toolbar toolbar;
    EditText editText;
    AppCompatButton submitbtn;
    int poetryid;
    String poetData;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_poetry);
        initislization();
        setToolbar();
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdata=editText.getText().toString();
                if (pdata.equals("")){
                    editText.setError("filed is empty");
                }
                else {
                    Toast.makeText(update_poetry.this, "Call Api", Toast.LENGTH_SHORT).show();
                    callAPi(pdata,poetryid+"");
                }

            }
        });


    }
    private void initislization(){
        toolbar=findViewById(R.id.uppoetrytool);
        editText=findViewById(R.id.up_poetry_data);
        submitbtn=findViewById(R.id.updata);
        poetryid=getIntent().getIntExtra("pid",0);
        poetData=getIntent().getStringExtra("p_data");
        editText.setText(poetData);
        Retrofit retrofit= ApiClient.getclient();
        apiInterface=retrofit.create(ApiInterface.class);

    }
    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void callAPi(String pData,String pid){
        apiInterface.updatepoetry(pData,pid).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                try {
                    if (response.body().getStatus().equals("1")){
                        Toast.makeText(update_poetry.this, "Data  updated", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(update_poetry.this, "Data not updated", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.e( "onResponse: ",e.getLocalizedMessage() );
                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Log.e("update",t.getLocalizedMessage() );

            }
        });

    }
}