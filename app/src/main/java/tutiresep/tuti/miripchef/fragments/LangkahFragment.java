package tutiresep.tuti.miripchef.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tutiresep.tuti.miripchef.R;

/**
 * Created by Rumah on 5/29/2017.
 */
public class LangkahFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private TextView tabText;

    public LangkahFragment() {
        // Required empty public constructor
    }

    public static LangkahFragment newInstance(String param1) {
        LangkahFragment fragment = new LangkahFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_langkah, container, false);

        tabText= (TextView) convertView.findViewById(R.id.textViewLangkah);
        tabText.setText(mParam1);
        return convertView;
    }

}
