package io.exponential.activitywithfragmentvialayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
// I imported OnClickListener for convenience
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Callbacks} interface
 * to handle interaction events.
 * Use the {@link ZFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZFragment extends Fragment {
    // Key used to get/set input param in arguments Bundle
    private static final String ARG_PASSED_IN_FROM_ACTIVITY = "welcomeMessage";

    // Individual parameter. This private member variable is required so that the parameter can be
    // shared by the various lifecycle callback methods.
    private String paramPassedInFromActivity;

    // A private member to store an instance of the Callbacks interface that is implemented by the
    // parent Activity. The member `callbacks` below allows you to access the implementation of each
    // method defined by the Callbacks Interface in the parent Activity.
    //
    // The setup process is:
    // - In Fragment, define an Interface of callback methods (I like the name Callbacks as it's
    //   short and sweet)
    // - In Fragment, create a private member that contains an instance of the Callbacks Interface
    // - In Fragment, bind event handlers to views in `onCreateView()`
    // - In Activity, add `implements` to the Activity's class definition
    // - In Activity, implement the Interface
    //
    // The runtime communication process flow is:
    // - The user clicks (or interacts) with a view within the Fragment's layout
    // - The event handler defined in this Fragment is called
    // - The event handler's body calls callbacks.someCallbackMethod(), which then calls the
    //   Interface method defined in the Activity
    // - The callback method in the Activity executes
    //
    private Callbacks callbacks;

    /**
     * Factory method to create a new instance of this fragment.
     *
     * @param inputParamFromActivityLocalVariable A string passed in from the Activity when creating
     *                                            and instance of this Fragment.
     * @return A new instance of fragment ZFragment.
     */
    public static ZFragment newInstance(String inputParamFromActivityLocalVariable) {
        // Create a new instance of ZFragment using the empty constructor below.
        ZFragment fragment = new ZFragment();

        // Create a new Bundle so that the parameter(s) passed from from the Activity to this
        // instance can be passed by Android to this Fragment when it's onCreate() lifecycle
        // callback method is called.
        Bundle args = new Bundle();

        // Save a key/value pair as a Bundle. Notice how this is similar to how you pass data via
        // an Intent. In both cases, data is passed via a Bundle.
        args.putString(ARG_PASSED_IN_FROM_ACTIVITY, inputParamFromActivityLocalVariable);

        // TODO: IS getArguments() ACCESSIBLE IN OTHER LCM??????????????????????????????????????????
        // TODO: IS getArguments() ACCESSIBLE IN OTHER LCM??????????????????????????????????????????
        // TODO: IS getArguments() ACCESSIBLE IN OTHER LCM??????????????????????????????????????????
        // TODO: IS getArguments() ACCESSIBLE IN OTHER LCM??????????????????????????????????????????
        // TODO: IS getArguments() ACCESSIBLE IN OTHER LCM??????????????????????????????????????????
        // TODO: IS getArguments() ACCESSIBLE IN OTHER LCM??????????????????????????????????????????
        // Set the arguments on this fragment so that they can be accessed via getArguments() in
        // other callback methods, such as onCreate().
        fragment.setArguments(args);

        // The newly created Fragment instance is returned to the caller, which will be some code
        // in the Activity.
        return fragment;
    }

    public ZFragment() {
        // Required empty public constructor
    }

    OnClickListener updateFirstNameHandler = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // Get a reference to the first_name EditText control. In this function, v is a
            // reference to the Button view, not to the root of the Fragment's view hierarchy. As a
            // result, you must call `getView().findViewById()`.
            EditText firstNameEditText = (EditText) getView().findViewById(R.id.first_name);
            // Save the name entered by the user in the first_name EditText control into the
            // firstName variable
            String firstName = firstNameEditText.getText().toString();

            // Call the Activity's implementation of an Interface method
            if (callbacks != null) {
                callbacks.updateFirstName(firstName);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Get each argument and save each value in a private member variable. The private
            // member, and therefor the argument's value, can be accessed in other lifecycle
            // callback methods by using the variable.
            paramPassedInFromActivity = getArguments().getString(ARG_PASSED_IN_FROM_ACTIVITY);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment and get a reference to it so that we can attach
        // event handlers, programmatically update the UI, etc.
        View view = inflater.inflate(R.layout.fragment_z, container, false);

        Button updateFirstName = (Button) view.findViewById(R.id.update_first_name);
        updateFirstName.setOnClickListener(updateFirstNameHandler);

        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (callbacks != null) {
//            callbacks.updateFirstName(uri);
//        }
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Callbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    // Define a public method that can be used by the Activity to pass data into this Fragment
    public void updateAge(String age) {
        TextView activityRuntimeVariable = (TextView) getView().findViewById(R.id.activity_runtime_variable);
        activityRuntimeVariable.setText(age);
    }

    /**
     * Define an interface that must be implemented by a parent Activity.
     * <p/>
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface Callbacks {
        public void updateFirstName(String firstName);
    }

}
