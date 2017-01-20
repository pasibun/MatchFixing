package com.matchfixing.minor.matchfixing;

/**
 * Created by jylti on 19-1-2017.
 */

public class MatchDeleter {
    private static MatchDeleter instance = null;
    private MatchDeleter() {
        // Exists only to defeat instantiation.
    }

    public static MatchDeleter getInstance() {
        if(instance == null) {
            instance = new MatchDeleter();
        }
        return instance;
    }

    public void DeleteMatch(String matchID, String input, String file){
        DbConnection db = new DbConnection();
        db.execute(input, file, "");
    }
}
