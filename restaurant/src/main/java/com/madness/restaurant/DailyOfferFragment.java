package com.madness.restaurant;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DailyOfferFragment extends Fragment {

    // TODO: Customize parameter argument names
    // fake content for list
    private ArrayList<String> dishNames = new ArrayList<>();
    private ArrayList<Integer> dishPics =  new ArrayList<Integer>();
    private ArrayList<String> quantities = new ArrayList<>();
    private ArrayList<String> types = new ArrayList<>();
    private ArrayList<String> prices = new ArrayList<>();

    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DailyOfferFragment() {
        //Required empty public constructor
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DailyOfferFragment newInstance(int columnCount) {
        DailyOfferFragment fragment = new DailyOfferFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dailyoffer_list, container, false);
        // initialize the fake content
        initElements();


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyDailyOfferRecyclerViewAdapter(context, dishNames,dishPics,quantities,types,prices));
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manager);
            // add a separator
            DividerItemDecoration decoration = new DividerItemDecoration(getContext(), manager.getOrientation());
            recyclerView.addItemDecoration(decoration);


            // set swipe controller
            SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
                @Override
                public void onLeftClicked(int position) {
                    Log.d("MAD", "onLeftClicked: left");
                    super.onLeftClicked(position);
                }

                @Override
                public void onRightClicked(int position) {
                    Log.d("MAD", "onLeftClicked: right");
                    super.onRightClicked(position);
                }
            });
            ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
            itemTouchhelper.attachToRecyclerView(recyclerView);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    private void initElements(){

        dishNames.add("Pizza Margherita");
        dishPics.add(R.drawable.dish_icon);
        quantities.add(String.valueOf(4));
        types.add("primo");
        prices.add("3.50$");

        dishNames.add("Pasta Carbonara");
        dishPics.add(R.drawable.dish_icon);
        quantities.add(String.valueOf(3));
        types.add("primo");
        prices.add("2.50$");

        dishNames.add("Pezza Invinada");
        dishPics.add(R.drawable.dish_icon);
        quantities.add(String.valueOf(1));
        types.add("secondo");
        prices.add("3.50$");


    }
}
