package com.example.wastemanagementsystem;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.AlertDialog;

import java.util.ArrayList;

public class BinAdapter extends  ArrayAdapter<String>{
    private final Activity context;
    private final ArrayList<String> Areabin;
    private final ArrayList<String> bLocality;
    private final ArrayList<String> bLandMark;
    private final ArrayList<String> bCity;
    private final ArrayList<String> bDriverEmail;
    private final ArrayList<String> bSource;
    private final ArrayList<String> bDestination;
    private final ArrayList<String> bloadtype;
    private final ArrayList<String> bcyclicPeriod;
    private final ArrayList<Integer> bid;
    DbHelper dbHelper;
    AlertDialog.Builder builder;
    ViewBin viewBin;

    public BinAdapter(Activity context, ArrayList<String> Areab, ArrayList<String> Locality, ArrayList<String> bLandMark, ArrayList<String> bCity,ArrayList<String> bDriverEmail,ArrayList<String> bSource,ArrayList<String> bDestination, ArrayList<String> bloadtype, ArrayList<String> bcyclicPeriod, ArrayList<Integer> bid,DbHelper dbHelper,AlertDialog.Builder builder)
    {
        super(context,R.layout.listview__custom,Areab);
        this.context=context;
        this.Areabin=Areab;
        this.bLocality=Locality;
        this.bLandMark=bLandMark;
        this.bCity=bCity;
        this.bDriverEmail=bDriverEmail;
        this.bSource=bSource;
        this.bDestination=bDestination;
        this.bloadtype=bloadtype;
        this.bcyclicPeriod=bcyclicPeriod;
        this.bid=bid;
        this.dbHelper=dbHelper;
        this.builder=builder;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View bincustom=layoutInflater.inflate(R.layout.listview__custom,null,true);
        TextView id=bincustom.findViewById(R.id.binId);

        TextView Area=bincustom.findViewById(R.id.BinArea);
        TextView Locality=bincustom.findViewById(R.id.Loc);
        TextView DriverEmail=bincustom.findViewById(R.id.dEmail);
        TextView Source=bincustom.findViewById(R.id.bsorce);
        TextView des=bincustom.findViewById(R.id.bdest);
        TextView LandMark=bincustom.findViewById(R.id.Land);
        TextView City=bincustom.findViewById(R.id.City);
        TextView load=bincustom.findViewById(R.id.loadty);;
        TextView  cycle=bincustom.findViewById(R.id.cyc);

        Button delete=bincustom.findViewById(R.id.deleteBin);
        Button update=bincustom.findViewById(R.id.updateBin);


        Area.setText(Areabin.get(position));
        Locality.setText(bLocality.get(position));
        DriverEmail.setText(bDriverEmail.get(position));
        Source.setText(bSource.get(position));
        des.setText(bDestination.get(position));
        LandMark.setText(bLandMark.get(position));
        City.setText(bCity.get(position));
        load.setText(bloadtype.get(position));
        cycle.setText(bcyclicPeriod.get(position));
        id.setText(bid.get(position).toString());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setCancelable(true);
                builder.setTitle("Warning");
                builder.setMessage("Are you sure you want to delete that bin");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.DeleteBin(bid.get(position));


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BinModel b=new BinModel();
                b.setId(bid.get(position));
                b.setBinArea(Areabin.get(position));
                b.setLocality(bLocality.get(position));
                b.setDriverEmail(bDriverEmail.get(position));
                b.setSource(bSource.get(position));
                b.setDestination(bDestination.get(position));
                b.setLandMak(bLandMark.get(position));
                b.setCity(bCity.get(position));
                b.setLoad(bloadtype.get(position));
                b.setCyclicPeriod(bcyclicPeriod.get(position));
                Intent intent=new Intent(context,updateBin.class);
                intent.putExtra("BinModel", b);
                context.startActivity(intent);
            }
        });
        return bincustom;
    }
}
