package junittest;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import pac.*;

public class BlinkyTest {
	ArrayList<String> mapString;
	PacMan pacman;
	Blinky blinky;

	
	@Before
	public void setUp() {
		Map map;
		Game game = new Game(new MainMenu(), Color.BLUE, "maps/pac1.txt");
		map = new Map("maps/pac1.txt", game, Color.BLUE);
		mapString = map.getMap();
		pacman = new PacMan(mapString);
		
		blinky = new Blinky(mapString, pacman);
	}
	
	@Test
	public void StartPositionCorrect(){
		int startx = blinky.getStart_x();
		int starty = blinky.getStart_y();
		Assert.assertEquals(10, startx);
		Assert.assertEquals(7, starty);
	}
	
	@Test
	public void StartIsSet() {
		int x = blinky.getX();
		int y = blinky.getY();
		int firststartx = blinky.getFirst_startX();
		int firststarty = blinky.getFirst_startY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(7*34, y);
		Assert.assertEquals(10*30, firststartx);
		Assert.assertEquals(7*34, firststarty);
		
	}
	
	@Test
	public void NewRoundWorks() {
		int x = blinky.getX();
		int y = blinky.getY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(7*34, y);
		blinky.setX(150);
		blinky.setY(150);
		x = blinky.getX();
		y = blinky.getY();
		Assert.assertEquals(150, x);
		Assert.assertEquals(150, y);
		blinky.NewRound();
		x = blinky.getX();
		y = blinky.getY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(7*34, y);
	}
	
	@Test
	public void SetBlikyesTarget() {
		int targetx = blinky.getTargetX();
		int targety = blinky.getTargetY();
		Assert.assertEquals(0, targetx);
		Assert.assertEquals(0, targety);
		blinky.SetTarget(pacman.getX(), pacman.getY(), blinky.getX(), blinky.getY());
		targetx = blinky.getTargetX();
		targety = blinky.getTargetY();
		int realTargetx = pacman.getX();
		int realTargety = pacman.getY();
		Assert.assertEquals(realTargetx, targetx);
		Assert.assertEquals(realTargety, targety);
		
	}
	
}
