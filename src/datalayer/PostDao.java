package datalayer;

import models.PostModel;

import java.io.*;
import java.util.ArrayList;

public class PostDao {

    /**
     * Given a story ID, return the story.
     */
    public static PostModel getPost(int postId) {
        File file = new File(getFilePath(postId));
        return getPost(file);
    }

    /*
     * Given a story ID, delete it from storage.
     */
    public static void deletePost(int postId) {
        File file = new File(getFilePath(postId));
        file.delete();
    }

    /*
     * Save the given story model.  Make sure you've set
     * the ID in the story model.
     */
    public static void savePost(PostModel postModel){
        try {
            File file = new File(getFilePath(postModel.getPostId()));
            file.createNewFile();
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(postModel);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a story ID and story text, make a story model
     * and save it.
     */
    public static void savePost(int postId, String postText, String username, int commentOnPostId) {
        PostModel post = new PostModel();
        post.setPostId(UniqueIdDao.getID());
        post.setPost(postText);
        post.setUsername(username);
        post.setCommentOnPostID(commentOnPostId);
        savePost(post);
    }

    /**
     * Return all saved stories.
     */
    public static ArrayList<PostModel> getPosts() {
        ArrayList<PostModel> posts = new ArrayList<>();
        String dir = DaoUtils.storageDirectoryName();
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < listOfFiles.length; i++){
            //changed "story" to "post"
            if(listOfFiles[i].getName().startsWith("post") &&
                    listOfFiles[i].getName().endsWith(".txt")){
                posts.add(getPost(listOfFiles[i]));
            }
        }

        return posts;
    }

    /**
     * Given a story ID, where are we saving it to storage (file name)?
     */
    private static String getFilePath(int postId) {
        return DaoUtils.storageDirectoryName() + File.separator + "post" + postId + ".txt";
    }

    /*
     * Given a story filename, return the story that's saved in the file.
     */
    private static PostModel getPost(File file) {
        PostModel post = null;
        try {
            post = new PostModel();

            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            else{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                post = (PostModel) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return post;
    }

    /**
     * Unit test program.
     *
     * @param args
     */
    public static void main(String[] args) {
        testPostDao();
    }

    private static void testPostDao() {
        int postId = 100;
        String text = "It was a dark and stormy night.";
        PostDao dao = new PostDao();
        PostModel post = new PostModel();
        post.setPostId(postId);
        post.setPost("It was a dark and stormy night.");
        post.setUsername("allan");
        post.setCommentOnPostID(0);
        dao.savePost(post);

        post = dao.getPost(postId);
        assert(post.getPostId() == 100);
        assert(post.getPost().compareTo(text) == 0);

        ArrayList<PostModel> posts = dao.getPosts();
        assert(posts != null && posts.size() >= 5);

        //dao.deleteStory(storyId);
    }

}
