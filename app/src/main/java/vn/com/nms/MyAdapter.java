package vn.com.nms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.samples.vision.face.facetracker.R;

import java.util.ArrayList;

import vn.com.nms.model.ChamCong;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<ChamCong> listChamCong;

    public MyAdapter(ArrayList<ChamCong> listChamCong) {
        this.listChamCong = listChamCong;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_template, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.viewDate.setText(listChamCong.get(position).getDate());
        holder.viewCheckin.setText(listChamCong.get(position).getTimeCheckin());
        holder.viewCheckout.setText(listChamCong.get(position).getTimeCheckout());
    }

    @Override
    public int getItemCount() {
        return listChamCong.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView viewDate;
        TextView viewCheckin;
        TextView viewCheckout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewCheckin = itemView.findViewById(R.id.view_checkin);
            viewCheckout = itemView.findViewById(R.id.view_checkout);
            viewDate = itemView.findViewById(R.id.view_date);
        }

    }
}
