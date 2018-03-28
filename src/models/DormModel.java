package models;
import java.util.ArrayList;

public class DormModel {
    public String name;
    public ArrayList <PostModel> allPosts;

    public DormModel(String name){
        this.name = name;
        allPosts = new ArrayList<PostModel>();
    }


}
