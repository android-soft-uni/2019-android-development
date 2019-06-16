package com.inveitix.a08_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inveitix.a08_fragments.web.Api;
import com.inveitix.a08_fragments.web.Fact;

import java.util.List;


public class DataFragment extends Fragment {

    private static final String TAG = "DataFragment";
    private static final String EXTRA_ANIMAL_TYPE = "com.inveitix.a08_fragments.EXTRA_AN_TP";

    private String animalType;

//    private OnFragmentInteractionListener mListener;

    public static DataFragment newInstance(String animalType) {
        DataFragment fragment = new DataFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_ANIMAL_TYPE, animalType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            animalType = getArguments().getString(EXTRA_ANIMAL_TYPE);
        }
        Api api = new Api();
        api.getRandomFacts(animalType, 5, new Api.DataListener() {
            @Override
            public void onFactsReceived(List<Fact> data) {
                Log.e(TAG, "Facts are here: " + data.toString());
            }
        });
    }

    //    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement TeamFragmentListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
