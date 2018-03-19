package models;


import java.util.ArrayList;

public class globals {
    public ArrayList<DormModel> dorms;
    public String universalTitle = "Alhambra Hall";

    public globals(){
        dorms = new ArrayList();

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
    public String getName(String title){
        //globals g = new globals();
         universalTitle = title;
         return universalTitle;

    }


}
