package com.llama.basilisk.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.llama.basilisk.Basilisk;
import com.llama.basilisk.BindModel;
import com.llama.basilisk.binder.Property;
import com.llama.basilisk.math.Add;
import com.llama.basilisk.math.DivideBy;
import com.llama.basilisk.math.Pow;
import com.llama.basilisk.rx.mapper.Mapper;
import com.llama.basilisk.sample.model.TwoWayModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TwoWayModel twoWayModel = new TwoWayModel();

    @BindModel
    @Bind(R.id.basilisk_edit_text_1)
    EditText editText1;

    @BindModel
    @Bind(R.id.basilisk_edit_text_2)
    EditText editText2;

    @BindModel
    @Bind(R.id.basilisk_text_view_1)
    TextView textView1;

    @BindModel
    @Bind(R.id.basilisk_text_view_2)
    TextView textView2;

    @BindModel
    @Bind(R.id.basilisk_text_view_3)
    TextView textView3;

    @BindModel
    @Bind(R.id.basilisk_frame_1)
    FrameLayout frame1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Basilisk.bind(this.twoWayModel, this.editText1);
        Basilisk.bind(this.twoWayModel, this.editText2);
        Basilisk.bind(this.twoWayModel, this.textView1,
                Mapper.forText()
                        .regexReplace("(luigi)", "mario likes -> $1")
                        .sort()
                        .explode()
        );
        Basilisk.bind(this.twoWayModel, this.textView2, Mapper.forText().regexReplace("(e\\@mail.com)", "email -> $1"));
        Basilisk.bind(this.twoWayModel, this.textView3, Mapper.forText().reverse());
        Basilisk.bind(
                this.twoWayModel,
                this.frame1,
                Mapper.forProperty(Property.WIDTH)
                        .density(this.getResources())
                        .math(
                                new Add(10),
                                new Pow(2),
                                new DivideBy(2)
                        )
        );
        Basilisk.bind(this.twoWayModel, this.frame1, Mapper.forProperty(Property.HEIGHT).density(this.getResources()));
        this.twoWayModel.bind();

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
