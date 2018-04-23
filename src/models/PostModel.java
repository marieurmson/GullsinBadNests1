package models;

import java.io.Serializable;

public class PostModel implements Serializable {
    private int postId;
    private String post;
    private String username;
    private int commentOnPostID; // if 0, then not a comment
    private int dormIndex;

    public String getDormByIndex(int dormIndex){
        if(dormIndex == 0){
            return "Alhambra Hall";
        }
        else if(dormIndex==1){
            return "Bayview Hall";
        }
        else if(dormIndex==2){
            return "Beacon Hall";
        }
        else if(dormIndex==3){
            return "Birchmont Hall";
        }
        else if(dormIndex==4){
            return "Brindle Hall";
        }
        else if(dormIndex==5){
            return "Endicott Hall";
        }
        else if(dormIndex==6){
            return "Grates Hall";
        }
        else if(dormIndex==7){
            return "Gloucester Hall";
        }
        else if(dormIndex==8){
            return "Hale Hall";
        }
        else if(dormIndex==9){
            return "Hamilton Hall";
        }
        else if(dormIndex==10){
            return "Hawthorne Hall";
        }
        else if(dormIndex==11){
            return "Kennedy Hall";
        }
        else if(dormIndex==12){
            return "Manchester Hall";
        }
        else if(dormIndex==13){
            return "Marblehead Hall";
        }
        else if(dormIndex==14){
            return "The Mods";
        }
        else if(dormIndex==15){
            return "Reynolds Hall";
        }
        else if(dormIndex==16){
            return "Rogers Hall";
        }
        else if(dormIndex==17){
            return "Standish Hall";
        }
        else if(dormIndex==18){
            return "Stoneridge Hall";
        }
        else if(dormIndex==19){
            return "Tower Hall";
        }
        else if(dormIndex==20){
            return "The Townhouses";
        }
        else if(dormIndex==21){
            return "Trexler Hall";
        }
        else if(dormIndex==22){
            return "Wenham Hall";
        }
        else if(dormIndex==23){
            return "Winthrop Hall";
        }
        else {
            return "Woodside Hall";
        }
    }
    public int getDormIndex(){
        return dormIndex;
    }
    public void setDormIndex(int dormIndex){
        this.dormIndex = dormIndex;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCommentOnPostID() {
        return commentOnPostID;
    }

    public void setCommentOnPostID(int commentOnPostID) {
        this.commentOnPostID = commentOnPostID;
    }
   // public String getCommentContent() {
     //   return commentContent;
   // }

   // public void setCommentContent(String commentContent) {
      //  this.commentContent = commentContent;
    //}
}