package Sealed_Bid;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import javafx.scene.layout.Border;
import static javafx.scene.paint.Color.rgb;
import javax.swing.border.TitledBorder;


public class Algorithm_Sealed_Bid extends JFrame
{
    static JLabel lblplithosantikeikenon, lblplithospaikton;
    static JTextField txtplithosantikeikenon, txtplithospaikton;
    
    static JLabel lblantikeimena[], lblplayers[];
    static JTextField txtantikeimena[],txtpplayers[],txtprosfores[];
    
    static JLabel labelplayers[],lblper,labelplayers1[],labelplayers2[],labelplayers3[],labelplayers4[],labeltempplayers[];
    static JTextField textplayers[],txtpe,textplayers1[],textplayers2[],textplayers3[],textplayers4[],txtper,texttempplayers[];
    static JPanel ypop[],subypop,subypop1,subypop2,subypop3,subypop4,subypoptemp[];
    static JButton btnrun;
    static  Container con;
    static int N;
    static float E;
    static GridBagConstraints c;
    static int distance=0;
    
    Algorithm_Sealed_Bid()
    {
        con=getContentPane();
        con.setLayout(new FlowLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
    }
    
    public static void main(String[] args) 
    {	 
        byte[] input;
         
        int ch,code_paikti,i,j,maxp=-1,n,N=0,code,pos,k;
        
        Player players[]=null;
        
        double sum=0,max;
        
        String playernames[]=null,objects[]=null; 
        
        BID bids[][]=null;
        
        boolean found=false,adminstoixeia=false,userprosfores=false;
        
        Scanner sc=new Scanner(System.in);
        
        N=Integer.parseInt(JOptionPane.showInputDialog("Give the number of Players"));
        n=Integer.parseInt(JOptionPane.showInputDialog("Give a number of items for which an offer will be given"));
    
        Algorithm_Sealed_Bid am=new Algorithm_Sealed_Bid();
            
        am.setSize(N*290,899); 
        am.setVisible(true); 
        
        players=new Player[N];

        playernames=new String[N]; //Array with player name
     
        objects=new String[n]; //Static array with items

        bids=new BID[objects.length][N]; //Array with offers

        String itemsplayers[]=new String[N];
        for (i=0;i<N;i++)
            itemsplayers[i]=" ";
        
        for (i=0;i<objects.length;i++)
            objects[i]=JOptionPane.showInputDialog("Give the name of the "+(i+1)+"st item");
       
        ypop=new JPanel[N*n];
            
      
        ypop[0]= new JPanel();
        ypop[0].setLayout(new FlowLayout());
       
        ypop[1]= new JPanel();
        ypop[1].setLayout(new FlowLayout());
        
            
        lblplithosantikeikenon=new JLabel("Number of Players");
        lblplithosantikeikenon.setFont(new Font("TimesRoman", Font.BOLD, 13));
        lblplithosantikeikenon.setForeground(new Color(77,90,175));

        txtplithosantikeikenon=new JTextField(10);
        txtplithosantikeikenon.setFont(new Font("TimesRoman", Font.BOLD, 14));

        txtplithosantikeikenon.setText(String.valueOf(N));
        txtplithosantikeikenon.setEditable(false);
        txtplithosantikeikenon.setForeground(new Color(144,119,69));
        
        lblplithospaikton=new JLabel("Number of Items");
        lblplithospaikton.setFont(new Font("TimesRoman", Font.BOLD, 13));
        lblplithospaikton.setForeground(new Color(77,90,175));

        txtplithospaikton=new JTextField(10);
        txtplithospaikton.setFont(new Font("TimesRoman", Font.BOLD, 14));
        txtplithospaikton.setForeground(new Color(144,119,69));

        txtplithospaikton.setText(String.valueOf(n));
        txtplithospaikton.setEditable(false);

        c.weightx = 0.5;
        c.gridx = 0;
        c.ipady = 10;
        c.ipadx = 10;
        c.gridy = distance;
        c.anchor = GridBagConstraints.LINE_START;
            
        ypop[0].add(lblplithosantikeikenon);
        ypop[0].add(txtplithosantikeikenon);
        
        con.add(ypop[0],c);
        con.repaint();
        con.validate();              
        
        c.weightx = 0.5;
        c.gridx = 10;
        c.ipady = 10;
        c.ipadx = 20;
        c.gridy = distance;
        c.anchor = GridBagConstraints.LINE_START;      
    
        ypop[1].add(lblplithospaikton);
        ypop[1].add(txtplithospaikton);

        con.add(ypop[1],c);
        con.repaint();
        con.validate();  
     
       for (i=0;i<N;i++) //Repeat for each player
       {
           players[i]=new Player();
           players[i].pname=JOptionPane.showInputDialog("Give the name of the "+(i+1)+"st Player");
        }   
 
        for (i=0;i<objects.length;i++) //Repeat for objects
             for (j=0;j<N;j++)
                 bids[i][j]=new BID();

        for (j=0;j<N;j++) //Repeat for each player
        {
            for (i=0;i<objects.length;i++) //Repeat for objects
            {
               if (j==0)
                   bids[i][j].object=objects[i];

               bids[i][j].amount=Double.parseDouble(JOptionPane.showInputDialog("Player: "+ players[j].pname+" give your bid amount for the item: "+objects[i]));
               
               bids[i][j].player=players[j].pname; 
   
               players[j].giveoffer=true;
            } 
        }

        for (i=0;i<objects.length;i++)
           for (j=0;j<N;j++) //Repeat for each player 
           {
              sum=0;

              for (i=0;i<objects.length;i++) //Repeat for objects
                  sum+=bids[i][j].amount;

              players[j].ptotal=sum;
            }     

         for (i=0;i<N;i++)
             players[i].pfairshare=players[i].ptotal/N; //we divide the total amount of offers of each player by the number of players claiming
              
        for (i=0;i<N;i++) 
        {
            players[i].pheapamount=0; //Amount of each player in the heap
            players[i].offers=false;
            players[i].totalbuyvalue=0;//initialization of total player purchase value
            players[i].objectscount=0; //initialization of player purchase items counter
            players[i].objectslist="";//initialize player marketplace list
        }

        //Find the maximum bid for each item
        for (i=0;i<objects.length;i++) //Repeat for objects
        {
            max=bids[i][0].amount;
            maxp=0;

            for (j=0;j<N;j++)
                if (bids[i][j].amount>max)
                {
                    max=bids[i][j].amount;
                    maxp=j;
                }

           
            players[maxp].pheapamount+=max;

            players[maxp].objectslist+=objects[i]+", ";

            players[maxp].objectscount++;

            players[maxp].offers=true; //an indication that this player has bid higher than the others on at least one item
        }

         for (i=0;i<N;i++)
         {
             players[i].totalbuyvalue=players[i].pheapamount;

             players[i].pheapamount-=players[i].pfairshare; 
         }

        Player.totalheap=0; //zero total stack

        for (i=0;i<N;i++) 
            if (players[i].offers==false)
                Player.totalheap-=players[i].pfairshare; //for a player who does not take a property his faishare is removed from the heap
            else
                 Player.totalheap+=players[i].pheapamount;//for a player that gets real estate the amount is added to the heap: (maximum offer-fairshare)

      
        for (i=0;i<N;i++)
            players[i].surplusshare=Player.totalheap/N;

     
        for (i=0;i<N;i++) //field initialization offers to each player
             players[maxp].offers=false;

        for (i=0;i<objects.length;i++) //Repeat for objects
        {
            max=bids[i][0].amount;
            maxp=0;

            for (j=0;j<N;j++)
                if (bids[i][j].amount>max)
                {
                    max=bids[i][j].amount;
                    maxp=j;
                }

            players[maxp].finalamount= -players[maxp].pheapamount+players[maxp].surplusshare;
            players[maxp].offers=true;
        }

        for (i=0;i<N;i++) //field control offers to each player
            if (players[maxp].offers==false)
                players[i].finalamount=players[i].pfairshare+players[i].surplusshare;

   
        for (i=0;i<N;i++) //field initialization offers to each player
            players[maxp].offers=false; 

        players[maxp].printonce=false;

        for (i=0;i<objects.length;i++) //repeat for objects
        {
            max=bids[i][0].amount;
            maxp=0;

            for (j=0;j<N;j++)
                if (bids[i][j].amount>max)
                {
                    max=bids[i][j].amount;
                    maxp=j;
                }

           if (players[maxp].finalamount>0 &&  players[maxp].printonce==false)
           {
               if (players[maxp].objectscount==1)
               {
                   itemsplayers[maxp]+=objects[i]+" ";;
                   players[maxp].nettotal=Math.abs(players[maxp].totalbuyvalue+players[maxp].finalamount);
               }
               else
                   if (players[maxp].objectscount>1)
                   {
                       itemsplayers[maxp]+=players[maxp].objectslist+" ";;
                       players[maxp].printonce=true;
                       players[maxp].nettotal=Math.abs(players[maxp].totalbuyvalue+players[maxp].finalamount);
                   }
               players[maxp].offers=true;
           }
           else
                if (players[maxp].finalamount<0 &&  players[maxp].printonce==false)
                {
                    if (players[maxp].objectscount==1)
                    {
                        itemsplayers[maxp]+=objects[i]+" ";
                        players[maxp].nettotal=Math.abs(players[maxp].totalbuyvalue+players[maxp].finalamount);
                    }
                    else
                        if (players[maxp].objectscount>1)
                         {
                            itemsplayers[maxp]+=players[maxp].objectslist+" ";
                            players[maxp].nettotal=Math.abs(players[maxp].totalbuyvalue+players[maxp].finalamount);
                            players[maxp].printonce=true;
                        }
                    players[maxp].offers=true;
                }
        }

        for (i=0;i<N;i++) //field control offers to each player
           if (players[i].offers==false)
           {
               players[i].finalamount=players[i].pfairshare+players[i].surplusshare;
               itemsplayers[maxp]+=" ";
               players[i].nettotal=players[i].pfairshare+players[i].surplusshare;
           }
              
     
        JPanel panel = new JPanel();
     
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,30,29,30);
         
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.CENTER;
        
        for (i=0;i<objects.length;i++)
        {
            if (i==0)
            {
                c.gridx = i; // c.gridx = 0;
                c.gridy = i+1; //c.gridy = 1;
                c.gridwidth = 1;
                 
                for (j=0;j<N;j++)
                {
                    c.gridx = 10+j; // c.gridx = 10;
                    c.gridy = 1;  // c.gridy = 1;
                    c.gridwidth = 1;
                    
                    JTextField pnam=new JTextField(players[j].pname);
                    pnam.setFont(new Font("TimesRoman", Font.BOLD, 14));
                    pnam.setForeground(new Color(144,119,69));
                    panel.add(pnam, c);
                    
                    c.gridx+=10;
                }   
            }
        }
        
       
        for (i=0;i<objects.length;i++)
        {
            c.gridx = 0;
            c.gridy = i+2;
            c.gridwidth = 1;
        
            JTextField pob=new JTextField(bids[i][0].object);
            pob.setFont(new Font("TimesRoman", Font.BOLD, 14));
            pob.setForeground(new Color(77,90,175));
            panel.add(pob, c);
             
        }
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 1;
         
        JLabel lbltotal=new JLabel("Total");
         lbltotal.setFont(new Font("TimesRoman", Font.BOLD, 13));
         lbltotal.setForeground(new Color(160,54,82));

         panel.add(lbltotal, c);
         
         c.gridx = 0;
         c.gridy++;
         c.gridwidth = 1;

         JLabel lblfs=new JLabel("Fair Share");
         lblfs.setFont(new Font("TimesRoman", Font.BOLD, 13));
         lblfs.setForeground(new Color(160,54,82));

         panel.add(lblfs, c);
         
         c.gridx = 0;
         c.gridy++;
         c.gridwidth = 1;
         
         JLabel iv=new JLabel("Items Value");
         iv.setFont(new Font("TimesRoman", Font.BOLD, 13));
         iv.setForeground(new Color(160,54,82));

         panel.add(iv, c);
        
         c.gridx = 0;
         c.gridy++;
         c.gridwidth = 1;
         
         JLabel ss=new JLabel("Surplus share");
         ss.setFont(new Font("TimesRoman", Font.BOLD, 13));
         ss.setForeground(new Color(160,54,82));

         panel.add(ss, c);
         
         c.gridx = 0;
         c.gridy++;
         c.gridwidth = 1;
         
         JLabel ca=new JLabel("Cash");
         ca.setFont(new Font("TimesRoman", Font.BOLD, 13));
         ca.setForeground(new Color(160,54,82));

         panel.add(ca, c);
        
         c.gridx = 0;
         c.gridy++;
         c.gridwidth = 1;
         
         /*JLabel nt=new JLabel("Net total");
         nt.setFont(new Font("TimesRoman", Font.BOLD, 13));
         nt.setForeground(new Color(160,54,82));

         panel.add(nt, c); 
         
         c.gridx = 0;
         c.gridy++;
         c.gridwidth = 1;*/
         
         JLabel itms=new JLabel("Items");
         itms.setFont(new Font("TimesRoman", Font.BOLD, 13));
         itms.setForeground(new Color(160,54,82));

         panel.add(itms, c); 
         
        c.gridx = 10;
        c.gridy = 2;
        c.gridwidth = 1;
          
        for (i=0;i<objects.length;i++)
        {
            for (j=0;j<N;j++)
            {
                JTextField txtposo=new JTextField(String.valueOf(bids[i][j].amount),16);
                txtposo.setFont(new Font("TimesRoman", Font.PLAIN, 14));
                panel.add(txtposo,c);
                c.gridx+=1;;
                    
           }
            c.gridx = 10;
            c.gridy++;
         }
        
        c.gridx = 10;
        c.gridy = 3;
        c.gridwidth = 1;
          
        for (i=0;i<objects.length;i++)
        {
            for (j=0;j<N;j++)
            {
                JTextField txttotal=new JTextField(String.valueOf(players[j].ptotal),16);
                txttotal.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                txttotal.setForeground(new Color(28,102,35));
                panel.add(txttotal, c);
                c.gridx+=1;   
           }
            c.gridx = 10;
            c.gridy++;
         }
         
        c.gridx = 10;
        c.gridy = 4;
        c.gridwidth = 1;
          
        for (i=0;i<objects.length;i++)
        {
            for (j=0;j<N;j++)
            { 
                JTextField txtpfairshare=new JTextField(String.valueOf(players[j].pfairshare),16);
                txtpfairshare.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                txtpfairshare.setForeground(new Color(28,102,35));
                panel.add(txtpfairshare, c);
                c.gridx+=1;   
           }
            c.gridx = 10;
            c.gridy++;
         }
            
        c.gridx = 10;
        c.gridy = 5;
        c.gridwidth = 1;
         
        for (i=0;i<objects.length;i++)
        {
            for (j=0;j<N;j++)
            { 
                JTextField txttotalbuyvalue=new JTextField(String.valueOf(players[j].totalbuyvalue),16);
                txttotalbuyvalue.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                txttotalbuyvalue.setForeground(new Color(28,102,35));
                panel.add(txttotalbuyvalue, c);
                c.gridx+=1;   
            }
            c.gridx = 10;
            c.gridy++;
         }
        
        c.gridx = 10;
        c.gridy = 6;
        c.gridwidth = 1;
         
        for (i=0;i<objects.length;i++)
        {
            for (j=0;j<N;j++)
            { 
                JTextField txtsurplusshare=new JTextField(String.valueOf(players[j].surplusshare),16);
                txtsurplusshare.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                txtsurplusshare.setForeground(new Color(28,102,35));
                panel.add(txtsurplusshare, c);
                c.gridx+=1;   
           }
            
           c.gridx = 10;
           c.gridy++;
         }
        
         c.gridx = 10;
         c.gridy = 7;
         c.gridwidth = 1;
         
         for (i=0;i<objects.length;i++)
         {
            for (j=0;j<N;j++)
            { 
                JTextField txtfinalamount=new JTextField(String.valueOf(players[j].finalamount),16);
                txtfinalamount.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                txtfinalamount.setForeground(new Color(28,102,35));
                panel.add(txtfinalamount, c);
                c.gridx+=1;   
           }
            
           c.gridx = 10;
           c.gridy++;
         }
   
        c.gridx = 10;
        c.gridy = 8;
        c.gridwidth = 1;
         
        /*for (i=0;i<objects.length;i++)
        {
            for (j=0;j<N;j++)
            { 
                JTextField txtnettotal=new JTextField(String.valueOf(players[j].nettotal),16);
                txtnettotal.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                txtnettotal.setForeground(new Color(28,102,35));
                panel.add(txtnettotal, c);
                c.gridx+=1;   
            }
            c.gridx = 10;
            c.gridy++;
         }
        
        c.gridx = 10;
        c.gridy = 9;
        c.gridwidth = 1;*/
       
        for (i=0;i<objects.length;i++)
        {
            for (j=0;j<N;j++)
            {    
                JTextField rest=new JTextField(16);
                rest.setFont(new Font("TimesRoman", Font.BOLD, 12));
                rest.setForeground(new Color(64,116,145));
                rest.setText(itemsplayers[j]);
                panel.add(rest, c);
                c.gridx+=1;   
           }
            c.gridx = 10;
            c.gridy++;
         }
        
        con.add(panel,c); 
        con.repaint();
        con.validate();
    }
 }