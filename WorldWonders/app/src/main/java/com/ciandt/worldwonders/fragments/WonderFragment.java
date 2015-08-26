package com.ciandt.worldwonders.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.activity.WonderDetailActivity;
import com.ciandt.worldwonders.adapters.WonderFragmentAdapter;
import com.ciandt.worldwonders.adapters.WonderItemAdpater;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.repository.WondersRepository;

import java.util.List;

public class WonderFragment extends Fragment {

    final int QUANTITY_ITEMS = 3;
    int dismissProgressDialogFlag = 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_world_wonders, container, false);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager_wonder);

        WondersRepository wondersRepository = new WondersRepository(getContext());

        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "Wonders", "Carregando...");

        wondersRepository.getRandon(QUANTITY_ITEMS, new WondersRepository.WonderRandomListener() {

            @Override
            public void onWonderRandom(Exception e, List<Wonder> wonderList) {

                WonderFragmentAdapter wonderFragmentAdapter = new WonderFragmentAdapter(getActivity().getSupportFragmentManager(), wonderList);
                viewPager.setAdapter(wonderFragmentAdapter);
                checkDismissDialog(progressDialog);

            }
        });

        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        wondersRepository.getAll(new WondersRepository.WonderAllListener() {

            @Override
            public void onWonderAll(Exception e, List<Wonder> wonderList) {

                WonderItemAdpater wonderItemAdpater = new WonderItemAdpater(getContext(), wonderList);
                wonderItemAdpater.setOnSelectItem(new WonderItemAdpater.OnSelectItem() {

                    @Override
                    public void onSelectItem(Wonder wonder) {

                        Intent intent = new Intent(getContext(), WonderDetailActivity.class);
                        intent.putExtra("wonder", wonder);
                        startActivity(intent);
                    }
                });

                recyclerView.setAdapter(wonderItemAdpater);
                checkDismissDialog(progressDialog);
            }
        });

        return view;
    }

    private void checkDismissDialog(ProgressDialog progressDialog) {

        if(dismissProgressDialogFlag < 2) {
            progressDialog.dismiss();
        } else {
            dismissProgressDialogFlag --;
        }
    }
}
