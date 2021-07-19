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

public class addPoetry extends AppCompatActivity {
    Toolbar toolbar;
    EditText poetName,poetryData;
    AppCompatButton submitBtn;
    ApiInterface apiInterface;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poetry);

    iniialization();
    setuptoolbar();
    submitBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String poetryDataString=poetryData.getText().toString();
            String poetNameString=poetName.getText().toString();
            if (poetryDataString.equals("")){
                poetryData.setError("Field is Empty");
            }else {
                if (poetNameString.equals("")){
                    poetName.setError("field is empty");
                }else {
                    Toast.makeText(addPoetry.this, "call Api", Toast.LENGTH_SHORT).show();
                    callapi(poetryDataString, poetNameString);
                }
            }
        }
    });
    }
    private void iniialization(){
        toolbar=findViewById(R.id.Apppoetrytool);
        poetName=findViewById(R.id.add_poet_name);
        poetryData=findViewById(R.id.add_poetry_data);
        submitBtn=findViewById(R.id.subdata);
        Retrofit retrofit= ApiClient.getclient();
        apiInterface=retrofit.create(ApiInterface.class);

    }
    private void setuptoolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(addPoetry.this, "finished", Toast.LENGTH_SHORT).show();

            finish();
            }
        });
    }
    private void callapi(String poetryData,String poetName){
        apiInterface.addpoetry(poetryData,poetName).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                try {
                    if (response.body().getStatus().equals("")){
                        Toast.makeText(addPoetry.this, " not Added succesfully", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(addPoetry.this, " add succedfully", Toast.LENGTH_SHORT).show();

                    }
                    
                }catch (Exception e){
                    Log.e( "exption ",e.getLocalizedMessage() );
                }
                
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Log.e("onFailure: ",t.getLocalizedMessage() );

            }
        });
    }

    
}