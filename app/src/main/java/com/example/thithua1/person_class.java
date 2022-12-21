package com.example.thithua1;

import java.util.ArrayList;

public class person_class
{
    private String fullname;
    private int Pic;

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPic(int pic) {
        this.Pic = pic;
    }

    public int getPic() {
        return this.Pic;
    }

    public String getFullname() {
        return this.fullname;
    }

    public static ArrayList<person_class> init(){
        String[] name = {"Lí thuyết","Câu hỏi liệt"};
        int[] pics = {R.drawable.gplx,R.drawable.liet};
        person_class p;
        ArrayList<person_class> listPerson = new ArrayList<>();
        for (int i = 0;i< name.length;i++){
            p = new person_class();
            p.setPic(pics[i]);
            p.setFullname(name[i]);
            listPerson.add(p);
        }
        return listPerson;
    }
}
