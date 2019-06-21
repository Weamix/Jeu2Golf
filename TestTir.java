import MG2D.* ;
import MG2D.geometrie.* ;

class TestTir{
    public static void main(String[] args){
        Tir t = new Tir(new Point(0,0),20,45);
        Point p = new Point();
        double tps = 0;
        while (p.getY()>=0){
                for (double i=0;i<5;i+=0.1) {
                    p = t.calculPosition(tps,1);
                    System.out.println(p);
                    tps+=0.1;
                }
        }

    }
}