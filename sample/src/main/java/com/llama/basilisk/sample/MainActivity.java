package com.llama.basilisk.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.llama.basilisk.Basilisk;
import com.llama.basilisk.sample.model.TwoWayModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TwoWayModel twoWayModel = new TwoWayModel();

    @Bind(R.id.basilisk_edit_text_1)
    EditText editText1;

    @Bind(R.id.basilisk_edit_text_2)
    EditText editText2;

    @Bind(R.id.basilisk_text_view_2)
    TextView textView2;

    @Bind(R.id.basilisk_text_view_3)
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Basilisk.bindModel(this.twoWayModel, this.editText1);
        Basilisk.bindModel(this.twoWayModel, this.editText2);
        Basilisk.bindTextView(this.editText1, this.twoWayModel);
        Basilisk.bindTextView(this.editText2, this.twoWayModel);

        Basilisk.bindTextView(this.textView2, this.twoWayModel);
        Basilisk.bindTextView(this.textView3, this.twoWayModel);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
