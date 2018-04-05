package models;
import java.util.ArrayList;

public class DormModel {
    public String name;
    public int indexNum;
    public ArrayList <PostModel> allPosts;

    public DormModel(String name){
        this.name = name;
        allPosts = new ArrayList<PostModel>();
    }
    public String getDormId() {
        return name;
    }

    public void setDormId(String name) {
        this.name = name;
    }
    public int getIndexNum(String dormID) {
        if(dormID.equals("Alhambra Hall")){
            return 0;
        }
        else if(dormID.equals("Bayview Hall")){
            return 1;
        }
        else if(dormID.equals("Beacon Hall")){
            return 2;
        }
        else if(dormID.equals("Birchmont Hall")){
            return 3;
        }
        else if(dormID.equals("Brindle Hall")){
            return 4;
        }
        else if(dormID.equals("Endicott Hall")){
            return 5;
        }
        else if(dormID.equals("Frates Hall")){
            return 6;
        }
        else if(dormID.equals("Gloucester Hall")){
            return 7;
        }
        else if(dormID.equals("Hale Hall")){
            return 8;
        }
        else if(dormID.equals("Hamilton Hall")){
            return 9;
        }
        else if(dormID.equals("Hawthorne Hall")){
            return 10;
        }
        else if(dormID.equals("Kennedy Hall")){
            return 11;
        }
        else if(dormID.equals("Manchester Hall")){
            return 12;
        }
        else if(dormID.equals("Marblehead Hall")){
            return 13;
        }
        else if(dormID.equals("The Mods")){
            return 14;
        }
        else if(dormID.equals("Reynolds Hall")){
            return 15;
        }
        else if(dormID.equals("Rogers Hall")){
            return 16;
        }
        else if(dormID.equals("Standish Hall")){
            return 17;
        }
        else if(dormID.equals("Stoneridge Hall")){
            return 18;
        }
        else if(dormID.equals("Tower Hall")){
            return 19;
        }
        else if(dormID.equals("The Townhouses")) {
            return 20;
        }
        else if(dormID.equals("Trexler Hall")){
            return 21;
        }
        else if(dormID.equals("Wenham Hall")){
            return 22;
        }
        else if(dormID.equals("Winthrop Hall")){
            return 23;
        }
        else{
            return 24;
        }
    }

    public void setIndexNum(int indexNum) {
        this.indexNum = indexNum;
    }


}
