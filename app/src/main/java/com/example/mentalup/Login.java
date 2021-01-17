import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mentalup.HomepageActivity;
import com.example.mentalup.R;

public class Login extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;

    String userName = "";
    String userPassword = "";

    private String Username = "Admin";
    private String Password = "12345678";
    boolean isValid = false;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(Login.this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
                }else{
                    isValid = validate(inputName, inputPassword);
                    if(!isValid){
                        Toast.makeText(Login.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, HomepageActivity.class));
                    }
                }
            }
        });
    }

    private boolean validate(String name, String Password) {
        if(name.equals(Username) && Password.equals(Password)) {
            return true;
        }
        return false;
    }
}
