package com.bootcamp.walletmanager.CustomView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bootcamp.walletmanager.Application.FileProvider;
import com.bootcamp.walletmanager.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LayoutCaptureDialog {

    Dialog layoutCapture;

    public LayoutCaptureDialog(final Activity activity, final Bitmap bitmap) {

        layoutCapture = new Dialog(activity);
        layoutCapture.requestWindowFeature(Window.FEATURE_NO_TITLE);
        layoutCapture.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        layoutCapture.setContentView(R.layout.layout_capture_dialog);

        final ImageView imageView = (ImageView) layoutCapture.findViewById(R.id.display);
        imageView.setImageBitmap(bitmap);
        final Button share = (Button) layoutCapture.findViewById(R.id.share);
        Button cancel = (Button) layoutCapture.findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage(activity, bitmap);
            }
        });
    }

    private void shareImage(Activity activity, Bitmap bitmap) {
        Uri imageUri = null;
        File file = null;
        FileOutputStream fos1 = null;
        try {
            File folder = new File(activity.getCacheDir() + File.separator + "My Temp Files");

            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdir();
            }

            String filename = "img.jpg";
            file = new File(folder.getPath(), filename);

            fos1 = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos1);

            imageUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".my.package.name.provider", file);
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/*");
            share.putExtra(Intent.EXTRA_STREAM, imageUri);
            activity.startActivity(Intent.createChooser(share,"Chia sẻ qua:"));
        } catch (Exception ex) {

        } finally {
            try {
                fos1.close();
                hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void hide() {
        if (layoutCapture.isShowing())
            layoutCapture.cancel();
    }

    public void show() {
        if (!layoutCapture.isShowing())
            layoutCapture.show();
    }

    public boolean isShowing() {
        return layoutCapture.isShowing();
    }
}
