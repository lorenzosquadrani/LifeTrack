package com.example.lifetrack;

import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.Navigation;

import java.util.List;

public class ObsListFragment extends Fragment implements OnToggleAlarmListener {
    private ObsRecyclerViewAdapter obsRecyclerViewAdapter;
    private AlarmsListViewModel alarmsListViewModel;
    private RecyclerView alarmsRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        obsRecyclerViewAdapter = new ObsRecyclerViewAdapter(this);

        alarmsListViewModel = ViewModelProviders.of(this).get(AlarmsListViewModel.class);
        alarmsListViewModel.getAlarmsLiveData().observe(this, observations -> {
            if (observations != null) {
                obsRecyclerViewAdapter.setObs(observations);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_obs_list, container, false);

        alarmsRecyclerView = view.findViewById(R.id.fragment_listobs_recylerView);
        alarmsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        alarmsRecyclerView.setAdapter(obsRecyclerViewAdapter);

        Button AddObs = view.findViewById(R.id.fragment_ObsList_CreateObs);
        AddObs.setOnClickListener(
                v -> Navigation.findNavController(v).navigate(R.id.action_ObsListFragment_to_CreateObsFragment)
        );

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onToggle(Observation observation) {
        if (observation.isStarted()) {
            observation.cancelAlarm(getContext());
        }
        else {
            observation.schedule(getContext());
        }
        alarmsListViewModel.update(observation);
    }
}