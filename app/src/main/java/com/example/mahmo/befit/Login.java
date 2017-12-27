package com.example.mahmo.befit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {
    private static final String TAG = "";
    private Button btSignUp;
    private Button btSignIn;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvFaceBook;
    private LoginButton btLogin;
    private ProgressBar pbSignIn;
    private CallbackManager callbackManager;
    private FirebaseAuth auth;
private FirebaseAuth.AuthStateListener firebaseAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btSignUp= (Button) findViewById(R.id.btSignUp);
        setContentView(R.layout.activity_login);
        tvFaceBook = (TextView)findViewById(R.id.info);
        btLogin = (LoginButton) findViewById(R.id.login_button);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etPassword= (EditText) findViewById(R.id.etPassword);
        pbSignIn= (ProgressBar) findViewById(R.id.pbSignIn);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        auth = FirebaseAuth.getInstance();
        btLogin.setReadPermissions("email","public_profile");




        btLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
        handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                tvFaceBook.setText("Login attempt canceled.");

            }

            @Override
            public void onError(FacebookException e) {
                tvFaceBook.setText("Login attempt failed.");

            }
        });
        auth = FirebaseAuth.getInstance();
        firebaseAuthListner= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=auth.getCurrentUser();

            }
        };


        //Hash Key code
/* try {
            PackageInfo info=getPackageManager().getPackageInfo("com.example.mahmo.befit", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/

    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential= FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
if(!task.isSuccessful()){
    //Toast.makeText(Login.this, "error", Toast.LENGTH_SHORT).show();
    Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
}
            }
        });
    }

    // da bara function l onCreate
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart(){
        super.onStart();
auth.addAuthStateListener(firebaseAuthListner);
    }
    @Override
    protected void onStop(){
        super.onStop();
        auth.removeAuthStateListener(firebaseAuthListner);
    }

    public void signIn(View view) {
        String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        ProgressDialog progressDialog=new ProgressDialog(Login.this);

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        pbSignIn.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pbSignIn.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                etPassword.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(Login.this, "Email or password isn't correct!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(Login.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });



    }
    @Override
    protected void onResume() {
        super.onResume();
        pbSignIn.setVisibility(View.GONE);
    }


    public void signUp(View view) {
        Intent intent=new Intent(Login.this,SignUp.class);// edited
        startActivity(intent);
    }


}
