package junittest;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import pac.*;


public class MapTest {
	Map map;
	
	@Before
	public void setUp() {
		Game game = new Game(new MainMenu(), Color.BLUE, "maps/pac1.txt");
		map = new Map("maps/pac1.txt", game, Color.BLUE);
		PacMan pac = new PacMan(map.getMap());
		Blinky blinky = new Blinky(map.getMap(), pac);
		map.setPacMan(pac);
		
		ArrayList<Ghosts> ghost = new ArrayList<Ghosts>();
		ghost .add(blinky);
		
		map.setGhosts(ghost);
	}
	
	@Test
	public void PointsAreNullAtFirst() {
		int point = map.getPoints();
		Assert.assertEquals(0, point);
	}
	
	@Test
	public void PointSet() {
		map.pointsset(map.getMap());
		int point = map.getPoints();
		Assert.assertEquals(201, point);
	}
	
	@Test
	public void IsColide() {
		boolean colide = map.isCollided();
		Assert.assertFalse(colide);
		map.getGhosts().get(0).setX(0);
		map.getGhosts().get(0).setY(0);
		map.getPacman().setX(0);
		map.getPacman().setY(0);
		colide = map.isCollided();
		Assert.assertTrue(colide);
		
	}
	
	@Test
	public void NewRound() {
		map.getGhosts().get(0).setX(0);
		map.getGhosts().get(0).setY(0);
		map.getPacman().setX(0);
		map.getPacman().setY(0);
		Assert.assertEquals(0, map.getGhosts().get(0).getX());
		Assert.assertEquals(0, map.getGhosts().get(0).getY());
		Assert.assertEquals(0, map.getPacman().getX());
		Assert.assertEquals(0, map.getPacman().getY());
		map.getGhosts().get(0).NewRound();;
		map.getPacman().newRoud();
		Assert.assertEquals(10*30, map.getGhosts().get(0).getX());
		Assert.assertEquals(7*34, map.getGhosts().get(0).getY());
		Assert.assertEquals(10*30, map.getPacman().getX());
		Assert.assertEquals(15*34, map.getPacman().getY());
	}
	

}
