package edu.cnm.deepdive.java_learn;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import edu.cnm.deepdive.java_learn.controller.MainActivity;
import edu.cnm.deepdive.java_learn.service.JavaLearnApplication;

/**
 * The type LoginActivity.
 */
public class LoginActivity extends AppCompatActivity {

  private static final String TAG = "LoginActivity";
  private static final int REQUEST_SIGNUP = 0;
  private static final int GOOGLE_REQUEST_SIGNIN = 1000;


  /**
   * The Email text.
   */
  @BindView(R.id.email_input)
  TextInputEditText emailText;
  /**
   * The Password text.
   */
  @BindView(R.id.password_input)
  TextInputEditText passwordText;
  /**
   * The LoginActivity button.
   */
  @BindView(R.id.login_button)
  Button loginButton;
  /**
   * The Signup link.
   */
  @BindView(R.id.link_signup)
  TextView signupLink;
  /**
   * The Google sign in button.
   */
  @BindView(R.id.google_sign_in_button)
  com.google.android.gms.common.SignInButton googleSignInButton;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_login);
    ButterKnife.bind(this);

    googleSignInButton.setOnClickListener(v -> googleSignIn());

    loginButton.setOnClickListener(v -> login());

    signupLink.setOnClickListener(v -> {
      // Start the Signup activity
      Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
      startActivityForResult(intent, REQUEST_SIGNUP);
    });

    TextInputLayout password = findViewById(R.id.password_input_layout);
    password.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/leaguespartanbold.otf"));

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

  /**
   * LoginActivity.
   */
  public void login() {
    Log.d(TAG, "LoginActivity");

// TODO comment or uncomment this to control login button
    if (!validate()) {
      onLoginFailed();
      return;
    }

    loginButton.setEnabled(false);

    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
        R.style.AppTheme);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Authenticating...");
    progressDialog.show();

    String email = emailText.getText().toString();
    String password = passwordText.getText().toString();

    // TODO: Implement your own authentication logic here.

    new android.os.Handler().postDelayed(
        () -> {
          // On complete call either onLoginSuccess or onLoginFailed
          onLoginSuccess();
          // onLoginFailed();
          progressDialog.dismiss();
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

  /**
   * On login success.
   */
  public void onLoginSuccess() {
    loginButton.setEnabled(true);
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  /**
   * On login failed.
   */
  public void onLoginFailed() {
    Toast.makeText(getBaseContext(), "LoginActivity failed", Toast.LENGTH_LONG).show();

    loginButton.setEnabled(true);
  }

  /**
   * Validate boolean.
   *
   * @return the boolean
   */
  public boolean validate() {
    boolean valid = true;

    String email = emailText.getText().toString();
    String password = passwordText.getText().toString();

    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      emailText.setError("enter a valid email address");
      valid = false;
    } else {
      emailText.setError(null);
    }

    if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
      passwordText.setError("between 4 and 10 alphanumeric characters");
      valid = false;
    } else {
      passwordText.setError(null);
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