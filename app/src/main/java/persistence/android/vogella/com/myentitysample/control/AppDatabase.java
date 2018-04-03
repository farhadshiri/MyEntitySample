package persistence.android.vogella.com.myentitysample.control;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import persistence.android.vogella.com.myentitysample.dao.Trophy;
import persistence.android.vogella.com.myentitysample.dao.User;
import persistence.android.vogella.com.myentitysample.service.TrophyDao;
import persistence.android.vogella.com.myentitysample.service.UserDao;

@Database(entities = {User.class,  Trophy.class}, version = 16, exportSchema = false )

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract TrophyDao trophyDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "userdatabase")
                           .allowMainThreadQueries()
                            // recreate the database if necessary
                           .fallbackToDestructiveMigration()
                           .build();

            //Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
            // To simplify the exercise, allow queries on the main thread.
            // Don't do this on a real app!
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}