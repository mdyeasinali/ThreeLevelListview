package com.talhahasanzia.threelevellistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * Add in parent for more main category
 * Define array for genre (subcategory) for each parent category added
 * Define LinkedHasMap for each subcategory where key is subcategory name, and value is a string array
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Expandable list view.
     */
    ExpandableListView expandableListView;


    /**
     * The Parent Group Names.
     */
    String[] parent = new String[]{"MOVIES", "GAMES", "Yeasin"}; // comment this when uncomment bottom
    //String[] parent = new String[]{"MOVIES", "GAMES", "SERIALS"}; // example for 3 main category lists

    /**
     * The Movies Second List.
     */
    // We have two  main category. (third one is left for example addition)
    String[] movies = new String[]{"Horror", "Action", "Thriller/Drama"};


    /**
     * The Horror movie list.
     */
    // movies category has further genres
    String[] horror = new String[]{"Conjuring", "Insidious", "The Ring"};
    /**
     * /**
     * Datastructure for Third level movies.
     */
    LinkedHashMap<String, String[]> thirdLevelMovies = new LinkedHashMap<>();
    /**
     * Datastructure for Third level games.
     */
    LinkedHashMap<String, String[]> thirdLevelGames = new LinkedHashMap<>();

    /**
     * Datastructure for Third level Serials.
     */
    // LinkedHashMap<String, String[]> thirdLevelSerials = new LinkedHashMap<>();


    /**
     * The Second level.
     */
    List<String[]> secondLevel = new ArrayList<>();


    /**
     * The Data.
     */
    List<LinkedHashMap<String, String[]>> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // second level category names (genres)
        secondLevel.add(movies);
        secondLevel.add(movies);
        // secondLevel.add(serials);

        // movies category all data
        thirdLevelMovies.put(movies[0], horror);
        thirdLevelMovies.put(movies[1], horror);
        thirdLevelMovies.put(movies[2], horror);


        // all data
        data.add(thirdLevelMovies);
        data.add(thirdLevelGames);
        //data.add(thirdLevelSerials);


        // expandable listview
        expandableListView = (ExpandableListView) findViewById(R.id.expandible_listview);

        // parent adapter
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, parent, secondLevel, data);


        // set adapter
        expandableListView.setAdapter(threeLevelListAdapterAdapter);


        // OPTIONAL : Show one list at a time
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });


    }
}
