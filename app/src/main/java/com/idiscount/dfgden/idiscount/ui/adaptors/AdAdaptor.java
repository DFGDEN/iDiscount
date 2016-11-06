package com.idiscount.dfgden.idiscount.ui.adaptors;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idiscount.dfgden.idiscount.R;
import com.idiscount.dfgden.idiscount.models.Apartment;
import com.idiscount.dfgden.idiscount.models.Currency;
import com.idiscount.dfgden.idiscount.utils.DateFormatUtils;
import com.idiscount.dfgden.idiscount.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdAdaptor   extends RecyclerView.Adapter<AdAdaptor.ViewItem> {

    public interface OnPositionListener {

        void onPosition(int pos);
    }

    private Activity activity;
    private List<Apartment> apartments;
    private LayoutInflater layoutInflater;
    private OnPositionListener onPositionListener;
    private final int height;


    public AdAdaptor(Activity activity) {
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(activity);
        this.apartments = new ArrayList<>();
        this.height = ScreenUtil.getDisplayWidth(activity) * 9 / 16;
    }

    public void addApartments(List<Apartment> apartments) {
        this.apartments.addAll(apartments);
    }

    public void updApartments(List<Apartment> apartments) {
        this.apartments.clear();
        this.apartments.addAll(apartments);
    }

    public void setOnPositionListener(OnPositionListener onPositionListener) {
        this.onPositionListener = onPositionListener;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    @Override
    public ViewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adaptor_ad_item, parent, false);
        return new ViewItem(view);
    }

    @Override
    public void onBindViewHolder(ViewItem holder, int position) {
        Apartment apartment = apartments.get(position);
        Glide.with(activity.getApplicationContext())
                .load(apartment.getPhoto())
                .crossFade()
                .into(holder.imgImage);
        holder.itemView.getLayoutParams().height = height;
        holder.txtPrice.setText(String.format(Locale.CHINA, activity.getApplicationContext()
                .getString(R.string.ad_common_price),
                apartment.getPrice().getConverted().get(Currency.USD).getAmount(),
                apartment.getPrice().getConverted().get(Currency.BYR).getAmount()).replaceAll(","," "));
        holder.txtNewPrice.setText(String.format(Locale.GERMAN, activity.getApplicationContext()
                .getString(R.string.ad_certain_price),
                apartment.getPrice().getConverted().get(Currency.BYN).getAmount()));
        holder.txtLocation.setText(activity.getResources().getText(activity.getResources().
                getIdentifier(apartment.getRentType().getSortType(), "string",
                        activity.getApplicationContext().getPackageName()))+" " +
                apartment.getLocation().getUserAddress());
        holder.txtInfo.setText(String.format(Locale.GERMAN, activity.getApplicationContext()
                        .getString(R.string.ad_info),
                apartment.getContact().isOwner()? "" :  activity.getApplicationContext()
                .getString(R.string.ad_agent) + " "));
        holder.txtTime.setText(DateFormatUtils.getPubDate(activity.getApplicationContext(),
                apartment.getLastTimeUp()));

        if (onPositionListener != null){
            onPositionListener.onPosition(position);
        }
    }

    @Override
    public int getItemCount() {
        return apartments.size();
    }

    static class ViewItem extends RecyclerView.ViewHolder {

        @BindView(R.id.txtLocation) TextView txtLocation;
        @BindView(R.id.txtPrice) TextView txtPrice;
        @BindView(R.id.txtNewPrice) TextView txtNewPrice;
        @BindView(R.id.txtInfo) TextView txtInfo;
        @BindView(R.id.txtTime) TextView txtTime;
        @BindView(R.id.imgImage) ImageView imgImage;
        @BindView(R.id.layoutContainer) RelativeLayout layoutContainer;
        public ViewItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
