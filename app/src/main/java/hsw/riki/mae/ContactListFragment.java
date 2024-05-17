package hsw.riki.mae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import hsw.riki.mae.databinding.FragmentContactListBinding;

public class ContactListFragment extends Fragment {
    private static final String ARG_CONTENT = "content";

    private String content;

    private FragmentContactListBinding binding;

    public static ContactListFragment newInstance(String content)
    {
        ContactListFragment lf = new ContactListFragment();
        Bundle args = new Bundle();

        args.putString(ARG_CONTENT, content);
        lf.setArguments(args);
        return lf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            content = getArguments().getString(ARG_CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContactListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.content.setText(content);

        binding.content.setOnClickListener(v -> {
            FragmentTransaction ta = requireActivity().getSupportFragmentManager().beginTransaction();
            ta.remove(this);
            ta.addToBackStack(null);
            ta.commit();
        });
    }
}
