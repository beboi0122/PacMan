package junittest;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import pac.*;

public class GhostsSearchPathTests {
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
	public void SearchPathWorksLeft() {
		int dirx = blinky.getDir_x();
		int diry = blinky.getDir_Y();
		int dirxbef = blinky.getDir_x_befor();
		int dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(0, dirx);
		Assert.assertEquals(0, diry);
		Assert.assertEquals(0, dirxbef);
		Assert.assertEquals(0, dirybef);
		blinky.searchPath(blinky.getTargetX(), blinky.getTargetY(), blinky.getX(), blinky.getY());
		dirx = blinky.getDir_x();
		diry = blinky.getDir_Y();
		dirxbef = blinky.getDir_x_befor();
		dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(-1, dirx);
		Assert.assertEquals(0, diry);
		Assert.assertEquals(-1, dirxbef);
		Assert.assertEquals(0, dirybef);
	}
	@Test
	public void SearchPathWorksRight() {
		int dirx = blinky.getDir_x();
		int diry = blinky.getDir_Y();
		int dirxbef = blinky.getDir_x_befor();
		int dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(0, dirx);
		Assert.assertEquals(0, diry);
		Assert.assertEquals(0, dirxbef);
		Assert.assertEquals(0, dirybef);
		blinky.setDir_x_befor(0);
		blinky.setDir_y_befor(0);
		blinky.setTargetX(11*30);
		blinky.setTargetY(7*34);
		blinky.searchPath(blinky.getTargetX(), blinky.getTargetY(), blinky.getX(), blinky.getY());
		dirx = blinky.getDir_x();
		diry = blinky.getDir_Y();
		dirxbef = blinky.getDir_x_befor();
		dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(1, dirx);
		Assert.assertEquals(0, diry);
		Assert.assertEquals(1, dirxbef);
		Assert.assertEquals(0, dirybef);
	}
	@Test
	public void SearchPathWorksUp() {
		int dirx = blinky.getDir_x();
		int diry = blinky.getDir_Y();
		int dirxbef = blinky.getDir_x_befor();
		int dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(0, dirx);
		Assert.assertEquals(0, diry);
		Assert.assertEquals(0, dirxbef);
		Assert.assertEquals(0, dirybef);
		blinky.setDir_x_befor(0);
		blinky.setDir_y_befor(0);
		blinky.searchPath(11*30, 7*34, 11*30, 8*34);
		dirx = blinky.getDir_x();
		diry = blinky.getDir_Y();
		dirxbef = blinky.getDir_x_befor();
		dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(0, dirx);
		Assert.assertEquals(-1, diry);
		Assert.assertEquals(0, dirxbef);
		Assert.assertEquals(-1, dirybef);
	}
	@Test
	public void SearchPathWorksDown() {
		int dirx = blinky.getDir_x();
		int diry = blinky.getDir_Y();
		int dirxbef = blinky.getDir_x_befor();
		int dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(0, dirx);
		Assert.assertEquals(0, diry);
		Assert.assertEquals(0, dirxbef);
		Assert.assertEquals(0, dirybef);
		blinky.setDir_x_befor(0);
		blinky.setDir_y_befor(0);
		blinky.searchPath(11*30, 7*34, 11*30, 6*34);
		dirx = blinky.getDir_x();
		diry = blinky.getDir_Y();
		dirxbef = blinky.getDir_x_befor();
		dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(0, dirx);
		Assert.assertEquals(1, diry);
		Assert.assertEquals(0, dirxbef);
		Assert.assertEquals(1, dirybef);
	}
	@Test
	public void SearchPathWorksSpec1() {
		int dirx = blinky.getDir_x();
		int diry = blinky.getDir_Y();
		int dirxbef = blinky.getDir_x_befor();
		int dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(0, dirx);
		Assert.assertEquals(0, diry);
		Assert.assertEquals(0, dirxbef);
		Assert.assertEquals(0, dirybef);
		blinky.setDir_x_befor(0);
		blinky.setDir_y_befor(0);
		blinky.searchPath(10*30, 6*34, 10*30, 7*34);
		dirx = blinky.getDir_x();
		diry = blinky.getDir_Y();
		dirxbef = blinky.getDir_x_befor();
		dirybef = blinky.getDir_y_befor();
		Assert.assertNotEquals(-1, diry);
		Assert.assertNotEquals(-1, dirybef);
	}
	@Test
	public void SearchPathWorksSpec2() {
		int dirx = blinky.getDir_x();
		int diry = blinky.getDir_Y();
		int dirxbef = blinky.getDir_x_befor();
		int dirybef = blinky.getDir_y_befor();
		Assert.assertEquals(0, dirx);
		Assert.assertEquals(0, diry);
		Assert.assertEquals(0, dirxbef);
		Assert.assertEquals(0, dirybef);
		blinky.setDir_x_befor(0);
		blinky.setDir_y_befor(0);
		blinky.searchPath(10*30, 14*34, 10*30, 15*34);
		dirx = blinky.getDir_x();
		diry = blinky.getDir_Y();
		dirxbef = blinky.getDir_x_befor();
		dirybef = blinky.getDir_y_befor();
		Assert.assertNotEquals(-1, diry);
		Assert.assertNotEquals(-1, dirybef);
	}
	
}
