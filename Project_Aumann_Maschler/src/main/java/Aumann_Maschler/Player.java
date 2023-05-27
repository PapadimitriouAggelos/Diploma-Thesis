package Aumann_Maschler;

public class Player 
{
	String pname; //Όνομα Παίκτη
    float box; //δοχείο παίκτη
    boolean full; //έλεγχος αν το δοχείο κάθε παίκτη γέμισε
    float claim; //αρχική διεκδίκηση παίκτη
    float finalclaim; //τελική αποζημίωση παίκτη
    float secclaim; //αποζημίωση παίκτη κατά το 2ο βήμα
    float amount;
    
    public Player()
    {
    }
    
    public Player(Player p)
    {
        this.box=p.box;
        this.claim=p.claim;
        this.finalclaim=p.finalclaim;
        this.full=p.full;
        this.pname=p.pname;
        this.secclaim=p.secclaim;
    }
}