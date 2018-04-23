package datalayer;

import models.PostModel;
import models.DormModel;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;


public class DormDao {
    public static void writeToFile(String dormID, ArrayList<PostModel> allPosts){
        //makes temp dorm to hold data passed in from servlet
        DormModel tempDorm = new DormModel(dormID);
        tempDorm.indexNum = tempDorm.getIndexNum(dormID);
        tempDorm.allPosts = (ArrayList<PostModel>)allPosts.clone();//copied arraylist
        ArrayList <PostModel> allPosts2 = new ArrayList<PostModel>();
        String fileName = "dorm" + String.valueOf(tempDorm.indexNum);
        File dormFile = new File(fileName);
        String backupName = "dorm" + String.valueOf(tempDorm.indexNum) + "backup";
        File backupFile = new File(backupName);
        //initialize the backup file name as a string


        //read in the new array, and add the new post to the end of it
        try {
            allPosts2 = readFromFile(tempDorm, tempDorm.indexNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        allPosts2.add(tempDorm.allPosts.get(0));


        //if that file already exists, append. if it doesn't exist, regular create and oos
        if(dormFile.exists()){
            //copy the file to a backup using copyFile method
            try {
                //copies the original to the backup file
                copyFile(dormFile, backupFile);
                //append the output object stream to the dormFile
                //researched appending of object output streams here: https://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
                FileOutputStream fos;
                fos = new FileOutputStream(dormFile);
                //AppendObjectOutputStream aoos = new AppendObjectOutputStream(fos);
                //aoos.writeObject(tempDorm.allPosts);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(allPosts2);
                oos.close();
            } catch (IOException e){
                e.printStackTrace();
                if(backupFile.exists()){
                    backupFile.renameTo(dormFile);
                    dormFile.delete();
                }
            }
        }
        else { //if dormFile doesn't exist:
            try {
                //create a new file with dormFile name and add the data
                FileOutputStream fos;
                fos = new FileOutputStream(dormFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(tempDorm.allPosts);
                //for(PostModel p: tempDorm.allPosts){
                    //oos.writeObject(p);
                //}
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
                if(backupFile.exists()){
                    backupFile.renameTo(dormFile);
                    dormFile.delete();
                }
            }
        }
        //delete the backup file
        backupFile.delete();
        //empty out the arraylist
        tempDorm.allPosts.clear();
        allPosts2.clear();
    }
    public static ArrayList<PostModel> readFromFile(DormModel tempDorm, int indexNum) throws IOException {
       DormModel tempDorm2 = new DormModel(tempDorm.name);
       tempDorm2.indexNum = indexNum;
        String fileName = "dorm" + String.valueOf(tempDorm2.indexNum);
        File dormFile = new File(fileName);
        tempDorm2.allPosts.clear();
        //read in the data from the appropriate file

        FileInputStream fin= new FileInputStream ("/glassfish5/glassfish/domains/domain1/config/" + fileName);
        if(fin.available()==0){
            return tempDorm2.allPosts;
        }
        else {
            while (fin.available() != 0) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(fin);
                    //ArrayList<PostModel> object = (ArrayList <PostModel>) ois.readObject();
                    //tempDorm.allPosts = (ArrayList<PostModel>) object.clone();
                    tempDorm2.allPosts = (ArrayList<PostModel>) ois.readObject();
                } catch (ClassNotFoundException | StreamCorruptedException e) {
                    e.printStackTrace();
                }
            }
            fin.close();
        }
        //return the arraylist populated with all the posts
        return tempDorm2.allPosts;
    }
    //researched copying of files here: https://www.journaldev.com/861/java-copy-file
    private static void copyFile(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }
    /**
     * Unit test program.
     *
     * @param args
     */
    /*public static void main(String[] args) {
        testPostDao();
    }*/

}
