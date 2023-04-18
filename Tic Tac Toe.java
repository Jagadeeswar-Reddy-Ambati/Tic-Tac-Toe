import java.util.Random;
import java.util.Scanner;

class TicTacToe{
	static char[][] board;
	public TicTacToe() {
		board = new char[3][3];
		initBoard();
	}
	static void initBoard() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}
	static void display() {
		System.out.println("-------------");
		for(int i=0; i<board.length; i++) {
			System.out.print("|");
			for(int j=0; j<board[i].length; j++) {
				System.out.print(" "+board[i][j]+" |");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	static void placeMark(int row, int col, char c) {
		board[row][col] = c;
	}
	static boolean checkColWin() {
		for(int i=0; i<3; i++) {
			if(board[0][i]!=' ' && board[0][i]==board[1][i] && board[1][i]==board[2][i]) {
				return true;
			}
		}
		return false;
	}
	static boolean checkRowWin() {
		for(int i=0; i<3; i++) {
			if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}
	static boolean checkdiaWin() {
			if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2]
					||(board[2][0]!=' ' && board[2][0]==board[1][1] && board[1][1]==board[0][2])) {
				return true;
			}
			return false;
	}
}

abstract class Player{
	String name;
	char mark;

	boolean isValidMove(int row, int col) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			if(TicTacToe.board[row][col]==' ') {
				return true;
			}
		}
		return false;
	}

	  abstract void makeMove();
}
class HumanPlayer extends Player{
	public HumanPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}
	 void makeMove() {
		 Scanner s = new Scanner(System.in);
		 int row;
		 int col;
		 do {
			 row = s.nextInt();
			 col = s.nextInt();
		 }while(!isValidMove(row, col));
		 TicTacToe.placeMark(row, col, mark);
	}
}

class AiPlayer extends Player{
	public AiPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}
	 void makeMove() {
		 Scanner s = new Scanner(System.in);
		 int row;
		 int col;
		 do {
			 Random r = new Random();
			 row = r.nextInt(3);
			 col = r.nextInt(3);
		 }while(!isValidMove(row, col));
		 TicTacToe.placeMark(row, col, mark);
	}
}

public class LaunchGame {
	public static void main(String[] args) {
		TicTacToe a = new TicTacToe();
		Scanner s = new Scanner(System.in);
		System.out.println("enter you name");
		HumanPlayer p1 = new HumanPlayer(s.next(), 'X');
		System.out.println(p1.name+"  your mark is X");
		System.out.println("do you want to play with computer yes/no");
		Player p2;
		if("yes".equalsIgnoreCase(s.next())) {
			AiPlayer aiplayer = new AiPlayer("computer", 'c');
			p2 = aiplayer;
		}
		else {
			System.out.println("enter 2nd player name");
			HumanPlayer humanplayer = new HumanPlayer(s.next(), 'O');
			p2 = humanplayer;
			System.out.println(p2.name+"  your mark is O");
		}
		Player p = p1;
		System.out.println(p.name+" goes first");
		while(true) {
			System.out.println(p.name+" turn");
		p.makeMove();
		TicTacToe.display();
		if(TicTacToe.checkColWin() || TicTacToe.checkdiaWin() || TicTacToe.checkRowWin()) {
			System.out.println(p.name+"has Won the game");
			break;
		}
		else {
			if(p==p1) {
				p = p2;
			}
			else {
				p = p1;
			}
		}
		}
	}
}
