package com.project.tsordat.rss_feed_aggregator.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.tsordat.rss_feed_aggregator.MainActivity;
import com.project.tsordat.rss_feed_aggregator.Model.Articles.Article;
import com.project.tsordat.rss_feed_aggregator.R;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;

public class ArticlesListCustomAdapter extends ArrayAdapter<Article> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Article> data = new ArrayList<>();
    private ArrayList<String> viewed = new ArrayList<>();
    private View listItem;
    private ImageView imageView;

    public ArticlesListCustomAdapter(Context mContext, int layoutResourceId, ArrayList<Article> data, ArrayList<String> viewed) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        this.viewed = viewed;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        TextView textTitle = (TextView) listItem.findViewById(R.id.label_first);
        TextView textDescription = (TextView) listItem.findViewById(R.id.label_second);
        imageView = (ImageView) listItem.findViewById(R.id.image_label);

        Article folder = data.get(position);

        textTitle.setText(folder.getTitle());
        textDescription.setText(replacer(folder.getDescription()));

        String imageURL = folder.getEnclosureUrl();

        if (imageURL != null && imageURL.length() > 0)
        {
            new ArticlesListCustomAdapter.DownloadImageTask().execute(imageURL);
        }

        for (int i = 0; i < viewed.size(); i++)
        {
            if (viewed.get(i).equals(folder.getId()))
            {
                listItem.setBackground(((MainActivity)mContext).getResources().getDrawable(R.color.lightgray2));
            }
        }

        return listItem;
    }

    public static String replacer(String data) {
        try {
            data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            data = data.replaceAll("\\+", "%2B");
            data = URLDecoder.decode(data, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        public DownloadImageTask() {
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}

