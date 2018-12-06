package edu.cnm.deepdive.java_learn;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import edu.cnm.deepdive.java_learn.service.JavaLearnApplication;

public class Login extends AppCompatActivity {
  private static final String TAG = "LoginActivity";
  private static final int REQUEST_SIGNUP = 0;
  private static final int GOOGLE_REQUEST_SIGNIN = 1000;


  @BindView(R.id.email_input) TextInputEditText _emailText;
  @BindView(R.id.password_input) TextInputEditText _passwordText;
  @BindView(R.id.login_button) Button _loginButton;
  @BindView(R.id.link_signup) TextView _signupLink;
  @BindView(R.id.google_sign_in_button) com.google.android.gms.common.SignInButton _googleSignInButton;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_login);
    ButterKnife.bind(this);

    _googleSignInButton.setOnClickListener(v -> googleSignIn());

    _loginButton.setOnClickListener(v -> login());

    _signupLink.setOnClickListener(v -> {
      // Start the Signup activity
      Intent intent = new Intent(getApplicationContext(), signupActivity.class);
      startActivityForResult(intent, REQUEST_SIGNUP);
    });

  }

  @Override
  protected void onStart() {
    super.onStart();
    // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if (account != null) {
      JavaLearnApplication.getInstance().setAccount(account);
      switchToMain();
    }

  }



  public void login() {
    Log.d(TAG, "Login");

    if (!validate()) {
      onLoginFailed();
      return;
    }

    _loginButton.setEnabled(false);

    final ProgressDialog progressDialog = new ProgressDialog(Login.this,
        R.style.AppTheme);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Authenticating...");
    progressDialog.show();

    String email = _emailText.getText().toString();
    String password = _passwordText.getText().toString();

    // TODO: Implement your own authentication logic here.

    new android.os.Handler().postDelayed(
        new Runnable() {
          public void run() {
            // On complete call either onLoginSuccess or onLoginFailed
            onLoginSuccess();
            // onLoginFailed();
            progressDialog.dismiss();
          }
        }, 3000);
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_SIGNUP) {
      if (resultCode == RESULT_OK) {

        // TODO: Implement successful signup logic here
        // By default we just finish the Activity and log them in automatically
        this.finish();
      }
    } else if (requestCode == GOOGLE_REQUEST_SIGNIN) {
      try {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        GoogleSignInAccount account = task.getResult(ApiException.class);
        JavaLearnApplication.getInstance().setAccount(account);
        switchToMain();
      } catch (ApiException e) {
        Toast.makeText(this, getString(R.string.google_signin_failure), Toast.LENGTH_LONG).show();
      }

    }
  }


  @Override
  public void onBackPressed() {
    // disable going back to the MainActivity
    moveTaskToBack(true);
  }

  public void onLoginSuccess() {
    _loginButton.setEnabled(true);
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  public void onLoginFailed() {
    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

    _loginButton.setEnabled(true);
  }

  public boolean validate() {
    boolean valid = true;

    String email = _emailText.getText().toString();
    String password = _passwordText.getText().toString();

    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      _emailText.setError("enter a valid email address");
      valid = false;
    } else {
      _emailText.setError(null);
    }

    if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
      _passwordText.setError("between 4 and 10 alphanumeric characters");
      valid = false;
    } else {
      _passwordText.setError(null);
    }

    return valid;
  }

  private void googleSignIn() {
    Intent intent = JavaLearnApplication.getInstance().getClient().getSignInIntent();
    startActivityForResult(intent, GOOGLE_REQUEST_SIGNIN);
  }

  private void switchToMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }


}