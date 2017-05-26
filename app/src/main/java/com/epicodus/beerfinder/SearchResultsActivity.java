package com.epicodus.beerfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity {
    @Bind(R.id.textView4) TextView mTextView;
    @Bind(R.id.searchResultsView) ListView mSearchResultsList;

    private String[] beers = new String[] {"Pils", "HUB Lager", "Na Zdraví Pils", "Pilsner", "Session Lager", "Kölsch", "Sterling Pils", "Omission Lager"};
    private String[] descriptions = new String[] {"This crisp, refreshing, and substantial pilsner is a classically European pale brew that’s cold-conditioned for six weeks plus. It’s the beer you want in summer, and Heater Allen—currently doubling capacity to meet demand—makes the best in the state, if not the entire country.", "HUB introduced this grainy, golden, 100 percent organic, formerly draft-only brew spiced with Zeus hops in 16-ounce cans just last summer. It already feels like a mainstay.", "True to style, this Czech-inspired pilsner is grainy, bready, spicy, and full-bodied, with a smack of spicy “noble” (read: delicate, hard-to-grow) hops.", "Suddenly Oregon is awash in great pilsners (a welcome development), and Breakside’s is among the best of the bunch, with biscuit-like maltiness and a light, tangy, citrusy kick.", "Barbecue-friendly stubby bottles, critically acclaimed pre-Prohibition-style recipe ... what’s not to love about this crisp, golden brew? It comes in six-packs, but it’s wise to buy by the case.", "A clear, dry, golden ale that’s cold-conditioned like lager, kölsch—the traditional brew of Cologne, Germany—is a great thirst quencher. Occidental’s, with spicy Perle hops, nails it.", "“We love to make lagers,” says founder Jamie Floyd. It shows. This malt-forward, German-style pilsner with spicy, crackling hop notes may be your new summer standard.", "Widmer’s gluten-free lager has the ample body and crisp, hoppy finish one expects from the style, minus the offending proteins."};
    private String[] breweries = new String[] {"Heater Allen Brewing", "Hopworks Urban Brewery", "Southern Oregon Brewing Co", "Breakside Brewing Co", "Full Sail Brewing Co", "Occidental Brewing Co", "Ninkasi Brewing Co", "Widmer Brothers Brewing"};
    private String parentString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        String searchTitle = intent.getStringExtra("params");
        parentString = intent.getStringExtra("parent");
        mTextView.setText("Search results for \"" + searchTitle +"\"");

        BeerArrayAdapter adapter = new BeerArrayAdapter(this, android.R.layout.simple_list_item_1, beers, descriptions, breweries);
        mSearchResultsList.setAdapter(adapter);
    }

    @Override
    public Intent getSupportParentActivityIntent() {
        return getParentActivityIntentImpl();
    }

    @Override
    public Intent getParentActivityIntent() {
        return getParentActivityIntentImpl();
    }

    private Intent getParentActivityIntentImpl() {
        Intent i = null;
        if (this.parentString.equals("beer") ) {
            i = new Intent(this, BeerSearchActivity.class);
        } else if (this.parentString.equals("style")) {
            i = new Intent(this, StyleSearchActivity.class);
        }
        return i;
    }
}
