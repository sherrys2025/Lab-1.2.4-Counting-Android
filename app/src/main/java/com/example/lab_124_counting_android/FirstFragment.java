package com.example.lab_124_counting_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lab_124_counting_android.databinding.FragmentFirstBinding;

import java.io.FileNotFoundException;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = String.valueOf(binding.editText1.getText());
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                Counter counter;
                try {
                    counter = new Counter(filename);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                String toDisplay = "The most common word in the text file \"" + filename + "\" is \"" + counter.topN(0)[0] + " with " + counter.topN(0)[1] + " occurences.";
                binding.textView.setText(toDisplay);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}