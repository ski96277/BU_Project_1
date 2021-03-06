package com.example.imransk.buproject1.Adapter;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imransk.buproject1.FragmentClass.Course_Assaign;
import com.example.imransk.buproject1.FragmentClass.Faculty_Aprove_details_F;
import com.example.imransk.buproject1.FragmentClass.Student_Details;
import com.example.imransk.buproject1.R;
import com.example.imransk.buproject1.pojoClass.SignUpPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by imran sk on 5/4/2018.
 */

public class StudentListAdapter extends BaseAdapter {


    Context context;
    List<SignUpPojo> signUpPojoList_student;


    private static LayoutInflater layoutInflater=null;


    public StudentListAdapter(Activity context, List<SignUpPojo> signUpPojoList_student) {

        this.context = context;
        this.signUpPojoList_student = signUpPojoList_student;
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return signUpPojoList_student.size();
    }

    @Override
    public Object getItem(int i) {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class myHolder{
        TextView student_NameET;

        ImageView imageView_p_student;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        myHolder myHolderObj=new myHolder();


        final View listViewItem_faculty = layoutInflater.inflate(R.layout.custom_faculty_listview_course_assaign, null);


        myHolderObj.student_NameET= listViewItem_faculty.findViewById(R.id.user_name_faculty);
        myHolderObj.imageView_p_student= listViewItem_faculty.findViewById(R.id.faculty_image_profile_list);



        final SignUpPojo signUpPojo_for_faculty = signUpPojoList_student.get(position);



        myHolderObj.student_NameET.append(signUpPojo_for_faculty.getFull_name());


//set image on list imageView
        Picasso.with(listViewItem_faculty.getContext()).load(signUpPojo_for_faculty.getImageUri_download_Link()).into(myHolderObj.imageView_p_student);



        final Bundle bundle=new Bundle();
        bundle.putString("user_id",signUpPojo_for_faculty.getUser_id().toString());

        bundle.putString("name",signUpPojo_for_faculty.getFull_name().toString());
        bundle.putString("department",signUpPojo_for_faculty.getDepartment_name().toString());
        bundle.putString("batch",signUpPojo_for_faculty.getBatch_number().toString());
        bundle.putString("id_roll",signUpPojo_for_faculty.getiD().toString());
        bundle.putString("email_id",signUpPojo_for_faculty.getEmail().toString());
        bundle.putString("phone",signUpPojo_for_faculty.getPhoneNumber().toString());
        bundle.putString("imageUrl",signUpPojo_for_faculty.getImageUri_download_Link());
        bundle.putString("userType",signUpPojo_for_faculty.getType());
        bundle.putString("address",signUpPojo_for_faculty.getAddress());

        myHolderObj.student_NameET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=null;

                fragment=new Student_Details();

                if (fragment!=null){
                    FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.screenArea, fragment);
                    fragmentTransaction.addToBackStack("");
                    fragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
            }
        });

        myHolderObj.imageView_p_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=null;

                fragment=new Student_Details();

                if (fragment!=null){
                    FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.screenArea, fragment);
                    fragmentTransaction.addToBackStack("");
                    fragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }

            }
        });

//On Click is not Working , so sad for me :'(

        /*listViewItem_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "you click", Toast.LENGTH_SHORT).show();
            }
        });*/
        return listViewItem_faculty;

    }




}