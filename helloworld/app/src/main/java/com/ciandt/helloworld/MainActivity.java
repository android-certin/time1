package com.ciandt.helloworld;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recuperando o xml
        FragmentManager fragmentManager = getSupportFragmentManager();
        PessoaFragment pessoaFragment = (PessoaFragment)fragmentManager.findFragmentById(R.id.fragment_pessoa);

        //criando em tempo de execução
        PessoaFragment pessoaFragment1 =  new PessoaFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, pessoaFragment1, "pessoa")
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doClick(View view)
    {
        TextView textView = (TextView)findViewById(R.id.helloText);
        EditText editText = (EditText)findViewById(R.id.editText);

        textView.setText(editText.getText());
    }
}
