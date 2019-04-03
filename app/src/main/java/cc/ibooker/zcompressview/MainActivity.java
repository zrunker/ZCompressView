package cc.ibooker.zcompressview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cc.ibooker.zcompressviewlib.CompressTV;
import cc.ibooker.zcompressviewlib.CompressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CompressView compressView = findViewById(R.id.compressLayout);
        compressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "第一个按钮", Toast.LENGTH_SHORT).show();
            }
        });

        CompressView compressView1 = findViewById(R.id.compressLayout1);
        compressView1.setOnCompressClickListener(new CompressTV.OnCompressClickListener() {
            @Override
            public void onCompressClick(View view) {
                Toast.makeText(MainActivity.this, "第二个按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
