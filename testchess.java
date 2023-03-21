// package statement
package cpt_chess;

// imports
import java.lang.Math;
import java.io.IOException;
import java.util.Scanner;
import com.github.bhlangonijr.chesslib.*;

public class testchess 

{
	public static String [][] board = {{"r","n","b","q","k","b","n","r"},{"p","p","p","p","p","p","p","p"},{"E","E","E","E","E","E","E","E"},{"E","E","E","E","E","E","E","E"},{"E","E","E","E","E","E","E","E"},{"E","E","E","E","E","E","E","E"},{"P","P","P","P","P","P","P","P"},{"R","N","B","Q","K","B","N","R"}};
	static boolean kmove = false;
	public static String engine_move;
	/* pre: enter rook command
	 * post: determine if it's valid
	 */
	public static boolean rookcheck(int num1a, int num1b, int num2a, int num2b, String r) {
            int i;
	    if (num1a == num2a && num1b == num2b)
	        return false;

	    // Check collision
	    if (num1a == num2a) {
	        // x-axis
	        if (num1b < num2b) {
	            for (i = num1b + 1; i != num2b; ++i) // Right
	                if (board[num1a][i] != "E" ) {
	                	
	                	// checks if peice is capurable
	                	if(capture(num1a,i,r) == true) {
	                		return false;
	                	}
	                	return false;
	                }
	                	
	        } else {
	            for (i = num1b - 1; i != num2b; --i) // Left
	                if (board[num1a][i] != "E") {
	                	
	                	// checks if peice is capurable
	                	if(capture(num1a,i,r) == true) {
	                		return false;
	                	}
	                    return false;
	                }
	        }
	    }else if (num1b == num2b) {
	        // y-axis
	        if (num1a < num2a) {
	            for (i = num1a + 1; i != num2a; ++i) // Up
	                if (board[i][num1b] != "E") {
	                	
	                	// checks if peice is capurable
	                	if(capture(i,num1b,r)== true) {
	                		return false;
	                	}
	                    return false;
	                }
	        } else {
	            for (i = num1a - 1; i != num2a; --i) // Down
	                if (board[i][num1b] != "E") {
	                	
	                	// checks if peice is capurable
	                	if(capture(i,num1b,r) == true) {
	       
	                		return false;
	                	}
	                    return false;
	                }
	        }
	        
	    } else {
	        return false;
	    }
	    
	    // checks last spot to see if peice is capturable provided the move is legal 
	    if(board[num2a][num2b] != "E"){
			if(capture(num2a,num2b,r) == true) {
				move(num1a,num1b,num2a,num2b,r);
        		return true;
        	}
			else {
				return false;
			}
		}
	    
	    // if there is no colision there is nothing so return true
	    return true;
		
		
		
	}
	
	
	
	/* pre: eneter bishop comand
	 * post: is it valid 
	 */
	public static boolean bishop_Collision(int num1a, int num1b, int num2a, int num2b,String b) {
		
		
		// determining if the move is legal 
		
		if(num1a == num2a || num1b == num2b){
			return false;
		}
		
		if(Math.abs(num2a - num1a) != Math.abs(num2b - num1b)){
			return false;
		}
		
		
		// get direction for for loop
		int row, col;
		
		if(num1a < num2a){
			row = 1;
		}else{
			row = -1;
		}
		
		if(num1b < num2b){
			col = 1;
		}else{
			col = -1;
		}
		
		
		
		//loop through move and check for colision 
		int y = num1b + col;
		
		
		for(int x = num1a + row; x != num2a; x += row){
			if(board[x][y] != "E"){
				if(capture(x,y,b) == true) {
            		return false;
            	}
				else {
					return false;
				}
		
				
			}
			
			
			y += col;
		}
		
		// check last spot for capture/ colllsion 
		if(board[num2a][num2b] != "E"){
			if(capture(num2a,num2b,b) == true) {
				move(num1a,num1b,num2a,num2b,b);
        		return true;
        	}
			else {
				return false;
			}
		}
		
		// didn't see anything
		move(num1a,num1b,num2a,num2b,b);
		return true;
		
	}
	
	
	/* pre: eneter coordiantes 
	 * post: can it capture 
	 */
	public static boolean capture(int x2,int y2, String peice) {
		
		// setting of vairbles, "parsing" string to char 
		boolean capital = false;
		char peicea = peice.charAt(0);
		
		if (Character.isUpperCase(peicea) == true) {
			capital = true;
			
		}
		// is it the same as it's type
		if(Character.isUpperCase(board[x2][y2].charAt(0)) == capital &board[x2][y2].equalsIgnoreCase("e") == false )  {
			return false;
			
		}
		// if not
		else {
			return true;
		}
				
		
	}
	
