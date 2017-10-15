package com.versalite.assettracking.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.versalite.assettracking.OpenSansTextView;
import com.versalite.assettracking.R;
import com.versalite.assettracking.WhitenyBooksFont;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;


/**
 * Created by nandhu on 28/8/17.
 *
 */

public class QRViewActivity extends AppCompatActivity {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    @BindView(R.id.qr_view) ImageView qrView;
    @BindView(R.id.asset_id) OpenSansTextView mAssetId;
    @BindView(R.id.assetName) OpenSansTextView mAssetName;
    @BindView(R.id.org_name) OpenSansTextView mOrgName;
    @BindView(R.id.location_text) OpenSansTextView mlocation;
    @BindView(R.id.deptname) OpenSansTextView mDeptname;
    @BindView(R.id.room_name) OpenSansTextView mRoomName;
    @BindView(R.id.type) OpenSansTextView mtype;
    @BindView(R.id.asset_root_qr) LinearLayout mAssetRoot;
    @BindView(R.id.send_mail) Button mSendMail;

//    @BindView(R.id.sb_image)
//    Button msaveButton;
    String name;
    private String TAG = "QR VIEW ACTIIVTY";
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    saveImage();

                }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_view_acitivity);

        ButterKnife.bind(this);

        if (getIntent() == null) {
            throw new NullPointerException("Intent cannot be null");
        }
        String qrToCode = getIntent().getStringExtra("qr");
        name = getIntent().getStringExtra("name");
        long AssetId = getIntent().getLongExtra("id",00000000);
        String assetType = getIntent().getStringExtra("type");
        String assetDescription = getIntent().getStringExtra("description");
//        String AsssetTag = getIntent().getStringExtra();
        String orgname = getIntent().getStringExtra("orgName");
        String location = getIntent().getStringExtra("location");
        String deptName = getIntent().getStringExtra("deptName");
        String roomNo = getIntent().getStringExtra("roomNo");

        mAssetRoot.setDrawingCacheEnabled(true);
        mAssetName.setText(name);
        mtype.setText(assetType);
        mAssetId.setText(""+AssetId);
//        dmDscr.setText(assetDescription);
        mDeptname.setText(deptName);
        mRoomName.setText(roomNo);
        mOrgName.setText(orgname);
        mlocation.setText(location);
        mSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPerm();
            }




        });

        mSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }


        });





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

    private void sendMail() {
        saveImage();
    }

    private void checkPerm() {
        Log.d(TAG, "checkPerm: ");
        verifyStoragePermissions(this);
    }


    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }
    public  void verifyStoragePermissions(Activity activity) {


        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        else{
            // Already HAve permission
            saveImage();
        }

    }

    private void saveImage() {

        try {
            Log.d(TAG, "saveImage: ");
//            new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_PICTURES), albumName);

//            mAssetRoot.buildDrawingCache();
            Bitmap b = loadBitmapFromView(mAssetRoot);

//            //// TODO: 12/9/17 send Mail Here
//            File fileName = this.getFilesDir();
//            Log.d(TAG, "Absolute Paht: File Name "+fileName.getAbsolutePath());
//            Log.d(TAG, " Path: File Name "+fileName.getPath());
//            Log.d(TAG, "name: File Name "+fileName.getName());
//            Log.d(TAG, "Canonical: File Name "+fileName.getCanonicalPath());
//            Log.d(TAG, "URi To String"+fileName.toURI().toString());
//
////            Absolute Paht: File Name /data/data/com.versalite.assettracking/files
//            // we have files directory
//            File f  = new File(fileName.getAbsoluteFile() + "/assetId.jpg");

//            String imageName = name+".jpg";
            java.io.File fileName = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES)+  "/" + name+".jpg");
            Log.d(TAG, "saveImage: at  "+fileName.getAbsolutePath());
            b.compress(Bitmap.CompressFormat.JPEG, 95, new FileOutputStream(fileName));
//            MediaStore.Images.Media.insertImage(getContentResolver(), b, "assestI" , "some");

//            addImageToGallery(f.getAbsolutePath(),this);
//
//
//
//            Intent i = new Intent(this,SendEmail.class);
//            startActivity(i);
        }
        catch (Exception e){
            Log.d(TAG, "saveImage: Exception "+e.getLocalizedMessage());
        }
    }


    public static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
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
