package com.example.imransk.buproject1.Adapter;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imransk.buproject1.FragmentClass.ShowUserProfileF;
import com.example.imransk.buproject1.R;
import com.example.imransk.buproject1.pojoClass.SignUpPojo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by imran sk on 3/24/2018.
 */


public class UserListAdapter extends BaseAdapter {

    TextView userNameET;
    TextView userTypeET;
    TextView departmentTypeET;
    ImageView imageView_p;
    Button right_button;
    Context context;
    List<SignUpPojo> signUpPojoList;
    private static LayoutInflater layoutInflater=null;
    FirebaseDatabase firebaseDatabase;

    public UserListAdapter(Context context, List<SignUpPojo> signUpPojoList) {

        this.context = context;
        this.signUpPojoList = signUpPojoList;
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return signUpPojoList.size();
    }

    @Override
    public Object getItem(int i) {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {


//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        final View listViewItem = layoutInflater.inflate(R.layout.custom_user_list, null);


        userNameET = listViewItem.findViewById(R.id.user_Id);
        userTypeET = listViewItem.findViewById(R.id.user_Type);
        departmentTypeET = listViewItem.findViewById(R.id.department_Type);
        imageView_p = listViewItem.findViewById(R.id.image_profile_list);

        right_button = listViewItem.findViewById(R.id.right_button);


        final SignUpPojo signUpPojo = signUpPojoList.get(position);

        userNameET.append(signUpPojo.getFull_name());
        userTypeET.append(signUpPojo.getType() + ", ");
        departmentTypeET.append(signUpPojo.getDepartment_name());

//set image on list imageView
        Picasso.with(listViewItem.getContext()).load(signUpPojo.getImageUri_download_Link()).into(imageView_p);


        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//set status 1 by default inside DataBase useing pojo class
                SignUpPojo signUpPojoForset = new SignUpPojo("1", signUpPojo.getUser_id(), signUpPojo.getType(), signUpPojo.getEmail(), signUpPojo.getFull_name(), signUpPojo.getDepartment_name(), signUpPojo.getBatch_number(), signUpPojo.getPhoneNumber(), signUpPojo.getiD(), signUpPojo.getImageUri_download_Link());
               //get Referance
                firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                databaseReference.child(signUpPojoForset.getType()).child(signUpPojoForset.getUser_id()).setValue(signUpPojoForset);
                Toast.makeText(context, "" + signUpPojoForset.getStatus(), Toast.LENGTH_SHORT).show();
            }
        });

        final Bundle bundle=new Bundle();
        bundle.putString("user_id",signUpPojo.getUser_id().toString());

        bundle.putString("name",signUpPojo.getFull_name().toString());
        bundle.putString("department",signUpPojo.getDepartment_name().toString());
        bundle.putString("batch",signUpPojo.getBatch_number().toString());
        bundle.putString("id_roll",signUpPojo.getiD().toString());
        bundle.putString("email_id",signUpPojo.getEmail().toString());
        bundle.putString("phone",signUpPojo.getPhoneNumber().toString());
        bundle.putString("imageUrl",signUpPojo.getImageUri_download_Link());
        bundle.putString("userType",signUpPojo.getType());

        imageView_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(listViewItem.getContext(),"click image"+signUpPojo.getFull_name(),Toast.LENGTH_LONG).show();

//                signUpPojo.getUser_id();

                Fragment fragment=null;
                fragment=new ShowUserProfileF();

                if (fragment!=null){

                    FragmentTransaction fragmentTransaction = ((FragmentActivity)context).
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.screenArea, fragment);
                    fragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
            }
        });
        userNameET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=null;
                fragment=new ShowUserProfileF();

                if (fragment!=null){

                    FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.screenArea, fragment);
                    fragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
            }


        });
        userTypeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=null;
                fragment=new ShowUserProfileF();

                if (fragment!=null){

                    FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.screenArea, fragment);
                    fragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
            }
        });
        departmentTypeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=null;
                fragment=new ShowUserProfileF();

                if (fragment!=null){

                    FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.screenArea, fragment);
                    fragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
            }


        });



        return listViewItem;
    }



}




/*

public class UserListAdapter extends ArrayAdapter {

    TextView userNameET;
    TextView userTypeET;
    TextView departmentTypeET;
    ImageView imageView_p;
    Button right_button;
    Button dot_Button;
    Context context;
    List<SignUpPojo> signUpPojoList;

    FirebaseDatabase firebaseDatabase;

    public UserListAdapter(Context context, List<SignUpPojo> signUpPojoList) {
        super(context, R.layout.custom_user_list, signUpPojoList);
        this.context = context;
        this.signUpPojoList = signUpPojoList;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        final View listViewItem = inflater.inflate(R.layout.custom_user_list, null);


        userNameET = listViewItem.findViewById(R.id.user_Id);
        userTypeET = listViewItem.findViewById(R.id.user_Type);
        departmentTypeET = listViewItem.findViewById(R.id.department_Type);
        imageView_p = listViewItem.findViewById(R.id.image_profile_list);

        right_button = listViewItem.findViewById(R.id.right_button);
        dot_Button = listViewItem.findViewById(R.id.dotButton);

        final SignUpPojo signUpPojo = signUpPojoList.get(position);

        userNameET.append(signUpPojo.getFull_name());
        userTypeET.append(signUpPojo.getType() + ", ");
        departmentTypeET.append(signUpPojo.getDepartment_name());

//set image on list imageView
        Picasso.with(getContext()).load(signUpPojo.getImageUri_download_Link()).into(imageView_p);
        dot_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();

            }
        });

        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//set status 1 by default inside DataBase useing pojo class
                SignUpPojo signUpPojoForset = new SignUpPojo("1", signUpPojo.getUser_id(), signUpPojo.getType(), signUpPojo.getEmail(), signUpPojo.getFull_name(), signUpPojo.getDepartment_name(), signUpPojo.getBatch_number(), signUpPojo.getPhoneNumber(), signUpPojo.getiD(), signUpPojo.getImageUri_download_Link());
                firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                databaseReference.child(signUpPojoForset.getType()).child(signUpPojoForset.getUser_id()).setValue(signUpPojoForset);
                Toast.makeText(context, "" + signUpPojoForset.getStatus(), Toast.LENGTH_SHORT).show();
            }
        });




        return listViewItem;
    }


}
*/























