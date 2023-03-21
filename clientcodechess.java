import java.util.Scanner;
public class clientcodechess {

	public static void main(String[] args) {
		
		
		Scanner reader = new Scanner(System.in);
		
		String x1;
		String y1;
		String x2;
		String y2;
		boolean game = true;
		char end;
		String b = "b";
		String w = "w";
			
		
		
		
		// TODO Auto-generated method stub
		
		
		testchess.boardprint(3,3);
		
		while (game == true) {
			System.out.println("welcome to the chess tetsing lab, please enter x1 and y1 for white");
			x1 = reader.next();
			y1 = reader.next();
			
			System.out.println(" please enter x2 and y2 for white ");
			x2 = reader.next();
			y2 = reader.next();
			
			testchess.pos_translate(x2);
			testchess.pos_translate(y2);
			
			
			testchess.move("w", x1, y1, x2, y2);
			
			
			System.out.println("welcome to the chess tetsing lab, please enter x1 and y1 for black");
			x1 = reader.next();
			y1 = reader.next();
			
			System.out.println(" please enter x2 and y2 for black ");
			x2 = reader.next();
			y2 = reader.next();
			
			testchess.pos_translate(x2);
			testchess.pos_translate(y2);
			
			
			testchess.move("b", x1, y1, x2, y2);
			
			System.out.println(" if you would like to quite enter q ");
			
			end = reader.next().charAt(0);
			
			if(end == 'q') {
				break;
			}
			
			
			
		}
		
		
//		System.out.println("welcome to the chess tetsing lab, please enter x1 and y1");
//		x1 = reader.next();
//		y1 = reader.next();
//		
//		System.out.println("welcome to the chess tetsing lab, please enter x2 and y2");
//		x2 = reader.next();
//		y2 = reader.next();
//		
//		testchess.pos_translate(x2);
//		testchess.pos_translate(y2);
//		
//		
//		testchess.move("w", x1, y1, x2, y2);
		
		
		
		
		
		
		
		

	}

}
