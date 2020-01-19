package com.example.administrator.room;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    CustomerDatabase db = null;
    EditText editText = null;
    TextView textView_insert = null;
    TextView textView_read = null;
    TextView textView_delete = null;
    TextView textView_update = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(), CustomerDatabase.class, "CustomerDatabase").fallbackToDestructiveMigration().build();
        Button addButton = (Button) findViewById(R.id.addButton);
        editText = (EditText) findViewById(R.id.editText);
        textView_insert = (TextView) findViewById(R.id.textView);
        Button readButton = (Button) findViewById(R.id.readButton);
        textView_read = (TextView) findViewById(R.id.textView_read);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        textView_delete = (TextView) findViewById(R.id.textView_delete);
        Button updateButton = (Button) findViewById(R.id.updateButton);
        textView_update = (TextView) findViewById(R.id.textView_update);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InsertDatabase addDatabase = new InsertDatabase();
                addDatabase.execute();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeleteDatabase deleteDatabase = new DeleteDatabase();
                deleteDatabase.execute();
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ReadDatabase readDatabase = new ReadDatabase();
                readDatabase.execute();
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateDatabase updateDatabase = new UpdateDatabase();
                updateDatabase.execute();
            }
        });
    }

    private class InsertDatabase extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            if (!(editText.getText().toString().isEmpty())) {
                String[] details = editText.getText().toString().split(" ");
                if (details.length == 3) {
                    Customer customer = new Customer(details[0], details[1], Double.parseDouble(details[2]));
                    long id = db.customerDao().insert(customer);
                    return (id + " " + details[0] + " " + details[1] + " " + details[2]);
                } else return "";
            } else return "";
        }

        @Override
        protected void onPostExecute(String details) {
            textView_insert.setText("Added Record: " + details);
        }
    }

    private class ReadDatabase extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            List<Customer> users = db.customerDao().getAll();
            if (!(users.isEmpty() || users == null)) {
                String allUsers = "";
                for (Customer temp : users) {
                    String userstr = (temp.getId() + " " + temp.getFirstName() + " " + temp.getLastName() + " " + temp.getweight() + " , ");
                    allUsers = allUsers + userstr;
                }
                return allUsers;
            } else return "";
        }

        @Override
        protected void onPostExecute(String details) {
            textView_read.setText("All data: " + details);
        }
    }

    private class DeleteDatabase extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            db.customerDao().deleteAll();
            return null;
        }

        protected void onPostExecute(Void param) {
            textView_delete.setText("All data was deleted");
        }
    }

    private class UpdateDatabase extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            Customer user = null;
            String[] details = editText.getText().toString().split(" ");
            if (details.length == 4) {
                int id = Integer.parseInt(details[0]);
                user = db.customerDao().findByID(id);
                user.setFirstName(details[1]);
                user.setLastName(details[2]);
                user.setLastName(details[3]);
            }
            if (user != null) {
                db.customerDao().updateUsers(user);
                return (details[0] + " " + details[1] + " " + details[2] + " " + details[3]);
            }
            return "";
        }

        @Override
        protected void onPostExecute(String details) {
            textView_update.setText("Updated details: " + details);
        }
    }
}