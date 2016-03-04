package br.com.mgalhardo.onyo.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.mgalhardo.onyo.R;
import br.com.mgalhardo.onyo.ui.base.BaseFragment;

public class EmptyFragment extends BaseFragment {

    public static EmptyFragment newInstance() {
        Bundle args = new Bundle();

        EmptyFragment fragment = new EmptyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

}
