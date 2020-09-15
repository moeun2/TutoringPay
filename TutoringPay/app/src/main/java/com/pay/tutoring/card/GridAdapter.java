package com.pay.tutoring.card;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pay.tutoring.card.CardActivity;
import com.pay.tutoring.card.CardGridItem;
import com.pay.tutoring.card.CardVO;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    Context context;
    ArrayList<CardVO> cards = new ArrayList<>();
    boolean visible = false;

    public GridAdapter(Context context, ArrayList<CardVO> cards){
        this.context = context;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = new CardGridItem(context);

            ((CardGridItem)view).setData(cards.get(position),visible);

        }
        return view;
    }

    public void show(boolean visible){
        this.visible = visible;
        this.notifyDataSetChanged();
    }
}