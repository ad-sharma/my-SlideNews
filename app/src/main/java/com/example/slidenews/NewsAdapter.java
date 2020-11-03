package com.example.slidenews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<News> values = new ArrayList<>();
    Context mContext;
    String queryStr;
    private RequestQueue requestQueue;

    public NewsAdapter(Context context, String Newsquery) {
        queryStr = Newsquery;
        requestQueue = Volley.newRequestQueue(context);
        mContext = context;
        loadNews();
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.news_row, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News newNewsCard = values.get(position);
        holder.heading_tv.setText(newNewsCard.getHeading());
        holder.author_tv.setText(newNewsCard.getAuthor());
        holder.content_tv.setText(newNewsCard.getContent());

        holder.containerView.setOnClickListener(v -> {
            News current = values.get(position);
            // Intent intent= new Intent(v.getContext(),emptyActivity.class);
            //intent.putExtra("name",current.getUrl());
            Uri uri = Uri.parse(current.getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            v.getContext().startActivity(intent);
        });


    }

    public void loadNews() {
        String url;
        Log.d("json_str_inside_Adapter", queryStr);
        if (queryStr.equals("")) {
            Log.d("json_str", "Condition Always true");
            url = "https://content.guardianapis.com/search?&api-key=56053666-9614-43e9-b99f-fbdabb55c3e4";
        } else
            url = "https://content.guardianapis.com/search?q=" + queryStr + "&api-key=56053666-9614-43e9-b99f-fbdabb55c3e4";
        Log.d("json_str", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject responseJSONObject = response.getJSONObject("response");
                        String ed = responseJSONObject.getString("userTier");
                        Log.d("json_str_inside_adapter", ed);
                        int pageSize = responseJSONObject.getInt("pageSize");
                        Log.d("json_str", String.valueOf(pageSize));
                        JSONArray results = responseJSONObject.getJSONArray("results");
                        for (int i = 0; i < pageSize; i++) {
                            JSONObject card = results.getJSONObject(i);
                            String title = card.getString("webTitle") + ".";
                            Log.d("json_str", title);
                            String author = card.getString("sectionName");
                            Log.d("json_str", author);
                            String date = card.getString("webPublicationDate");
                            date = date.substring(0, date.indexOf('T'));
                            Log.d("json_str", date);
                            String url1 = card.getString("webUrl");
                            Log.d("json_str", "here goes the url ");
                            News news = new News(author, date, title, url1);
                            values.add(news);
                        }
                        // String ed=one.get(0).toString();

                    } catch (JSONException e) {
                        Log.e("json_str", "no");
                    }

                    notifyDataSetChanged();
                }
                , error -> Log.e("CS50", "POKEMON LIST ERROR")
        );
        requestQueue.add(request);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout containerView;
        TextView heading_tv;
        TextView author_tv;
        TextView content_tv;

        public NewsViewHolder(View v) {
            super(v);
            heading_tv = v.findViewById(R.id.heading_text_view);
            author_tv = v.findViewById(R.id.author_text_view);
            content_tv = v.findViewById(R.id.desc_text_view);
            containerView = v.findViewById(R.id.news_row_linear);

        }
    }

}
