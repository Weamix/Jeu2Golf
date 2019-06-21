import MG2D.* ;      
import MG2D.geometrie.* ;

class Tir{

    //attributs
    private Point origine;
    private double puissance;
    private double angle;

   public Tir(){ // constructeur par défaut
       origine = new Point(0,0);
       puissance = 0;
       angle = 0;
   }

   public Tir(Tir t){ // constructeur par copie
       origine = new Point(t.origine); // on utilise le constructeur par copie de point qu'on stocke dans origine (pour éviter d'avoir 2 points?)
       puissance = t.puissance;
       angle = t.angle;
   }

   public Tir (Point o, double pui, double an){ // constructeur avec en paramètre le point, la puisance et l'angle
        origine = new Point(o);
        puissance = pui;
        angle = an;
   }

    // accesseurs/mutateurs   
    public Point getOrigine(){
        return origine;
    }

    public double getPuissance(){
        return puissance;
    }

    public double getAngle(){
        return angle;
    }

    public void setOrigine(Point p){
        origine = new Point(p);
    }

    public void setPuissance(double pu){
        puissance= pu;
    }

    public void setAngle(double a){
        angle = a;
    }


    /* methode qui retourne la position de la balle en fonction du temps et du poids */
    public Point calculPosition(double temps, int poids){ 
        double angleRad = (angle/180.0)*Math.PI;
        double x = puissance*Math.cos(angle)*temps+origine.getX();
        double y = -0.5*9.81*poids*temps*temps+puissance*Math.sin(angleRad)*temps+origine.getY();
        Point position = new Point((int)x,(int)y);
        return position;
    }

    public String toString(){ // methode toString
        String str = new String("Le tir a une puissance de"+puissance+", d'origine"+origine+"et d'angle"+angle);
        return str;
    }
}
