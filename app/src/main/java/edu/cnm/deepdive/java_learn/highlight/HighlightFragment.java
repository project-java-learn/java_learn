package edu.cnm.deepdive.java_learn.highlight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.view.GameFragment;
import java.io.IOException;
import java.io.InputStream;


public class HighlightFragment extends GameFragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_highlight, container, false);
    TextView textView = view.findViewById(R.id.highlight_text);
    InputStream is = getActivity().getResources().openRawResource(R.raw.code);
    try {
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      String text = new String(buffer);
      textView.setText(text);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return view;
  }
}
