package com.mahavira.partner.dashboard.presentation;

import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mahavira.partner.base.entity.EncryptedString;
import com.mahavira.partner.base.prefs.UserSharedPrefs;
import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.base.presentation.ExtraInjectable;
import com.mahavira.partner.dashboard.R;
import com.mahavira.partner.dashboard.databinding.ActivityDashboardBinding;
import com.mahavira.partner.inventory.presentation.InventoryRouter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.inject.Inject;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> implements ExtraInjectable {

    public static final String EMAIL_EXTRA = "email";

    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 100;

    private String mPartnerEmail;

    @Inject
    InventoryRouter mInvRouter;

    @Inject
    UserSharedPrefs mPrefs;

    @Override
    public int getViewModelBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDataBinding().returnGamesBtn.setOnClickListener(v -> mInvRouter.goToReturnList(this, mPartnerEmail));

        getDataBinding().generateQrStoreBtn.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
            } else {
                createQrCodeImageFile();
            }
        });

        getViewModel().getPartnerData().observe(this, partner -> {
            if(partner != null) {
                switch (partner.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Load Profile Success", Toast.LENGTH_SHORT).show();
                        mPrefs.setLoggedUserEmail(partner.data.getEmail());
                        break;
                    case ERROR:
                        Toast.makeText(this, "Load Profile Failed, " + partner.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().getBorrowedGames().observe(this, listResource -> {
            if(listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:
                        getDataBinding().setBorrowed(listResource.data);
                        break;
                    case ERROR:
                        break;
                }
            }
        });

        getViewModel().attemptGetProfile(mPartnerEmail);

        getViewModel().attemptSaveLoggedProfile(mPartnerEmail);

        getViewModel().attemptGetBorrowedGames(mPartnerEmail);
    }

    private void createQrCodeImageFile() {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            EncryptedString ciphertextString = new EncryptedString(mPartnerEmail).encryptMsg();

            BitMatrix bitMatrix = writer.encode(ciphertextString.getValue(), BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            FileOutputStream out = null;
            try {
                String path = Environment.getExternalStorageDirectory().toString();
                File file = new File(path, "qrcode.png");
                out = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                // PNG is a lossless format, the compression factor (100) is ignored
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void injectExtras(@NonNull Bundle extras) {
        if(extras.containsKey(EMAIL_EXTRA)) {
            mPartnerEmail = extras.getString(EMAIL_EXTRA);
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
            @NonNull final int[] grantResults) {
        switch(requestCode) {
            case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    createQrCodeImageFile();
                }
            }
        }
    }
}
