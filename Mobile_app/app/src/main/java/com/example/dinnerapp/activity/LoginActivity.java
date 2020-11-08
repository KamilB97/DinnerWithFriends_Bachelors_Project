package com.example.dinnerapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dinnerapp.R;
import com.example.dinnerapp.connection.ADDRESS;
import com.example.dinnerapp.connection.MySingleton;
import com.example.dinnerapp.model.entity.Credencials;
import com.example.dinnerapp.model.entity.LoginVariable;
import com.example.dinnerapp.model.entity.Message;
import com.example.dinnerapp.model.entity.MyProfileData;
import com.example.dinnerapp.model.send.ProfileBrief;
import com.example.dinnerapp.model.send.SendMessage;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmailView, mPasswordView;
    LoginVariable loginSuccessful = new LoginVariable();

    //private User user;
    Context context = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.my_statusbar_color));

//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);



        mEmailView = (EditText) (findViewById(R.id.editText_username));
        mPasswordView = (EditText) (findViewById(R.id.editText_pass));

        View loginButton = (View) (findViewById(R.id.loginButton));
       // TextView signUp = (TextView) (findViewById(R.id.textView_enroll));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                Log.d("KB", "mail " + mEmailView.getText().toString() + "pass " + mPasswordView.getText().toString());

               if( attemptLogin(mEmailView.getText().toString(), mPasswordView.getText().toString())){

                signInExistingUser(mail, password);

                loginSuccessful.setListener(new LoginVariable.ChangeListener() {
                    @Override
                    public void onChange() {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();
                        Log.d("Login", "successful");
                    }
                });


//                Intent intent = new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//                finish();
//                String token = "token";
//                Integer profileId = 38;
//                String name = "Erwin";
//                String surname = "Brzycki";
//                Integer userId = 38;
//
//                MyProfileData.setProfileId(profileId);
//                MyProfileData.setName(name);
//                MyProfileData.setSurname(surname);
//                MyProfileData.setToken(token);
//                MyProfileData.setUserId(userId);

            }
        }
        });

//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
//
//            }
//        });

    }


    public void signInExistingUser(final String mail, String password)   {
        String unreaded_url = "http://" + ADDRESS.IP + ":8080/login";

        Gson gson = new Gson();
        final  Credencials credencials = new Credencials(mail, password);
        String json = gson.toJson(credencials);


        final String requestBody = json;//jsonBodyObj.toString();
        Log.d("requestBody: ", requestBody);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                unreaded_url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", String.valueOf(response));
                Toast.makeText(getApplicationContext(),"wysyłanie wiadomości pomyślne",Toast.LENGTH_SHORT);
                Log.d("RESPONSE", response.toString());
                try {
                   String token = response.getString("token");
                   Integer profileId = response.getInt("profileId");
                   String name = response.getString("name");
                   String surname = response.getString("surname");
                   Integer userId = response.getInt("userId");

                    MyProfileData.setProfileId(profileId);
                    MyProfileData.setName(name);
                    MyProfileData.setSurname(surname);
                    MyProfileData.setToken(token);
                    MyProfileData.setUserId(userId);

                    loginSuccessful.setLoginSuccessful(true);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Logowanie nie powiodło się", Toast.LENGTH_SHORT).show();
                }




                   // finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(getApplicationContext(), "Logowanie nie powiodło się", Toast.LENGTH_SHORT).show();
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





    }

    private boolean attemptLogin(String mail, String password) {

        boolean isEmailEmpty = TextUtils.isEmpty(mail);
        boolean isPasswordEmpty = TextUtils.isEmpty(password);

        StringBuilder sb = new StringBuilder();

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Login");
        if(isEmailEmpty){
            sb.append("pole email jest puste\n");
        }else if (!mail.contains("@")){
            sb.append("nieprawidłowy email\n");
        }
        if(isPasswordEmpty){
            sb.append("pole hasło jest puste ");
        }


        if(sb.length()>0){
            builder.setMessage(sb.toString());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return false;
        }
        else{
            return true;}
    }


}
