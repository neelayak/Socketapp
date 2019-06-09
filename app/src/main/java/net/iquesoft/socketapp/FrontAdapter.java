package net.iquesoft.socketapp;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FrontAdapter extends ArrayAdapter {
    ArrayList<Videohelper> arrayList = new ArrayList<>();
    Context context;
    int n;

    public FrontAdapter(Context c, int custom1, ArrayList<Videohelper> arrayList, int a) {
        super(c, custom1, arrayList);
        context = c;
        n = a;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        if (convertView == null) {
            if (n == 1) {
                view = layoutInflater.inflate(R.layout.custom1, parent, false);
            } else if (n == 2) {
                view = layoutInflater.inflate(R.layout.custom2, parent, false);
            }
        }
        TextView textView = (TextView) view.findViewById(R.id.textView3);
        ImageView imageView = (ImageView) view.findViewById(R.id.albumimage);
        Videohelper videohelper = (Videohelper) getItem(position);
        TextView textView1 = (TextView) view.findViewById(R.id.textView4);
        textView.setText(videohelper.getTitle());
        textView.setText(videohelper.getDescription());
        getStationIcon(imageView, videohelper.getThumb());
        return view;
    }

    public void getStationIcon(final ImageView holder, String fromUrl) {
        final String iconUrl = fromUrl;
        if (iconUrl != null) {
            if (iconUrl.trim().equals("")) return;
            Resources r = context.getResources();
            final float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, r.getDisplayMetrics());

            Callback imageLoadCallback = new Callback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {
                    Picasso.with(context)
                            .load(iconUrl)
                            .resize((int) px, 0)
                            .networkPolicy(NetworkPolicy.NO_CACHE)
                            .into(holder);
                }
            };

            Picasso.with(context)
                    .load(iconUrl)
                    .resize((int) px, 0)

                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(holder, imageLoadCallback);
        }
    }
}
