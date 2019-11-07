package main.dagrouphardcore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import net.md_5.bungee.api.ChatColor;

public class ScorebroadTracker {

	LivesManager lives;
	DaysTracker daysTrack;
	
	public ScorebroadTracker(LivesManager l, DaysTracker d ) {
		lives = l;
		daysTrack = d;
	}
	
	
	public void setScoreBoardToPlayer(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        
        Objective obj = board.registerNewObjective("LivesObj", "life", 
        		ChatColor.DARK_RED.toString() 
        		+ ChatColor.BOLD.toString() 
        		+ "DaGroupHardcore");
        
        obj.setDisplaySlot(DisplaySlot.SIDEBAR); 
        
        if(lives.currentLives < 0) {
        	obj.getScore(" Lives: " + ChatColor.RED + "OUT!" + "   ").setScore(1); 
        }
        else if(lives.currentLives == 0) {
        	obj.getScore(" Lives: " + ChatColor.YELLOW + lives.currentLives + "   ").setScore(1); 
        }
        else {
        	obj.getScore(" Lives: " + ChatColor.GREEN + lives.currentLives + "   ").setScore(1); 
        }
        
        if(daysTrack.daysLeft == 0) {
        	obj.getScore(" Days Left: " + ChatColor.RED + "OUT!" + " ").setScore(0);
        }
        else if(daysTrack.daysLeft == 1) {
        	obj.getScore(" Days Left: " + ChatColor.YELLOW + daysTrack.daysLeft + " ").setScore(0);
        }
        else {
        	obj.getScore(" Days Left: " + ChatColor.GREEN + daysTrack.daysLeft + " ").setScore(0);
        }
        
        player.setScoreboard(board);
	}
	
	public void updateScoreBoardOfLives() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			setScoreBoardToPlayer(p);
		}
	}	
	
	public void updateScoreBoardOfDaysLeft() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			setScoreBoardToPlayer(p);
		}
	}	
	
	public void clearScoreBoard() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());			
		}
	}
	
}
