package io.exponential.activitywithfragmentvialayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// Implement the Callbacks Interface defined in ZFragment
public class AActivity
        extends AppCompatActivity
        implements ZFragment.Callbacks {

    OnClickListener updateAgeHandler = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // The following two lines of code work as the Fragment's layout is inserted into the
            // Activity's layout. Therefore, the `activity_runtime_variable` TextView is directly
            // accessible via findViewById within this Activity. However, this is not a good
            // approach as it tightly couples the Activity to the implementation of the Fragment.
            // For example, a change to the Fragment's layout (such as changing the TextView's id
            // would break this code.
            //
            // TextView activityRuntimeVariable = (TextView) findViewById(R.id.activity_runtime_variable);
            // activityRuntimeVariable.setText("Hi from AActivity");

            // Get the Fragment instance and call a public method. The Fragment can then update its
            // layout as appropriate. The benefit of this approach vs. the one above is that the
            // Fragment remains a self-contained unit, where it is solely responsible for modifying
            // the views in its layout.
            EditText ageEditText = (EditText) findViewById(R.id.age);
            String age = ageEditText.getText().toString();

            ZFragment zFragment = (ZFragment) getSupportFragmentManager().findFragmentById(R.id.zfragment_container);
            zFragment.updateAge(age);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Button updateAge = (Button) findViewById(R.id.update_age);
        updateAge.setOnClickListener(updateAgeHandler);
    }

    // Implement the updateFirstName() method required by the ZFragment.Callbacks Interface.
    @Override
    public void updateFirstName(String firstName) {
        // Get a reference to the TextView that we want to display firstName in and set the text.
        TextView valuePassedFromZFragment = (TextView) findViewById(R.id.zfragment_runtime_variable);
        valuePassedFromZFragment.setText(firstName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_a, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
