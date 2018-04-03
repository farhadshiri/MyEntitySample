package persistence.android.vogella.com.myentitysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import persistence.android.vogella.com.myentitysample.control.AppDatabase;
import persistence.android.vogella.com.myentitysample.dao.Trophy;
import persistence.android.vogella.com.myentitysample.dao.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDatabase.getDatabase(getApplicationContext());

        // cleanup for testing some initial data
        database.userDao().removeAllUsers();
        // add some data
        List<User> users = database.userDao().getAllUser();
        if (users.size()==0) {
            database.userDao().addUser(new User(1, "Test 1", 1));
            User user = database.userDao().getAllUser().get(0);
            user.level = 1;
            Trophy trophy = new Trophy(user.id, "Learned to use 3");
            database.trophyDao().addTrophy(trophy);

            database.userDao().addUser(new User(2, "Test 2", 2));
            user = database.userDao().getAllUser().get(1);
            Trophy trophy1 = new Trophy(user.id, "Learned to use 3");
            database.trophyDao().addTrophy(trophy1);

            database.userDao().addUser(new User(3, "Test 3", 3));
        }

        updateFirstUserData();
    }

    private void updateFirstUserData() {
        List<User> user = database.userDao().getAllUser();
        List<Trophy> trophiesForUser = database.trophyDao().findTrophiesForUser(user.get(1).id);
        //TextView textView = findViewById(R.id.result);
        Toast.makeText(this, user.get(1).name + " Skill points " + user.get(1).skillPoints + " Trophys " + trophiesForUser.get(0).description, Toast.LENGTH_LONG).show();
        //if (user.size()>0){
        //    textView.setText(user.get(0).name + " Skill points " + user.get(0).skillPoints + " Trophys " + trophiesForUser.size() );
       // }
    }

    @Override
    public void onClick(View view){
/*        if (view.getId()==R.id.addtrophybutton) {
            // TODO add trophy
            // TODO call updatefirstUserData
            Toast.makeText(this,String.valueOf(user.id), Toast.LENGTH_SHORT).show();
            Trophy trophy = new Trophy(user.id, "More stuff");
            database.trophyDao().addTrophy(trophy);
        }
        if (view.getId()==R.id.increaseskills ){
            user.skillPoints++;
            database.userDao().updateUser(user);
            // TODO to skillpoints

        }*/
        // TODO call updatefirstUserData
        updateFirstUserData();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

}
