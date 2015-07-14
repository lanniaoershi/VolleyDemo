package com.example.volley_captech_blog.utopia.VolleyDemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.volley_captech_blog.ImageSrouce.ImageUri;
import com.example.volley_captech_blog.R;
import com.example.volley_captech_blog.common.VolleyCaptechApplication;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImageRequestFragment} interface
 * to handle interaction events.
 * Use the {@link ImageRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageRequestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GridView mGridView;
    private String[]  mSamples ;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageRequestFragment newInstance(String param1, String param2) {
        ImageRequestFragment fragment = new ImageRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ImageRequestFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSamples = ImageUri.getImageUri();
        mImageLoader = ((VolleyCaptechApplication)getActivity().getApplicationContext()).getImageLoader();
        mRequestQueue = ((VolleyCaptechApplication)getActivity().getApplicationContext()).getQueue();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_request, container, false);
        mGridView = (GridView) rootView.findViewById(android.R.id.list);
        mGridView.setAdapter(new SampleAdapter());
        return rootView;
    }

    private class SampleAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mSamples.length;
        }

        @Override
        public Object getItem(int position) {
            return mSamples[position];
        }

        @Override
        public long getItemId(int position) {
            return mSamples[position].hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.sample_dashboard_image,
                        container, false);
            }
            NetworkImageView sampleImage = (NetworkImageView) convertView.findViewById(R.id.sampleImage);
            sampleImage.setScaleType(ImageView.ScaleType.CENTER);
            sampleImage.setImageUrl(mSamples[position], mImageLoader);

            return convertView;
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
