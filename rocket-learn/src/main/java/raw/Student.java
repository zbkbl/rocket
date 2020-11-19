package raw;

/**
 * @author liuyang
 * @description
 * @date 2020/05/19 10:08
 **/
public class Student {

    private double addfScore;

    public Student(double addfScore) {
        this.addfScore = addfScore;
    }

    public double getAddfScore() {
        return addfScore;
    }

    public void setAddfScore(double addfScore) {
        this.addfScore = addfScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "addfScore=" + addfScore +
                '}';
    }
}
