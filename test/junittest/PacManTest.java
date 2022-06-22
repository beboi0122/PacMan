package junittest;


import java.awt.Color;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import pac.*;



public class PacManTest {
	PacMan pacman;
	ArrayList<String> mapString;
	
	
	@Before
	public void setUp() {
		Map map;
		Game game = new Game(new MainMenu(), Color.BLUE, "maps/pac1.txt");
		map = new Map("maps/pac1.txt", game, Color.BLUE);
		mapString = map.getMap();
		pacman = new PacMan(mapString);
	}
	
	@Test
	public void MapIsSet() {
		Assert.assertArrayEquals(mapString.toArray(), pacman.getMap().toArray());
	}
	
	@Test
	public void LifeIsSet() {
		int life = pacman.getLife();
		Assert.assertEquals(3, life);
	}
	
	@Test
	public void StartPositionCorrect(){
		int startx = pacman.getStart_x();
		int starty = pacman.getStart_y();
		Assert.assertEquals(10, startx);
		Assert.assertEquals(15, starty);
	}
	
	@Test
	public void StartIsSet() {
		int x = pacman.getX();
		int y = pacman.getY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(15*34, y);
		
	}
	
	@Test
	public void NewRoundWorks() {
		int x = pacman.getX();
		int y = pacman.getY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(15*34, y);
		pacman.setX(150);
		pacman.setY(150);
		x = pacman.getX();
		y = pacman.getY();
		Assert.assertEquals(150, x);
		Assert.assertEquals(150, y);
		pacman.newRoud();
		x = pacman.getX();
		y = pacman.getY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(15*34, y);
	}
}
