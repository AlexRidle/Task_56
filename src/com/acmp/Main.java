package com.acmp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> yourFriends = new ArrayList<>();
    private static ArrayList<String> usersWhoAddedYou = new ArrayList<>();
    private static ArrayList<String> mutualFriends = new ArrayList<>();
    private static ArrayList<String> alsoFriendOf = new ArrayList<>();

    public static void main(String[] args) {
        try {
            readFile();
            findMutualFriends();
            writeFile();
        } catch (IOException | TaskException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() throws IOException, TaskException {
        File file = new File("INPUT.txt");
        Scanner inputFile = new Scanner(file);
        int yourFriendlistCount = inputFile.nextInt();
        for(int i = 0; i < yourFriendlistCount; i++){
            yourFriends.add(inputFile.next());
        }
        int youreInTheFriendlistCount = inputFile.nextInt();
        for(int i = 0; i < youreInTheFriendlistCount; i++){
            usersWhoAddedYou.add(inputFile.next());
        }
        checkInputFile(yourFriendlistCount, youreInTheFriendlistCount);
        Collections.sort(yourFriends);
        Collections.sort(usersWhoAddedYou);
    }

    private static void checkInputFile(int yourFriendlistCount, int youreInTheFriendlistCount) throws TaskException {
        boolean wrongFriendlist = 0 > yourFriendlistCount || yourFriendlistCount > 200;
        boolean wrongUserFriendList = 0 > youreInTheFriendlistCount || youreInTheFriendlistCount > 200;
        if (wrongFriendlist || wrongUserFriendList) {
            throw new TaskException("Please, check INPUT.txt file. Friends cant be less than 0 or higher than 200");
        }
    }

    private static void findMutualFriends(){
        for(String friendName : usersWhoAddedYou){
            if (yourFriends.contains(friendName)) {
                mutualFriends.add(friendName);
            } else {
                alsoFriendOf.add(friendName);
            }
        }
    }

    private static void writeFile() {
        File file = new File("OUTPUT.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println("Friends: " + Arrays.toString(yourFriends.toArray())
                    .replace("[","")
                    .replace("]",""));
            writer.println("Mutual Friends: " + Arrays.toString(mutualFriends.toArray())
                    .replace("[","")
                    .replace("]",""));
            writer.println("Also Friend of: " + Arrays.toString(alsoFriendOf.toArray())
                    .replace("[","")
                    .replace("]",""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }
}

class TaskException extends Exception {

    public TaskException(String message) {
        super(message);
    }

}
