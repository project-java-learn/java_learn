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
import edu.cnm.deepdive.java_learn.controller.MainActivity;


/**
 * The SignupActivity class allows the user to signup and save their progress.
 */
public class SignupActivity extends AppCompatActivity {

  private static final String TAG = "SignupActivity";

  @BindView(R.id.input_name)
  TextInputEditText nameText;
  @BindView(R.id.email_input)
  TextInputEditText emailText;
  @BindView(R.id.password_input)
  TextInputEditText passwordText;
  @BindView(R.id.signup_button)
  Button signupButton;
  @BindView(R.id.link_login)
  TextView loginLink;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_signup);
    ButterKnife.bind(this);

    signupButton.setOnClickListener(v -> signup());

    loginLink.setOnClickListener(v -> {
      // Finish the registration screen and return to the LoginActivity activity
      finish();
    });
  }

  public void signup() {
    Log.d(TAG, "Signup");

    if (!validate()) {
      onSignupFailed();
      return;
    }

    signupButton.setEnabled(false);

    final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
        R.style.AppTheme);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Creating Account...");
    progressDialog.show();

    String name = nameText.getText().toString();
    String email = emailText.getText().toString();
    String password = passwordText.getText().toString();

    // TODO: Implement your own signup logic here.

    new android.os.Handler().postDelayed(
        () -> {
          // On complete call either onSignupSuccess or onSignupFailed
          // depending on success
          onSignupSuccess();
          // onSignupFailed();
          progressDialog.dismiss();
        }, 3000);
  }


  public void onSignupSuccess() {
    signupButton.setEnabled(true);
    setResult(RESULT_OK, null);
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  public void onSignupFailed() {
    Toast.makeText(getBaseContext(), "LoginActivity failed", Toast.LENGTH_LONG).show();

    signupButton.setEnabled(true);
  }

  public boolean validate() {
    boolean valid = true;

    String name = nameText.getText().toString();
    String email = emailText.getText().toString();
    String password = passwordText.getText().toString();

    if (name.isEmpty() || name.length() < 3) {
      nameText.setError("at least 3 characters");
      valid = false;
    } else {
      nameText.setError(null);
    }

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
}