package com.example.volley_captech_blog.utopia.VolleyDemo;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley_captech_blog.R;
import com.example.volley_captech_blog.common.VolleyCaptechApplication;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StringRequestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StringRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StringRequestFragment extends MyBaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    private static final String URL = "http://www.w3school.com.cn/html5/index.asp";
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;

    private TextView mTextWhere;
    private TextView mTextContent;
    private ProgressBar mProgressBar;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DemoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StringRequestFragment newInstance() {
        StringRequestFragment fragment = new StringRequestFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public StringRequestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            mRequestQueue = ((VolleyCaptechApplication) this.getActivity().getApplication()).getQueue();
            stringRequest = new StringRequest(URL, new ResponseListener(), new ErrorListener());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_string_request, container, false);
//        String demo = getResources().getStringArray(R.array.demo_array)[getArguments().getInt(ARGS_POSITION_NUMBER)];

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.INVISIBLE);
        Button makeRequest = (Button) rootView.findViewById(R.id.make_request);
        Button clear = (Button) rootView.findViewById(R.id.clear);
        mTextWhere = (TextView) rootView.findViewById(R.id.text_where);
        mTextContent = (TextView) rootView.findViewById(R.id.text_content);

        makeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRequestQueue.getCache().get(URL) != null) {
                    String cachedResponse = new String(mRequestQueue.getCache().get(URL).data);
                    mTextWhere.setText("Data From Cache");
                    mTextContent.setText(cachedResponse);
                    mProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    mRequestQueue.add(stringRequest);
                    mProgressBar.setVisibility(View.VISIBLE);
                }


            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRequestQueue.getCache().get(URL) != null)
                    mRequestQueue.getCache().remove(URL);
                mTextWhere.setText("Clear done");
                mTextContent.setText("");
                mProgressBar.setVisibility(View.INVISIBLE);

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int position) {
        if (mListener != null) {
            mListener.onFragmentInteraction(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int position);
    }

    private class ResponseListener implements Response.Listener<String> {

        @Override
        public void onResponse(String response) {
            mTextWhere.setText("Data From Network");
            mTextContent.setText(response);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private class ErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            mTextWhere.setText("Error = " + error);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }


}