        /* pre: king coordinates
	 * post: is there something there
	 */
	public static boolean king_Collision(int num2a, int num2b,String k) {
                // is there something 
		if (board[num2a][num2b] == "E") {
			return true;
		}
		else {
                        // is it allied or enemey
			if(capture(num2a,num2b,k)== true) {
        		return true;
        	}
			return false;
		}
	}
	
	/* pre:enter knight command
	 * post : is it valid
	 */
	public static boolean knightcheck(int num1a, int num1b, int num2a, int num2b) {
                // checking move validility
		if(board[num1a][num1b].equalsIgnoreCase("N") | board[num1a][num1b].equalsIgnoreCase("n")) {
			int nx = (Math.abs(num1a - num2a)) ;
			int ny = (Math.abs(num1b - num2b));
			if ((nx == 1 & ny ==2)|(nx == 2 & ny == 1)) {
                                
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
        
        /* pre: king command 
	 * post: validity
	 */
	public static boolean kingcheck(int num1a, int num1b, int num2a, int num2b) {
                // checking move validity
		if(board[num1a][num1b].equalsIgnoreCase("K") | board[num1a][num1b].equalsIgnoreCase("k")) {
			if (Math.abs(num1a - num2a) <= 1 & Math.abs(num1b - num2b) <= 1) {
				
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	


        /*pre: enetr a king move
        *post: is it a legal castle
        */
        public static boolean castle(int x1, int y1,int x2,int y2, String colour ){

                        // white side castle 
                        if (colour.equalsIgnoreCase("w")) {

                                //castle permameters 
                                if ((x1 == 7 & y1 == 4)&(x2 == 7 & y2 == 6) ) {
                                        if(board[7][5].equalsIgnoreCase("E") & board[7][6].equalsIgnoreCase("E") & board[7][7].equals("R")& kmove == false) {

                                                // castle move 
                                                move(7,7,7,5,"R");
                                                move(7,4,7,6,"K");
                                                return true;

                                        }
                                        else {
                                                System.out.println("illicit stuff");
                                                return false;
                                        }

                                }
                                else if((x1 == 7 & y1 == 4)&(x2 == 7 & y2 == 2) ){
                                        if(board[7][3].equalsIgnoreCase("E") & board[7][2].equalsIgnoreCase("E") & board[7][1].equalsIgnoreCase("E")& board[7][0].equals("R")& kmove == false) {
                                                move(7,0,7,3,"R");
                                                move(7,4,7,2,"K");
                                                return true;
                                        }
                                        else {
                                                return false;
                                        }

                                }


                                
                        }
                        // black
                        else {
                                //kingside
                                if ((x1 == 0 & y1 == 4)&(x2 == 0 & y2 == 6) ) {

                                        // parameters 
                                        if(board[0][5].equalsIgnoreCase("E") & board[0][6].equalsIgnoreCase("E") & board[0][7].equals("r")& kmove == false) {

                                                // castle move 
                                                move(0,7,0,5,"r");
                                                move(0,4,0,6,"k");
                                                return true;
                                        }

                                }
                                // queenside
                                else if((x1 == 0 & y1 == 4)&(x2 == 0 & y2 == 2) ){
                                        // parameters
                                        if(board[0][3].equalsIgnoreCase("E") & board[0][2].equalsIgnoreCase("E") & board[0][1].equalsIgnoreCase("E")& board[0][0].equals("r") & kmove == false) {

                                                // castle move
                                                move(7,0,0,3,"r");
                                                move(7,4,0,2,"k");
                                                return true;
                                        }
                                        else {
                                                return false;
                                        }

                                }


                        }

                        return false; 
                }
                
               
	/* pre: n/a
	 * post: prints board
	 */
	public static void boardprint(int x1, int y1) {
		// run through board with loop and print it 
              
		System.out.println("  " + "a" +  " " + "b" +  " " + "c" +  " " + "d" + " " + "e" +  " " +"f" +  " " +"g" +  " " +"h");
		for(int i = 0 ; i < 8; i ++) {
			System.out.println( (8 - i)+ " " + board[i][0] + " " + board[i][1] + " " + board[i][2] +" " + board[i][3] +" " + board[i][4] +" " + board[i][5] +" " + board[i][6]+ " " +board[i][7]);
		}
		
		
		
		System.out.println(" ");
		
		
	}
	
        /* pre:  enter queen command 
	 * post: validity
	 */
	public static boolean queencheck(int num1a, int num1b, int num2a, int num2b, String q) {
		if(board[num1a][num1b].equalsIgnoreCase("q") | board[num1a][num1b].equalsIgnoreCase("Q")) {
			int i;
			
			// determining if rook move
			if(num2a == num1a | num2b == num1b) {
				
				//see rook for more in-depth comenting
				
			    if (num1a == num2a && num1b == num2b)
			        return false;

			    // Check collision
			    if (num1a == num2a) {
			        // x-axis
			        if (num1b < num2b) {
			            for (i = num1b + 1; i != num2b; ++i) // Right
			                if (board[num1a][i] != "E" ) {
			                	if(capture(num1a,i,q) == true) {
			                		return false;
			                	}
			                	return false;
			                }
			                	
			        } else {
			            for (i = num1b - 1; i != num2b; --i) // Left
			                if (board[num1a][i] != "E") {
			                	if(capture(num1a,i,q) == true) {
			                		return false;
			                	}
			                    return false;
			                }
			        }
			    }else if (num1b == num2b) {
			        // y-axis
			        if (num1a < num2a) {
			            for (i = num1a + 1; i != num2a; ++i) // Up
			                if (board[i][num1b] != "E") {
			                	if(capture(i,num1b,q)== true) {
			                		return false;
			                	}
			                    return false;
			                }
			        } else {
			            for (i = num1a - 1; i != num2a; --i) // Down
			                if (board[i][num1b] != "E") {
			                	if(capture(i,num1b,q) == true) {
			       
			                		return false;
			                	}
			                    return false;
			                }
			        }
			        
			    } else {
			        return false;
			    }
			    if(board[num2a][num2b] != "E"){
					if(capture(num2a,num2b,q) == true) {
						move(num1a,num1b,num2a,num2b,q);
		        		return true;
		        	}
					else {
						return false;
					}
				}

			    return true;
			}
			
			// checking to see if it's the bishop 
			else if (Math.abs(num1a - num2a) == Math.abs(num1b - num2b)) {
				
				
				// see bishop for better comments
				if(num1a == num2a || num1b == num2b){
					
					return false;
				}
				
				if(Math.abs(num2a - num1a) != Math.abs(num2b - num1b)){
					return false;
				}
				
				int row, col;
				
				if(num1a < num2a){
					row = 1;
				}else{
					row = -1;
				}
				
				if(num1b < num2b){
					col = 1;
				}else{
					col = -1;
				}
				
				
				int y = num1b + col;
				
				
				for(int x = num1a + row; x != num2a; x += row){
					if(board[x][y] != "E"){
						if(capture(x,y,q) == true) {
		            		return false;
		            	}
						else {
							return false;
						}
				
						
					}
					
					
					y += col;
				}
				if(board[num2a][num2b] != "E"){
					if(capture(num2a,num2b,q) == true) {
						move(num1a,num1b,num2a,num2b,q);
		        		return true;
		        	}
					else {
						return false;
					}
				}
				
				move(num1a,num1b,num2a,num2b,q);
				return true;
			
		}
		
			
			else {
			return false;
		}
	}
		else {
			return false;
		}
		
		
	}
        
        /* Translate chess notation into array location
         * Pre: valid position and in lower case
         * Post: array location returned
         */
	public static int pos_translate (String pos)
    {
       
        switch (pos)
       {
           case "a":
               return 0;
           case "b":
               return 1;
           case "c":
               return 2;
           case "d":
               return 3;
           case "e":
               return 4;
           case "f":
               return 5;
           case "g":
               return 6;
           case "h":
               return 7;
           default:
               return 8-Integer.parseInt(pos);
                  
       }
                
    }
        /* Move piece to location on board
         * Pre: place1, place2, valid piece 
         * Post: piece moved
         */
	public static void move(int x1, int y1,int x2,int y2,String piece) {
		board[x1][y1] = "E";
		board[x2][y2] = piece;
		
	}
	
	
    /* Determine if pawn move is legal
     * Pre: valid cords
     * Post: boolean representing if move is valid returned
     * X = row, y = coll
     */
    public static boolean pawncheck (int x1, int y1, int x2, int y2, String current_colour)
    {
       if (current_colour.equals("w"))
       {
           if (x1 == 6 && x1-2 == x2 && y1 == y2 && board[x1-2][y1].equals("E") && board[x1-1][y1].equals("E"))
           {
              return true;  
           }
           
           if (x1-1 == x2 && y1 == y2 && board[x1-1][y1].equals("E"))
           {
               return true;
           }
           
           if (x1-1 == x2 && y1+1 == y2)
           {
               return ((!board[x1-1][y1+1].equals("E")) && Character.isLowerCase(board[x1-1][y1+1].charAt(0)));
           }
           
           if (x1-1 == x2 && y1-1 == y2)
           {
               return ((!board[x1-1][y1-1].equals("E")) && Character.isLowerCase(board[x1-1][y1-1].charAt(0)));
           }
           
        
       }
       
       else
       {
           if (x1 == 1 && x1+2 == x2 && y1 == y2 && board[x1+2][y1].equals("E") && board[x1+1][y1].equals("E"))
           {
               return true; 
           }
           
           if (x1+1 == x2 && y1 == y2 && board[x1+1][y1].equals("E"))
           {      
               return true;
           }
           
           if (x1+1 == x2 && y1+1 == y2) 
           {
               return ((!board[x1+1][y1+1].equals("E")) && Character.isUpperCase(board[x1+1][y1+1].charAt(0)));
           }
           
           if (x1+1 == x2 && y1-1 == y2) 
           {
               return ((!board[x1+1][y1-1].equals("E")) && Character.isUpperCase(board[x1+1][y1-1].charAt(0)));
           }
           
       }
       
       return false;
    }
    
       /* Find attacking pieces of a given square
        * Pre: valid position, direction, and colour
        * Post: location in terms of array of first attacking piece of square is returned 
        */
        public static String ray_trace (int posx, int posy, String direction, String colour)
      {
          
        int xpos;
        int ypos;
        
        if (colour.equals("w"))
        {   //down left
            if (direction.equals("dl"))  
            {
               xpos = posx + 1;
               ypos = posy - 1;    
               while (xpos != 8 && ypos >= 0)
               {
                   if (!board[xpos][ypos].equals("E") && Character.isUpperCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos++;
                   ypos--;
               }

            }

            //down right
            if (direction.equals("dr"))
            {
               xpos = posx + 1;
               ypos = posy + 1;    
               while (xpos != 8 && ypos != 8)
               {
                   if (!board[xpos][ypos].equals("E") && Character.isUpperCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos++;
                   ypos++;
               }
            }

            //up left
            if (direction.equals("ul"))
            {
               xpos = posx - 1;
               ypos = posy - 1;    
               while (xpos >= 0 && ypos >= 0)
               {
                   if (!board[xpos][ypos].equals("E") && Character.isUpperCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos--;
                   ypos--;
               }
            }

            //up right
            if (direction.equals("ur"))
            {
               xpos = posx - 1;
               ypos = posy + 1;    
               while (xpos >= 0 && ypos != 8)
               {
                   if (!board[xpos][ypos].equals("E") && Character.isUpperCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos--;
                   ypos++;
               }
            }

            //up
            if (direction.equals("u"))
            {
               xpos = posx - 1;
               ypos = posy;
               while (xpos >= 0 )
               {
                   if (!board[xpos][ypos].equals("E") && Character.isUpperCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos--;
               }
            }

            //down
            if (direction.equals("d"))
            {
               xpos = posx + 1;
               ypos = posy;
               while (xpos != 8 )
               {
                   if (!board[xpos][ypos].equals("E") && Character.isUpperCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos++;
               }
            }

            //left
            if (direction.equals("l"))
            {
               xpos = posx;
               ypos = posy - 1;
               while (ypos >= 0 )
               {
                   if (!board[xpos][ypos].equals("E") && Character.isUpperCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   ypos--;
               }
            }

            //right
            if (direction.equals("r"))
            {
               xpos = posx;
               ypos = posy + 1;
               while (ypos != 8 )
               {
                   if (!board[xpos][ypos].equals("E") && Character.isUpperCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   ypos++;
               }
        
            }
        
        }
        
        else
        {
                //down left
            if (direction.equals("dl"))  
            {
               xpos = posx + 1;
               ypos = posy - 1;    
               while (xpos != 8 && ypos >= 0)
               {
                   if (!board[xpos][ypos].equals("E") && Character.isLowerCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos++;
                   ypos--;
               }

            }

            //down right
            if (direction.equals("dr"))
            {
               xpos = posx + 1;
               ypos = posy + 1;    
               while (xpos != 8 && ypos != 8)
               {
                   if (!board[xpos][ypos].equals("E") && Character.isLowerCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos++;
                   ypos++;
               }
            }

            //up left
            if (direction.equals("ul"))
            {
               xpos = posx - 1;
               ypos = posy - 1;    
               while (xpos >= 0 && ypos >= 0)
               {
                   if (!board[xpos][ypos].equals("E") && Character.isLowerCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos--;
                   ypos--;
               }
            }

            //up right
            if (direction.equals("ur"))
            {
               xpos = posx - 1;
               ypos = posy + 1;    
               while (xpos >= 0 && ypos != 8)
               {
                   if (!board[xpos][ypos].equals("E") && Character.isLowerCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos--;
                   ypos++;
               }
            }

            //up
            if (direction.equals("u"))
            {
               xpos = posx - 1;
               ypos = posy;
               while (xpos >= 0 )
               {
                   if (!board[xpos][ypos].equals("E") && Character.isLowerCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos--;
               }
            }

            //down
            if (direction.equals("d"))
            {
               xpos = posx + 1;
               ypos = posy;
               while (xpos != 8 )
               {
                   if (!board[xpos][ypos].equals("E") && Character.isLowerCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   xpos++;
               }
            }

            //left
            if (direction.equals("l"))
            {
               xpos = posx;
               ypos = posy - 1;
               while (ypos >= 0 )
               {
                   if (!board[xpos][ypos].equals("E") && Character.isLowerCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   ypos--;
               }
            }

            //right
            if (direction.equals("r"))
            {
               xpos = posx;
               ypos = posy + 1;
               while (ypos != 8 )
               {
                   if (!board[xpos][ypos].equals("E") && Character.isLowerCase(board[xpos][ypos].charAt(0)))
                   {
                       return Integer.toString(xpos) + Integer.toString(ypos);
                   }

                   ypos++;
               }
            }
        
        }
            
        return Integer.toString(posx) + Integer.toString(posy);
      }
        
        
        /* Determine if king is in check
         * Pre: valid colour
         * Post: boolean returned true if check, false if not
         */
        public static boolean is_check (String colour)
      {
            String [] positions = new String [10];
            
            // find king loc
            String king;
            if(colour.equals("w")) {
                    king = "k";
            }
            else {
                    king = "K";
            }
            int kingposx=1;
            int kingposy=1;
            for(int j = 0; j<= 7; j++) {
                    for (int x =0; x <= 7 ; x++) {
                            if(board[j][x].equals(king)) {
                                    kingposx = j;
                                    kingposy = x;
                            }
                    }
            }
            
                //find knights
                String knight;
                int knight1posx = kingposx;
                int knight1posy = kingposy;
                int knight2posx = kingposx;
                int knight2posy = kingposy;
                
                if(colour.equals("w")) {
                    knight = "N";
                }
                else {
                        knight = "n";
                }

                for(int j = 0; j<= 7; j++) {
                        for (int x =0; x <= 7 ; x++) {
                                if(board[j][x].equals(knight)) {
                                        knight1posx = j;
                                        knight1posy = x;
                                }
                        }
                }
                
                for(int j = 7; j >= 0; j--) {
                        for (int x = 7; x >= 0 ; x--) {
                                if(board[j][x].equals(knight)) {
                                        knight2posx = j;
                                        knight2posy = x;
                                }
                        }
                }
                
                positions [8] = Integer.toString(knight1posx)+Integer.toString(knight1posy);
                positions [9] = Integer.toString(knight2posx)+Integer.toString(knight2posy);
                
                
            if (colour.equals("w"))  
            {
                positions[0] = ray_trace(kingposx,kingposy,"u",colour);
                positions[1] = ray_trace(kingposx,kingposy,"d",colour);
                positions[2] = ray_trace(kingposx,kingposy,"l",colour);
                positions[3] = ray_trace(kingposx,kingposy,"r",colour);
                positions[4] = ray_trace(kingposx,kingposy,"ul",colour);
                positions[5] = ray_trace(kingposx,kingposy,"ur",colour);
                positions[6] = ray_trace(kingposx,kingposy,"dl",colour);
                positions[7] = ray_trace(kingposx,kingposy,"dr",colour);
                
                
                for (String elems : positions)
                {
                    
                    if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("P"))
                    {
                        if (pawncheck(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy,colour))
                        {
                            return true;
                        }
                        
                    }

                    else if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("R"))
                    {
                        if (rookcheck(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy,"R"))
                        {
                            return true;
                        }
                    }
                    
                    else if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("B"))
                    {
                        if (bishop_Collision(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy,"B"))
                        {
                            return true;
                        }
                    }
                    
                    else if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("N"))
                    {
                        if (knightcheck(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy))
                        {
                            return true;
                        }
                    }
                    
                    else if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("Q"))
                    {
                        if (queencheck(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy,"Q"))
                        {
                            return true;
                        }
                    }
                    
                
                } 

            }
            
            else
            {
                positions[0] = ray_trace(kingposx,kingposy,"u",colour);
                positions[1] = ray_trace(kingposx,kingposy,"d",colour);
                positions[2] = ray_trace(kingposx,kingposy,"l",colour);
                positions[3] = ray_trace(kingposx,kingposy,"r",colour);
                positions[4] = ray_trace(kingposx,kingposy,"ul",colour);
                positions[5] = ray_trace(kingposx,kingposy,"ur",colour);
                positions[6] = ray_trace(kingposx,kingposy,"dl",colour);
                positions[7] = ray_trace(kingposx,kingposy,"dr",colour);
                
                for (String elems : positions)
                {
                    
                    if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("p"))
                    {
                         if (pawncheck(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy,colour))
                         {
                      
                             return true;
                         }
                    }

                    else if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("r"))
                    {
                        if (rookcheck(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy,"r"))
                        {   
                            return true;
                        }
                    }
                    
                    else if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("b"))
                    {
                        if (bishop_Collision(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy,"b"))
                        {
                            
                            return true;
                        }
                    }
                    
                    else if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("n"))
                    {
                        if (knightcheck(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy))
                        {
                            
                            return true;
                        }
                        
                    }
                    
                    else if (board[Integer.parseInt(elems.split("")[0])][Integer.parseInt(elems.split("")[1])].equals("q"))
                    {
                        if (queencheck(Integer.parseInt(elems.split("")[0]),Integer.parseInt(elems.split("")[1]),kingposx,kingposy,"q"))
                        {
                       
                            return true;
                        }
                    }
                    
                    
                    
                
                }
            }
      
        
        return false;
         
      }
      
      
     /* pre: enter command and coordinates, main game function
      * post: makes the move and uses other functions to check the validity
      */
	public static boolean move(String colour, String x1, String y1, String x2, String y2) {
                // colour set up
		String r;
		String n;
		String b;
		String k;
		String q;
		String p;
		String opposite_colour;
                
		if(colour.equalsIgnoreCase("w") == true) {
			r = "R";
			n = "N";
			b = "B";
			k = "K";
			q = "Q";
			p = "P";
			opposite_colour = "b";
		}
		else {
			r = "r";
			n = "n";
			b = "b";
			k = "k";
			q = "q";
			p = "p";
                        opposite_colour = "w";
			
		}
		
                // setting up vairbles for use in the array
		int x1a = pos_translate(y1);
		int y1a = pos_translate(x1);
		int x2a = pos_translate(y2);
		int y2a = pos_translate(x2);
	
		// which perice are they selecting
		if (board[x1a][y1a].equals(r) == true) {
                        //checking validinlty of move
			if (rookcheck(x1a, y1a, x2a, y2a,r) == true) {
				if (!is_check(opposite_colour))
                                {
                                    System.out.println("this move works");
                                    return true;
                                }
                                
                                else
                                {
                                    move(x2a,y2a,x1a,y1a,r);
                                }
			}
			else {
				System.out.println("you cannot move this peice here");
			}
		}
		else if (board[x1a][y1a].equals(n) == true) {
                        //checking validinlty of move
			if (knightcheck(x1a, y1a, x2a, y2a)== true & capture(x2a, y2a,n)== true) {
				move(x1a,y1a,x2a,y2a,n);
				if (!is_check(opposite_colour))
                                {
                                    System.out.println("this move works");
                                    return true;
                                }
                                
                                else
                                {
                                    move(x2a,y2a,x1a,y1a,n);
                                }
			}
			else {
				System.out.println("you cannot move this peice here");
			}
		}
		else if (board[x1a][y1a].equals(b) == true) {
                        //checking validinlty of move
			if (bishop_Collision(x1a, y1a, x2a, y2a,b)) {
				if (!is_check(opposite_colour))
                                {
                                    System.out.println("this move works");
                                    return true;
                                }
                                
                                else
                                {
                                    move(x2a,y2a,x1a,y1a,b);
                                }
			}
			else {
				System.out.println("you cannot move this peice here");
			}
		}
		else if (board[x1a][y1a].equals(k) == true) {
			if (kingcheck(x1a, y1a, x2a, y2a)== true ) {
				
				//checking validinlty of move
				
				if (king_Collision(x2a,y2a,k)) {
					System.out.println("this move works");
					kmove = true;
					move(x1a,y1a,x2a,y2a,k);
					boardprint(0,0);
				}
				else {
					System.out.println("you cannot move this peice here");
				}
			}
			else {
				if (castle(x1a,y1a,x2a,y2a,colour) == true) {
					System.out.println("castled");
					kmove = true;
					boardprint(0,0);
				}
				else {
					System.out.println("castle failed");
					System.out.println("you cannot move this peice here");
				
				}
			}
		}
		else if (board[x1a][y1a].equals(q) == true) {
                        //checking validinlty of move
			if (queencheck(x1a, y1a, x2a, y2a,q)== true) {
				move(x1a,y1a,x2a,y2a,q);
				if (!is_check(opposite_colour))
                                {
                                    System.out.println("this move works");
                                    return true;
                                }
                                
                                else
                                {
                                    move(x2a,y2a,x1a,y1a,q);
                                }
			}
			else {
				System.out.println("you cannot move this peice here");
			}
		}
		else if (board[x1a][y1a].equals(p) == true) {
                        //checking validinlty of move
			if (pawncheck(x1a, y1a,x2a, y2a,colour)== true) {
				move(x1a,y1a,x2a,y2a,p);
                                if (!is_check(opposite_colour))
                                {
                                    System.out.println("this move works");
                                    return true;
                                }
                                
                                else
                                {
                                    move(x2a,y2a,x1a,y1a,p);
                                }
                                
			}
			else {
				System.out.println("you cannot move this peice here");
			}
		}
		else {
			System.out.println("this is not a valid peice");
		}
		return false; 
	}
        
        
        /* Run a game of chess 
         * Pre: n/a
         * Post: Game is run
         */
        public static void gameplay_loop () throws IOException, InterruptedException
        {
            Scanner reader = new Scanner(System.in);
		
		String x1, y1, x2, y2;
		boolean game = true;
		char end;
		Board bor = new Board ();
                
                
                System.out.println("Enter (s) for single player and (m) for multiplayer:");
                
                if (reader.next().equals("m"))
                {
                    boardprint(0,0);
                    while (game)
                    {
                        System.out.println("welcome to the chess tetsing lab, please enter x1 and y1 for white");
                        x1 = reader.next();
                        y1 = reader.next();
                        
                        System.out.println(" please enter x2 and y2 for white ");
                        x2 = reader.next();
                        y2 = reader.next();
                        
                        
                        while (testchess.move("w", x1, y1, x2, y2) != true)
                        {
                            System.out.println("please enter valid x1, y1 cords for white");
                            x1 = reader.next();
                            y1 = reader.next();
                            
                            System.out.println("please enter valid x2, y2 cords for white");
                            x2 = reader.next();
                            y2 = reader.next();
                            boardprint(0,0);
                        }
                        
                        boardprint(0,0);
                        
                        bor.loadFromFen(FEN_Thing.return_FEN(board, "b"));
                        
                        if (bor.isMated())
                        {
                            System.out.print("Checkmate White Wins");
                            game = false;
                        }
                        
                        if (bor.isStaleMate())
                        {
                            System.out.print("Stalemate it's a draw");
                            break;
                        }
                        
                        
                        System.out.println("welcome to the chess tetsing lab, please enter x1 and y1 for black");
                        x1 = reader.next();
                        y1 = reader.next();
                        
                        System.out.println(" please enter x2 and y2 for black ");
                        x2 = reader.next();
                        y2 = reader.next();
                        
                        
                        while (testchess.move("b", x1, y1, x2, y2) != true)
                        {
                            System.out.println("please enter valid x1, y1 cords for black");
                            x1 = reader.next();
                            y1 = reader.next();
                            
                            System.out.println("please enter valid x2, y2 cords for black");
                            x2 = reader.next();
                            y2 = reader.next();
                            boardprint(0,0);
                        }
                        
                        boardprint(0,0);
                        
                        bor.loadFromFen(FEN_Thing.return_FEN(board, "w"));
                        
                        if (bor.isMated())
                        {
                            System.out.print("Checkmate Black Wins");
                            break;
                        }
                        
                        if (bor.isStaleMate())
                        {
                            System.out.print("Stalemate it's a draw");
                            break;
                        }
                        
                        
                        System.out.println(" if you would like to quite enter q ");
                        
                        end = reader.next().charAt(0);
                        
                        if(end == 'q') {
                            
                            game = false;
                        }
                        
                        
                        
                    }
                } 
                
                else 
                {
                    System.out.print("Enter (w) for white or (b) for black:\n");
                    
                    if (reader.next().equals("w"))
                    {
                        Engine e = new Engine ();
                        e.start_process();
                        e.read();
                        
                        System.out.print("Enter engine skill level (1-20):\n");
                                
                        Engine.executeCommand(reader.next());
                        
                        boardprint(0,0);
                        
                        while (game)
                    {
                        System.out.println("welcome to the chess tetsing lab, please enter x1 and y1 for white");
                        x1 = reader.next();
                        y1 = reader.next();
                        
                        System.out.println(" please enter x2 and y2 for white ");
                        x2 = reader.next();
                        y2 = reader.next();
                        
                        
                        while (testchess.move("w", x1, y1, x2, y2) != true)
                        {
                            System.out.println("please enter valid x1, y1 cords for white");
                            x1 = reader.next();
                            y1 = reader.next();
                            
                            System.out.println("please enter valid x2, y2 cords for white");
                            x2 = reader.next();
                            y2 = reader.next();
                            boardprint(0,0);
                        }
                        
                        boardprint(0,0);
                        
                        bor.loadFromFen(FEN_Thing.return_FEN(board, "b"));
                        
                        if (bor.isMated())
                        {
                            System.out.print("Checkmate White Wins");
                            game = false;
                        }
                        
                        if (bor.isStaleMate())
                        {
                            System.out.print("Stalemate it's a draw");
                            break;
                        }
                        
                        Engine.executeCommand("position fen " + FEN_Thing.return_FEN(board, "b"));
                        Engine.executeCommand("go depth 10");
                        
                        Thread.sleep(100);
                        
                        testchess.move("b", engine_move.split(" ")[1].split("")[0], engine_move.split(" ")[1].split("")[1], engine_move.split(" ")[1].split("")[2], engine_move.split(" ")[1].split("")[3]);
                        
                        boardprint(0,0);
                        
                        bor.loadFromFen(FEN_Thing.return_FEN(board, "w"));
                        
                        if (bor.isMated())
                        {
                            System.out.print("Checkmate Black Wins");
                            e.stop_processs();
                            break;
                        }
                        
                        if (bor.isStaleMate())
                        {
                            System.out.print("Stalemate it's a draw");
                            e.stop_processs();
                            break;
                        }
                        
                        
                        System.out.println(" if you would like to quite enter q ");
                        
                        end = reader.next().charAt(0);
                        
                        if(end == 'q') {
                            e.stop_processs();
                            game = false;
                        }
                    }
                    
                    
                }
                
                else
                {
                 
                    Engine e = new Engine ();
                        e.start_process();
                        e.read();
                        
                        System.out.print("Enter engine skill level (1-20):\n");
                                
                        Engine.executeCommand(reader.next());
                        
                        while (game)
                    {
                        
                        Engine.executeCommand("position fen " + FEN_Thing.return_FEN(board, "w"));
                        Engine.executeCommand("go depth 10");
                        
                        Thread.sleep(100);
                        
                        testchess.move("w", engine_move.split(" ")[1].split("")[0], engine_move.split(" ")[1].split("")[1], engine_move.split(" ")[1].split("")[2], engine_move.split(" ")[1].split("")[3]);
                        
                        boardprint(0,0);
                        
                        bor.loadFromFen(FEN_Thing.return_FEN(board, "b"));
                        
                        if (bor.isMated())
                        {
                            System.out.print("Checkmate White Wins");
                            game = false;
                        }
                        
                        if (bor.isStaleMate())
                        {
                            System.out.print("Stalemate it's a draw");
                            break;
                        }
                        
                        
                        System.out.println("welcome to the chess tetsing lab, please enter x1 and y1 for black");
                        x1 = reader.next();
                        y1 = reader.next();
                        
                        System.out.println(" please enter x2 and y2 for black");
                        x2 = reader.next();
                        y2 = reader.next();
                        
                        
                        while (testchess.move("b", x1, y1, x2, y2) != true)
                        {
                            System.out.println("please enter valid x1, y1 cords for black");
                            x1 = reader.next();
                            y1 = reader.next();
                            
                            System.out.println("please enter valid x2, y2 cords for black");
                            x2 = reader.next();
                            y2 = reader.next();
                            boardprint(0,0);
                        }
                        
                        boardprint(0,0);
                        
                        bor.loadFromFen(FEN_Thing.return_FEN(board, "w"));
                        
                        if (bor.isMated())
                        {
                            System.out.print("Checkmate Black Wins");
                            e.stop_processs();
                            break;
                        }
                        
                        if (bor.isStaleMate())
                        {
                            System.out.print("Stalemate it's a draw");
                            e.stop_processs();
                            break;
                        }
                        
                        
                        System.out.println(" if you would like to quite enter q ");
                        
                        end = reader.next().charAt(0);
                        
                        if(end == 'q') {
                            e.stop_processs();
                            game = false;
                        }
                    }
                       
                
                }
        
            }
        }
	
}
        
