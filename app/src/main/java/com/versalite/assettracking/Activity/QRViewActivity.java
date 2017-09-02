package com.versalite.assettracking.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.versalite.assettracking.R;

import butterknife.ButterKnife;

import static android.R.attr.width;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;



/**
 * Created by nandhu on 28/8/17.
 *
 */

public  class QRViewActivity extends AppCompatActivity{

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_view_acitivity);

        ButterKnife.bind(this);

        if (getIntent() == null){
            throw new NullPointerException("Intent cannot be null");
        }
        String qrToCode = getIntent().getStringExtra("qr");
        String Name = getIntent().getStringExtra("name");
        String AssetId  = getIntent().getStringExtra("id");
        String assetType= getIntent().getStringExtra("type");
        String assetDescription = getIntent().getStringExtra("description");
//        String AsssetTag = getIntent().getStringExtra();
        String orgname = getIntent().getStringExtra("orgname");
        String  location = getIntent().getStringExtra("location");
        String deptName = getIntent().getStringExtra("deptName");
        String roomNo = getIntent().getStringExtra();


        ImageView imageView = (ImageView) findViewById(R.id.qr_view);
        try {
            Bitmap bitmap = encodeAsBitmap(qrToCode);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();

        }


//        view.setDrawingCacheEnabled(true);
//        Bitmap b = view.getDrawingCache();
//        b.compress(CompressFormat.JPEG, 95, new FileOutputStream("/some/location/image.jpg"));
    }



    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, WIDTH, 0, 0, w, h);
        return bitmap;
    }
}
