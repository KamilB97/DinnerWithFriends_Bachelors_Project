package com.example.dinnerapp.activity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.dinnerapp.R;
import com.example.dinnerapp.connection.ADDRESS;
import com.example.dinnerapp.connection.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    TextView mPhoneTextView, mEmailTextView, mPassTextView, mConfirmPassTextView,topRegisterText;
    Typeface font1;
    Typeface font2;
    private View mRegisterButton;
    private boolean emailAvailable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);


        mPhoneTextView = (TextView)findViewById(R.id.textView_phone);
        mEmailTextView = (TextView)findViewById(R.id.textView_mail);
        mPassTextView = (TextView)findViewById(R.id.textView_pass);
        mConfirmPassTextView = (TextView)findViewById(R.id.textView_confirm_password);
        topRegisterText = (TextView) findViewById(R.id.textView_top_register);

        font1 = Typeface.createFromAsset(this.getAssets(), "fonts/Sheila Crayon.ttf");
        font2 = Typeface.createFromAsset(this.getAssets(), "fonts/DK Cool Crayon.ttf");

        topRegisterText.setTypeface(font1);
        mPhoneTextView.setTypeface(font2);
        mEmailTextView.setTypeface(font2);
        mPassTextView.setTypeface(font2);
        mConfirmPassTextView.setTypeface(font2);

        mRegisterButton = (View) (findViewById(R.id.cardView_register));

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = mPhoneTextView.getText().toString();
                final String email = mEmailTextView.getText().toString();
                final String password = mPassTextView.getText().toString();
                String confirmPassword = mConfirmPassTextView.getText().toString();

                isEmailAvailable(email);

            }
        });




    }

    private boolean attemptRegistration() {

        boolean isDataValid = true;
        // Reset errors displayed in the form

        mEmailTextView.setError(null);
        mPassTextView.setError(null);
        mConfirmPassTextView.setError(null);

        String email = mEmailTextView.getText().toString();
        String password = mPassTextView.getText().toString();
        String confirmPassword = mConfirmPassTextView.getText().toString();

        // Check for a valid password, if the user entered one.

        if(!fieldsNotEmpty(email,password,confirmPassword )
                || !isEmailValid(email) || !isPasswordValid(password,confirmPassword)){
            Toast.makeText(this,"Wypełnij wszystkie pola",Toast.LENGTH_SHORT).show();

            return false;

        } else {
            Toast.makeText(this,"Rejestracja pomyślna",Toast.LENGTH_SHORT).show();
            // TODO: Call create User() here
            // boolean isUserCreated = createNewUser(name,surname,country,province,email,password);

            //if(!isUserCreated) return false;
            return true;
        }
    }
    private boolean isEmailValid(String email) {

        if(email.contains("@")){
            return true;
        }
        else{
            mEmailTextView.setError("Email jest niepoprawny");
            return false;
        }
    }
    private boolean isPasswordValid(String password, String confirmPassword) {
        final Pattern[] inputRegexes = new Pattern[3];

        inputRegexes[0] = Pattern.compile(".*[A-Z].*");
        inputRegexes[1] = Pattern.compile(".*[a-z].*");
        inputRegexes[2] = Pattern.compile(".*[0-9].*");
        boolean isCorrect=false;
        if (password.length()>=7){
            if (password.equals(confirmPassword)){
                if(isMatchingRegex(password,inputRegexes)){
                    isCorrect=true;
                }else{
                    mPassTextView.setError("Hasło powinno zawierać co najmniej jedną małą literę, jedną dużą literę oraz cyfrę." +
                            " Długość hasła powinna wynosić 7 znaków" );
                }
            }else {
                mConfirmPassTextView.setError("Hasła nie pasują");
            }
        }else{
            mPassTextView.setError("Hasło zbyt krótkie");
        }
        Log.d("isPasswordCorrect: ", Boolean.toString(isCorrect));
        return isCorrect;

    }
    private static boolean isMatchingRegex(String input, Pattern[] inputRegexes) {
        boolean inputMatches = true;
        for (Pattern inputRegex : inputRegexes) {
            if (!inputRegex.matcher(input).matches()) {
                inputMatches = false;
            }
        }
        Log.d("MatchesTORegrex" ,Boolean.toString(inputMatches));
        return inputMatches;
    }
    private boolean fieldsNotEmpty(String email,String  password,String  confirmPassword){

        boolean areFieldsNotEmpty = true;

        if (TextUtils.isEmpty(email)){
            mEmailTextView.setError("Pole wymagane");
            areFieldsNotEmpty=false;
        }
        if (TextUtils.isEmpty(password)) {
            mPassTextView.setError("To pole jest wymagane i powinno zawierać co najmniej 7 znaków");
            areFieldsNotEmpty = false;
        }
        if (TextUtils.isEmpty(confirmPassword)){
            mConfirmPassTextView.setError("Pole wymagane");
            areFieldsNotEmpty = false;
        }
        Log.d("areFieldsNotEmpty: ", Boolean.toString(areFieldsNotEmpty));
        return areFieldsNotEmpty;

    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public void isEmailAvailable(String email){
        Log.d("email: x", email);
        String url = "http://" + ADDRESS.IP + ":8080/CheckIfParticipantEmailAvailable?email="+email;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                                    try {
                        Log.d("emailAvailable", "json: "+ response );

                        if(Boolean.parseBoolean(response)){
                            Log.d("test","email is available");

                            emailAvailable = true;

                            boolean registrationSuccesful = attemptRegistration();

                            if (registrationSuccesful) {

                                // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                                if(emailAvailable){
                                    //  Toast.makeText(getApplicationContext(), "2nd step validation passed", Toast.LENGTH_SHORT).show();
                                    JSONObject jsonBodyObj = new JSONObject();
                                    String registerUrl = "http://" + ADDRESS.IP +":8080/AddNewParticipant";
                                    try {
                                        if(mPhoneTextView.getText() != null){
                                            jsonBodyObj.put("phone", mPhoneTextView.getText().toString());
                                        }
                                        jsonBodyObj.put("email", mEmailTextView.getText().toString());
                                        jsonBodyObj.put("password", mPassTextView.getText().toString());

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    final String requestBody = jsonBodyObj.toString();
                                    Log.d("requestBody: ", requestBody);

                                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                            registerUrl, (String) null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.i("Response", String.valueOf(response));
                                            Toast.makeText(getApplicationContext(),"Rejestracja pomyślna",Toast.LENGTH_SHORT);
                                            finish();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            VolleyLog.e("Error: ", error.getMessage());
                                            Toast.makeText(getApplicationContext(),"Błąd rejestracji",Toast.LENGTH_SHORT).show();
                                        }
                                    }) {
                                        @Override
                                        public Map<String, String> getHeaders() throws AuthFailureError {
                                            HashMap<String, String> headers = new HashMap<String, String>();
                                            headers.put("Content-Type", "application/json; charset=utf-8");
                                            return headers;
                                        }


                                        @Override
                                        public byte[] getBody() {
                                            try {
                                                return requestBody == null ? null : requestBody.getBytes("utf-8");
                                            } catch (UnsupportedEncodingException uee) {
                                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                                        requestBody, "utf-8");
                                                return null;
                                            }
                                        }


                                    };

                                    MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);

                                    ///@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                                }
                                else {
                                    Log.d("emailNotAvailable", " email not available");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
                                    builder.setTitle("Email zajęty");
                                    builder.setMessage("Email jest już zajęty");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            }




                        }else{
                            emailAvailable = false;
                            Log.d("test","email is unavailable");
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
                            builder.setTitle("Email zajęty");
                            builder.setMessage("Email jest już zajęty");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"Błąd rejestracji",Toast.LENGTH_SHORT).show();


            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);


    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@




}
