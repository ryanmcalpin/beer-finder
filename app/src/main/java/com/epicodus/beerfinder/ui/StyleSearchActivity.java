package com.epicodus.beerfinder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.beerfinder.R;
import com.epicodus.beerfinder.adapters.StyleListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StyleSearchActivity extends AppCompatActivity {

    @Bind(R.id.styleListView) ListView mStylesList;

    private String[] styles = new String[] {"India Pale Ale", "Lager", "Pale Ale", "Stout", "Pilsner", "Wheat Beer", "English Bitter", "Porter", "Bock", "Brown Ale", "Saison", "Kolsch", "Cider"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_search);
        ButterKnife.bind(this);

        StyleListAdapter adapter = new StyleListAdapter(this, android.R.layout.simple_list_item_1, styles);
        mStylesList.setAdapter(adapter);

        mStylesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String style = ((TextView) view).getText().toString();
                Intent intent = new Intent(StyleSearchActivity.this, SearchResultsActivity.class);
                intent.putExtra("params", style);
                intent.putExtra("parent", "style");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }
}
