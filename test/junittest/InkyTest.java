package junittest;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import pac.*;

public class InkyTest {
	ArrayList<String> mapString;
	PacMan pacman;
	Blinky blinky;
	Inky inky;
	
	@Before
	public void setUp() {
		Map map;
		Game game = new Game(new MainMenu(), Color.BLUE, "maps/pac1.txt");
		map = new Map("maps/pac1.txt", game, Color.BLUE);
		mapString = map.getMap();
		pacman = new PacMan(mapString);
		
		blinky = new Blinky(mapString, pacman);
		inky = new Inky(mapString, pacman, blinky);
	}
	
	@Test
	public void StartPositionCorrect(){
		int startx = inky.getStart_x();
		int starty = inky.getStart_y();
		Assert.assertEquals(9, startx);
		Assert.assertEquals(9, starty);
	}
	
	@Test
	public void StartIsSet() {
		int x = inky.getX();
		int y = inky.getY();
		int firststartx = inky.getFirst_startX();
		int firststarty = inky.getFirst_startY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(7*34, y);
		Assert.assertEquals(10*30, firststartx);
		Assert.assertEquals(7*34, firststarty);
		
	}
	
	@Test
	public void NewRoundWorks() {
		int x = inky.getX();
		int y = inky.getY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(7*34, y);
		inky.setX(150);
		inky.setY(150);
		x = inky.getX();
		y = inky.getY();
		Assert.assertEquals(150, x);
		Assert.assertEquals(150, y);
		inky.NewRound();
		x = inky.getX();
		y = inky.getY();
		Assert.assertEquals(9*30, x);
		Assert.assertEquals(9*34, y);
	}
	
	@Test
	public void SetBlikyesTarget() {
		int targetx = inky.getTargetX();
		int targety = inky.getTargetY();
		Assert.assertEquals(0, targetx);
		Assert.assertEquals(0, targety);
		inky.SetTarget(pacman.getX(), pacman.getY(), inky.getX(), inky.getY());
		targetx = inky.getTargetX();
		targety = inky.getTargetY();
		int realTargetx = (pacman.getX()+pacman.getDir_x()*30*2)- (blinky.getX()-(pacman.getX()+pacman.getDir_x()*30*2));
		int realTargety = (pacman.getY()+pacman.getDir_Y()*34*2)- (blinky.getY()-(pacman.getY()+pacman.getDir_Y()*34*2));
		Assert.assertEquals(realTargetx, targetx);
		Assert.assertEquals(realTargety, targety);
		
	}
}
