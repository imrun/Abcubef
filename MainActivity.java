package np.com.ranjansitikhu.abcubef;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String server_url = "http://192.168.0.105/Android/abcube.php";
    ImageView ImageView;
    Button buttonCamera, buttonGallery;
    File file;
    Uri uri;
    Intent CamIntent, GalIntent, CropIntent;
    public Bitmap bitmap;
    //for spinner
    String[] mystring;//creating a string array named mystring
    Spinner samplespinner;
    String Selecteditem;
    String date;
int year,month,day;
    //personal details
    EditText Firstname, Lastname, Personal_Address, Country, Personal_Email, Mobile, Course, Nationality, Citizenship, Passport_no, Visa_no,
            Visagranted, Pass_expiry, Visa_expiry,Dob;
   // DatePicker Dob;

    Button Submit;
    RadioButton Gender;

    //educational background
    EditText Schoolname, Schoolmarks, Schoolyear,
            Highschoolname, Highschoolmarks, Highschoolyear,
            Bachelorname, Bachelormarks, Bacheloryear,
            Mastername, Mastermarks, Masteryear;


    //emergency contact
    EditText Name, Email, Relationship, Address, Phone_no;

    //English Language
    EditText Testname, Testdate, Testreport, Overallresult, Reading, Writing, Listening, Speaking;


