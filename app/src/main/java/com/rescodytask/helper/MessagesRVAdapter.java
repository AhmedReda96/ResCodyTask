package com.rescodytask.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.rescodytask.R;
import com.rescodytask.databinding.MessagesModelBinding;
import com.rescodytask.pojo.Entry;
import com.rescodytask.ui.MainScreen;
import com.rescodytask.ui.MainScreenViewModel;

import java.util.List;

public class MessagesRVAdapter extends RecyclerView.Adapter<MessagesRVAdapter.ViewHolder> {
    private List<Entry> entryList;
    private Activity activity;
    private MainScreen mainScreen;
    public MessagesModelBinding binding;

    public MessagesRVAdapter(List<Entry> entries, Activity activity) {
        this.entryList = entries;
        this.activity = activity;
        mainScreen = new MainScreen();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.messages_model, parent, false
        );


        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"CheckResult", "ResourceType"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Entry model = entryList.get(position);


        String content = model.getContent().get$t();
        String message = content.substring(content.indexOf("message:") + 8, content.indexOf("sentiment:"));

        String sentiment = content.substring(content.indexOf("sentiment:") + 10, content.length()).trim();

        holder.binding.message.setText(message);
        holder.binding.sentiment.setText(sentiment);


        switch (sentiment) {
            case "Positive":
                holder.binding.sentiment.setTextColor(Color.BLUE);
                break;
            case "Negative":
                holder.binding.sentiment.setTextColor(Color.RED);
                break;
            case "Neutral":
                holder.binding.sentiment.setTextColor(Color.GREEN);
                break;
        }



    }


    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public MessagesModelBinding binding;

        public ViewHolder(MessagesModelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }
}
