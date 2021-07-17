package com.example.backyardcinema;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;


public class homeFragment extends Fragment {

    final private ArrayList<Movie> nowShowingMovies = new ArrayList<>();
    final private ArrayList<Movie> comingSoonMovies = new ArrayList<>();

    final private movieAdapter nowShowingAdapter = new movieAdapter(nowShowingMovies);
    final private movieAdapter comingSoonAdapter = new movieAdapter(comingSoonMovies);

    RecyclerView ComingSoonRecycler;

    // Access a Cloud FireStore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public homeFragment() {

        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView NowShowingRecycler = (RecyclerView)view.findViewById(R.id.mNowShowingRecycler);
        NowShowingRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        NowShowingRecycler.setAdapter(nowShowingAdapter);

        RecyclerView ComingSoonRecycler = (RecyclerView)view.findViewById(R.id.mComingSoonRecycler);
        ComingSoonRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        ComingSoonRecycler.setAdapter(comingSoonAdapter);

        loadData();
    }

    private void loadData(){ //load data from fireStore
        db.collection("movies").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    QuerySnapshot documents = task.getResult();
                    nowShowingMovies.clear();

                    for (QueryDocumentSnapshot document : documents){
                        String docID = document.getId(); // get id from the document
                        HashMap<String, Object> movieMap = (HashMap<String, Object>) document.getData();
                        movieMap.put("ID",docID); //put the id in movieMap with the documents
                        Movie m = Movie.fromMapToMovie(movieMap);
                        Log.i("loadDataFromFireStore",m.toString());
                        //add the movies to the list only if the availability property is nowShowing
                        if (m.getAvailability().equals("now showing")){
                            nowShowingMovies.add(m);
                        // notify the adapter of a change to the movie list
                        nowShowingAdapter.notifyDataSetChanged();
                        } else {
                            comingSoonMovies.add(m);
                            comingSoonAdapter.notifyDataSetChanged();
                        }

                    }
                }
            }
        });
    }
}