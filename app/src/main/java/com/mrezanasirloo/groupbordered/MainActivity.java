package com.mrezanasirloo.groupbordered;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mrezanasirloo.groupbordered.Item.ItemHeader;
import com.mrezanasirloo.groupbordered.Item.ItemRow;
import com.mrezanasirloo.groupbordered.adapter.Adapter;
import com.mrezanasirloo.groupbordered.adapter.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Adapter adapter = new Adapter();
        ArrayList<Item> items = new ArrayList<>();

        items.add(new ItemHeader("Vegetable"));
        items.add(new ItemRow("1) Potato"));
        items.add(new ItemRow("2) Cabbage"));
        items.add(new ItemRow("3) Onion"));

        items.add(new ItemHeader("Fruits"));
        items.add(new ItemRow("1) Apple"));
        items.add(new ItemRow("2) Orange"));

        items.add(new ItemHeader("Books"));
        items.add(new ItemRow("1) Harry Potter"));
        items.add(new ItemRow("2) Inferno"));


        adapter.setItems(items);

        ItemDecoratorBorder itemDecoratorBorder = new ItemDecoratorBorder(
                getResources().getDimensionPixelSize(R.dimen.stroke_size),
                Color.GRAY
        );
        recyclerView.addItemDecoration(itemDecoratorBorder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
