package geek.app.rgbpanel;

import android.app.Dialog;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private Button b_show;
    private View panel_view;
    private Dialog panel_dialog;
    private ConstraintLayout cl_main;
    private SeekBar sb_red;
    private SeekBar sb_green;
    private SeekBar sb_blue;
    private Button b_set;
    private Button b_reset;
    private View v_color;
    private ImageButton b_cancel;

    private int R_color = 0x26;
    private int  G_color = 0x2f;
    private int  B_color = 0x40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_show = findViewById(R.id.b_show);
        cl_main = findViewById(R.id.cl_main);
        //panel view
        panel_view = LayoutInflater.from(this).inflate(R.layout.rgb_panel_dialog,cl_main,false);
        //dialog for the panel
        panel_dialog = new Dialog(this);
        panel_dialog.setContentView(panel_view);
        //to make the panel background transparent
        panel_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //panel buttons and seek bars
        b_cancel = panel_view.findViewById(R.id.ib_close);
        v_color = panel_view.findViewById(R.id.v_color);
        sb_red = panel_view.findViewById(R.id.sb_red);
        sb_green = panel_view.findViewById(R.id.sb_green);
        sb_blue = panel_view.findViewById(R.id.sb_blue);
        b_set = panel_view.findViewById(R.id.b_set);
        b_reset = panel_view.findViewById(R.id.b_reset);
        //canel click
        b_cancel.setOnClickListener(v -> panel_dialog.dismiss());
        //getting the values from seek bars
        sb_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                R_color = progress;
                v_color.setBackgroundColor( Color.rgb(progress,G_color,B_color));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        sb_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                G_color = progress;
                v_color.setBackgroundColor(Color.rgb(R_color,progress,B_color));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        sb_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                B_color = progress;
                v_color.setBackgroundColor(Color.rgb(R_color,G_color,progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        //click for set button to change the background of the activity
        b_set.setOnClickListener(v -> {
            cl_main.setBackgroundColor(Color.rgb(R_color,G_color,B_color));
            panel_dialog.dismiss();
        });
        //click for reset button to change the background to default color
        b_reset.setOnClickListener(v -> panel_dialog.dismiss());

        //to show the panel
        b_show.setOnClickListener(v -> panel_dialog.show());
    }
}
