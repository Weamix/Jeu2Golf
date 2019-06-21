import MG2D.* ;
import MG2D.geometrie.* ;
import MG2D.geometrie.Texture;
import java.awt.Font;

class Main{
    public static void main(String[] args){
        
        // création de la fênetre et du clavier
        Fenetre f = new Fenetre("Jeu 2 golf",1800,600);
        Clavier clavier = f.getClavier();

        /*Rectangle rec = new Rectangle(Couleur.NOIR,new Point(150,100),4,true);
        f.ajouter(rec);*/    
        
        // création et insertion des 2 fonds du jeu (qu'on fait translater l'un après l'autre dans la suite du programme)

        Texture background = new Texture ("background.png", new Point(0,0));
        f.ajouter(background);
        Texture background2 = new Texture ("background.png", new Point(1600,0));
        f.ajouter(background2);

        // création et insertion de la balle, de l'image du joueur, du score, de la hauteur et de l'invitation à jouer (espace) = INTERFACE

        Cercle balle = new Cercle(Couleur.NOIR,new Point(150,100),6,true);
        f.ajouter(balle);

        Texture joueur = new Texture ("joueurb1.png", new Point(100,95));
        f.ajouter(joueur);

        double x=0;
        Texte score = new Texte(Couleur.NOIR, new String("Score : "+(int)x), new Font("Calibri", Font.TYPE1_FONT, 20), new Point(1200, 550));
        f.ajouter(score);
        
        double y=0;
        Texte hauteur = new Texte(Couleur.NOIR, new String("Hauteur : "+(int)y), new Font("Calibri", Font.TYPE1_FONT, 20), new Point(800, 550));
        f.ajouter(hauteur);

        Texte espace = new Texte(Couleur.NOIR, new String("Appuyer sur espace pour lancer la balle "), new Font("Calibri", Font.TYPE1_FONT, 20), new Point(800, 400));
        f.ajouter(espace);

        // JAUGE DE PUISSANCE fonctionnel mais pas lié au TIR
        /*Rectangle cadre=new Rectangle(new Point(250,15),1000,50);
        f.ajouter(cadre);
        Rectangle jauge=new Rectangle(new Couleur(255,255,0),new Point(251,17),20,46,true);
        f.ajouter(jauge);*/

        // création d'un tir

        Tir t = new Tir(new Point(150,100),200,45);
        Point p = new Point();
        double tps = 0;
              
        while(true){
        
            if(clavier.getEspaceTape()){
                
                // execute si on appuie sur la barre espace du clavier
                f.supprimer(espace); //suppression du message d'invitation à jouer ("appuyer sur espace")
                for (int nb_img_joueur=1;nb_img_joueur<6;nb_img_joueur++) { //si la barre espace est enclenché l'animation du joueur (mouvement du club de golf) est lancé
                    joueur.setImg("joueurb"+nb_img_joueur+".png");
                    nb_img_joueur++;
                    try {
                        Thread.sleep(50);
                        }
                        catch (Exception e){
                        }
                        f.rafraichir();
                }
                while(t.getPuissance()>=10){
                    do{ /* Boucle qui demande au tir sa position avec la méthode de calcul de position en fonction du temps.
                        On récupère la coordonnée Y du point retourné et on met à jour la hauteur de la balle avec cette coordonnée*/
                        System.out.println(p.getY());
                        p = t.calculPosition(tps,1); // récuperation de la position du tir avec la méthode calcul position en fonction du temps
                        System.out.println(p);
                        tps+=0.1; //incrémentation du temps
                        balle.setO(new Point(150,(int)p.getY())); //On récupère la coordonnée Y du point retourné et on met à jour la hauteur de la balle avec cette coordonnée
                        hauteur.setTexte("Hauteur :"+String.valueOf(p.getY()-99)); //mise à jour de la hauteur dans l'affichage (nouvelle chaîne de caractere à attribuer)
                        score.setTexte("Score :"+String.valueOf(p.getX()));

                        /* translation des backgrounds en fonction du coin en bas à gauche du background (class Texture) => cela donne une impression de vitesse car les fonds défilent */

                        int bgX=-20; 
                        background.translater(bgX,0);
                        background2.translater(bgX,0);

                        if(background.getA().getX()<-1600){
                            background.translater(3200,0);
                        }

                        if(background2.getA().getX()<-1600){
                            background2.translater(3200,0);
                        }
                      
                        // disparition ("translation en dehors de l'écran") du joueur quand on tire la balle
                        joueur.translater(-20,0);

                        f.rafraichir(); 
                                
                    }
                    while(p.getY()>=100); // création d'un nouveau tir lorsque la balle touche le sol en divisant sa puissance par 2 et en intialisant le temps à 0
                    t = new Tir(new Point(150,100),(t.getPuissance()/2.0),45);
                    tps = 0;   
                }
            }
            else{
                
                    joueur.setImg("joueurb1.png"); // affichage du joueur de base (avant qu'il est tiré SOIT avant qu'on est cliqué)
                    try {
                        Thread.sleep(40);
                        }
                        catch (Exception e){
                        }
                        f.rafraichir();
                
            }
        }
    }
}


