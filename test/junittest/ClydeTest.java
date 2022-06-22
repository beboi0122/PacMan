package junittest;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import pac.*;

public class ClydeTest {
	ArrayList<String> mapString;
	PacMan pacman;
	Clyde clyde;
	
	@Before
	public void setUp() {
		Map map;
		Game game = new Game(new MainMenu(), Color.BLUE, "maps/pac1.txt");
		map = new Map("maps/pac1.txt", game, Color.BLUE);
		mapString = map.getMap();
		pacman = new PacMan(mapString);
		
		clyde = new Clyde(mapString, pacman);
	}
	
	@Test
	public void StartPositionCorrect(){
		int startx = clyde.getStart_x();
		int starty = clyde.getStart_y();
		Assert.assertEquals(11, startx);
		Assert.assertEquals(9, starty);
	}
	
	@Test
	public void StartIsSet() {
		int x = clyde.getX();
		int y = clyde.getY();
		int firststartx = clyde.getFirst_startX();
		int firststarty = clyde.getFirst_startY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(7*34, y);
		Assert.assertEquals(10*30, firststartx);
		Assert.assertEquals(7*34, firststarty);
		
	}
	
	@Test
	public void NewRoundWorks() {
		int x = clyde.getX();
		int y = clyde.getY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(7*34, y);
		clyde.setX(150);
		clyde.setY(150);
		x = clyde.getX();
		y = clyde.getY();
		Assert.assertEquals(150, x);
		Assert.assertEquals(150, y);
		clyde.NewRound();
		x = clyde.getX();
		y = clyde.getY();
		Assert.assertEquals(11*30, x);
		Assert.assertEquals(9*34, y);
	}
	
	@Test
	public void SetBlikyesTarget1() {
		int targetx = clyde.getTargetX();
		int targety = clyde.getTargetY();
		Assert.assertEquals(0, targetx);
		Assert.assertEquals(0, targety);
		clyde.SetTarget(pacman.getX(), pacman.getY(), 0, 0);
		targetx = clyde.getTargetX();
		targety = clyde.getTargetY();
		int realTargetx = pacman.getX();
		int realTargety = pacman.getY();
		Assert.assertEquals(realTargetx, targetx);
		Assert.assertEquals(realTargety, targety);
		
	}
	
	@Test
	public void SetBlikyesTarget2() {
		int targetx = clyde.getTargetX();
		int targety = clyde.getTargetY();
		Assert.assertEquals(0, targetx);
		Assert.assertEquals(0, targety);
		clyde.SetTarget(pacman.getX(), pacman.getY(), clyde.getX(), clyde.getY());
		targetx = clyde.getTargetX();
		targety = clyde.getTargetY();
		int realTargetx = 0;
		int realTargety = 21*34;
		Assert.assertEquals(realTargetx, targetx);
		Assert.assertEquals(realTargety, targety);
		
	}
}
