package com.example.sabbir.ispbilling;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddListActivity extends AppCompatActivity {

    
    EditText idNumber,userName,PhoneNumber,Address;
   // RadioButton active,unactive;
    Button submitButton,imageGetButton;
    private ImageView imageView;

    private Uri imgUri=null;

    DatabaseReference databaseReference,payment;
    DatabaseReference Store_databaseReference,Store_payment;
    private StorageReference mStorageRef;

  //  List<Information> sabbirlist;

    //ListView listView;
    /**
     *   Save data Variable
     */

    String address,phoneNumber,username,userid,checked = null;

    public static final String STORAGE_PATH = "image/";

    public static final int Request_code = 15;




    @Override
    protected void onStart() {
        super.onStart();
      //  Toast.makeText(this,"fsaf  111", Toast.LENGTH_SHORT).show();
        //new Firebase_payment_add().firebase_payment();

        //Toast.makeText(this,"fsaf   22", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        Store_databaseReference = FirebaseDatabase.getInstance().getReference("brackup");


/*

        payment = FirebaseDatabase.getInstance().getReference("payment");
        Store_payment = FirebaseDatabase.getInstance().getReference("brackupp_payment");
*/


        imageGetButton = findViewById(R.id.uploadImageINtoGallary);

        imageView = findViewById(R.id.imagelode);
        idNumber = findViewById(R.id.Add_id_Number);
        userName = findViewById(R.id.Add_Name);
        PhoneNumber = findViewById(R.id.Add_PhoneNuber);
        Address = findViewById(R.id.Add_Address);
        submitButton = findViewById(R.id.Add_Button);
      //  active = findViewById(R.id.radio_button_active);
       // unactive = findViewById(R.id.radio_button_un_active);

        imageButton();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addmethod();
/*
                if(imgUri!= null)
                {
                    final ProgressDialog dialog = new ProgressDialog(AddListActivity.this);

                    dialog.setTitle("Upload Image ....");
                    dialog.show();


                    StorageReference ref = mStorageRef.child(STORAGE_PATH + System.currentTimeMillis()+"."+getImageExt(imgUri));

                    ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            dialog.dismiss();

                            Toast.makeText(AddListActivity.this," Upload Done ", Toast.LENGTH_SHORT).show();

                           *//* ImageUpload imageUpload  = new ImageUpload(editText.getText().toString(),taskSnapshot.getDownloadUrl().toString());

                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(imageUpload);
*//*


                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            dialog.setMessage("Uploaded ..  " + (int) progress + "%");

                        }
                    });

                }*/


            }
        });

    }

    private void imageButton()
    {
        imageGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"Select image"),Request_code);


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Request_code && resultCode == RESULT_OK && data != null  && data.getData() != null)
        {
				imgUri = data.getData();

            try
            {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                imageView.setImageBitmap(bm);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    String imageuri ;

    private void addmethod() {
        //String checked = null;

         userid = idNumber.getText().toString().trim();
         username = userName.getText().toString().trim();
         phoneNumber =  PhoneNumber.getText().toString().trim();
         address = Address.getText().toString().trim();


        if(!TextUtils.isEmpty(userid))
        {

         //   String idm = databaseArtists.push().getKey();

            if(imgUri!= null)
            {
                final ProgressDialog dialog = new ProgressDialog(AddListActivity.this);

                dialog.setTitle("Upload data ....");
                dialog.show();


                StorageReference r = mStorageRef.child(getImageExt(imgUri));

                r.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();

                        Toast.makeText(AddListActivity.this," Upload Done ", Toast.LENGTH_SHORT).show();

                    /*ImageUpload imageUpload  = new ImageUpload(editText.getText().toString(),taskSnapshot.getDownloadUrl().toString());

                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(imageUpload);*/


                        imageuri = taskSnapshot.getDownloadUrl().toString();
                        Information information = new Information( userid ,username ,phoneNumber,address,imageuri);
                        databaseReference.child(userid).setValue(information);

                        Store_databaseReference.child(userid).setValue(information);
                        //Toast.makeText(AddListActivity.this," Complite ", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddListActivity.this," problem ", Toast.LENGTH_SHORT).show();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                        dialog.setMessage("Uploaded ...  " + (int) progress + "%");

                    }
                });

            }
           // imageload();


            idNumber.setText(null);
            userName.setText(null);
            PhoneNumber.setText(null);
            Address.setText(null);
//            imageView.setImageBitmap();

            Uri u=null;
            imageView.setImageURI(u);
            imageView.setImageBitmap(null);

           // active.setSelected(false);


          //  payment.setValue("s : fd");


        } else
            {

                Toast.makeText(AddListActivity.this," You should enter the 'Id Number' ", Toast.LENGTH_SHORT).show();

            }

    }

    public String getImageExt(Uri uri)
    {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

}
