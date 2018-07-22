package me.guowenlong.kchartdemo.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.guowenlong.kchartdemo.R;

/**
 * 类的描述
 *
 * @author guowenlong
 * 创建时间:2018-07-18-15:51
 */
public class SymbolsAdatper extends RecyclerView.Adapter<SymbolsAdatper.ViewHolder> implements View.OnClickListener{
    private List<String> lists;
    private OnItemClickListener mItemClickListener;

    public SymbolsAdatper() {
    }

    public SymbolsAdatper(List<String> lists) {
        this.lists = lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SymbolsAdatper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_symbols, parent, false);
        view.setOnClickListener(this);
        return new SymbolsAdatper.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymbolsAdatper.ViewHolder holder, int position) {
        holder.mText.setText(lists.get(position));
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return lists==null?0:lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mText;

        ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.tv_item_symbol);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}