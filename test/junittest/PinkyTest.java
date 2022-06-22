package junittest;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import pac.*;
public class PinkyTest {
	ArrayList<String> mapString;
	PacMan pacman;
	Pinky pinky;
	
	@Before
	public void setUp() {
		Map map;
		Game game = new Game(new MainMenu(), Color.BLUE, "maps/pac1.txt");
		map = new Map("maps/pac1.txt", game, Color.BLUE);
		mapString = map.getMap();
		pacman = new PacMan(mapString);
		
		pinky = new Pinky(mapString, pacman);
	}
	
	@Test
	public void StartPositionCorrect(){
		int startx = pinky.getStart_x();
		int starty = pinky.getStart_y();
		Assert.assertEquals(10, startx);
		Assert.assertEquals(9, starty);
	}
	
	@Test
	public void StartIsSet() {
		int x = pinky.getX();
		int y = pinky.getY();
		int firststartx = pinky.getFirst_startX();
		int firststarty = pinky.getFirst_startY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(7*34, y);
		Assert.assertEquals(10*30, firststartx);
		Assert.assertEquals(7*34, firststarty);
		
	}
	
	@Test
	public void NewRoundWorks() {
		int x = pinky.getX();
		int y = pinky.getY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(7*34, y);
		pinky.setX(150);
		pinky.setY(150);
		x = pinky.getX();
		y = pinky.getY();
		Assert.assertEquals(150, x);
		Assert.assertEquals(150, y);
		pinky.NewRound();
		x = pinky.getX();
		y = pinky.getY();
		Assert.assertEquals(10*30, x);
		Assert.assertEquals(9*34, y);
	}
	
	@Test
	public void SetPinkysTarget() {
		pacman.setDir_Y(-1);
		int targetx = pinky.getTargetX();
		int targety = pinky.getTargetY();
		Assert.assertEquals(0, targetx);
		Assert.assertEquals(0, targety);
		pinky.SetTarget(pacman.getX(), pacman.getY(), pinky.getX(), pinky.getY());
		targetx = pinky.getTargetX();
		targety = pinky.getTargetY();
		int realTargetx = pacman.getX()-4*30;
		int realTargety = pacman.getY()-4*34;
		Assert.assertEquals(realTargetx, targetx);
		Assert.assertEquals(realTargety, targety);
		
	}
}
