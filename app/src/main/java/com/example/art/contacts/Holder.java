package com.example.art.contacts;

import com.example.art.contacts.DBHelper;
import java.util.ArrayList;
import java.util.List;

public class Holder
    {
        private static ArrayList<String> mainContactList;
        private static ArrayList<Integer> mainIds;
        private static DBHelper myDB;

        public static void setMainContactList(ArrayList<String> contacts) {
            mainContactList = contacts;
        }
        public static ArrayList<String> getMainContactList() {
            return mainContactList;
        }

        public static void setMainIds(ArrayList<Integer> ids) {
            mainIds = ids;
        }
        public static ArrayList<Integer> getMainIds() {
            return mainIds;
        }

        public static void setMaindb(DBHelper db) {
            myDB = db;
        }
        public static DBHelper getMaindb() {
            return myDB;
        }
    }

