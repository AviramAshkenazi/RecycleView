package com.example.recycleview;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_LONG;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private ArrayList<DataModel> dataset;
    private  View.OnClickListener clickListener;
    FirebaseFirestore db;

    public CustomAdapter(ArrayList<DataModel> dataset ) {
        this.dataset = dataset;
        this.db = FirebaseFirestore.getInstance();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        CardView cardview;
        TextView textviewname;
        TextView textviewversion;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardview = itemView.findViewById(R.id.cardViewPage);
            textviewname = itemView.findViewById(R.id.textView1);
            //textviewversion= itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from( parent.getContext() ).inflate(R.layout.cardview,parent,false);

        MyViewHolder myviewholder = new MyViewHolder(view);



        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textviewname.setText(dataset.get(position).getName());
        //holder.textviewversion.setText(dataset.get(position).getDescription());
        holder.imageView.setImageBitmap(dataset.get(position).getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyData.currentCard = dataset.get(holder.getAdapterPosition() );
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailsFragment2);

            }
        });
        
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
