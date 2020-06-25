package vn.com.nms;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.google.android.gms.samples.vision.face.facetracker.R;

import vn.com.nms.api.APIThemAnh;

public class DialogAddImage extends Dialog implements View.OnClickListener{
    private Context context;
    private EditText editText;
    private Button button;
    private byte[] imageData;
    private String idEmployee;
    public DialogAddImage(@NonNull Context context, byte[] imageData) {
        super(context);
        this.context = context;
        this.imageData = imageData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_image);
        editText = findViewById(R.id.editTextId);
        button = findViewById(R.id.button_ok);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        idEmployee = editText.getText().toString();
        DialogLoading dialogLoading = new DialogLoading(this.context);
        APIThemAnh api = new APIThemAnh(this.context, imageData, idEmployee, dialogLoading);
        api.themAnh();
        dialogLoading.show();
        this.cancel();
    }
}
