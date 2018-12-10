package edu.cnm.deepdive.java_learn;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.w3c.dom.Text;


/**
 * The type Signup activity.
 */
public class signupActivity extends AppCompatActivity {

  private static final String TAG = "SignupActivity";

  /**
   * The Name text.
   */
  @BindView(R.id.input_name)
  TextInputEditText _nameText;
  /**
   * The Email text.
   */
  @BindView(R.id.email_input)
  TextInputEditText _emailText;
  /**
   * The Password text.
   */
  @BindView(R.id.password_input)
  TextInputEditText _passwordText;
  /**
   * The Signup button.
   */
  @BindView(R.id.signup_button)
  Button _signupButton;
  /**
   * The Login link.
   */
  @BindView(R.id.link_login)
  TextView _loginLink;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_signup);
    ButterKnife.bind(this);

    _signupButton.setOnClickListener(v -> signup());

    _loginLink.setOnClickListener(v -> {
      // Finish the registration screen and return to the Login activity
      finish();
    });

//    EditText password = (TextInputEditText) findViewById(R.id.password_input);
//    password.setTransformationMethod(new PasswordTransformationMethod());

    TextInputLayout password = (TextInputLayout) findViewById(R.id.password_input_layout);
    password.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/leaguespartanbold.otf"));

  }

  /**
   * Signup.
   */
  public void signup() {
    Log.d(TAG, "Signup");

    if (!validate()) {
      onSignupFailed();
      return;
    }

    _signupButton.setEnabled(false);

    final ProgressDialog progressDialog = new ProgressDialog(signupActivity.this,
        R.style.AppTheme);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Creating Account...");
    progressDialog.show();

    String name = _nameText.getText().toString();
    String email = _emailText.getText().toString();
    String password = _passwordText.getText().toString();

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


  /**
   * On signup success.
   */
  public void onSignupSuccess() {
    _signupButton.setEnabled(true);
    setResult(RESULT_OK, null);
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  /**
   * On signup failed.
   */
  public void onSignupFailed() {
    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

    _signupButton.setEnabled(true);
  }

  /**
   * Validate boolean.
   *
   * @return the boolean
   */
  public boolean validate() {
    boolean valid = true;

    String name = _nameText.getText().toString();
    String email = _emailText.getText().toString();
    String password = _passwordText.getText().toString();

    if (name.isEmpty() || name.length() < 3) {
      _nameText.setError("at least 3 characters");
      valid = false;
    } else {
      _nameText.setError(null);
    }

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
}