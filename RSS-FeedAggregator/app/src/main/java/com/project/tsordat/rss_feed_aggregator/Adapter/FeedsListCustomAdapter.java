package com.project.tsordat.rss_feed_aggregator.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.tsordat.rss_feed_aggregator.Model.Feed.Feed;
import com.project.tsordat.rss_feed_aggregator.R;

import java.util.ArrayList;

public class FeedsListCustomAdapter extends ArrayAdapter<Feed> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Feed> data = new ArrayList<>();
    private View listItem;
    private ImageView imageView;

    public FeedsListCustomAdapter(Context mContext, int layoutResourceId, ArrayList<Feed> data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        TextView textTitle = (TextView) listItem.findViewById(R.id.label_first);
        TextView textDescription = (TextView) listItem.findViewById(R.id.label_second);
        imageView = (ImageView) listItem.findViewById(R.id.image_label);

        Feed folder = data.get(position);

        textTitle.setText(folder.getUrl());
        textDescription.setText("");

        return listItem;
    }
}

