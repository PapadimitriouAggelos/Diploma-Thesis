
package Aumann_Maschler;

import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import javafx.scene.layout.Border;
import static javafx.scene.paint.Color.rgb;
import javax.swing.border.TitledBorder;

public class Algorithm_Aumann_Maschler extends JFrame
{
	static JLabel labelplayers[],lblper,labelplayers1[],labelplayers2[],labelplayers3[],labelplayers4[],labeltempplayers[];
	static JTextField textplayers[],txtpe,textplayers1[],textplayers2[],textplayers3[],textplayers4[],txtper,texttempplayers[];
	static JPanel ypop[],subypop,subypop1,subypop2,subypop3,subypop4,subypoptemp[];
        static JButton btnrun;
        static  Container con;
        static int N;
        static float E;
        static GridBagConstraints c;
        static int distance=0;
        
        Algorithm_Aumann_Maschler()
        {
            con=getContentPane();
            con.setLayout(new GridBagLayout());
            c = new GridBagConstraints();
          
            c.fill = GridBagConstraints.VERTICAL;
        }

	public static void main(String[] args)
	{
            int i, p, k, minp=-1, maxp, difp, smaxp, counter;
            float sumamount=0, min=0, max, dif, smax, sum;
            boolean enough=true;
                     
            N=Integer.parseInt(JOptionPane.showInputDialog("Give the number of Players"));
            E=Float.parseFloat(JOptionPane.showInputDialog("Give Estate"));
    
            Algorithm_Aumann_Maschler am=new Algorithm_Aumann_Maschler();
            
            am.setSize(N*160,920); 
            
            am.setVisible(true); 
       
            Player players[]=new Player[N];

            ypop=new JPanel[N+3];
            
            
            ypop[0]= new JPanel();
            ypop[0].setLayout(new FlowLayout());
            
            lblper=new JLabel("Estate");
            lblper.setFont(new Font("TimesRoman", Font.BOLD, 13));
            lblper.setForeground(new Color(160,54,82));
            
            txtper=new JTextField(10);
            txtper.setFont(new Font("TimesRoman", Font.BOLD, 14));
            
            txtper.setText(String.valueOf(E));
            txtper.setEditable(false);
            txtper.setForeground(Color.red);
         
           
            c.weightx = 0.5;
            c.gridx = 0;
            c.ipady = 30;
            c.ipadx = 30;
            c.gridy = distance;
            c.anchor = GridBagConstraints.LINE_START;
            
            ypop[0].add(lblper);
            ypop[0].add(txtper);
            
            con.add(ypop[0],c);
            con.repaint();
            con.validate();  
          
     
            subypop=new JPanel();
            subypop.setLayout(new GridBagLayout());
            
            GridBagConstraints f = new GridBagConstraints();
            f.fill = GridBagConstraints.VERTICAL;
            subypop1=new JPanel();
            subypop1.setLayout(new FlowLayout());
           
           
            subypop2=new JPanel();
            subypop2.setLayout(new FlowLayout());
          
        
            ypop[1]=new JPanel();
            ypop[1].setLayout(new FlowLayout());
          
            labelplayers1=new JLabel[N];
            textplayers1=new JTextField[N];
            
            ypop[2]= new JPanel();
            ypop[2].setLayout(new FlowLayout());

            labelplayers2=new JLabel[N];
            textplayers2=new JTextField[N];
            
            subypoptemp=new JPanel[2*N+2];
            labeltempplayers=new JLabel[2*N+2];
            texttempplayers=new JTextField[2*N+2];
             
            for(i=0;i<N;i++) //Repeat for each player
            {
                players[i]=new Player();  
                
                players[i].pname=JOptionPane.showInputDialog("Give the name of the "+(i+1)+"st Player");
                players[i].claim=Float.parseFloat(JOptionPane.showInputDialog("Give the amount claimed by the "+(i+1)+"st Player"));
                      
                labelplayers1[i]=new JLabel("Player "+(i+1));
                labelplayers1[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                labelplayers1[i].setForeground(new Color(46,146,100));
                
                textplayers1[i]=new JTextField(players[i].pname);
                textplayers1[i].setFont(new Font("TimesRoman", Font.PLAIN, 14));
                textplayers1[i].setForeground(new Color(27,100,70));
              
                
                labelplayers2[i]=new JLabel("<html>Initial Claim <br>Amount</html>");
                labelplayers2[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                labelplayers2[i].setForeground(new Color(160,54,82));
        
                
                textplayers2[i]=new JTextField(String.valueOf(players[i].claim)); 
                textplayers2[i].setFont(new Font("TimesRoman", Font.PLAIN, 14));
                             
                ypop[1].add(labelplayers1[i]);
                ypop[1].add(textplayers1[i]);
                
                ypop[2].add(labelplayers2[i]);
                ypop[2].add(textplayers2[i]);
            }
            
           
            subypop1.add(ypop[1]);
            subypop2.add(ypop[2]);
            
            c.weightx = 0.5;
            c.gridx = 0;
            distance+=20;
            c.ipady = 30;
            c.ipadx = 30;
            c.gridy = distance;
            c.anchor = GridBagConstraints.LINE_START;
            
            con.add(subypop1,c);
            
            c.weightx = 0.5;
            c.gridx = 0;
            distance+=20;
            c.ipady = 30;
            c.ipadx = 30;
            c.anchor = GridBagConstraints.LINE_START;
            
            c.gridy = distance;
            
            
            con.add(subypop2,c);
      
            con.repaint();
            con.validate();
            
        
            int M=N;
            int round=0;
            
            for(i=0;i<N;i++) //we perform one repetition for each player until his container is full
            {
                //Calculation of the minimum amount of each player
                min=players[i].claim/2-players[i].box;
                minp=i;

                if (min*M<E)
                {
                    Player tempplayers[]=new Player[N];
                    int pos=0;
                    
                     for(int t=0;t<i;t++)
                     {
                         players[t].amount=0;
                         tempplayers[pos]=new Player();
                         tempplayers[pos++]=players[t];
                     }
                  
                    
                    for(int t=i;t<N;t++)
                    {
                        if(players[t].full==false)
                        {
                            players[t].box+=min;
                            players[t].finalclaim=players[t].box;
                            E-=min;
                            players[t].amount=min;
                            tempplayers[pos]=new Player();
                            tempplayers[pos++]=players[t];
                        }
                        else
                        {
                            players[i].amount=0;
                            tempplayers[pos]=new Player();
                            tempplayers[pos++]=players[i];
                        }
                    }
                 
                    round++;
                    printResults30(tempplayers,M,round);
                }
                else
                    if (min*M==E)
                    {
                        Player tempplayers[]=new Player[N];
                        int pos=0;
                        
                        for(int t=0;t<i;t++)
                        {
                             players[t].amount=0;
                             tempplayers[pos]=new Player();
                             tempplayers[pos++]=players[t];
                        }
                        
                        for(int t=i;t<N;t++)
                        {
                            if(players[t].full==false)
                            {
                                players[t].box+=min;
                                players[t].finalclaim=players[t].box;
                                E-=min;
                                players[t].amount=min;
                                tempplayers[pos]=new Player();
                                tempplayers[pos++]=players[t];
                                
                                if (E==0)
                                {
                                   round++;
                                   
                                   printResults30(tempplayers,M,round);

                                   printResults2(players);

                                   return;
                                } 
                               
                            }
                          
                        }
                       
                        round++;
                        
                        printResults3(tempplayers,M,round);
                    }
                    else
                        if (min*M>E)
                        {
                            Player tempplayers[]=new Player[N];
                            int pos=0;
                            
                            for(int t=0;t<i;t++)
                            {
                                 players[t].amount=0;
                                 tempplayers[pos]=new Player();
                                 tempplayers[pos++]=players[t];
                            }
                        
                  
                            for(int t=i;t<N;t++)
                            {
                                 players[t].finalclaim+=E/M;
                                 
                                 players[t].amount=E/M;
                                 tempplayers[pos]=new Player();
                                 tempplayers[pos++]=players[t];
                            }

                             round++;
                             
                             printResults30(tempplayers,M,round);
                              
                             printResults2(players);
                            
                             return;
                                
                        }
                        else
                        {
                            for(int t=0;t<N;t++)
                                players[i].finalclaim+=(float)E/N;
                            
                            printResults2(players);
                         
                            return;
                        }
                
                        M--;
                } 

      
            for(i=0;i<N;i++)
               players[i].claim=Math.abs(players[i].claim-players[i].finalclaim); //The amount received by each player is deducted from the initial requirement
            
            enough=false;
            
            for(p=0;p<N;p++)
            {
                    if(enough==true)
                       break;

                    max=players[0].claim;
                    maxp=0;

                    min=players[0].claim;
                    minp=0;

                    //With the next for we find the 1st maximum claim
                    for(i=0;i<N;i++)
                    {
                            if(players[i].claim>max)
                            {
                                    max=players[i].claim;
                                    maxp=i;
                            }

                            if(players[i].claim<min)
                            {
                                min=players[i].claim;
                                minp=i;
                            }
                    }

                    smax=getSecondLargest(players,players.length);

                    sum=0;
                    counter=0;

                    if(smax<0)
                        sum=N*max;

                    for(i=0;i<N;i++)
                    {
                            if(players[i].claim==max && smax>0)
                            {
                                    sum+=max-smax; //subtract from the maximum claim the 2nd maximum claim to find the 
                                    //amount that the player must get with the max claim in order to reach losses
                                    //the player with the second highest claim

                                    counter++; //number of players whose amount is equal to max
                            }
                            else
                                    if (players[i].claim==max && smax<0)
                                            counter++;
                    }

                    if(sum==E && counter==1)
                    {
                         Player tempplayers[]=new Player[N];
                         int pos=0;
                    
                            for(i=0;i<N;i++)
                                    if(players[i].claim==max)
                                    {
                                            players[i].claim-=max-smax;
                                            players[i].finalclaim+=max-smax;
                                            E-=max-smax;
                                            
                                             players[i].amount=max-smax;
                                             tempplayers[pos]=new Player();
                                             tempplayers[pos++]=players[i];
                                    }
                                    else
                                    {
                                         players[i].amount=0;
                                         tempplayers[pos]=new Player();
                                         tempplayers[pos++]=players[i];
                                    }
                          
                             round++;
                             
                             printResults40(tempplayers,round);
                            
                            printResults(players);
                           
                            return;
                    }

                    if(sum==E && counter==N)
                    {
                        Player tempplayers[]=new Player[N];
                        int pos=0;
                            
                        for(i=0;i<N;i++)
                        {
                            players[i].finalclaim+=E/N;
                            
                            players[i].amount=E/counter;
                            tempplayers[pos]=new Player();
                            tempplayers[pos++]=players[i];
                        }
                                                
                        if (pos-1!=-1) 
                            round++;
                          
                        printResults3(tempplayers,pos,round);

                        printResults(players);
                       
                        return;
                    }

                    if(sum==E && counter>1)
                    {
                       
                         Player tempplayers[]=new Player[N];
                         int pos=0;
                    
                            for(i=0;i<N;i++)
                                if(players[i].claim==max)
                                {
                                    players[i].claim-=max-smax;
                                    players[i].finalclaim+=max-smax;
                                    E-=max-smax;

                                    players[i].amount=max-smax;
                                    tempplayers[pos]=new Player();
                                    tempplayers[pos++]=players[i];
                                }
                                else
                                {
                                    players[i].amount=0;
                                    tempplayers[pos]=new Player();
                                    tempplayers[pos++]=players[i]; 
                                }
                             
                             round++;
                             
                             printResults30(tempplayers,pos,round);
                            
                            printResults(players);
                         
                            return;
                    }

                    if(sum<E && counter==1)
                    {
                         Player tempplayers[]=new Player[N];
                         int pos=0;
                         
                         for(i=0;i<N;i++)
                            if(players[i].claim==max)
                            {
                                players[i].claim-=max-smax;
                                players[i].finalclaim+=max-smax;
                                E-=max-smax;

                                players[i].amount=max-smax;
                                tempplayers[pos]=new Player();
                                tempplayers[pos++]=players[i];
                            }
                            else
                            {
                                players[i].amount=0;
                                tempplayers[pos]=new Player();
                                tempplayers[pos++]=players[i];
                            }
                                                                          
                         round++;
                         
                         printResults40(tempplayers,round);

                            if(E==0)
                               printResults(players);
                    }

                    if(sum<E && counter>1)
                    {
                        Player tempplayers[]=new Player[N];
                         int pos=0;
                    
                         for(i=0;i<N;i++)
                             if(players[i].claim==max)
                             {
                                players[i].claim-=max-smax;
                                players[i].finalclaim+=max-smax;
                                E-=max-smax;
                                
                                players[i].amount=max-smax;
                                tempplayers[pos]=new Player();
                                tempplayers[pos++]=players[i];
                             }
                             else
                             {
                                players[i].amount=0;
                                tempplayers[pos]=new Player();
                                tempplayers[pos++]=players[i];
                             }
                            
                             round++;
                             
                             printResults3(tempplayers,pos,round);

                            if(E==0)
                            {
                                printResults(players);
                              
                                return;
                            }
                    }

                    if(sum>E && counter==1)
                    {
                        for(i=0;i<N;i++)
                            if(players[i].claim==max)
                            {
                                players[i].claim-=E;
                                players[i].finalclaim+=E;
                                E-=max-smax;
                            }

                        printResults(players);
                           
                        return;
                    }

                    if(sum>E && counter>1)
                    {
                         Player tempplayers[]=new Player[N];
                         int pos=0;
                    
                         float initE=E;
                         int zeros=0;
                           
                         for(i=0;i<N;i++)    
                             if(players[i].claim==max)
                             {
                                players[i].claim-=initE/counter;
                                players[i].finalclaim+=initE/counter;
                                E-=initE/counter;

                                players[i].amount=initE/counter;
                                tempplayers[pos]=new Player();
                                tempplayers[pos++]=players[i];
                            }
                            else
                            {
                              players[i].amount=0;
                              tempplayers[pos]=new Player();
                              tempplayers[pos++]=players[i];
                              zeros++;
                            }


                            if (pos-1!=-1) 
                                round++;

                            if (zeros!=N)
                               printResults30(tempplayers,pos,round);
                            else
                               round--;


                             if (E==0)
                             {
                                 printResults(players);
                                
                                 return;
                             }
                    }
            }
            
            printResults(players);
         
            return;
	}
        
        
        static void printResults4(Player players[],int round)
	{
            subypoptemp[round-1]=new JPanel();
            subypoptemp[round-1].setLayout(new FlowLayout());            
           
            labeltempplayers[0]=new JLabel("Round "+round+" Player "+players[0].pname);
            labeltempplayers[0].setFont(new Font("TimesRoman", Font.BOLD, 13));
            labeltempplayers[0].setForeground(new Color(46,146,100));
               
                   
            texttempplayers[0]=new JTextField((String.format("%.2f", players[0].amount)));
            texttempplayers[0].setForeground(new Color(15,115,170));
                    
            texttempplayers[0].setFont(new Font("TimesRoman", Font.PLAIN, 14));
           
            
            subypoptemp[round-1].add(labeltempplayers[0]);
            subypoptemp[round-1].add(texttempplayers[0]);
            
            c.weightx = 0.5;
            c.gridx = 0;
            distance+=20;
            c.ipady = 30;
            c.ipadx = 30;
            c.gridy = distance;
            c.anchor = GridBagConstraints.LINE_START;
            
            con.add(subypoptemp[round-1],c);
            
            con.repaint();
            con.validate();
	}
        
        
        static void printResults3(Player players[],int M,int round)
	{
             subypoptemp[round-1]=new JPanel();
             
             subypoptemp[round-1].setLayout(new FlowLayout());
             
            for (int i=0;i<M;i++)
            {
                if (i==0)
                    labeltempplayers[i]=new JLabel("Round "+round+" Player "+players[i].pname);
                
                else
                     labeltempplayers[i]=new JLabel(" Player "+players[i].pname);
                
               labeltempplayers[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
               labeltempplayers[i].setForeground(new Color(46,146,100));
         
               texttempplayers[i]=new JTextField((String.format("%.2f", players[i].amount)));
               texttempplayers[i].setForeground(new Color(15,115,170));
              
               texttempplayers[i].setFont(new Font("TimesRoman", Font.PLAIN, 14));
             
     
               subypoptemp[round-1].add(labeltempplayers[i]);
               subypoptemp[round-1].add(texttempplayers[i]);
                
            }
            c.weightx = 0.5;
            c.gridx = 0;
            distance+=20;
            c.ipady = 30;
            c.ipadx = 30;
            c.gridy = distance;
            c.anchor = GridBagConstraints.LINE_START;
            
            con.add(subypoptemp[round-1],c);
            
            con.repaint();
            con.validate();
	}
        
        static void printResults2(Player players[])
	{
            subypop3=new JPanel();
            subypop3.setLayout(new FlowLayout());

            labelplayers3=new JLabel[N];
            textplayers3=new JTextField[N];
            
            ypop[3]= new JPanel();
            ypop[3].setLayout(new FlowLayout());

            for(int i=0;i<N;i++) //Repeat for each player
            { 
                labelplayers3[i]=new JLabel("<html>Final <br>Allocation</html>");
                labelplayers3[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                
                textplayers3[i]=new JTextField((String.format("%.2f", players[i].finalclaim)));
            
                textplayers3[i].setFont(new Font("TimesRoman", Font.PLAIN, 14));
                       
                ypop[3].add(labelplayers3[i]);
                ypop[3].add(textplayers3[i]);
            }
            
            subypop3.add(ypop[3]);
            
            c.weightx = 0.5;
            c.gridx = 0;
            distance+=20;
            c.ipady = 30;
            c.ipadx = 30;
            c.gridy = distance;
            c.anchor = GridBagConstraints.LINE_START;
            
            con.add(subypop3,c);
      
            con.repaint();
            con.validate();
	}
        
        
	static void printResults(Player players[])
	{ 
            subypop4=new JPanel();
            subypop4.setLayout(new FlowLayout());

            labelplayers4=new JLabel[N];
            textplayers4=new JTextField[N];
            
            ypop[4]= new JPanel();
            ypop[4].setLayout(new FlowLayout());

            for(int i=0;i<N;i++) //Επανάληψη για κάθε παίκτη
            { 
                labelplayers4[i]=new JLabel("<html>Final <br>Allocation</html>");
                labelplayers4[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                labelplayers4[i].setForeground(Color.blue);
               
                textplayers4[i]=new JTextField((String.format("%.2f", players[i].finalclaim)));  
                textplayers4[i].setFont(new Font("TimesRoman", Font.PLAIN, 14)); 
                textplayers4[i].setForeground(new Color(46, 58, 160)); 
               
                ypop[4].add(labelplayers4[i]);
                ypop[4].add(textplayers4[i]);
            }
            
            subypop4.add(ypop[4]);
            c.weightx = 0.5;
            c.gridx = 0;
            distance+=20;
            c.ipady = 30;
            c.ipadx = 30;
            c.gridy = distance;
            c.anchor = GridBagConstraints.LINE_START;
            
            con.add(subypop4,c);
      
            con.repaint();
            con.validate();
	}
        
        
        static void printResults30(Player players[],int M,int round)
	{
             subypoptemp[round-1]=new JPanel();
             
             subypoptemp[round-1].setLayout(new FlowLayout());
               
            for (int i=0;i<N;i++)
            {
                if (i==0)
                    labeltempplayers[i]=new JLabel("Round "+round+" Player "+players[i].pname);
                else
                     labeltempplayers[i]=new JLabel(" Player "+players[i].pname);
                
                labeltempplayers[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                labeltempplayers[i].setForeground(new Color(46,146,100));
         
                texttempplayers[i]=new JTextField((String.format("%.2f", players[i].amount)));
                texttempplayers[i].setForeground(new Color(15,115,170));

                texttempplayers[i].setFont(new Font("TimesRoman", Font.PLAIN, 14)); 
         
                subypoptemp[round-1].add(labeltempplayers[i]);
                subypoptemp[round-1].add(texttempplayers[i]);
            }
            
            c.weightx = 0.5;
            c.gridx = 0;
            distance+=20;
            c.ipady = 30;
            c.ipadx = 30;
            c.gridy = distance;
            c.anchor = GridBagConstraints.LINE_START;
            
            con.add(subypoptemp[round-1],c);
            
            con.repaint();
            con.validate();
	}
          
         static void printResults40(Player players[],int round)
	{
            subypoptemp[round-1]=new JPanel();
            subypoptemp[round-1].setLayout(new FlowLayout());            
           
            for (int i=0;i<N;i++)
            {
                if (i==0)
                    labeltempplayers[i]=new JLabel("Round "+round+" Player "+players[i].pname);
                else
                    labeltempplayers[i]=new JLabel(" Player "+players[i].pname);
                
                labeltempplayers[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                labeltempplayers[i].setForeground(new Color(46,146,100));
         
             
                texttempplayers[i]=new JTextField((String.format("%.2f", players[i].amount)));
                texttempplayers[i].setForeground(new Color(15,115,170));

                texttempplayers[i].setFont(new Font("TimesRoman", Font.PLAIN, 14));  
             
                
                subypoptemp[round-1].add(labeltempplayers[i]);
                subypoptemp[round-1].add(texttempplayers[i]);
            }
            
            c.weightx = 0.5;
            c.gridx = 0;
            distance+=20;
            c.ipady = 30;
            c.ipadx = 30;
            c.gridy = distance;
            c.anchor = GridBagConstraints.LINE_START;
            
            con.add(subypoptemp[round-1],c);
            
            con.repaint();
            con.validate();
	}  
          
	static float getSecondLargest(Player[] a, int total) //function of calculating the 2 largest claim amounts
	{
            float temp,max,res;
            int maxp;
            Player b[]=new Player[a.length];

            b=Arrays.copyOf(a, total);

            for(int i=0;i<total;i++)
                b[i]=new Player(a[i]);

            max=b[0].claim;
            maxp=0;

            for(int i=0;i<total;i++)
                if(b[i].claim>max)
                {
                    max=b[i].claim;
                    maxp=i;
                }

            res=max;

            for(int i=0;i<total;i++)
                if(b[i].claim==max)
                    b[i].claim=-1;

            max=b[0].claim;
            maxp=0;
            for(int i=0;i<total;i++)
                if(b[i].claim>max)
                {
                    max=b[i].claim;
                    maxp=i;
                }

                res=max;

            return res;	

        }
}
