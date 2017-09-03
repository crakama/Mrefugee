package com.dadaabcamps.mrefugee.UpdateDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.dadaabcamps.mrefugee.R;
import com.dadaabcamps.mrefugee.FirebaseModels.TownsModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class UpdateTownInfo extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference databaseReference;
    private StorageReference storageReference,myfileRef;
    private EditText textSchoolInfo,townName,hospInfo;
    Button btnUpdateTownInfo;
    ImageButton choosepic;
    ImageView imageView;
    TownsModel townsModel;
    private ProgressDialog progressDialog;
    String DOWNLOAD_URL, imgDownloadUrl;
    private static final int SELECT_PICTURE_ID = 1;
    private static final String KEY_FILE_URI = "key_file_uri";
    private static final String KEY_DOWNLOAD_URL = "key_download_url";

    private Uri mDownloadUrl = null;
    private Uri mFileUri = null;
    String newmDownloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Restore instance state
        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable(KEY_FILE_URI);
            newmDownloadUrl = savedInstanceState.getParcelable(KEY_DOWNLOAD_URL);
        }


        setContentView(R.layout.activity_update_town_info);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        choosepic = (ImageButton) findViewById(R.id.ImgBtnTowns);
        imageView =(ImageView) findViewById(R.id.ImgBtn) ;
        textSchoolInfo = (EditText) findViewById(R.id.editTextSchoolInfo);
        townName = (EditText) findViewById(R.id.editTextPopulationInfo);
        hospInfo = (EditText) findViewById(R.id.editTextHospInfo);
        btnUpdateTownInfo = (Button) findViewById(R.id.btnUpdateTownInfo);
        progressDialog = new ProgressDialog(this);

        btnUpdateTownInfo.setOnClickListener(UpdateTownInfo.this);
        choosepic.setOnClickListener(UpdateTownInfo.this);

    }
    public static final String TOWNS= "TownsModel";

    private  void saveTownInfo(){
        String school = textSchoolInfo.getText().toString();
        String townname = townName.getText().toString();
        String hospital = hospInfo.getText().toString();
        //String imgDownloadUrl = "URL" ;

        /*** Set Data    */
        townsModel = new TownsModel();
        townsModel.setTownName(townname);
        townsModel.setSchoolInfo(school);
        townsModel.setHospitalInfo(hospital);
        //townsModel.setTownImgUrl( );
        if (newmDownloadUrl != null) {
            townsModel.setTownImgUrl(newmDownloadUrl.toString());
        }else {
            townsModel.setTownImgUrl("URL IS NULL!!");
        }

        //TownsModel townsModel = new TownsModel(townname,school,hospital,imgDownloadUrl);
        databaseReference.child(TOWNS).push().setValue(townsModel);
    }


    @Override
    public void onClick(View v) {
        if(v == btnUpdateTownInfo){
             onUploadButtonClick();
            saveTownInfo();
        }

        if(v == choosepic ){
            uploadtownInfo();
        }

    }
    private void uploadtownInfo(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"),SELECT_PICTURE_ID );
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            if(requestCode==SELECT_PICTURE_ID){
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    Log.i("IMAGE PATH TAG", "Image Path : " + path);
                    // Set the image in ImageView
                    imageView.setImageURI(selectedImageUri);

                }
            }
        }

//        if (requestCode == SELECT_PICTURE_ID && resultCode == RESULT_OK && data != null) {
//            //set the progress dialog
//            progressDialog.setMessage("Uploding image...");
//            progressDialog.show();
//
//            //get the camera image
//            Bundle extras = data.getExtras();
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//
//            /** saves the pic locally*/
////            Bundle extras = data.getExtras();
////            Bitmap bitmap = (Bitmap) extras.get("data");
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte[] databaos = baos.toByteArray();
//
//            //set the image into imageview
//            choosepic.setImageBitmap(bitmap);
//            //String img = "fire"
//            /***************** UPLOADS THE PIC TO FIREBASE*****************/
//            //Firebase storage folder where you want to put the images
//            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("towns");
//
//            //name of the image file (add time to have different files to avoid rewrite on the same file)
//            StorageReference imagesRef = storageRef.child("town" + new Date().getTime());
//
//            //TO DO
//            //send this name to database
//            //upload image
//            UploadTask uploadTask = imagesRef.putBytes(databaos);
//            uploadTask.addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    Toast.makeText(UpdateTownInfo.this, "Sending failed", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    // taskSnapshot.getMetadata() contains file metadata such as size, content_product_image-type, and download URL.
//                    // handle success
//                    imgDownloadUrl = taskSnapshot.getDownloadUrl().toString();
//                    progressDialog.dismiss();
//                }
//            } );}else{
//            Toast.makeText(UpdateTownInfo.this, "DATA IS NULL", Toast.LENGTH_SHORT).show();
//        }
    }
    private String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    protected void onUploadButtonClick(){

           progressDialog.setMessage("Uploading ...");
                // Creating a reference to the full path of the file. myfileRef now points
                // gs://fir-demo-d7354.appspot.com/myuploadedfile.jpg
               // StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("Towns").child("town" + new Date().getTime());
                myfileRef = storageReference.child("Towns").child("town" + new Date().getTime());

                //StorageReference myfileRef = storageRef.child("myuploadedfile.jpg");
                //imageView.setDrawingCacheEnabled(true);
               // imageView.buildDrawingCache();
               // Bitmap bitmap = imageView.getDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = myfileRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(UpdateTownInfo.this, "TASK FAILED", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(UpdateTownInfo.this, "TASK SUCCEEDED", Toast.LENGTH_SHORT).show();
                        Uri downloadUrl =taskSnapshot.getDownloadUrl();
                        //DOWNLOAD_URL = downloadUrl.getPath();
                        //DOWNLOAD_URL = taskSnapshot.getDownloadUrl().toString();
                        mDownloadUrl = downloadUrl;
                        //TownsModel townsModel = new TownsModel();
                        //townsModel.setTownImgUrl(DOWNLOAD_URL);
                        townsModel.setTownImgUrl(mDownloadUrl.toString());
                        //PicassoClient.downloadProductImage(UpdateTownInfo.this,townsModel.getTownImgUrl(),viewHolder.productImg);
                        Log.v("DOWNLOAD URL", mDownloadUrl.toString());
                        Toast.makeText(UpdateTownInfo.this, mDownloadUrl.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_FILE_URI, mFileUri);
        outState.putParcelable(KEY_DOWNLOAD_URL, mDownloadUrl);
        // If there's an upload in progress, save the reference so you can query it later
//        if (storageReference != null) {
//            outState.putString("myfileRef", myfileRef.toString());
//        }
    }



//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        // If there's an upload in progress, save the reference so you can query it later
//        if (mStorageRef != null) {
//            outState.putString("reference", mStorageRef.toString());
//        }
//    }

}
