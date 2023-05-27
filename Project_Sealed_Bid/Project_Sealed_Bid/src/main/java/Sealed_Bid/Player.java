package Sealed_Bid;

public class Player 
{
	int pcode; //Player code
	String pname; //Player name
	double ptotal;	//Total amount of player offers
	double pfairshare; //Player Fair Share 
	double pheapamount;	//Player amount for the heap
	boolean offers;	//Check for offers
	static double totalheap; //Total pile amount
	double surplusshare;	//Player surplusshare
	double finalamount;	//Final player payment amount
	boolean giveoffer;	//Indication whether the player made an offer or not
	double totalbuyvalue;	//Total value of items acquired by the player
	int objectscount;	//Lots of items acquired by the player
	String objectslist;	//Object names acquired by the player
	boolean printonce;	//logical field to display the data of the player who acquired more than 1 item only once 
        double nettotal; //Amount left to one player
}

