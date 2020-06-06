package app.ij.alertdialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.ij.alertdialogs.R;

public class MainActivity extends AppCompatActivity {
//YouTube Tutorial Series: https://www.youtube.com/playlist?list=PLLmkb5CTw5rSCT3szl8VLBjKhLC8ttt8B
    Button button;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("IJ Apps");
                dialog.setMessage("Loading Video...");
                dialog.setIndeterminate(false);
                dialog.setCancelable(true);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                dialog.show();

                final int[] progress = {0};
                dialog.setProgress(progress[0]);

                new CountDownTimer(5000, 50){
                    @Override
                    public void onTick(long l) {
                        progress[0]++;
                        dialog.setProgress(progress[0]);
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("IJ Apps");
                dialog.setMessage("Loading Video...");
                dialog.setIndeterminate(true);
                dialog.setCancelable(true);
                dialog.show();
            }
        });

//Tutorial on making custom dialogs:  https://youtu.be/iZnOIcSQ8n0
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.alert_dialog);

                Button back, done;
                back = dialog.findViewById(R.id.goBack);
                done = dialog.findViewById(R.id.done);
                final EditText age;
                age = dialog.findViewById(R.id.age);

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Back clicked. Age is " + age.getText(), Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Done clicked Age is " + age.getText(), Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                dialog.show();

            }
        });

//Tutorials on making Alert Dialogs
//   https://youtu.be/n8oasrJs_eY
//   https://youtu.be/EJ4LaV_DNh8
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("IJ Apps");
                    builder.setMessage("Are you subscribed to IJ Apps?");

                    builder.setIcon(R.drawable.icon);
                    builder.setCancelable(false);
                    builder.setNeutralButton("IDK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Subscribe!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Great!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), ":(", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            Toast.makeText(getApplicationContext(), "The dialog was dismissed.", Toast.LENGTH_SHORT).show();

                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }

                return false;
            }
        });

    }
}
