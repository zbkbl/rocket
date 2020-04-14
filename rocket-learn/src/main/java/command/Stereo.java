package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 17:09
 **/
public class Stereo {
    String str;

    public Stereo(String str){
        this.str = str;
    }

    public void on(){
        System.out.println(str + " stereo is on!");
    }

    public void off(){
        System.out.println(str + " stereo is off!");
    }

    public void setCd(){
        System.out.println(str + " stereo is setting Cd!");
    }

    public void setDvd(){
        System.out.println(str + " stereo is setting Dvd!");
    }

    public void setRadio(){
        System.out.println(str + " stereo is setting Radio!");
    }

    public void setVolume(int num){
        System.out.println(str + " stereo is setting volume, num is " + num);
    }
}