//for date
    EditText fromDateEtxt,toDateEtxt;


     DatePickerDialog fromDatePickerDialog;
    DatePickerDialog toDatePickerDialog;
    DatePickerDialog tovisagrantedDialog;
    DatePickerDialog topassexpiry;
    DatePickerDialog totestdate,tosyear,tohyear,tobyear,tomyear;


    SimpleDateFormat dateFormatter,dateFormatter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateFormatter2 = new SimpleDateFormat("yyyy", Locale.US);

        findViewsById();

        setDateTimeField();
    }

    public void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.date);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.visa_expiry);
        toDateEtxt.setInputType(InputType.TYPE_NULL);

        Visagranted = (EditText) findViewById(R.id.Visa_granted);
        Visagranted.setInputType(InputType.TYPE_NULL);

        Pass_expiry = (EditText) findViewById(R.id.pass_expiry);
        Pass_expiry.setInputType(InputType.TYPE_NULL);

        Testdate = (EditText) findViewById(R.id.testdate);
        Testdate.setInputType(InputType.TYPE_NULL);

        Schoolyear = (EditText) findViewById(R.id.schoolyear);
        Schoolyear.setInputType(InputType.TYPE_NULL);

        Highschoolyear = (EditText) findViewById(R.id.highscchoolyear);
        Highschoolyear.setInputType(InputType.TYPE_NULL);

        Bacheloryear = (EditText) findViewById(R.id.bacheloryear);
        Bacheloryear.setInputType(InputType.TYPE_NULL);

        Masteryear = (EditText) findViewById(R.id.masteryear);
        Masteryear.setInputType(InputType.TYPE_NULL);


    }

    public void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);
        Visagranted.setOnClickListener(this);
        Pass_expiry.setOnClickListener(this);
        Testdate.setOnClickListener(this);
        Schoolyear.setOnClickListener(this);
        Highschoolyear.setOnClickListener(this);
        Bacheloryear.setOnClickListener(this);
        Masteryear.setOnClickListener(this);



        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tovisagrantedDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Visagranted.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        topassexpiry = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Pass_expiry.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        totestdate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Testdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tosyear = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Schoolyear.setText(dateFormatter2.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tohyear = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Highschoolyear.setText(dateFormatter2.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tobyear = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Bacheloryear.setText(dateFormatter2.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tomyear = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Masteryear.setText(dateFormatter2.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));



















//for spinner
        ArrayAdapter sampleadapter;//Assigning a name for ArrayAdapter
        Resources res = getResources();//Assigning a name for Resources
        mystring = res.getStringArray(R.array.Marital_status);//getting the array items to string named my string
        //mystring is an array which is defined on the top
        samplespinner = (Spinner) findViewById(R.id.spinner); //samplespinner is defined in the top
        //samplespinner is the name given to the spinner at the top
        sampleadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mystring);
        samplespinner.setAdapter(sampleadapter);
        samplespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //Toast.makeText(getBaseContext(), spVIA.getSelectedItem().toString(),
                //Toast.LENGTH_LONG).show();

                Selecteditem = samplespinner.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        //for radio button
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId) {
                    case R.id.male:
                        Gender = (RadioButton) findViewById(R.id.male);
                        break;
                    case R.id.female:
                        Gender = (RadioButton) findViewById(R.id.female);
                        break;

                }
            }
        });


        //personal details
        ImageView = (ImageView) findViewById(R.id.imageview);
        Firstname = (EditText) findViewById(R.id.firstname);
        Lastname = (EditText) findViewById(R.id.lastname);
        Personal_Address = (EditText) findViewById(R.id.adress);
        Country = (EditText) findViewById(R.id.country);
        Personal_Email = (EditText) findViewById(R.id.email);
        Mobile = (EditText) findViewById(R.id.mobile);
        Course = (EditText) findViewById(R.id.course);
        Nationality = (EditText) findViewById(R.id.nationality);
        Citizenship = (EditText) findViewById(R.id.citizenship);
        Passport_no = (EditText) findViewById(R.id.pass_no);
        Visa_no = (EditText) findViewById(R.id.visa_no);







        //educational background
        Schoolname = (EditText) findViewById(R.id.schoolname);
        Schoolmarks = (EditText) findViewById(R.id.schoolmarks);

        Highschoolname = (EditText) findViewById(R.id.highschoolname);
        Highschoolmarks = (EditText) findViewById(R.id.highschoolmarks);

        Bachelorname = (EditText) findViewById(R.id.bachelorname);
        Bachelormarks = (EditText) findViewById(R.id.bachelormarks);

        Mastername = (EditText) findViewById(R.id.mastername);
        Mastermarks = (EditText) findViewById(R.id.mastermarks);



        //eergency ccontact
        Name = (EditText) findViewById(R.id.contactname);
        Email = (EditText) findViewById(R.id.emailaddress);
        Relationship = (EditText) findViewById(R.id.relation);
        Phone_no = (EditText) findViewById(R.id.phone);
        Address = (EditText) findViewById(R.id.address);

        //english language
        Testname = (EditText) findViewById(R.id.testname);

        Testreport = (EditText) findViewById(R.id.testreport);
        Overallresult = (EditText) findViewById(R.id.overallresult);
        Reading = (EditText) findViewById(R.id.reading);
        Writing = (EditText) findViewById(R.id.writing);
        Listening = (EditText) findViewById(R.id.listening);
        Speaking = (EditText) findViewById(R.id.speaking);

        buttonCamera = (Button) findViewById(R.id.capturebtn);
        buttonGallery = (Button) findViewById(R.id.browsebtn);
        Submit = (Button) findViewById(R.id.submit);


        buttonCamera.setOnClickListener(this);
        buttonGallery.setOnClickListener(this);
        Submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.browsebtn:
                // GetImageFromGallery();
                break;
            case R.id.submit:
                submitall();
                break;
            case R.id.capturebtn:
                // ClickImageFromCamera();
            case R.id.date:
                    fromDatePickerDialog.show();
                break;
            case R.id.visa_expiry:
                toDatePickerDialog.show();
                break;
            case R.id.Visa_granted:
                tovisagrantedDialog.show();
                break;
            case R.id.pass_expiry:
                topassexpiry.show();
                break;
            case R.id.testdate:
                totestdate.show();
                break;
            case R.id.schoolyear:
                tosyear.show();
                break;
            case R.id.highscchoolyear:
                tohyear.show();
                break;
            case R.id.bacheloryear:
                tobyear.show();
                break;
            case R.id.masteryear:
                tomyear.show();
                break;


        }
    }

    public void submitall() {


        Firstname.setText("");
        Lastname.setText("");
        Country.setText("");



        final String first_name, last_name, personal_address, country, dob, personal_email, course, citizenship, nationality, pass_no,
                visa_no, visa_expiry, mobile_no, contact, address, email, phone, relation, pass_expiry, gender, image, visa_grant;
        //personal details
        String PATTERN = "([a-zA-Z]{3,30})+";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        first_name = Firstname.getText().toString();
        if (!first_name.matches(PATTERN) && Firstname.getText().toString().length() == 0)
            Firstname.setError("only letters and is required");



        last_name = Lastname.getText().toString();
        if (!last_name.matches(PATTERN) && Lastname.getText().toString().length() == 0)
            Lastname.setError("only letters and is required");

        personal_address = Personal_Address.getText().toString();
        country = Country.getText().toString();
        if (!country.matches(PATTERN) && Country.getText().toString().length() == 0)
            Country.setError("only letters and is required");


        personal_email = Personal_Email.getText().toString();
        if (!personal_email.matches(emailPattern) && Personal_Email.getText().toString().length() == 0)
            Personal_Email.setError("Invalid email address");

        mobile_no = Mobile.getText().toString();
        dob = fromDateEtxt.getText().toString();

        course = Course.getText().toString();
        if (!course.matches(PATTERN) && Course.getText().toString().length() == 0)
            Course.setError("only letters and is required");


        nationality = Nationality.getText().toString();
        if (!nationality.matches(PATTERN) && Nationality.getText().toString().length() == 0)
            Nationality.setError("only letters and is required");

        citizenship = Citizenship.getText().toString();
        if (!citizenship.matches(PATTERN) && Citizenship.getText().toString().length() == 0)
            Citizenship.setError("only letters and is required");

        pass_no = Passport_no.getText().toString();
        pass_expiry = Pass_expiry.getText().toString();
        visa_no = Visa_no.getText().toString();
        visa_grant = Visagranted.getText().toString();
        visa_expiry = toDateEtxt.getText().toString();
        gender = Gender.getText().toString();


        //emergency contact
        contact = Name.getText().toString();
        if (!contact.matches(PATTERN) && Name.getText().toString().length() == 0)
            Name.setError("only letters and is required");

        email = Email.getText().toString();
        if (!email.matches(emailPattern) && Email.getText().toString().length() == 0)
            Email.setError("Invalid Email");

        address = Address.getText().toString();
        phone = Phone_no.getText().toString();
        relation = Relationship.getText().toString();
        if (!relation.matches(PATTERN) && Relationship.getText().toString().length() == 0)
            Relationship.setError("only letters and is required");


        //english test
        final String testname, testdate, testreport, overallresult, reading, writing, listening, speaking;
        testname = Testname.getText().toString();
        if (!testname.matches(PATTERN) && Testname.getText().toString().length() == 0)
            Testname.setError("only letters and is required");


        testreport = Testreport.getText().toString();
        overallresult = Overallresult.getText().toString();
        reading = Reading.getText().toString();
        writing = Writing.getText().toString();
        listening = Listening.getText().toString();
        speaking = Speaking.getText().toString();
        testdate = Testdate.getText().toString();


        //educational background
        final String schoolname, schoolmarks, schoolyear, highschoolname, highschoolmarks,
                highshoolyear, bachelorname, bachelormarks, bacheloryear, mastername, mastermarks, masteryear;
        schoolname = Schoolname.getText().toString();
        if (!schoolname.matches(PATTERN) && Schoolname.getText().toString().length() == 0)
            Schoolname.setError("only letters and is required");


        schoolmarks = Schoolmarks.getText().toString();
        schoolyear = Schoolyear.getText().toString();
        highschoolname = Highschoolname.getText().toString();
        if (!highschoolname.matches(PATTERN) && Highschoolname.getText().toString().length() == 0)
            Highschoolname.setError("only letters and is required");


        highschoolmarks = Highschoolmarks.getText().toString();
        highshoolyear = Highschoolyear.getText().toString();
        bachelorname = Bachelorname.getText().toString();
        if (!bachelorname.matches(PATTERN) && Bachelorname.getText().toString().length() == 0)
            Bachelorname.setError("only letters and is required");


        bachelormarks = Bachelormarks.getText().toString();
        bacheloryear = Bacheloryear.getText().toString();
        mastername = Mastername.getText().toString();
        if (!mastername.matches(PATTERN) && Mastername.getText().toString().length() == 0)
            Mastername.setError("only letters and is required");


        mastermarks = Mastermarks.getText().toString();
        masteryear = Masteryear.getText().toString();






//validation
        /*
        String PATTERN = "([a-zA-Z]{3,30})+";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if( !first_name.matches(PATTERN) && !last_name.matches(PATTERN) && !country.matches(PATTERN)  && !nationality.matches(PATTERN)  && !course.matches(PATTERN)
                && !citizenship.matches(PATTERN)  && !highschoolname.matches(PATTERN)  && !mastername.matches(PATTERN) && !relation.matches(PATTERN) && !testname.matches(PATTERN)
                && !bachelorname.matches(PATTERN) && !schoolname.matches(PATTERN) && !contact.matches(PATTERN)
                )

            //&& Firstname.getText().toString().length() == 0 )
            Firstname.setError( "only letters and is required" );
        Lastname.setError("only letters and is required");
        Country.setError("only letters and is required");
        Nationality.setError("only letters and is required");
        Course.setError("only letters and is required");
        Citizenship.setError("only letters and is required");
        Highschoolname.setError("only letters and is required");
        Mastername.setError("only letters and is required");
        Relationship.setError("only letters and is required");
        Bachelorname.setError("only letters and is required");
        Schoolname.setError("only letters and is required");
        Name.setError("only letters and is required");


*/

              /*  if(!first_name.isEmpty() && !last_name.isEmpty() && !country.isEmpty() && !personal_email.isEmpty() && !mobile_no.isEmpty()
         && !course.isEmpty() && !nationality.isEmpty() && !citizenship.isEmpty() && !pass_no.isEmpty() && !visa_no.isEmpty()
         && !visa_expiry.isEmpty() && !contact.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !relation.isEmpty()) {

         */

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("cropIntent");
                    Toast.makeText(MainActivity.this, Response, Toast.LENGTH_SHORT).show();
                    ImageView.setImageResource(0);
                    ImageView.setVisibility(View.GONE);
                    //Name.setText("");
                    // Name.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("personal_address", personal_address);
                params.put("country", country);
                params.put("dob", dob);
                params.put("personal_email", personal_email);
                params.put("mobile", mobile_no);
                params.put("course", course);
                params.put("nationality", nationality);
                params.put("citizenship", citizenship);
                params.put("pass_no", pass_no);
                params.put("pass_expiry", pass_expiry);
                params.put("visa_no", visa_no);
                params.put("visagrant", visa_grant);
                params.put("visa_expiry", visa_expiry);
                params.put("gender", gender);
                params.put("status", Selecteditem);
                params.put("first_name", first_name);
                params.put("last_name", last_name);

                //educational background
                params.put("schoolname", schoolname);
                params.put("schoolmarks", schoolmarks);
                params.put("schoolyear", schoolyear);
                params.put("highschoolname", highschoolname);
                params.put("highschoolmarks", highschoolmarks);
                params.put("highschoolyear", highshoolyear);
                params.put("bachelorname", bachelorname);
                params.put("bachelormarks", bachelormarks);
                params.put("bacheloryear", bacheloryear);
                params.put("mastername", mastername);
                params.put("mastermarks", mastermarks);
                params.put("masteryear", masteryear);

                //english test
                params.put("testname", testname);
                params.put("testdate", testdate);
                params.put("testreport", testreport);
                params.put("overallresult", overallresult);
                params.put("reading", reading);
                params.put("writing", writing);
                params.put("listening", listening);
                params.put("speaking", speaking);

                //emergency contact
                params.put("contact", contact);
                params.put("relation", relation);
                params.put("address", address);
                params.put("phone", phone);
                params.put("email", email);
                return params;
            }
        };
        Mysingleton.getInstance(MainActivity.this).addToRequest(stringRequest);
        /**else {
         Toast.makeText(main1.this,
         "Please enter your details!", Toast.LENGTH_LONG)
         .show();
         }
         } **/










    }
}








