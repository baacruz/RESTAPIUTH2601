package com.example.restapiuth2601;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.restapiuth2601.Models.Post;

import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {

    public PostAdapter(@NonNull Context context, @NonNull List<Post> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
        }

        Post post = getItem(position);

        TextView title = convertView.findViewById(R.id.textViewTitle);
        TextView body = convertView.findViewById(R.id.textViewBody);

        if (post != null) {
            title.setText(post.getTitle());
            body.setText(post.getBody());
        }

        return convertView;
    }
}
