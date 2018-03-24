package com.mrezanasirloo.groupbordered.listview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.mrezanasirloo.groupbordered.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

	private ExpandableListViewItemDecoration expandableList;
	private ExpandableListAdapter listAdapter;
	private ArrayList<String> listDataHeader;
	private HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		expandableList = findViewById(R.id.list_view);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(listDataHeader, listDataChild);

		// setting list adapter
		expandableList.setAdapter(listAdapter);

		expandableList.addItemDecoration(new ItemDecoratorBorderListView(
				getResources().getDimensionPixelSize(R.dimen.stroke_size),
				Color.GRAY
		));
		expandableList.expandGroup(0);
		expandableList.expandGroup(1);
		expandableList.expandGroup(2);

		expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
			@Override
			public boolean onGroupClick(
					ExpandableListView parent, View v,
					int groupPosition, long id
			) {
				return true; // This way the expander cannot be collapsed
			}
		});
	}

	/*
	* Preparing the list data
	*/
	private void prepareListData() {
		listDataHeader = new ArrayList<>();
		listDataChild = new HashMap<>();

		// Adding child data
		listDataHeader.add("Vegetable");
		listDataHeader.add("Fruits");
		listDataHeader.add("Books");

		// Adding child data
		List<String> vegetable = new ArrayList<>();
		vegetable.add("1) Potato");
		vegetable.add("2) Cabbage");
		vegetable.add("3) Onion");

		List<String> fruits = new ArrayList<>();
		fruits.add("1) Apple");
		fruits.add("2) Orange");

		List<String> books = new ArrayList<>();
		books.add("1) Harry Potter");
		books.add("2) Inferno");

		listDataChild.put(listDataHeader.get(0), vegetable); // Header, Child data
		listDataChild.put(listDataHeader.get(1), fruits);
		listDataChild.put(listDataHeader.get(2), books);
	}
}
