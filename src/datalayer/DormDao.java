package datalayer;

import models.PostModel;
import models.DormModel;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;


public class DormDao {
    /**
     * Given a dorm ID, return the dorm.
     */
    /*public static DormModel getDorm(int dormId) {
        File file = new File(getFilePath(dormId));
        return getDorm(file);
    }*/

    /*
     * Given a dorm ID, delete it from storage.
     */
    /*public static void deleteDorm(int dormId) {
        File file = new File(getFilePath(dormId));
        file.delete();
    }*/

    /*
     * Save the given dorm model.  Make sure you've set
     * the ID in the dorm model.
     */
    /*public static void saveDorm(DormModel dormModel){
        try {
            File file = new File(getFilePath(dormModel.getIndexNum(dormModel.name)));
            file.createNewFile();
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dormModel);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Given a story ID and story text, make a story model
     * and save it.
     */
    /*public static void saveDorm(int dormId, String username) {
        DormModel dorm = new DormModel();
        dorm.setDormId(UniqueIdDao.getID());
        dorm.setUsername(username);
        saveDorm(dorm);
    }*/

    /**
     * Return all saved dorms.
     */
    /*public static ArrayList<DormModel> getDorms() {
        ArrayList<DormModel> dorms = new ArrayList<>();
        String dir = DaoUtils.storageDirectoryName();
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < listOfFiles.length; i++){
            //changed "story" to "post"
            if(listOfFiles[i].getName().startsWith("post") &&
                    listOfFiles[i].getName().endsWith(".txt")){
                dorms.add(getDorm(listOfFiles[i]));
            }
        }

        return dorms;
    }*/

    /**
     * Given a story ID, where are we saving it to storage (file name)?
     */
    /*private static String getFilePath(int dormId) {
        return DaoUtils.storageDirectoryName() + File.separator + "dorm" + dormId + ".txt";
    }*/

    /*
     * Given a story filename, return the story that's saved in the file.
     */
    /*private static DormModel getDorm(File file) {
        DormModel dorm = null;
        try {
            dorm = new DormModel(dorm.name);

            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            else{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                dorm = (DormModel) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dorm;
    }*/
    //!!!!!!!!!!!!!!!!! does this work?
    public static void writeToFile(String dormID, ArrayList<PostModel> allPosts){
        //makes temp dorm to hold data passed in from servlet
        DormModel tempDorm = new DormModel(dormID);
        tempDorm.indexNum = tempDorm.getIndexNum(dormID);
        //tempDorm.allPosts = allPosts;//actually copy it not just =
        tempDorm.allPosts = (ArrayList<PostModel>)allPosts.clone(); //copied arraylist
        ArrayList <PostModel> allPosts2 = new ArrayList<PostModel>();
        //tempDorm.allPosts.get(0);
        String fileName = "dorm" + String.valueOf(tempDorm.indexNum);
        File dormFile = new File(fileName);
        String backupName = "dorm" + String.valueOf(tempDorm.indexNum) + "backup";
        File backupFile = new File(backupName);
        //initialize the backup file name as a string


        //read in the new array, and ADD THE NEW POST TO THE END OF IT!!!!!

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

                //for(PostModel p: tempDorm.allPosts){
                    //aoos.writeObject(p);
                //}
                //aoos.close();
                //byte[] rawData = buffer.toByteArray();

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
        /*try {
            FileInputStream fis = new FileInputStream(dormFile);

            //if the end of the file has not been reached, researched here:https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html
            if(fis.getChannel().size() != 0){
                //ObjectInputStream ois = new ObjectInputStream(fis);
                Object object = ois.readObject();
                tempDorm.allPosts = (ArrayList <PostModel>)object;
                //while(object != null){
                //tempDorm.allPosts.add((PostModel)object);
                //object = ois.readObject();
                //}
                ois.close();
                //tempDorm.allPosts = (ArrayList<PostModel>)ois.readObject(); //= (ArrayList<PostModel>) ois.readObject();
                //ois.reset();
            }
            fis.close();
        }catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
        }*/
        //returns arraylist that is populated with the correct data from the file

        //this isn't fully reading what is in the file for some reason
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
