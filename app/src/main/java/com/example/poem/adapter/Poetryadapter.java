package com.example.poem.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poem.Api.ApiClient;
import com.example.poem.Api.ApiInterface;
import com.example.poem.R;
import com.example.poem.Responce.GetPoetryResponse;
import com.example.poem.models.PoetryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Poetryadapter extends RecyclerView.Adapter<Poetryadapter.ViewHolder> {
    Context context;
    List<PoetryModel> poetryModels;
    ApiInterface apiInterface;


    public Poetryadapter(Context context, List<PoetryModel> poetryModels) {
        this.context = context;
        this.poetryModels = poetryModels;
        Retrofit retrofit= ApiClient.getclient();
        apiInterface =retrofit.create(ApiInterface.class);
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=  LayoutInflater.from(context).inflate(R.layout.poetlist,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Poetryadapter.ViewHolder holder, int position) {
        holder.poetName.setText(poetryModels.get(position).getPoet_name());
        holder.poetry.setText(poetryModels.get(position).getPoetry_data());
        holder.date_time.setText(poetryModels.get(position).getDate_time());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletepoetry(poetryModels.get(position).getId()+"",position);

            }
        });


    }

    @Override
    public int getItemCount() {

        return poetryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView poetName,poetry,date_time;
        AppCompatButton updateBtn,deleteBtn;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            poetName=itemView.findViewById(R.id.textview_poetName);
            poetry=itemView.findViewById(R.id.textview_poetryData);
            date_time=itemView.findViewById(R.id.textview_poetryDateandtime);
            updateBtn=itemView.findViewById(R.id.update_btn);
            deleteBtn=itemView.findViewById(R.id.delete_btn);
        }
    }
    private void deletepoetry(String id,int pose){
        apiInterface.deletepoetry(id).enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(Call<GetPoetryResponse> call, Response<GetPoetryResponse> response) {
                try {
                    if (response!=null){
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if (response.body().getStatus().equals("1")){
                            poetryModels.remove(pose);
                            notifyDataSetChanged();
                        }
                    }
                }catch (Exception e){
                    Log.e("exp",e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<GetPoetryResponse> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });
    }

}
