package models;


import java.util.ArrayList;

public class DormData {
    public ArrayList<DormModel> dorms;
    public String universalTitle;

    public DormData(){//rename to dormdata
        dorms = new ArrayList<DormModel>();

        DormModel a = new DormModel("Alhambra Hall");
        DormModel b = new DormModel("Bayview Hall");
        DormModel c = new DormModel("Beacon Hall");
        DormModel d = new DormModel("Birchmont Hall");
        DormModel e = new DormModel("Brindle Hall");
        DormModel f = new DormModel("Endicott Hall");
        DormModel g = new DormModel("Frates Hall");
        DormModel h = new DormModel("Gloucester Hall");
        DormModel i = new DormModel("Hale Hall");
        DormModel j = new DormModel("Hamilton Hall");
        DormModel k = new DormModel("Hawthorne Hall");
        DormModel l = new DormModel("Kennedy Hall");
        DormModel m = new DormModel("Manchester Hall");
        DormModel n = new DormModel("Marblehead Hall");
        DormModel o = new DormModel("The Mods");
        DormModel p = new DormModel("Reynolds Hall");
        DormModel q = new DormModel("Rogers Hall");
        DormModel r = new DormModel("Standish Hall");
        DormModel s = new DormModel("Stoneridge Hall");
        DormModel t = new DormModel("Tower Hall");
        DormModel u = new DormModel("The Townhouses");
        DormModel v = new DormModel("Trexler Hall");
        DormModel w = new DormModel("Wenham Hall");
        DormModel x = new DormModel("Winthrop Hall");
        DormModel y = new DormModel("Woodside Hall");

        dorms.add(a);
        dorms.add(b);
        dorms.add(c);
        dorms.add(d);
        dorms.add(e);
        dorms.add(f);
        dorms.add(g);
        dorms.add(h);
        dorms.add(i);
        dorms.add(j);
        dorms.add(k);
        dorms.add(l);
        dorms.add(m);
        dorms.add(n);
        dorms.add(o);
        dorms.add(p);
        dorms.add(q);
        dorms.add(r);
        dorms.add(s);
        dorms.add(t);
        dorms.add(u);
        dorms.add(v);
        dorms.add(w);
        dorms.add(x);
        dorms.add(y);
    }
    public int getGlobalIndexNum(String dormID){
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

    public ArrayList<PostModel> getArrayByIndex(int indexNum) {
        if(indexNum == 0){
            return dorms.get(0).allPosts;
        }
        else if(indexNum == 1){
            return dorms.get(1).allPosts;
        }
        else if(indexNum == 2){
            return dorms.get(2).allPosts;
        }
        else if(indexNum == 3){
            return dorms.get(3).allPosts;
        }
        else if(indexNum == 4){
            return dorms.get(4).allPosts;
        }
        else if(indexNum == 5){
            return dorms.get(5).allPosts;
        }
        else if(indexNum == 6){
            return dorms.get(6).allPosts;
        }
        else if(indexNum == 7){
            return dorms.get(7).allPosts;
        }
        else if(indexNum == 8){
            return dorms.get(8).allPosts;
        }
        else if(indexNum == 9){
            return dorms.get(9).allPosts;
        }
        else if(indexNum == 10){
            return dorms.get(10).allPosts;
        }
        else if(indexNum == 11){
            return dorms.get(11).allPosts;
        }
        else if(indexNum == 12){
            return dorms.get(12).allPosts;
        }
        else if(indexNum == 13){
            return dorms.get(13).allPosts;
        }
        else if(indexNum == 14){
            return dorms.get(14).allPosts;
        }
        else if(indexNum == 15){
            return dorms.get(15).allPosts;
        }
        else if(indexNum == 16){
            return dorms.get(16).allPosts;
        }
        else if(indexNum == 17){
            return dorms.get(17).allPosts;
        }
        else if(indexNum == 18){
            return dorms.get(18).allPosts;
        }
        else if(indexNum == 19){
            return dorms.get(19).allPosts;
        }
        else if(indexNum == 20){
            return dorms.get(20).allPosts;
        }
        else if(indexNum == 21){
            return dorms.get(21).allPosts;
        }
        else if(indexNum == 22){
            return dorms.get(22).allPosts;
        }
        else if(indexNum == 23){
            return dorms.get(23).allPosts;
        }
        else{
            return dorms.get(24).allPosts;
        }
    }
    public DormModel getDormByIndex(int indexNum) {
        if(indexNum == 0){
            return dorms.get(0);
        }
        else if(indexNum == 1){
            return dorms.get(1);
        }
        else if(indexNum == 2){
            return dorms.get(2);
        }
        else if(indexNum == 3){
            return dorms.get(3);
        }
        else if(indexNum == 4){
            return dorms.get(4);
        }
        else if(indexNum == 5){
            return dorms.get(5);
        }
        else if(indexNum == 6){
            return dorms.get(6);
        }
        else if(indexNum == 7){
            return dorms.get(7);
        }
        else if(indexNum == 8){
            return dorms.get(8);
        }
        else if(indexNum == 9){
            return dorms.get(9);
        }
        else if(indexNum == 10){
            return dorms.get(10);
        }
        else if(indexNum == 11){
            return dorms.get(11);
        }
        else if(indexNum == 12){
            return dorms.get(12);
        }
        else if(indexNum == 13){
            return dorms.get(13);
        }
        else if(indexNum == 14){
            return dorms.get(14);
        }
        else if(indexNum == 15){
            return dorms.get(15);
        }
        else if(indexNum == 16){
            return dorms.get(16);
        }
        else if(indexNum == 17){
            return dorms.get(17);
        }
        else if(indexNum == 18){
            return dorms.get(18);
        }
        else if(indexNum == 19){
            return dorms.get(19);
        }
        else if(indexNum == 20){
            return dorms.get(20);
        }
        else if(indexNum == 21){
            return dorms.get(21);
        }
        else if(indexNum == 22){
            return dorms.get(22);
        }
        else if(indexNum == 23){
            return dorms.get(23);
        }
        else{
            return dorms.get(24);
        }
    }
}
